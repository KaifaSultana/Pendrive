import java.util.ArrayList;
import java.util.*;

class Admin extends User {
    ArrayList<User> registeredUsers;

    public Admin(String name, String userName, String password) {
        super(name, userName, password, "Admin");
        this.registeredUsers = new ArrayList<>();
    }

   /*
    // Display feedback for both doctors and patients
    public void displayFeedback(ArrayList<Integer> feedbackList, String userType) {
        if (feedbackList.isEmpty()) {
            System.out.println("No feedback available at this moment.");
        } else {
            System.out.println("Feedback for " + userType + ":");
            for (Integer feedback : feedbackList) {
                System.out.println("Rating: " + feedback);
            }
        }
    }

    // Display feedback for doctors
    public void displayDoctorFeedback(Doctor doctor) {
        displayFeedback(doctor.getDoctorFeedback(), "Doctor " + doctor.getName());
    }

    // Display feedback for patients
    public void displayPatientFeedback(Patient patient) {
        displayFeedback(patient.getPatientFeedback(), "Patient " + patient.getName());
    } */


    public String toString() {
        return super.toString() + " Admin: ";
    }
}