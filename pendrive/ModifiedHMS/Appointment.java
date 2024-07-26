import java.util.Scanner;
import java.util.ArrayList;

class Appointment {
    private String patientName;
    private String patientUsername;
    private int patientAge;
    private String doctorUsername;
    private String appointmentDate;
    private String appointmentTime;
    private double bookingAmount;

    public Appointment(String patientName, String patientUsername, int patientAge, String doctorUsername, String appointmentDate, String appointmentTime, double bookingAmount) {
        this.patientName = patientName;
        this.patientUsername = patientUsername;
        this.patientAge = patientAge;
        this.doctorUsername = doctorUsername;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.bookingAmount = bookingAmount;
    }

         @Override
        public String toString() {
        return "Patient: " + patientName + ", Age: " + patientAge + ", Doctor: " + doctorUsername +
                ", Date: " + appointmentDate + ", Time: " + appointmentTime + ", Amount: " + bookingAmount;
    }
     public String getDoctorUsername() 
     {
        return doctorUsername;
     }

    public String getPatientUsername() 
    {
        return patientUsername;
    }

    public String getAppointmentDate()
    {
        return appointmentDate;
    }

    public String getAppointmentTime()
    {
        return appointmentTime;
    }
}
