class Patient extends User {

    //private ArrayList<Integer> patientFeedback = new ArrayList<>();

    Patient(String name, String userName, String password) {
        super(name, userName, password, "Patient");
    }

    /*
    public void giveFeedback(Scanner sc) {
        System.out.println("On a scale of 0 to 10, how would you rate your experience with the hospital?");
        int feedbackScore = sc.nextInt();

        if (feedbackScore < 0 || feedbackScore > 10) {
            System.out.println("Invalid feedback score. Please enter a number between 0 and 10.");
        } else {
            patientFeedback.add(feedbackScore);
            System.out.println("Thank you for your feedback!");
        }
    }

    public ArrayList<Integer> getPatientFeedback() {
        return patientFeedback;
    }
    */

    @Override
    public String toString() {
        return super.toString() + " ";
    }

    /* public void performPatientAction() {
        System.out.println("Patient action performed.");
        // Add patient-specific actions here
    } */
}
