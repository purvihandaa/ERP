import java.util.ArrayList;
import java.util.Scanner;

class Professor extends User {
    private String name;
    private String expertise;
    private String email;
    private String currently_teaches;

    // Constructor
    public Professor(String name, String expertise, String email,String password,String currently_teaches) {
        super(name, email, password);
        this.name = name;
        this.expertise = expertise;
        this.email = email;
        this.currently_teaches=currently_teaches;
    }

    public String getCurrently_teaches() {
        return currently_teaches;
    }

    public void setCurrently_teaches(String currently_teaches) {
        this.currently_teaches = currently_teaches;
    }

    public String getName() {
        return name;
    }

    public String getExpertise() {
        return expertise;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }
    public void setEmail(String email) {
        this.email = email;
    }



    public void printProfessorDetails() {
        System.out.println("Name: " + name);
        System.out.println("Expertise: " + expertise);
        System.out.println("Email: " + email);
    }

    public void assignGrade(int rollno, int grade, ArrayList<Student> enrolledStudents) {
        System.out.println("Assigning Grades for Course: "+ this.currently_teaches);
        for (Student i : enrolledStudents) {
            if (i.getRollNo() == rollno) {
                if (i.getEnrolledCourses().contains(this.currently_teaches)) {
                    i.getGrades().put(this.currently_teaches,grade);
                    if (!i.getCompletedCourses().contains(this.currently_teaches)) {
                        i.getCompletedCourses().add(this.currently_teaches);
                    }
                    i.getEnrolledCourses().remove(this.currently_teaches);
                    System.out.println("Grade assigned successfully for " + this.currently_teaches);
                } else {
                    System.out.println("ERROR: Student is not enrolled in " + this.currently_teaches);
                }
                return;
            }
        }
        System.out.println("ERROR: Student with roll number " + rollno + " not found.");
    }

    public void manageCourses(String profname) {
        Scanner obj = new Scanner(System.in);

        for (ArrayList<Courses> courses : Courses.courseCatalog.values()) {
            for (Courses i : courses) {
                if (i.professor.equals(profname)) {
                    System.out.println("Course Code: " + i.getCode() + "\nCourse Title: " + i.title + "\nCourse Timings: " + i.getTimings() + "\nOffice Hours: " + i.getOfficeHours() + "\nPre Requisites Required: " + i.getPrerequisites() + "\nSyllabus: " + i.getSyllabus() + "\nCredits: " + i.getCredits() + "\nEnrollment Limit: " + i.getEnrollmentLimit());
                    System.out.println("Do you want to update course information? (y/n)");
                    String update = obj.nextLine();

                    if (update.equalsIgnoreCase("y")) {
                        System.out.print("Enter new course timings: ");
                        String newcoursetimings = obj.nextLine();
                        i.setTimings(newcoursetimings);

                        System.out.print("Enter new Office Hours: ");
                        String newofficehours = obj.nextLine();
                        i.setOfficeHours(newofficehours);

                        System.out.print("Enter new Credits: ");
                        int newcredits = obj.nextInt();
                        i.setCredits(newcredits);

                        System.out.print("Enter new Enrollment Limit: ");
                        int newenrollmentlimit = obj.nextInt();
                        i.setEnrollmentLimit(newenrollmentlimit);

                        System.out.print("Enter new Pre Requisites Required: ");
                        String newsyllabus = obj.nextLine();
                        i.setSyllabus(newsyllabus);

                        System.out.print("Enter new Pre Requisites Required: ");
                        String newpre = obj.nextLine();
                        i.setPrerequisite(newpre);


                        System.out.println("Course information updated.");
                    }
                    break;

                }
            }
        }


    }

    public void assignTA(int rollno,String courseCode){
        int check=0;
        for(Student i: Main.all_students){
            if(i.getRollNo()==rollno){
                ArrayList<String> completed = i.getCompletedCourses();
                for (String j:completed){
                    if (j.equals(courseCode)){
                        check=1;
                        break;

                    }
                }

                if (check==1){
                    TA ta1 = new TA(rollno, i.getName(), i.getEmail(), i.getPassword(), i.getSem(), courseCode);
                    Main.all_tas.add(ta1);
                    System.out.println("TA Assigned successfully");
                    break;
                }
                else{
                    System.out.println("Student has not completed this course. Cant assign him as TA");
                }


            }
        }
    }

    public void viewEnrolledStudents() {
        System.out.println("Viewing enrolled students for Course: "+ this.currently_teaches);
        ArrayList<Student> std= Courses.getEnrolledStudents();
        for (Student i: std){
                if(i.getEnrolledCourses().contains(this.currently_teaches)) {
                    System.out.println("Student Name: "+ i.getName());
                    System.out.println("Email: "+ i.getEmail());
                    System.out.println("Roll No. "+ i.getRollNo());
                    System.out.println("Grade: "+ i.getSgpa());

                }
        }
    }




}
