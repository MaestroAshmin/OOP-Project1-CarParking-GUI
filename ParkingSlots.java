 

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ParkingSlots extends JPanel implements ActionListener {

    private JScrollPane jScrollPane1;
    private JTable slotTable;
    private JButton unoccupiedSlot, deleteUnoccupiedSlots;

    //Creating the carPark and initialize it with new instance of CarPark class
    private static CarPark carPark = CarPark.getInstance();

    public ParkingSlots() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add the title label to the panel
        JLabel labelTitle = new JLabel("Parking Slots", SwingConstants.CENTER);
        labelTitle.setFont(new Font("Serif", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(labelTitle, gbc);

        // Create the search panel
        JPanel searchPanel = new JPanel(new FlowLayout());
        unoccupiedSlot = new JButton("Get Unoccupied Slots");
        unoccupiedSlot.setPreferredSize(new Dimension(200, 28));
        unoccupiedSlot.setBackground(Color.LIGHT_GRAY); // Set the background color to red
        unoccupiedSlot.setForeground(Color.WHITE); // Set the foreground (text) color of the button to white

        deleteUnoccupiedSlots = new JButton("Delete Unoccupied Slots");
        deleteUnoccupiedSlots.setBackground(Color.RED); // Set the background color to red
        deleteUnoccupiedSlots.setForeground(Color.WHITE); // Set the foreground (text) color of the button to white
        deleteUnoccupiedSlots.setPreferredSize(new Dimension(200, 28));
        searchPanel.add(unoccupiedSlot);
        searchPanel.add(deleteUnoccupiedSlots);
        gbc.gridy = 1;
        add(searchPanel, gbc);

        // Add action listeners to buttons
        unoccupiedSlot.addActionListener(this);
        deleteUnoccupiedSlots.addActionListener(this);

        // Create the panel for the parking slot table
        JPanel panelTable = new JPanel(new BorderLayout());
        slotTable = new JTable();
        jScrollPane1 = new JScrollPane(slotTable);

        // Set the preferred height of the JScrollPane
        jScrollPane1.setPreferredSize(new Dimension(screenWidth - 300, 300));
        panelTable.add(jScrollPane1, BorderLayout.CENTER);

        // Add the table panel to the main panel
        gbc.gridy = 2;
        add(panelTable, gbc);
        // To set the slot table data
        setTb();
    }
    
    private void setTb(){
        // Load the parking slots data into the table
        DefaultTableModel tableModel = new DefaultTableModel();
        // Add columns to the model:
        tableModel.addColumn("Slot Number");
        tableModel.addColumn("Type");
        tableModel.addColumn("Occupied");
        tableModel.addColumn("Car Registration Number");
        tableModel.addColumn("Car Owner Name");
        tableModel.addColumn("Car Parked Time");

        for (ParkingSlot slot : carPark.getParkingSlots()) {
            System.out.println(slot.getIdentifier());
            Vector<Object> rowData = new Vector<>();
            rowData.add(slot.getIdentifier());
            rowData.add(slot.isStaff() ? "Staff" : "Visitor");
            rowData.add(slot.isOccupied() ? "Yes" : "No");
            if (slot.isOccupied()) {
                Car car = slot.getParkedCar();
                rowData.add(car.getRegistrationNumber());
                rowData.add(car.getOwnerName());
                rowData.add(car.getParkingTimeLength());
            } else {
                rowData.add("-");
                rowData.add("-");
                rowData.add("-");
            }
            tableModel.addRow(rowData);
        }
        // Set the table model
        slotTable.setModel(tableModel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == unoccupiedSlot) {
            //Create an instance of DefaultTableModel and set it as the model for your JTable:
            DefaultTableModel dtm = new DefaultTableModel();
            slotTable.setModel(dtm);

            //Add columns to the model:
            dtm.addColumn("Slot Number");
            dtm.addColumn("Type");
            dtm.addColumn("Occupied");
            dtm.addColumn("Car Registration Number");
            dtm.addColumn("Car Owner Name");
            dtm.addColumn("Car Parked Time");
            for (ParkingSlot slot : carPark.getAllUnoccupiedSlots()) {
                Vector<Object> rowData = new Vector<Object>();
                rowData.add(slot.getIdentifier());
                rowData.add(slot.isStaff() ? "Staff" : "Visitor");
                rowData.add(slot.isOccupied() ? "Yes" : "No");
                if (slot.isOccupied()) {
                    Car car = slot.getParkedCar();
                    rowData.add(car.getRegistrationNumber());
                    rowData.add(car.getOwnerName());
                    rowData.add(car.getParkingTimeLength());
                } else {
                    rowData.add("-");
                    rowData.add("-");
                    rowData.add("-");
                }
                dtm.addRow(rowData);
            }
        } else if (e.getSource() == deleteUnoccupiedSlots) {
            int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete all unoccupied parking slots?", "Delete Unoccupied Slots", JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                int total = 0;
                for (ParkingSlot slot : carPark.getAllUnoccupiedSlots()) {
                    String whatHappened = carPark.deleteParkingSlot(slot.getIdentifier());
                    if (whatHappened == "deleted") {
                        total++;
                    }
                }
                if (total > 0) {
                    JOptionPane.showMessageDialog(this, total + " unoccupied slot removed successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "No unoccupied slot available to removed.");
                }
            }
        }

    }
}
