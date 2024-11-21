import java.util.*;

public class Main {

    public static ArrayList<Complaint> all_complaints = new ArrayList<>();
    public static ArrayList<Feedback> all_feedbacks = new ArrayList<>();
    public static ArrayList<Student> all_students = new ArrayList<>();
    public static ArrayList<Professor> all_professors = new ArrayList<>();
    public static ArrayList<TA> all_tas = new ArrayList<>();
    public static ArrayList<Admin> all_admins = new ArrayList<>();

    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);

        Courses.initializeCourses();

        Professor prof1 = new Professor("Dr. Sharma", "Programming", "sharma.com", "pp1","CSE101");
        Professor prof2 = new Professor("Dr. Verma", "Mathematics", "verma.com", "pp2","MAT101");
        Professor prof3 = new Professor("Dr. Gupta", "Data Structures", "gupta.com", "pp3","CSE102");
        Professor prof4 = new Professor("Shad Akhtar", "Advance Programming", "shad.com", "pp4","No course assigned");
        all_professors.add(prof1);
        all_professors.add(prof2);
        all_professors.add(prof3);
        all_professors.add(prof4);

        Student student1 = new Student(1, "Purvi", "purvi@gmail.com", "sp1", 1);
        Student student2 = new Student(2, "Reva", "reva@gmail.com", "sp2", 1);
        Student student3 = new Student(3, "Bhavya", "bhavya@gmail.com", "sp3", 1);
        Student student4 = new Student(5, "Sid", "sid@gmail.com", "tp1", 4);
        all_students.add(student1);
        all_students.add(student2);
        all_students.add(student3);
        all_students.add(student4);

        Admin admin1 = new Admin("Admin", "iiitd@gmail.com", "admin123");
        all_admins.add(admin1);

        Complaint complaint1 = new Complaint("Class timings clash in cse101 and mth3");
        Complaint complaint2 = new Complaint("Class timings clash in eng and chem");
        all_complaints.add(complaint1);
        all_complaints.add(complaint2);

        Feedback feedback1 = new Feedback("CSE101",8,"Tough but interesting course");
        all_feedbacks.add(feedback1);

        TA ta1 = new TA(5, "Sid", "sid@gmail.com", "tp1", 4, "CSE101");
        all_tas.add(ta1);

        // Main menu
        System.out.println("Welcome to the University Course Registration System:");
        while (true) {
            System.out.println("Login as: \n1) Student or TA \n2) Professor \n3) Admin \n4) Exit");
            int choice = obj.nextInt();
            obj.nextLine();  // Consume newline

            switch (choice) {
                case 1: // Student login
                    System.out.print("Enter your email: ");
                    String studentEmail = obj.nextLine();
                    System.out.print("Enter your password: ");
                    String studentPassword = obj.nextLine();

                    // Check if the student exists
                    Student loggedInStudent = null;
                    for (Student i : all_students) {
                        if (i.getEmail().equals(studentEmail) && i.getPassword().equals(studentPassword)) {
                            loggedInStudent = i;
                            break;
                        }
                    }
                    try{
                        if (loggedInStudent != null) {
                            System.out.println("Logged in as Student: " + loggedInStudent.getName());
                            studentMenu(loggedInStudent, obj, all_tas);
                        }
                        else {
                            throw new InvalidLoginException("Wrong email or password.");
                        }
                    } catch (InvalidLoginException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                    break;

                case 2: // Professor login
                    System.out.print("Enter your email: ");
                    String professorEmail = obj.nextLine();
                    System.out.print("Enter your password: ");
                    String professorPassword = obj.nextLine();

                    Professor loggedInProfessor = null;
                    for (Professor professor : all_professors) {
                        if (professor.getEmail().equals(professorEmail) && professor.getPassword().equals(professorPassword)) {
                            loggedInProfessor = professor;
                            break;
                        }
                    }
                    try {
                        if (loggedInProfessor != null) {
                            System.out.println("Logged in as Professor: " + loggedInProfessor.getName());
                            professorFunctions(loggedInProfessor, obj);
                        }
                        else {
                            throw new InvalidLoginException("Wrong email or password.");
                        }
                    } catch (InvalidLoginException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }

                    break;

                case 3: // Admin login
                    System.out.print("Enter your email: ");
                    String adminEmail = obj.nextLine();
                    System.out.print("Enter your password: ");
                    String adminPassword = obj.nextLine();

                    Admin loggedInAdmin = null;
                    for (Admin admin : all_admins) {
                        if (admin.getEmail().equals(adminEmail) && admin.getPassword().equals(adminPassword)) {
                            loggedInAdmin = admin;
                            break;
                        }
                    }
                    try {
                        if (loggedInAdmin != null) {
                            System.out.println("Logged in as Admin: " + loggedInAdmin.getName());
                            adminMenu(loggedInAdmin, obj, all_complaints,all_students,all_professors);
                        } else {
                            throw new InvalidLoginException("Wrong email or password.");
                        }
                    } catch (InvalidLoginException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                    break;

                case 4: // Exit
                    System.out.println("Exiting the ERP system. Goodbye!");
                    System.out.println("-------------------------------------------");
                    obj.close();
                    return;

                default:
                    System.out.println("ERROR: Invalid choice. Please try again.");
            }
        }
    }

    // Student menu
    private static void studentMenu(Student student, Scanner obj, ArrayList<TA> all_tas) {
        while (true) {
            System.out.println("Student Menu:");
            System.out.println("1) View available courses");
            System.out.println("2) Register for a course");
            System.out.println("3) View schedule");
            System.out.println("4) Track academic progress");
            System.out.println("5) Drop courses");
            System.out.println("6) Submit complaints");
            System.out.println("7) Submit feedback");
            System.out.println("8) Log in as TA");
            System.out.println("9) Log out");
            int choice = obj.nextInt();
            obj.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // View available courses
                    Student.viewCourses(student.getSem());
                    break;
                case 2:
                    // Register for a course
                    System.out.print("Enter the course code to register: ");
                    String courseCode = obj.nextLine();
                    student.registerCourses(courseCode, student.getSem());
                    break;
                case 3:
                    // View schedule
                    System.out.print("Viewing Schedule for Registered Courses: ");
                    student.viewSchedule();
                    break;
                case 4:
                    // Track academic progress
                    student.trackProgress(student.getRollNo(),Courses.getEnrolledStudents());
                    break;
                case 5:
                    // Drop courses
                    System.out.print("Enter the course code to drop: ");
                    String dropCourseCode = obj.nextLine();
                    student.dropCourse(dropCourseCode, student.getSem());
                    break;
                case 6:
                    // Submit complaints
                    System.out.print("Enter complaint details: ");
                    String complaintDetail = obj.nextLine();

                    student.submitComplaint(complaintDetail);
                    break;
                case 7:
                    // Submit feedback
                    System.out.print("Enter Course Code: ");
                    String code = obj.nextLine();
                    System.out.print("Rate the subject: ");
                    int rating = obj.nextInt();
                    obj.nextLine();

                    System.out.print("Enter your feedback: ");
                    String feedback = obj.nextLine();
                    student.submitFeedback(code,rating,feedback);
                    break;
                case 8:
                    // Check if the student is a TA
                    int c=0;
                    for (TA i : all_tas) {
                        if (i.getEmail().equals(student.getEmail()) && i.getPassword().equals(student.getPassword())) {
                            System.out.println("Logged in as TA: " + student.getName());
                            taMenu(i, all_tas, obj);
                            c=1;
                            break;
                        }
                    }
                    if (c==0){
                        System.out.println("You are Not a TA. ");
                    }
                    break;
                case 9:
                    System.out.println("Logging out");
                    System.out.println("-------------------------------------------");
                    return;

                default:
                    System.out.println("ERROR: Invalid choice. Please try again.");
            }
        }
    }

    // Professor menu
    private static void professorFunctions(Professor professor, Scanner obj) {
        while (true) {
            System.out.println("Professor Menu:");
            System.out.println("1. Manage Courses");
            System.out.println("2. View Enrolled Students");
            System.out.println("3. Assign Grade");
            System.out.println("4. Assign TA");
            System.out.println("5. View Feedback");
            System.out.println("6. Logout");
            int choice = obj.nextInt();
            obj.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    professor.manageCourses(professor.getName());
                    break;
                case 2:
                    professor.viewEnrolledStudents();
                    break;
                case 3:
                    System.out.print("Enter Roll No of the student: ");
                    int rollno = obj.nextInt();
                    obj.nextLine();
                    System.out.print("Enter grade (1-10): ");
                    int grade = obj.nextInt();
                    obj.nextLine();

                    professor.assignGrade(rollno,grade,Courses.getEnrolledStudents());
                    break;
                case 4:
                    System.out.print("Enter Roll No of the student: ");
                    int roll = obj.nextInt();
                    obj.nextLine();
                    System.out.print("Enter course code for which you want to assign him as the TA ");
                    String code = obj.nextLine();

                    professor.assignTA(roll,code);
                    break;

                case 5:
                    System.out.println("All feedbacks for Course code: " + professor.getCurrently_teaches());
                    for(Feedback i: all_feedbacks){
                        if(i.getCourseCode().equals(professor.getCurrently_teaches())){
                            i.printFeedback();
                        }
                    }
                    break;
                case 6:
                    System.out.println("Logging out");
                    System.out.println("-------------------------------------------");
                    return;

                default:
                    System.out.println("ERROR: Invalid choice. Please try again.");
            }
        }
    }

    // TA menu
    private static void taMenu(TA ta, ArrayList<TA> all_tas, Scanner obj) {
        while (true) {
            System.out.println("TA Menu:");
            System.out.println("1) View enrolled students");
            System.out.println("2) Assign grades ");
            System.out.println("3) Log out ");
            int choice = obj.nextInt();
            obj.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    ta.viewEnrolledStudents();
                    break;
                case 2:
                    System.out.print("Enter roll number of the student to assign grade: ");
                    int rollNo = obj.nextInt();
                    obj.nextLine();
                    System.out.print("Enter the grade: ");
                    int grade = obj.nextInt();
                    obj.nextLine();
                    ta.assignGrade(rollNo, grade, Courses.getEnrolledStudents());
                    break;
                case 3:
                    System.out.println("Logging out");
                    System.out.println("-------------------------------------------");
                    return;
                default:
                    System.out.println("ERROR: Invalid choice. Please try again.");
            }
        }
    }

    // Admin menu
    private static void adminMenu(Admin admin, Scanner obj, ArrayList<Complaint> all_complaints,ArrayList<Student>all_students,ArrayList<Professor>all_professors) {
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. View All Courses ");
            System.out.println("2. Add a Course ");
            System.out.println("3. Delete a Course ");
            System.out.println("4. Manage Student Records");
            System.out.println("5. Assign Professors to Courses");
            System.out.println("6. View Complaints");
            System.out.println("7. Handle Complaints ");
            System.out.println("8. Logout");
            int choice = obj.nextInt();
            obj.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter semester: ");
                    int sem = obj.nextInt();
                    obj.nextLine();
                    admin.viewCourseCatalog(sem);
                    break;
                case 2:
                    System.out.print("Enter semester: ");
                    int seme = obj.nextInt();
                    admin.addCourse(seme);
                    break;
                case 3:
                    System.out.print("Enter semester: ");
                    int semester = obj.nextInt();
                    admin.deleteCourse(semester);
                    break;
                case 4:
                    admin.manageStudentRecords(all_students);
                    break;
                case 5:
                    System.out.print("Enter semester: ");
                    int semest = obj.nextInt();
                    admin.assignProfessorsToCourses(all_professors,semest);
                    break;
                case 6:
                    int index=1;
                    for (Complaint i : all_complaints) {
                        System.out.println(index+". ");
                        i.printComplaint();
                        index++;
                    }
                    break;

                case 7:
                    System.out.print("Enter Complaint Number ");
                    int no = obj.nextInt();
                    obj.nextLine();

                    System.out.print("Enter new status (Pending/Resolved): ");
                    String status = obj.nextLine();

                    admin.handleComplaints(no, status);
                    break;

                case 8:
                    System.out.println("Logging out");
                    System.out.println("-------------------------------------------");
                    return;
                default:
                    System.out.println("ERROR: Invalid choice. Please try again.");
            }
        }
    }
}


