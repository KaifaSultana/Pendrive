import java.util.ArrayList;

public class Pharmacist extends User {
    private ArrayList<Medicine> stock;

    // Constructor
    public Pharmacist(String name, String userName, String password) {
        super(name, userName, password, "Pharmacist");
        this.stock = new ArrayList<>();
    }

    // Inner class to represent Medicine
    private class Medicine {
        private String name;
        private int quantity;

        // Constructor
        public Medicine(String name, int quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        // Getters and setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
