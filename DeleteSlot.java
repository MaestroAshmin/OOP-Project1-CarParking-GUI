import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DeleteSlot extends JPanel implements ActionListener {

    private JLabel slotLabel, headingLabel;
    private JTextField slotIdentifier;
    private JComboBox<String> type;
    private JButton deleteSlotButton;

    //Creating the carPark and initialize it with new instance of CarPark class
    private static CarPark carPark = CarPark.getInstance();

    public DeleteSlot() {
        // For heading
        headingLabel = new JLabel("Delete Parking Slot");
        Font labelFont = headingLabel.getFont();
        headingLabel.setFont(labelFont.deriveFont(20f));

        // two fields to get the identifier and type
        slotLabel = new JLabel("Enter Slot Identifier");
        slotIdentifier = new JTextField(18);
        slotIdentifier.setPreferredSize(new Dimension(slotIdentifier.getPreferredSize().width, 30));
        deleteSlotButton = new JButton("DELETE");

        // Add action listeners to buttons
        deleteSlotButton.addActionListener(this);

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
        gc.insets = new Insets(10, 0, 0, 0);
        add(deleteSlotButton, gc);
    }

    public void actionPerformed(ActionEvent e) {
        if (slotIdentifier.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter the identifier to delete.");
        } else {
            int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this parking slot?", "Delete Parking Slot", JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                if (carPark.deleteParkingSlot(slotIdentifier.getText()) == "deleted") {
                    JOptionPane.showMessageDialog(this, "Slot of identifier " + slotIdentifier.getText() + " is deleted.");
                    slotIdentifier.setText("");
                } else if (carPark.deleteParkingSlot(slotIdentifier.getText()) == "occupied") {
                    JOptionPane.showMessageDialog(this, "Slot of identifier " + slotIdentifier.getText() + " is occupied.");
                } else {
                    JOptionPane.showMessageDialog(this, "Slot of identifier " + slotIdentifier.getText() + " not found.");
                }
            }
        }
    }
}
