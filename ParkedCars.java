import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ParkedCars extends JPanel implements ActionListener {

    private JScrollPane jScrollPane1;
    private JTable slotTable;
    private JTextField searchField;
    private JButton searchButton;

    //Creating the carPark and initialize it with new instance of CarPark class
    private static CarPark carPark = CarPark.getInstance();

    public ParkedCars() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        // Create the main panel using GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add the title label to the panel
        JLabel labelTitle = new JLabel("Parked Cars", SwingConstants.CENTER);
        labelTitle.setFont(new Font("Serif", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(labelTitle, gbc);

        // Create the search panel
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(20);
        searchField.setPreferredSize(new Dimension(searchField.getPreferredSize().width, 28));
        searchButton = new JButton("Search");
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        gbc.gridy = 1;
        add(searchPanel, gbc);
        // Add action listeners to buttons
        searchButton.addActionListener(this);

        // Create the panel for the parking slot table
        JPanel panelTable = new JPanel(new BorderLayout());
        slotTable = new JTable();
        jScrollPane1 = new JScrollPane(slotTable);

        // Set the preferred height of the JScrollPane
        jScrollPane1.setPreferredSize(new Dimension(screenWidth - 300, 300));
        panelTable.add(jScrollPane1, BorderLayout.CENTER);

        // Add the table panel to the main panel
        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(panelTable, gbc);

        // Load the parking slots data into the table
        DefaultTableModel tableModel = new DefaultTableModel();
        // Add columns to the model:
        tableModel.addColumn("Car Registration Number");
        tableModel.addColumn("Car Owner Name");
        tableModel.addColumn("Car Type");
        tableModel.addColumn("Car Parked Time");

        for (Car car : carPark.getParkedCars()) {
            Vector<Object> rowData = new Vector<>();
            rowData.add(car.getRegistrationNumber());
            rowData.add(car.getOwnerName());
            rowData.add(car.isStaff() ? "Staff" : "Visitor");
            rowData.add(car.getParkingTimeLength());
            tableModel.addRow(rowData);
        }
        // Set the table model
        slotTable.setModel(tableModel);
    }

    public void actionPerformed(ActionEvent e) {
        if (searchField.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter the car registration no to search.");
        } else {
            ParkingSlot slot = carPark.findCarByRegistrationNumber(searchField.getText());
            if (slot == null) {
                JOptionPane.showMessageDialog(this, "Car of registration no " + searchField.getText() + " not found.");
            } else {
                DefaultTableModel dtm = new DefaultTableModel();
                slotTable.setModel(dtm);

                //Add column to the table:
                dtm.addColumn("Car Registration Number");
                dtm.addColumn("Car Owner Name");
                dtm.addColumn("Car Type");
                dtm.addColumn("Car Parked Time");
                Vector<Object> rowData = new Vector<Object>();
                rowData.add(searchField.getText());
                rowData.add(slot.getParkedCar().getOwnerName());
                rowData.add(slot.getParkedCar().isStaff() ? "Staff" : "Visitor");
                rowData.add(slot.getParkedCar().getParkingTimeLength());
                dtm.addRow(rowData);
                searchField.setText("");
            }
        }
    }
}
