 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JPanel implements ActionListener {

    // GUI components
    private JLabel titleLabel, staffLabel, visitorLabel, totalVisitorParking, totalStaffParking;
    private JTextField totalStaff, totalVisitor;
    private JButton addBtn;

    //Creating the carPark and initialize it with new instance of CarPark class
    private static CarPark carPark = CarPark.getInstance();

    public Dashboard() {
        totalVisitorParking = new JLabel("Total Visitor Slot: " + carPark.getTotalVisitorSlot());
        totalStaffParking = new JLabel("Total Staff Slot: " + carPark.getTotalStaffSlot());

        // Create the component to display total staff and visitor slot
        totalVisitorParking.setFont(new Font("Calibri", Font.BOLD, 16));
        totalStaffParking.setFont(new Font("Calibri", Font.BOLD, 16));

        titleLabel = new JLabel("Add Total Slot");
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 18));

        staffLabel = new JLabel("Total Staff Slot:");
        totalStaff = new JTextField(20);
        totalStaff.setPreferredSize(new Dimension(totalStaff.getPreferredSize().width, 30));

        visitorLabel = new JLabel("Total Visitor Slot:");
        totalVisitor = new JTextField(20);
        totalVisitor.setPreferredSize(new Dimension(totalVisitor.getPreferredSize().width, 30));

        addBtn = new JButton("Add");

        // Add action listeners to buttons
        addBtn.addActionListener(this);

        // Set up the layout
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(10, 0, 20, 0);
        add(totalVisitorParking, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(10, 0, 20, 0);
        add(totalStaffParking, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(10, 0, 20, 0);
        add(titleLabel, gc);
        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 5, 0);
        add(staffLabel, gc);
        gc.gridx = 0;
        gc.gridy = 4;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 10, 0);
        add(totalStaff, gc);
        gc.gridx = 0;
        gc.gridy = 5;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 5, 0);
        add(visitorLabel, gc);
        gc.gridx = 0;
        gc.gridy = 6;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 10, 0);
        add(totalVisitor, gc);
        gc.gridx = 0;
        gc.gridy = 7;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(20, 0, 0, 0);
        add(addBtn, gc);

        // To check  if slot has already been set
        checkAvailable();
    }

    private void checkAvailable() {
        System.out.println(carPark.getTotalStaffSlot());
        if (carPark.getTotalStaffSlot() > 0) {
            titleLabel.setVisible(false);
            staffLabel.setVisible(false);
            totalStaff.setVisible(false);
            visitorLabel.setVisible(false);
            totalVisitor.setVisible(false);
            addBtn.setVisible(false);
        }
    }

    // Handle button clicks
    public void actionPerformed(ActionEvent e) {
        if (carPark.getTotalVisitorSlot() > 0 || carPark.getTotalStaffSlot() > 0) {
            JOptionPane.showMessageDialog(this, "Not Allowed. The slot for staff and visitors has already been allocated.");
        } else {
            if (!totalStaff.getText().equals("") && !totalVisitor.getText().equals("")) {
                try {
                    // For checking the parking number 
                    int sSLot = Integer.parseInt(totalStaff.getText());
                    int vSLot = Integer.parseInt(totalVisitor.getText());

                    if (sSLot > 0 && vSLot > 0) {
                        // To set total slots for staffs and for visitors
                        carPark.setTotalSlots(sSLot, vSLot);

                        totalVisitorParking.setText("Total Visitor Slot: " + vSLot);
                        totalStaffParking.setText("Total Staff Slot: " + sSLot);
                        checkAvailable();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid input. Please enter a slot number greater than 0.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Slot must be a valid number.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter all data.");
            }
        }
    }
}
