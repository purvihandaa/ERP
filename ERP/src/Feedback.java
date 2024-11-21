import java.util.*;

public class Feedback <T>{
    private T rating;
    private T comments;
    private T courseCode;

    public Feedback(T courseCode,T rating,T comments){
        this.courseCode=courseCode;
        this.rating=rating;
        this.comments=comments;
    }

    public void printFeedback() {
        System.out.println("Rating: " + rating);
        System.out.println("Comment: " + comments+"\n");
    }

    public T getCourseCode() {
        return courseCode;
    }
}
