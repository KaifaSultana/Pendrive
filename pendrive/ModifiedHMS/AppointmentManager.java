import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class AppointmentManager {

    private ArrayList<Appointment> appointments;

    public AppointmentManager(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    // Method to cancel an appointment
    public void cancelAppointment(Scanner sc, User loggedInUser) {
        if (loggedInUser instanceof Patient) {
            Patient patient = (Patient) loggedInUser;

            System.out.println("Enter your appointment date (MM/DD/YYYY): ");
            String appointmentDate = sc.next();
            System.out.println("Enter your appointment time: ");
            String appointmentTime = sc.next();

            // Iterate through appointments to find and remove the patient's appointment
            Iterator<Appointment> iterator = appointments.iterator();
            while (iterator.hasNext()) {
                Appointment appointment = iterator.next();
                if (appointment.getPatientUsername().equals(patient.getUserName())
                        && appointment.getAppointmentDate().equals(appointmentDate)
                        && appointment.getAppointmentTime().equals(appointmentTime)) {
                    iterator.remove();
                    System.out.println("Appointment canceled successfully!");
                    return;
                }
            }

            System.out.println("Appointment not found for cancellation.");
        } else if (loggedInUser instanceof Admin) {
            System.out.println("Enter patient's username for appointment cancellation: ");
            String patientUsername = sc.next();

            // Iterate through appointments to find and remove the specified patient's appointment
            Iterator<Appointment> iterator = appointments.iterator();
            while (iterator.hasNext()) {
                Appointment appointment = iterator.next();
                if (appointment.getPatientUsername().equals(patientUsername)) {
                    iterator.remove();
                    System.out.println("Appointment for " + patientUsername + " canceled successfully!");
                    return;
                }
            }

            System.out.println("No appointments found for the specified patient.");
        } else {
            System.out.println("Invalid user type for appointment cancellation.");
        }
    }
}
