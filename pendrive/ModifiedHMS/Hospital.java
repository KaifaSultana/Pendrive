import java.util.*;
import java.util.ArrayList;

class Hospital {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Appointment> appointments = new ArrayList<>();
    private ArrayList<Prescription> prescriptions;//New ArrayList to store Prescription.
    private AppointmentManager appointmentManager = new AppointmentManager(appointments);

    public Hospital() {
        users = new ArrayList<>();
        doctors = new ArrayList<>();
        appointments = new ArrayList<>();
        prescriptions = new ArrayList<>();
    }
   
    public void registerUser(Scanner sc) {
        System.out.println("Enter your Name: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.println("Enter your Username: ");
        String userName = sc.next();
        System.out.println("Enter your Password: ");
        String password = sc.next();

        System.out.println("Available User Types:");
        System.out.println("1. Patient");
        System.out.println("2. Doctor");
        System.out.println("3. Admin");
        System.out.println("4. Pharmacist");
        System.out.println("Enter User Type (1/2/3/4): ");
        int userTypeChoice = sc.nextInt();

        User newUser;

        switch (userTypeChoice) {
            case 1:
                newUser = new Patient(name, userName, password);
                break;
            case 2:
                newUser = new Doctor(name, userName, password);
                break;
            case 3:
                newUser = new Admin(name, userName, password);
                break;
            case 4:
                newUser = new Pharmacist(name, userName, password);
                break;
            default:
                System.out.println("Invalid user type. Registration failed.");
                return;
        }

        users.add(newUser);
        System.out.println("User Registered Successfully!");
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User verifyPatient(String userName, String password) {
        for (User user : users) {
            if (user instanceof Patient && user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // Patient not found
    }

    public User verifyDoctor(String userName, String password) {
        for (User user : users) {
            if (user instanceof Doctor && user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // Doctor not found
    }

    public User verifyAdmin(String userName, String password) {
        for (User user : users) {
            if (user instanceof Admin && user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // Admin not found
    }

    public User verifyPharmacist(String userName, String password) {
        for (User user : users) {
            if (user instanceof Pharmacist && user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // Pharmacist not found
    }

    //  Register Doctor function for Doctor.
    public void registerDoctor(Scanner sc, User loggedInUser) {
    Doctor doctor = (Doctor) loggedInUser;

    System.out.println("Enter your degree: ");
    String degree = sc.next();
    sc.nextLine();  

    System.out.println("Enter your specialization: ");
    String specialization = sc.nextLine();

    System.out.println("Enter your room number: ");
    int roomNumber;
    try {
        roomNumber = Integer.parseInt(sc.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("Invalid room number. Please enter a valid integer.");
        return;
    }

    Doctor newDoctor = new Doctor(doctor.getName(), doctor.getUserName(), doctor.getPassword(), "Doctor", degree, specialization, roomNumber);
    doctors.add(newDoctor);
    System.out.println("Doctor Registered Successfully!");
}

    // Register Dcotor function for Admin.
    public void registerDoctorByAdmin(Scanner sc) 
    {
        System.out.println("Enter Doctor's Name: ");
        sc.nextLine(); // Consume the newline character
        String name = sc.nextLine();

       System.out.println("Enter Doctor's UserName: ");
        String userName = sc.next();

        System.out.println("Enter Doctor's Password: ");
        String password = sc.next();

        System.out.println("Enter Doctor's Degree: ");
        String degree = sc.next();

        System.out.println("Enter Doctor's Specialization: ");
        String specialization = sc.next();

        System.out.println("Enter Doctor's Room Number: ");
        int roomNumber = sc.nextInt();

        Doctor newDoctor = new Doctor(name, userName, password, "Doctor", degree, specialization, roomNumber);
        doctors.add(newDoctor); // Assuming you have an ArrayList doctors to store doctors
        System.out.println("Doctor Registered Successfully!");
}


    public void displayAllDoctors() {
    if (doctors.isEmpty()) {
        System.out.println("No doctors available at this moment.");
    } else {
        System.out.println("Available Doctors:");
        for (Doctor doctor : doctors) {
            System.out.println(doctor.toString());
        }
    }
}

    // Method to book an appointment
    public void bookAppointment(Scanner sc, User loggedInUser) {
        String patientName;
        String patientUsername;
        int patientAge;

        if (loggedInUser instanceof Patient) {
            Patient patient = (Patient) loggedInUser;
            patientName = patient.getName();
            patientUsername = patient.getUserName();
        } else if (loggedInUser instanceof Admin) {
            System.out.println("Enter patient's name: ");
            sc.nextLine(); // Consume the newline character
            patientName = sc.nextLine();

            System.out.println("Enter patient's username: ");
            patientUsername = sc.next();
        } else {
            System.out.println("Invalid user type for booking appointment.");
            return;
        }

        System.out.println("Enter patient's age: ");
        patientAge = sc.nextInt();

        String doctorUsername;
        Doctor selectedDoctor = null;

        // Loop until a valid doctorUsername is entered
        while (true) {
            try {
                System.out.println("Enter doctor's username for appointment: ");
                doctorUsername = sc.next();

                // Find the doctor in the list
                for (Doctor doctor : doctors) {
                    if (doctor.getUserName().equals(doctorUsername)) {
                        selectedDoctor = doctor;
                        break;
                    }
                }

                // Check if a valid doctor was found
                if (selectedDoctor != null) {
                    break; // Exit the loop
                } else {
                    throw new NoSuchElementException(); // Throw exception if username not found
                }
            } catch (NoSuchElementException e) {
                System.out.println("Invalid doctor username. Please enter a valid username.");
            }
        }

        System.out.println("Enter appointment date (MM/DD/YYYY): ");
        String appointmentDate = sc.next();

        System.out.println("Enter appointment time: ");
        String appointmentTime = sc.next();

        System.out.println("Enter booking amount: ");
        double bookingAmount = sc.nextDouble();

        // Create an Appointment object and add it to the appointments list
        Appointment newAppointment = new Appointment(patientName, patientUsername, patientAge, doctorUsername, appointmentDate, appointmentTime, bookingAmount);
        appointments.add(newAppointment);
        System.out.println("Appointment booked successfully!");
    }
    
    // Method to display all appointments for Admin
public void displayAllAppointments() {
    if (appointments.isEmpty()) {
        System.out.println("No appointments available at this moment.");
    } else {
        System.out.println("All Appointments:");
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }
}

// Method to display appointments for Doctor based on username
public void displayDoctorAppointments(String doctorUsername) {
    if (appointments.isEmpty()) {
        System.out.println("No appointments available at this moment.");
    } else {
        System.out.println("Appointments for Doctor " + doctorUsername + ":");
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorUsername().equals(doctorUsername)) {
                System.out.println(appointment);
            }
        }
    }
}

// Method to display appointments for Patient based on username
public void displayPatientAppointments(String patientUsername) {
    if (appointments.isEmpty()) {
        System.out.println("No appointments available at this moment.");
    } else {
        System.out.println("Appointments for Patient " + patientUsername + ":");
        for (Appointment appointment : appointments) {
            if (appointment.getPatientUsername().equals(patientUsername)) {
                System.out.println(appointment);
            }
        }
    }
}

//Call Cancel Appointment function in hospital class
public void cancelAppointment(Scanner sc, User loggedInUser)
{
    appointmentManager.cancelAppointment(sc, loggedInUser);
}

/* public void displayAllUsers() 
    {
        for (int i = 0; i < registeredUsers.size(); i++) {
            System.out.println(registeredUsers.get(i).toString());
        }
    } */
    public void displayAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No registered users at this moment.");
        } else {
            System.out.println("Registered Users:");
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    // Method to delete a user by userId for Admin
    public void deleteUserByAdmin(Scanner sc) {
        System.out.println("Enter the userName of the user to delete: ");
        String userNameToDelete = sc.next();

        User userToDelete = null;

        // Find the user in the list
        for (User user : users) {
            if (user.getUserName().equals(userNameToDelete)) {
                userToDelete = user;
                break;
            }
        }

        // Check if a valid user was found
        if (userToDelete != null) {
            users.remove(userToDelete);
            System.out.println("User with userId " + userNameToDelete + " deleted successfully.");
        } else {
            System.out.println("User with userId " + userNameToDelete + " not found.");
        }
    }

    //Function for Doctor to provide Prescription
    public void providePrescription(Scanner sc, Doctor loggedInDoctor) {
        System.out.println("Enter patient's username: ");
        String patientUsername = sc.next();

        System.out.println("Enter medicine prescribed: ");
        String medicine = sc.next();

        Prescription prescription = new Prescription(patientUsername, loggedInDoctor.getUserName(), new Date(), medicine);
        prescriptions.add(prescription);
        System.out.println("Prescription added successfully!");
    }

    //Function to display Prescription for Doctor
    public void displayPrescriptionsForDoctor(String doctorUsername) {
        boolean found = false;
        for (Prescription prescription : prescriptions) {
            if (prescription.getDoctorUsername().equals(doctorUsername)) {
                System.out.println(prescription);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No prescriptions found for doctor " + doctorUsername);
        }
    }

    //Function to display Prescription for Patient
    public void displayPrescriptionsForPatient(String patientUsername) {
        boolean found = false;
        for (Prescription prescription : prescriptions) {
            if (prescription.getPatientUsername().equals(patientUsername)) {
                System.out.println(prescription);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No prescriptions found for patient " + patientUsername);
        }
    }


    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }
}
