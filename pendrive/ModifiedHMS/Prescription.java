import java.util.Date;

public class Prescription {
    private String patientUsername;
    private String doctorUsername;
    private Date date;
    private String medicine;

    public Prescription(String patientUsername, String doctorUsername, Date date, String medicine) {
        this.patientUsername = patientUsername;
        this.doctorUsername = doctorUsername;
        this.date = date;
        this.medicine = medicine;
    }

    public String getPatientUsername() {
        return patientUsername;
    }

    public void setPatientUsername(String patientUsername) {
        this.patientUsername = patientUsername;
    }

    public String getDoctorUsername() {
        return doctorUsername;
    }

    public void setDoctorUsername(String doctorUsername) {
        this.doctorUsername = doctorUsername;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "patientUsername='" + patientUsername + '\'' +
                ", doctorUsername='" + doctorUsername + '\'' +
                ", date=" + date +
                ", medicine='" + medicine + '\'' +
                '}';
    }
}
