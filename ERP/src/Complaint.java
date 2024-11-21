public class Complaint {
    public String detail;
    public String status;

    // Constructor
    public Complaint(String detail) {

        this.detail=detail;
        this.status="Pending"; // Default status
    }

    public void printComplaint() {
        System.out.println("Complaint: " + detail);
        System.out.println("Status: " + status+ "\n");
    }

    // (for admin)
    public void setStatus(String status) {
        if (status.equals("Pending") || status.equals("Resolved")) {
            this.status = status;
        }
    }

    public String getStatus() {
        return status;
    }


}
