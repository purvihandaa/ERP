import java.util.ArrayList;
import java.util.HashMap;

public class Courses {
    static HashMap<Integer, ArrayList<Courses>> courseCatalog = new HashMap<>();

    private String code;
    public String title;
    public String professor;
    private int credits;
    private String prerequisite;
    private String timings;
    private String syllabus;
    private int enrollmentLimit;
    private String officeHours;
    public static ArrayList<Student> enrolledStudents;

    // Constructor
    public Courses(String code, String title, String professor, int credits, String prerequisite, String timings,String syllabus, int enrollmentLimit, String officeHours) {
        this.code = code;
        this.title = title;
        this.professor = professor;
        this.credits = credits;
        this.prerequisite = prerequisite;
        this.timings = timings;
        this.syllabus = syllabus;
        this.enrollmentLimit = enrollmentLimit;
        this.officeHours = officeHours;
        this.enrolledStudents = new ArrayList<>();
    }

    public int getCredits(){
        return credits;
    }
    public String getPrerequisites(){
        return prerequisite;
    }
    public String getCode(){
        return code ;
    }
    public String getTimings(){
        return timings;
    }
    public String getSyllabus(){
        return syllabus;
    }
    public int getEnrollmentLimit(){
        return enrollmentLimit;
    }
    public String getOfficeHours(){
        return officeHours;
    }
    public void setCredits(int credits){
        this.credits=credits;
    }
    public void setPrerequisite(String prerequisite){
        this.prerequisite=prerequisite;
    }
    public void setTimings(String timings){
        this.timings=timings;
    }
    public void setSyllabus(String syllabus){
        this.syllabus=syllabus;
    }
    public void setEnrollmentLimit(int enrollmentLimit){
        this.enrollmentLimit=enrollmentLimit;
    }
    public void setOfficeHours(String officeHours){
        this.officeHours=officeHours;
    }
    public void setCode(String code){
        this.code=code ;
    }

    public void printCourses(){
        System.out.println("Course Code: " + code);
        System.out.println("Title: " + title);
        System.out.println("Professor: " + professor);
        System.out.println("Credits: " + credits);
        System.out.println("Prerequisites: " + prerequisite);
        System.out.println("Timings: " + timings);
        System.out.println();

    }

    public static void enrollStudent(Student stu) {
        enrolledStudents.add(stu);
    }

    public static void removeStudent(Student stu) {
        enrolledStudents.remove(stu);
    }

    public static ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public static void initializeCourses() {
        // Create courses for semester 1
        ArrayList<Courses> sem1Courses = new ArrayList<>();
        sem1Courses.add(new Courses("CSE101", "Introduction to Programming", "Prof. Sharma", 4, "None", "Monday 9:00-11:00",
                "Introduction to programming concepts, basics of algorithms.", 2, "Monday 11:00-12:00"));
        sem1Courses.add(new Courses("MAT101", "Calculus", "Prof. Verma", 5, "None", "Tuesday 10:00-12:00",
                "Fundamentals of calculus, differentiation and integration.", 30, "Tuesday 12:00-1:00"));
        sem1Courses.add(new Courses("PHY101", "Physics I", "Prof. Khurana", 5, "None", "Wednesday 1:00-3:00",
                "Basic principles of physics, mechanics and thermodynamics.", 30, "Wednesday 3:00-4:00"));
        sem1Courses.add(new Courses("ENG101", "English Communication", "Prof. Singh", 6, "None", "Thursday 11:00-12:30",
                "Development of English language skills for communication.", 30, "Thursday 12:30-1:30"));
        sem1Courses.add(new Courses("CHE101", "Chemistry", "Prof. Rao", 3, "None", "Friday 2:00-4:00",
                "Introduction to chemical principles, periodic table and bonding.", 30, "Friday 4:00-5:00"));

        // Create courses for semester 2
        ArrayList<Courses> sem2Courses = new ArrayList<>();
        sem2Courses.add(new Courses("CSE102", "Data Structures", "Prof. Gupta", 4, "CSE101", "Monday 10:00-12:00",
                "Study of data structures like arrays, lists, and trees.", 30, "Monday 12:00-1:00"));
        sem2Courses.add(new Courses("MAT102", "Linear Algebra", "Prof. Iyer", 3, "None", "Tuesday 9:00-11:00",
                "Fundamentals of vector spaces, matrices, and linear transformations.", 30, "Tuesday 11:00-12:00"));
        sem2Courses.add(new Courses("PHY102", "Physics II", "Prof. Reddy", 3, "PHY101", "Wednesday 1:00-3:00",
                "Electromagnetism, wave optics, and modern physics.", 30, "Wednesday 3:00-4:00"));
        sem2Courses.add(new Courses("HIS102", "World History", "Prof. Mehta", 2, "None", "Thursday 10:00-11:30",
                "Overview of global historical events and trends.", 30, "Thursday 11:30-12:30"));
        sem2Courses.add(new Courses("BIO101", "Biology", "Prof. Arora", 3, "None", "Friday 2:00-4:00",
                "Introduction to biology, cell structure, and genetics.", 30, "Friday 4:00-5:00"));

        // Create courses for semester 3
        ArrayList<Courses> sem3Courses = new ArrayList<>();
        sem3Courses.add(new Courses("CSE201", "Algorithms","Prof. Mehra", 4, "CSE102", "Monday 2:00-4:00",
                "Study of algorithm design and analysis techniques.", 30, "Monday 4:00-5:00"));
        sem3Courses.add(new Courses("CSE221", "Database Systems", "Prof. Nanda", 4, "None", "Tuesday 10:00-12:00",
                "Introduction to database design, SQL, and transactions.", 30, "Tuesday 12:00-1:00"));
        sem3Courses.add(new Courses("CSE231", "Operating Systems", "Prof. Mishra", 4, "CSE201", "Wednesday 9:00-11:00",
                "Principles of operating systems including processes and memory management.", 30, "Wednesday 11:00-12:00"));
        sem3Courses.add(new Courses("MAT201", "Probability & Statistics", "Prof. Sinha", 4, "MAT102", "Thursday 2:00-4:00",
                "Fundamentals of probability theory and statistical inference.", 30, "Thursday 4:00-5:00"));
        sem3Courses.add(new Courses("ECO201", "Microeconomics", "Prof. Raj", 4, "None", "Friday 9:00-11:00",
                "Principles of microeconomics including supply and demand, market structures.", 30, "Friday 11:00-12:00"));

        // Add courses to the catalog based on semester
        courseCatalog.put(1, sem1Courses);
        courseCatalog.put(2, sem2Courses);
        courseCatalog.put(3, sem3Courses);
    }


    public static ArrayList<Courses> getCoursesBySemester(int semester) {
        return courseCatalog.getOrDefault(semester, new ArrayList<>());
    }

    }

