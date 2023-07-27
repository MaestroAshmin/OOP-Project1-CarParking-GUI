 

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AddSlot extends JPanel implements ActionListener {

    private JLabel slotLabel, headingLabel, slotType;
    private JTextField slotIdentifier;
    private JComboBox<String> type;
    private JButton addSlotButton;

    //Creating the carPark and initialize it with new instance of CarPark class
    private static CarPark carPark = CarPark.getInstance();

    public AddSlot() {
        // For heading
        headingLabel = new JLabel("Add New Slot");
        Font labelFont = headingLabel.getFont();
        headingLabel.setFont(labelFont.deriveFont(20f));

        // two fields to get the identifier and type
        slotLabel = new JLabel("Enter Slot Identifier");
        slotIdentifier = new JTextField(18);
        slotIdentifier.setPreferredSize(new Dimension(slotIdentifier.getPreferredSize().width, 28));
        slotType = new JLabel("Choose Slot Type");
        type = new JComboBox<>(new String[]{"Staff", "Visitor"});
        type.setPreferredSize(new Dimension(200, type.getPreferredSize().height));
        addSlotButton = new JButton("ADD");

        // Add action listeners to buttons
        addSlotButton.addActionListener(this);

        // Set up the layout
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(10, 0, 20, 0);
        add(headingLabel, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(8, 0, 0, 0);
        add(slotLabel, gc);
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 10, 0);
        add(slotIdentifier, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(4, 0, 0, 0);
        add(slotType, gc);
        gc.gridx = 0;
        gc.gridy = 4;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 10, 0);
        add(type, gc);

        gc.gridx = 0;
        gc.gridy = 5;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(10, 0, 0, 0);
        add(addSlotButton, gc);
    }

    public void actionPerformed(ActionEvent e) {
        if (slotIdentifier.getText().equals("") || type.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter all data.");
        } else {
            try {
                // validate that identifier starts with a capital letter followed by a two-digit number
                if (!slotIdentifier.getText().matches("^[A-Z]\\d{2}$")) {
                    throw new IllegalArgumentException("Invalid identifier. Identifier should start with a capital letter followed by a two-digit number.e.g,'D01', 'E01'");
                }

                boolean exists = carPark.getParkingSlots().stream().anyMatch(slot -> slot.getIdentifier().equals(slotIdentifier.getText()));
                if (exists) {
                    throw new IllegalArgumentException("Slot identifier already exists. Please provide a unique identifier.");
                }

                if (type.getSelectedItem().equals("Visitor")) {
                    if (carPark.isFull("visitor")) {
                        JOptionPane.showMessageDialog(this, "Maximum slot capacity reached for visitor");
                    } else {
                        ParkingSlot slot = new ParkingSlot(slotIdentifier.getText(), false);
                        carPark.addSlot(slot, "visitor");
                        JOptionPane.showMessageDialog(this, "Visitor slot " + slotIdentifier.getText() + " added for visitor");
                    }
                } else if (type.getSelectedItem().equals("Staff")) {
                    if (carPark.isFull("staff")) {
                        JOptionPane.showMessageDialog(this, "Maximum slot capacity reached for staff");
                    } else {
                        ParkingSlot slot = new ParkingSlot(slotIdentifier.getText(), true);
                        carPark.addSlot(slot, "staff");
                        JOptionPane.showMessageDialog(this, "Staff slot " + slotIdentifier.getText() + " added for staff");
                        slotIdentifier.setText(""); // To clear the text field after adding the slot
                    }
                } else {
                    JOptionPane.showMessageDialog(this, slotIdentifier.getText() + type.getSelectedItem());
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }
}
