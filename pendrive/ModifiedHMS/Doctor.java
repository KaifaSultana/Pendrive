import java.util.*;

class Doctor extends User {
    private String degree;
    private String specialization;
    private int roomNumber;

    //private ArrayList<Integer> doctorFeedback = new ArrayList<>();

    public Doctor(String name, String userName, String password)
    {
        super(name, userName, password, "Admin");
    }

    public Doctor(String name, String userName, String password, String role, String degree, String specialization, int roomNumber) 
    {
        super(name, userName, password, role);
        this.degree = degree;
        this.specialization = specialization;
        this.roomNumber = roomNumber;
    }


    public void registerDoctor(Scanner sc)
    {
        System.out.println("Enter Doctor's Degree: ");
        this.degree = sc.next();
        System.out.println("Enter Doctor's Specialization: ");
        this.specialization = sc.next();
        System.out.println("Enter Doctor's Room Number: ");
        this.roomNumber = sc.nextInt();

        //System.out.println("Doctor is Successfully Registered.");
    }

    /*
    public void giveFeedback(Scanner sc) {
        System.out.println("On a scale of 0 to 10, how would you rate your experience with Dr. " + getName() + "?");
        int feedbackScore = sc.nextInt();

        if (feedbackScore < 0 || feedbackScore > 10) {
            System.out.println("Invalid feedback score. Please enter a number between 0 and 10.");
        } else {
            doctorFeedback.add(feedbackScore);
            System.out.println("Thank you for your feedback!");
        }
    }

    public ArrayList<Integer> getDoctorFeedback() {
        return doctorFeedback;
    }
    */

    public String getDegree()
    {
        return degree;
    }

    public String getSpecialization()
    {
        return specialization;
    }

    public int getRoomNumber()
    {
        return roomNumber;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + getName() + '\'' +
                ", userName='" + getUserName() + '\'' +
                ", degree='" + getDegree() + '\'' +
                ", specialization='" + getSpecialization() + '\'' +
                ", roomNumber=" + getRoomNumber() +
                '}';
}

}