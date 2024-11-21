import java.time.LocalDate;
import java.util.*;

class Student extends User {

    //attributes
    private LocalDate dropDeadline = LocalDate.of(2024, 10, 25);
    private int rollNo;
    private int sem;
    private float cgpa;
    private float sgpa;
    private int totalCredits;
    public ArrayList<String> enrolledCourses;
    private ArrayList<String> completedCourses;
    private HashMap<String, Integer> grades;


    //constructor
    public Student(int rollNo, String name, String email,String password, int sem) {
        super(name, email,password);
        this.rollNo = rollNo;
        this.sem = sem;
        this.cgpa = 0;
        this.sgpa = 0;
        this.totalCredits = 0;
        this.enrolledCourses = new ArrayList<>();
        this.completedCourses = new ArrayList<>();
        this.grades = new HashMap<>();
    }

    public int getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getSem() {
        return sem;
    }
    public void setSem(int sem) {
        this.sem = sem;
    }
    public float getCgpa() {
        return cgpa;
    }
    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }
    public float getSgpa() {
        return sgpa;
    }
    public void setSgpa(float sgpa) {
        this.sgpa = sgpa;
    }

    public HashMap<String,Integer> getGrades() {
        return grades;
    }

    public ArrayList<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    ArrayList<String> getCompletedCourses(){
        return completedCourses;
    }

    public void addEnrolledCourse(String courseCode) {
        this.enrolledCourses.add(courseCode);
    }

    public static void viewCourses(int sem) {
        System.out.println("Displaying available courses for Semester " + sem + ":");
        ArrayList<Courses> courses = Courses.getCoursesBySemester(sem);
        for (Courses i : courses) {
            i.printCourses();
        }
    }

    public void registerCourses(String courseCode,int sem) {
        ArrayList<Courses> availableCourses = Courses.getCoursesBySemester(sem);
        System.out.println("Registering for courses");

        int found=0;
        int a=0;
        String pre = "";
        int limit=0;
        for (Courses i: availableCourses){
            if (i.getCode().equals(courseCode)) {
                a = i.getCredits();
                pre = i.getPrerequisites();
                limit = i.getEnrollmentLimit();
                found = 1;
                break;
            }
        }

        if (found==0){
                System.out.println("Course not available for your current semester");
                return;
            }


        if ((totalCredits + a) > 20) {
            System.out.println("ERROR: Maximum credits reached! Can not Register!");
            return;
        }

        if (!pre.equals("None") && !completedCourses.contains(pre)) {
            System.out.println("ERROR: Prerequisite course not done. Can not Register! ");
            return;
        }

        try {
            if (limit <= 0) {
                throw new CourseFullException("Course limit exceeded. Can not Register!");
            }
            addEnrolledCourse(courseCode);
            totalCredits += a;
            limit--;

            for (Courses i : availableCourses) {
                if (i.getCode().equals(courseCode)) {
                    i.setEnrollmentLimit(limit);
                    break;
                }
            }

            Courses.enrollStudent(this);
            System.out.println("Successfully Registered: " + courseCode);
        } catch (CourseFullException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }


    public void viewSchedule() {
        System.out.println("Viewing schedule");

        for (String i : enrolledCourses) {
            ArrayList<Courses> availableCourses = Courses.getCoursesBySemester(sem);
            for (Courses j : availableCourses) {
                if (j.getCode().equals(i)) {
                    System.out.println("Course Title: " + j.title);
                    System.out.println("Professor: " + j.professor);
                    System.out.println("Timings: " + j.getTimings());
                    System.out.println();
                }
            }
        }
    }

    public void trackProgress(int rollNo,ArrayList<Student> enrolledStudents) {
        System.out.println("Tracking academic progress of roll no: "+rollNo);

        HashMap<String, Integer> gradeslist = null;
        for (Student i : enrolledStudents) {
            if (i.getRollNo() == rollNo) {
                gradeslist = i.getGrades();
                break;
            }
        }

        if (gradeslist==null||gradeslist.isEmpty()) {
            System.out.println("No grades available for student with roll number: " + rollNo);
            return;
        }

        for (Map.Entry<String, Integer> entry : gradeslist.entrySet()) {
            System.out.println("Course: " + entry.getKey() + " - Grade: " + entry.getValue());
        }

        float sum=0;
        float size= gradeslist.size();
        for(int i: grades.values() ){
            sum+=i;
        }

        float cumSum = 0;
        float completedSize= completedCourses.size();

        for (String i : completedCourses) {
            if (gradeslist.containsKey(i)) {
                int grade = gradeslist.get(i);
                cumSum += grade;
            }
        }
        float sgpa=sum/size;
        float cgpa=cumSum/completedSize;

        System.out.println("SGPA is: "+ sgpa);
        System.out.println("CGPA is: "+ cgpa);

    }

    public void dropCourse(String courseCode,int sem) {
        System.out.println("Dropping course");
        int credits=0;

        ArrayList<Courses> availableCourses = Courses.getCoursesBySemester(sem);

        try {
            if (LocalDate.now().isAfter(dropDeadline)) {
                throw new DropDeadlinePassedException("Cannot drop course " + courseCode + ": deadline has passed.");
            }

            // dropping the course
            if (enrolledCourses.contains(courseCode)) {
                enrolledCourses.remove(courseCode);



                for (Courses i : availableCourses) {
                    if (i.getCode().equals(courseCode)) {
                        int limit=i.getEnrollmentLimit();
                        limit++;
                        i.setEnrollmentLimit(limit);
                        credits=i.getCredits();
                        break;
                    }
                }
                totalCredits -= credits;

                Courses.removeStudent(this);
                System.out.println("Successfully removed course: " + courseCode);

            } else {
                System.out.println("ERROR: This course is not registered");
            }

        } catch (DropDeadlinePassedException e) {
            System.out.println("ERROR: " + e.getMessage());
        }


    }

    public void submitComplaint(String detail) {
        Complaint complaint1 = new Complaint(detail);
        Main.all_complaints.add(complaint1);
        System.out.println("Complaint submitted successfully.");
    }


    public void submitFeedback(String code,int rating,String comment) {
        Feedback feedback1 = new Feedback(code,rating,comment);
        Main.all_feedbacks.add(feedback1);
        System.out.println("Feedback submitted successfully.");
    }


}

