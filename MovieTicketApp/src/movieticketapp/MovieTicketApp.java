/*
 * This class represents a Movie Ticket Application.
 *--------------------------------------------------

 * It allows users to select a movie, enter ticket prices and counts,
 * and generate a sales report.
 * The application uses a graphical user interface (GUI) built with Swing.
 */
package movieticketapp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieTicketApp extends JFrame {
    private JComboBox<String> movieComboBox;
    private JTextField ticketPriceField, ticketCountField;
    private JTextArea reportArea;
    private MovieTickets movieTickets;

    public MovieTicketApp() {
        movieTickets = new MovieTickets();
        setTitle("Movie Ticket Application");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        
        // Set background color
        getContentPane().setBackground(new Color(0x282A36)); // Dark theme background

        // Panel for inputs with spacing and custom background
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        inputPanel.setBackground(new Color(0x44475A)); // Slightly lighter than the main background
        
        // Customizing Labels
        JLabel movieLabel = new JLabel("Select Movie:");
        JLabel priceLabel = new JLabel("Ticket Price:");
        JLabel countLabel = new JLabel("Number of Tickets:");
        styleLabel(movieLabel);
        styleLabel(priceLabel);
        styleLabel(countLabel);
        
        // ComboBox for movie selection with custom UI
        movieComboBox = new JComboBox<>(new String[]{"Napoleon", "Oppenheimer", "Damsel"});
        movieComboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = super.createArrowButton();
                button.setBackground(new Color(0x6272A4));
                return button;
            }
        });
        movieComboBox.setBackground(new Color(0x6272A4));
        movieComboBox.setForeground(Color.WHITE);
        movieComboBox.setFocusable(false);

        // Text fields for ticket price and count
        ticketPriceField = new JTextField();
        ticketCountField = new JTextField();
        styleTextField(ticketPriceField);
        styleTextField(ticketCountField);

        inputPanel.add(movieLabel);
        inputPanel.add(movieComboBox);
        inputPanel.add(priceLabel);
        inputPanel.add(ticketPriceField);
        inputPanel.add(countLabel);
        inputPanel.add(ticketCountField);

        // Text area for the report with a custom scroll bar
        reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setLineWrap(true);
        reportArea.setWrapStyleWord(true);
        reportArea.setBackground(new Color(0x44475A));
        reportArea.setForeground(Color.WHITE);
        reportArea.setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane reportScrollPane = new JScrollPane(reportArea);
        reportScrollPane.setBorder(BorderFactory.createEmptyBorder());
        reportScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(0x6272A4);
            }
        });

        // Menu
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(0x282A36));
        menuBar.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        // File menu
        JMenu fileMenu = createMenu("File");
        @SuppressWarnings("unused")
        JMenuItem exitItem = createMenuItem("Exit", e -> System.exit(0));
        fileMenu.add(exitItem);
        
        // Tools menu
        JMenu toolsMenu = createMenu("Tools");
        JMenuItem processItem = createMenuItem("Process", new ProcessAction());
        JMenuItem clearItem = createMenuItem("Clear", new ClearAction());
        
        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);
        
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);

        // Add components to frame
        add(inputPanel, BorderLayout.NORTH);
        add(reportScrollPane, BorderLayout.CENTER);
    }

    private void styleLabel(JLabel label) {
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SansSerif", Font.PLAIN, 12));
    }

    private void styleTextField(JTextField textField) {
        textField.setBackground(new Color(0x6272A4));
        textField.setForeground(Color.WHITE);
        textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        textField.setCaretColor(Color.WHITE);
    }

    private JMenu createMenu(String title) {
        JMenu menu = new JMenu(title);
        menu.setForeground(Color.WHITE);
        return menu;
    }

    private JMenuItem createMenuItem(String title, ActionListener actionListener) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.setForeground(Color.WHITE);
        menuItem.setBackground(new Color(0x282A36));
        menuItem.addActionListener(actionListener);
        return menuItem;
    }

    private class ProcessAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String movieName = (String) movieComboBox.getSelectedItem();
            String priceText = ticketPriceField.getText();
            String countText = ticketCountField.getText();

            try {
                double ticketPrice = Double.parseDouble(priceText);
                int ticketCount = Integer.parseInt(countText);

                MovieTicketData ticketData = new MovieTicketData(movieName, ticketCount, ticketPrice);

                if (movieTickets.ValidateData(ticketData)) {
                    double totalPrice = movieTickets.CalculateTotalTicketPrice(ticketCount, ticketPrice);
                    reportArea.setText("dylan Gorrah:\n");
                    reportArea.append("Movie: " + movieName + "\n");
                    reportArea.append("Ticket Price: R" + ticketPrice + "\n");
                    reportArea.append("Number of Tickets: " + ticketCount + "\n");
                    reportArea.append("Total Price (with VAT): R" + totalPrice + "\n");
                } else {
                    JOptionPane.showMessageDialog(MovieTicketApp.this, "Invalid input. Please check the values entered.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(MovieTicketApp.this, "Please enter valid numbers for ticket price and count.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ClearAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            movieComboBox.setSelectedIndex(0);
            ticketPriceField.setText("");
            ticketCountField.setText("");
            reportArea.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MovieTicketApp().setVisible(true));
    }
}
