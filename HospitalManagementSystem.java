import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class Patient {
    int id;
    String name;
    int age;
    String gender;
    String diagnosis;

    public Patient(int id, String name, int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}

class Appointment {
    int patientId;
    String doctorName;
    String dateTime;

    public Appointment(int patientId, String doctorName, String dateTime) {
        this.patientId = patientId;
        this.doctorName = doctorName;
        this.dateTime = dateTime;
    }
}

class Bill {
    int patientId;
    double amount;
    boolean paid;

    public Bill(int patientId, double amount) {
        this.patientId = patientId;
        this.amount = amount;
        this.paid = false;
    }
}

class InventoryItem {
    String itemName;
    int quantity;

    public InventoryItem(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }
}

class Staff {
    int id;
    String name;
    String role;

    public Staff(int id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }
}

public class HospitalManagementSystem extends JFrame {
    static java.util.List<Patient> patients = new ArrayList<>();
    static java.util.List<Appointment> appointments = new ArrayList<>();
    static java.util.List<Bill> bills = new ArrayList<>();
    static java.util.List<InventoryItem> inventory = new ArrayList<>();
    static java.util.List<Staff> staffList = new ArrayList<>();

    public HospitalManagementSystem() {
        setTitle("Hospital Management System");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 1));

        JButton patientBtn = new JButton("Register Patient");
        JButton appointmentBtn = new JButton("Schedule Appointment");
        JButton ehrBtn = new JButton("Add EHR");
        JButton billingBtn = new JButton("Generate Bill");
        JButton inventoryBtn = new JButton("Manage Inventory");
        JButton staffBtn = new JButton("Manage Staff");
        JButton exitBtn = new JButton("Exit");

        add(patientBtn);
        add(appointmentBtn);
        add(ehrBtn);
        add(billingBtn);
        add(inventoryBtn);
        add(staffBtn);
        add(exitBtn);

        patientBtn.addActionListener(e -> registerPatient());
        appointmentBtn.addActionListener(e -> scheduleAppointment());
        ehrBtn.addActionListener(e -> addEHR());
        billingBtn.addActionListener(e -> generateBill());
        inventoryBtn.addActionListener(e -> manageInventory());
        staffBtn.addActionListener(e -> manageStaff());
        exitBtn.addActionListener(e -> System.exit(0));
    }

    void registerPatient() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Patient ID:"));
            String name = JOptionPane.showInputDialog("Enter Name:");
            int age = Integer.parseInt(JOptionPane.showInputDialog("Enter Age:"));
            String gender = JOptionPane.showInputDialog("Enter Gender:");
            patients.add(new Patient(id, name, age, gender));
            JOptionPane.showMessageDialog(this, "Patient Registered.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }

    void scheduleAppointment() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Patient ID:"));
            String doctor = JOptionPane.showInputDialog("Enter Doctor Name:");
            String datetime = JOptionPane.showInputDialog("Enter DateTime:");
            appointments.add(new Appointment(id, doctor, datetime));
            JOptionPane.showMessageDialog(this, "Appointment Scheduled.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }

    void addEHR() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Patient ID:"));
            String diag = JOptionPane.showInputDialog("Enter Diagnosis:");
            for (Patient p : patients) {
                if (p.id == id) {
                    p.diagnosis = diag;
                    JOptionPane.showMessageDialog(this, "EHR Updated.");
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Patient not found.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }

    void generateBill() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Patient ID:"));
            double amt = Double.parseDouble(JOptionPane.showInputDialog("Enter Amount:"));
            bills.add(new Bill(id, amt));
            JOptionPane.showMessageDialog(this, "Bill Generated.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }

    void manageInventory() {
        try {
            String name = JOptionPane.showInputDialog("Enter Item Name:");
            int qty = Integer.parseInt(JOptionPane.showInputDialog("Enter Quantity:"));
            inventory.add(new InventoryItem(name, qty));
            JOptionPane.showMessageDialog(this, "Inventory Updated.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }

    void manageStaff() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Staff ID:"));
            String name = JOptionPane.showInputDialog("Enter Name:");
            String role = JOptionPane.showInputDialog("Enter Role:");
            staffList.add(new Staff(id, name, role));
            JOptionPane.showMessageDialog(this, "Staff Added.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HospitalManagementSystem().setVisible(true);
        });
    }
}
