 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    private JPanel contentPane;
    private JPanel mainPanel;
    private JPanel sidePanel;

    public MainMenu() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        Color bgColor = new Color(54, 70, 78);
        Font font = new Font("Calibri", Font.BOLD, 16);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(screenWidth, screenHeight);
        setTitle("Car Parking Management System");

        // Create the content pane and set the layout
        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        // Create the main panel and add it to the content pane
        mainPanel = new JPanel();
        contentPane.add(mainPanel, BorderLayout.CENTER);

        // Create the side panel and add it to the content pane
        sidePanel = new JPanel(new GridLayout(20, 1));
        sidePanel.setPreferredSize(new Dimension(200, screenHeight));
        sidePanel.setBackground(bgColor);
        contentPane.add(sidePanel, BorderLayout.LINE_START);

        // Create the menu items and add them to the side panel
        // ===================================== DASHBOARD MENU ITEM ==================================        
        JMenuItem dashboard = new JMenuItem("Dashboard");
        dashboard.setFont(new Font("Calibri", Font.BOLD, 16));
        dashboard.setBackground(bgColor);
        dashboard.setMargin(new Insets(10, 10, 0, 0));
        dashboard.setHorizontalTextPosition(JMenuItem.CENTER);
        dashboard.setHorizontalAlignment(JLabel.LEADING);
        dashboard.setForeground(Color.WHITE);
//        dashboard.setIcon(new ImageIcon(getClass().getResource("/icons/home.png")));
//        dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home.png"))); // NOI18N

        dashboard.addActionListener((ActionEvent e) -> {
            // Code to execute when item 1 is clicked
            Dashboard dashboardPanel = new Dashboard();
            mainPanel.removeAll(); // remove any existing components
            mainPanel.add(dashboardPanel); // add the new panel
            mainPanel.revalidate(); // refresh the layout
            mainPanel.repaint(); // repaint the panel
        });
        sidePanel.add(dashboard);

        // ===================================== PARKING SLOTS MENU ITEM ==================================        
        JMenuItem parkingSlot = new JMenuItem("Parking Slots");
        parkingSlot.setFont(new Font("Calibri", Font.BOLD, 16));
        parkingSlot.setBackground(bgColor);
        parkingSlot.setMargin(new Insets(10, 10, 0, 0));
        parkingSlot.setHorizontalAlignment(JMenuItem.LEADING);
        parkingSlot.setForeground(Color.WHITE);

        parkingSlot.addActionListener((ActionEvent e) -> {
            ParkingSlots slots = new ParkingSlots();
            mainPanel.removeAll(); // remove any existing components
            mainPanel.add(slots); // add the new panel
            mainPanel.revalidate(); // refresh the layout
            mainPanel.repaint(); // repaint the panel
        });
        sidePanel.add(parkingSlot);

        // ===================================== ADD PARKING SLOT MENU ITEM ==================================        
        JMenuItem addSlot = new JMenuItem("Add Parking Slot");
        addSlot.setFont(new Font("Calibri", Font.BOLD, 16));
        addSlot.setBackground(bgColor);
        addSlot.setMargin(new Insets(10, 10, 0, 0));
        addSlot.setHorizontalAlignment(JMenuItem.LEADING);
        addSlot.setForeground(Color.WHITE);

        addSlot.addActionListener((ActionEvent e) -> {
            AddSlot newSlot = new AddSlot();
            mainPanel.removeAll(); // remove any existing components
            mainPanel.add(newSlot); // add the new panel
            mainPanel.revalidate(); // refresh the layout
            mainPanel.repaint(); // repaint the panel
        });
        sidePanel.add(addSlot);

        // ===================================== DELETE PARKING SLOT MENU ITEM ==================================        
        JMenuItem deleteSlot = new JMenuItem("Delete Parking Slot");
        deleteSlot.setFont(new Font("Calibri", Font.BOLD, 16));
        deleteSlot.setBackground(bgColor);
        deleteSlot.setMargin(new Insets(10, 10, 0, 0));
        deleteSlot.setHorizontalAlignment(JMenuItem.LEADING);
        deleteSlot.setForeground(Color.WHITE);

        deleteSlot.addActionListener((ActionEvent e) -> {
            DeleteSlot removeSlot = new DeleteSlot();
            mainPanel.removeAll(); // remove any existing components
            mainPanel.add(removeSlot); // add the new panel
            mainPanel.revalidate(); // refresh the layout
            mainPanel.repaint(); // repaint the panel
        });
        sidePanel.add(deleteSlot);

        // ===================================== GET ALL PARKED CARS ==================================        
        JMenuItem parkedCars = new JMenuItem("All Parked Cars");
        parkedCars.setFont(new Font("Calibri", Font.BOLD, 16));
        parkedCars.setBackground(bgColor);
        parkedCars.setMargin(new Insets(10, 10, 0, 0));
        parkedCars.setHorizontalAlignment(JMenuItem.LEADING);
        parkedCars.setForeground(Color.WHITE);

        parkedCars.addActionListener((ActionEvent e) -> {
            ParkedCars parkedCar = new ParkedCars();
            mainPanel.removeAll(); // remove any existing components
            mainPanel.add(parkedCar); // add the new panel
            mainPanel.revalidate(); // refresh the layout
            mainPanel.repaint(); // repaint the panel
        });
        sidePanel.add(parkedCars);

        // ===================================== PARK A NEW CAR MENU ITEM ==================================        
        JMenuItem parkCar = new JMenuItem("Park Car");
        parkCar.setFont(new Font("Calibri", Font.BOLD, 16));
        parkCar.setBackground(bgColor);
        parkCar.setMargin(new Insets(10, 10, 0, 0));
        parkCar.setHorizontalAlignment(JMenuItem.LEADING);
        parkCar.setForeground(Color.WHITE);

        parkCar.addActionListener((ActionEvent e) -> {
            ParkCar parkNewCar = new ParkCar();
            mainPanel.removeAll(); // remove any existing components
            mainPanel.add(parkNewCar); // add the new panel
            mainPanel.revalidate(); // refresh the layout
            mainPanel.repaint(); // repaint the panel
        });
        sidePanel.add(parkCar);
        
           // ===================================== DELETE PARKING CAR MENU ITEM ==================================        
        JMenuItem deleteParkedCar = new JMenuItem("Remove Car");
        deleteParkedCar.setFont(new Font("Calibri", Font.BOLD, 16));
        deleteParkedCar.setBackground(bgColor);
        deleteParkedCar.setMargin(new Insets(10, 10, 0, 0));
        deleteParkedCar.setHorizontalAlignment(JMenuItem.LEADING);
        deleteParkedCar.setForeground(Color.WHITE);

        deleteParkedCar.addActionListener((ActionEvent e) -> {
            DeleteCar removeCar = new DeleteCar();
            mainPanel.removeAll(); // remove any existing components
            mainPanel.add(removeCar); // add the new panel
            mainPanel.revalidate(); // refresh the layout
            mainPanel.repaint(); // repaint the panel
        });
        sidePanel.add(deleteParkedCar);

    }

    public static void main(String[] args) {
        MainMenu example = new MainMenu();
        example.setVisible(true);
    }
}
