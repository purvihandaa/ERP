import java.util.ArrayList;

class TA extends Student{
    String assignedCourseCode;
    public TA(int rollNo, String name, String email,String password,int sem,String assignedCourseCode) {
        super(rollNo,name,email,password,sem);
        this.assignedCourseCode=assignedCourseCode;

    }

    public String getAssignedCourseCode() {
        return assignedCourseCode;
    }

    public void setAssignedCourseCode(String assignedCourseCode) {
        this.assignedCourseCode = assignedCourseCode;
    }

    public void assignGrade(int rollno, int grade, ArrayList<Student> enrolledStudents) {
        System.out.print("Assigning grade for the course "+ this.assignedCourseCode);
        for (Student i : enrolledStudents) {
            if (i.getRollNo() == rollno) {
                if (i.getEnrolledCourses().contains(assignedCourseCode)) {
                    i.getGrades().put(assignedCourseCode,grade);
                    if (!i.getCompletedCourses().contains(assignedCourseCode)) {
                        i.getCompletedCourses().add(assignedCourseCode);
                    }
                    i.getEnrolledCourses().remove(assignedCourseCode);
                    System.out.println("Grade assigned successfully for " + assignedCourseCode);
                } else {
                    System.out.println("ERROR: Student is not enrolled in " + assignedCourseCode);
                }
                return;
            }
        }
        System.out.println("ERROR: Student with roll number " + rollno + " not found.");
    }

    public void viewEnrolledStudents() {
        ArrayList<Student> std= Courses.getEnrolledStudents();
        for (Student i: std){
            if(i.getEnrolledCourses().contains(this.assignedCourseCode)) {
                System.out.println("Student Name: "+ i.getName());
                System.out.println("Email: "+ i.getEmail());
                System.out.println("Grade: "+ i.getCgpa());

            }
            else{
                System.out.println("No students found for Course: "+ this.assignedCourseCode);
            }
        }
    }


}


