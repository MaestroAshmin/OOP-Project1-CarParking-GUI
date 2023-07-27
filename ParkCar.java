import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import javax.swing.*;

public class ParkCar extends JPanel implements ActionListener {

    private JLabel registrationNumberLabel, headingLabel, ownerNameLabel,slotType;
    private JTextField registrationNumber,ownerName;
    private JComboBox<String> type;
    private JButton parkCarButton;

    //Creating the carPark and initialize it with new instance of CarPark class
    private static CarPark carPark = CarPark.getInstance();

    public ParkCar() {
        // For heading
        headingLabel = new JLabel("Park New Car");
        Font labelFont = headingLabel.getFont();
        headingLabel.setFont(labelFont.deriveFont(20f));

        // for car registration number
        registrationNumberLabel = new JLabel("Enter Car Registration Number");
        registrationNumber = new JTextField(18);
        registrationNumber.setPreferredSize(new Dimension(registrationNumber.getPreferredSize().width, 28));
        
        // for car owner name
        ownerNameLabel = new JLabel("Enter Owner Name");
        ownerName = new JTextField(18);
        ownerName.setPreferredSize(new Dimension(ownerName.getPreferredSize().width, 28));
        
        // for owner type
        slotType = new JLabel("Choose Owner Type");
        type = new JComboBox<>(new String[]{"Staff", "Visitor"});
        type.setPreferredSize(new Dimension(200, type.getPreferredSize().height));
        
        // add new car in the parking slot button
        parkCarButton = new JButton("ADD");
        parkCarButton.setPreferredSize(new Dimension(200, 28));
        parkCarButton.setBackground(Color.LIGHT_GRAY); // Set the background color to red
        parkCarButton.setForeground(Color.WHITE);
        // Add action listeners to buttons
        parkCarButton.addActionListener(this);

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
        add(registrationNumberLabel, gc);
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 10, 0);
        add(registrationNumber, gc);
        
        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(8, 0, 0, 0);
        add(ownerNameLabel, gc);
        gc.gridx = 0;
        gc.gridy = 4;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 10, 0);
        add(ownerName, gc);

        gc.gridx = 0;
        gc.gridy = 5;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(4, 0, 0, 0);
        add(slotType, gc);
        gc.gridx = 0;
        gc.gridy = 6;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 10, 0);
        add(type, gc);

        gc.gridx = 0;
        gc.gridy = 7;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(10, 0, 0, 0);
        add(parkCarButton, gc);
    }

    // For handling the add park car button
    public void actionPerformed(ActionEvent e) {
        if (registrationNumber.getText().equals("") || type.getSelectedItem().equals("") || ownerName.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter all data.");
        } else {
            try {
                // validate that identifier starts with a capital letter followed by a two-digit number
                if (!registrationNumber.getText().matches("^[A-Z]\\d{4}$")) {
                    throw new IllegalArgumentException("Invalid identifier. Identifier should start with a capital letter followed by a four-digit number. e.g. “T2345�?, “G2345�?");
                }

                Car car;
                //Checking If car has already been parked in a slot
                ParkingSlot parkedSlot = carPark.findCarByRegistrationNumber(registrationNumber.getText());
                if (parkedSlot == null) {
                    if (type.getSelectedItem().equals("Staff")) {
                        car = new Car(registrationNumber.getText(), ownerName.getText(), true);
                    } else {
                        car = new Car(registrationNumber.getText(), ownerName.getText(), false);
                    }

                    // Set the parked time to the current time
                    LocalDateTime parkedTime = LocalDateTime.now();
                    car.setParkedTime(parkedTime);

                    ParkingSlot slot = carPark.getFirstAvailableSlot(type.getSelectedItem().equals("Staff"));
                    if (slot == null) {
                        JOptionPane.showMessageDialog(this, "Sorry, there are no available slots.");
                    } else {
                        slot.parkCar(car);
                        JOptionPane.showMessageDialog(this, "Car parked in slot " + slot.getIdentifier() + " at " + car.getParkedTime());
                        registrationNumber.setText("");
                        ownerName.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Car with registration number " + registrationNumber.getText() + " is already parked in slot " + parkedSlot.getIdentifier() + " and is owned by " + parkedSlot.getParkedCar().getOwnerName());
                    JOptionPane.showMessageDialog(this, "Parking time: " + parkedSlot.getParkedCar().getParkingTimeLength());
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }
}
