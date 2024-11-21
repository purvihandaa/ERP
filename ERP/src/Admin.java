import java.util.ArrayList;
import java.util.Scanner;


class Admin extends User {
    private static final String ADMIN_PASSWORD = "admin123";

    // Constructor
    public Admin(String name, String email,String ADMIN_PASSWORD) {
        super(name, email, ADMIN_PASSWORD);

    }

    public String getEmail() {
        return email;
    }

    public void handleComplaints(int index, String updatedStatus) {
        Main.all_complaints.get(index-1).setStatus(updatedStatus);
        System.out.println("Complaint status updated to: " + updatedStatus);
    }

    public void viewCourseCatalog(int sem) {
        System.out.println("Manage Course Catalog: ");
        Student.viewCourses(sem);

    }

    public void addCourse(int sem) {
        Scanner obj = new Scanner(System.in);

        System.out.print("Enter Course Code: ");
        String code = obj.nextLine();

        System.out.print("Enter Course Title: ");
        String title = obj.nextLine();

        System.out.print("Enter Professor Name: ");
        String professor = obj.nextLine();

        System.out.print("Enter Credits: ");
        int credits = obj.nextInt();
        obj.nextLine();

        System.out.print("Enter Prerequisite (or 'None'): ");
        String prerequisite = obj.nextLine();

        System.out.print("Enter Timings: ");
        String timings = obj.nextLine();

        System.out.print("Enter Syllabus: ");
        String syllabus = obj.nextLine();

        System.out.print("Enter Enrollment Limit: ");
        int enrollmentLimit = obj.nextInt();
        obj.nextLine();

        System.out.print("Enter Office Hours: ");
        String officeHours = obj.nextLine();

        Courses Course1 = new Courses(code, title, professor, credits, prerequisite, timings, syllabus, enrollmentLimit, officeHours);

        ArrayList<Courses> semesterCourses = Courses.getCoursesBySemester(sem);
        semesterCourses.add(Course1);
        System.out.println("Course added successfully!");
    }

    public void deleteCourse(int sem) {
        Scanner obj = new Scanner(System.in);

        System.out.print("Enter the course code to delete: ");
        String courseCode = obj.nextLine();

        ArrayList<Courses> existingCourses = Courses.getCoursesBySemester(sem);

        for (Courses i : existingCourses) {
            if (i.getCode().equals(courseCode)) {
                existingCourses.remove(i);
                System.out.println("Course deleted successfully!");
                break;
            } else {
                System.out.println("ERROR: Course not found!");
            }
        }
    }

    public void manageStudentRecords(ArrayList<Student> students) {
        Scanner obj = new Scanner(System.in);
        System.out.print("Enter the roll number of the student: ");
        int rollNo = obj.nextInt();
        obj.nextLine();

        for (Student i : students) {
            if (i.getRollNo() == rollNo) {
                System.out.println("Student Found:");
                System.out.println("Name: " + i.getName());
                System.out.println("Email: " + i.getEmail());
                System.out.println("Semester: " + i.getSem());
                System.out.println("CGPA: " + i.getCgpa());

                System.out.println("Do you want to update student information? (y/n)");
                String update = obj.nextLine();

                if (update.equalsIgnoreCase("y")) {
                    System.out.print("Enter new email: ");
                    String newEmail = obj.nextLine();
                    i.setEmail(newEmail);

                    System.out.print("Enter new CGPA: ");
                    float newCgpa = obj.nextFloat();
                    i.setCgpa(newCgpa);

                    System.out.println("Student information updated.");
                }

                return;
            }
        }
        System.out.println("ERROR: Student not found!");
    }

    public void assignProfessorsToCourses(ArrayList<Professor> professors, int sem) {
        Scanner obj = new Scanner(System.in);

        System.out.println("Courses available for Semester " + sem + ":");
        ArrayList<Courses> availCourseDetails = Courses.getCoursesBySemester(sem);
        for (Courses i : availCourseDetails) {
            System.out.println("Course Code: " + i.getCode() + ", Title: " + i.title + ", Current Professor: " + i.professor);
        }

        System.out.print("Enter the course code to assign a professor: ");
        String courseCode = obj.nextLine();

        for (Courses i : availCourseDetails) {
            if (i.getCode().equals(courseCode)) {
                System.out.println("Available Professors:");

                for (int j = 0; j < professors.size(); j++) {
                    System.out.println((j + 1) + ". " + professors.get(j).getName() + " (Expertise: " + professors.get(j).getExpertise() + ")");
                }

                System.out.print("Select the professor number: ");
                int profIndex = obj.nextInt() - 1;
                if (profIndex >= 0 && profIndex < professors.size()) {
                    i.professor = professors.get(profIndex).getName();
                    System.out.println("Professor " + i.professor + " assigned to " + i.getCode() + " successfully.");
                } else {
                    System.out.println("ERROR: Invalid professor selection.");
                }
                return;
            }
        }

        System.out.println("ERROR: Course not found!");
    }
}


