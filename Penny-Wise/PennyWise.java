import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/* 
?    Penny Wise is a Java app that helps manage personal 
?      finances by tracking income, expenses, and financial goals.
*/

// The PennyWise class implements ActionListener and MouseListener interfaces
public class PennyWise implements ActionListener, MouseListener {

    // Declaring GUI components as static fields for easy access
    private static JLabel pwtitle, userTitle, passTitle, MessRes, log, dateLabel, amountLabel, ammount, goalammount;
    private static JButton OkButton, goalbut;
    private static JPasswordField password;
    private static JTextField username, amountField, expenseField, dateField, expdateField, setgoal;
    private static JFrame frame;
    private static JTabbedPane tabbedPane;
    private static JProgressBar progbar;
    private static JButton progbut, expprogbut, logbut;
    private static DefaultTableModel tableModel;
    private JTable historyTable;
    private SimpleDateFormat dateFormat;

    private static JFrame editPanel;
    private ComparisonPanel comparisonPanel; // Comparison panel for income and expense visualization

    // Declaring constants for font, colors, and other properties
    private static final Font FONT = new Font("myFont", Font.PLAIN, 20);
    private static final Color LIGHT_RED = new Color(255, 137, 141);
    private static final Color DARK_RED = new Color(212, 0, 0);
    private static final Color DARK_GREEN = new Color(2,113,72);
    private static final LineBorder border = new LineBorder(DARK_RED, 2);
    private static final Border underlineBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, DARK_RED);
    private static double GOAL = 0.0;
    private static final DecimalFormat decfor = new DecimalFormat("0.00");  

    // ImageIcon for the logo and background
    private static final ImageIcon logo = new ImageIcon("Untitled_design__1_-removebg-preview.png");
    private static final Image resize = logo.getImage().getScaledInstance(50, 65, Image.SCALE_SMOOTH);  // Adjust size as needed
    private static final ImageIcon logoo = new ImageIcon(resize);

    private static final ImageIcon background = new ImageIcon("71252046-abstract-red-blurred-vector-portrait-background.png");
    private static final Image backgroundImage = background.getImage();

    private static final ImageIcon memelike = new ImageIcon("343249662_254006320370369_7025729033798750623_n-removebg-preview.png");
    private static final Image resizelike = memelike.getImage().getScaledInstance(70, 45, Image.SCALE_SMOOTH);  // Adjust size as needed
    private static final ImageIcon like = new ImageIcon(resizelike);

    private static final ImageIcon memeskeri = new ImageIcon("346099725_202283579320925_8805223696508504620_n-removebg-preview.png");
    private static final Image resizeskeri = memeskeri.getImage().getScaledInstance(70, 55, Image.SCALE_SMOOTH);  // Adjust size as needed
    private static final ImageIcon skeri = new ImageIcon(resizeskeri);

    private static final ImageIcon memewoah = new ImageIcon("354036772_997086104627408_3814402219805205023_n-removebg-preview.png");
    private static final Image resizewoah = memewoah.getImage().getScaledInstance(75, 55, Image.SCALE_SMOOTH);  // Adjust size as needed
    private static final ImageIcon woah = new ImageIcon(resizewoah);

    private static final ImageIcon mainpanelbg = new ImageIcon("WELCOME USER (7).png");
    private static final Image mapabg = mainpanelbg.getImage();

    private static final ImageIcon panelbg = new ImageIcon("2.png");
    private static final Image pabg = panelbg.getImage();


    // Main method to start the program ------------------------------------------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PennyWise().createAndShowGUI());
    }

    // Method to create and display the GUI components ----------------------------------------------
    private void createAndShowGUI() {
        editPanel();
        editshow(false);

        LoginPanel();
        showmain(true);
    }

    // Method to create the login panel ------------------------------------------------------------
    private void LoginPanel() {
        frame = new JFrame("Login");
        frame.setSize(520, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Creating a layered pane for overlapping components
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 520, 700);

        // Creating a custom panel with a background image
        CustomPanel mainPanel = new CustomPanel();
        mainPanel.setBounds(0, 0, 520, 700);
        mainPanel.setLayout(null);
        mainPanel.setBorder(border);

        // Adding a logo to the login panel
        log = new JLabel();
        log.setBounds(215, 20, 170, 180);
        log.setIcon(logoo);
        
        layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(log, JLayeredPane.PALETTE_LAYER); 

        // PennyWise title
        pwtitle = new JLabel("Penny Wise");
        pwtitle.setFont(FONT);
        pwtitle.setForeground(Color.WHITE);
        pwtitle.setBounds(195, 600, 140, 20);
        mainPanel.add(pwtitle);

        // User Title
        userTitle = new JLabel("Username:");
        userTitle.setFont(FONT);
        userTitle.setForeground(DARK_RED);
        userTitle.setBounds(70, 202, 140, 20);
        mainPanel.add(userTitle);

        // Username Field
        username = new JTextField();
        username.setFont(FONT);
        username.setForeground(DARK_RED);
        username.setBounds(190, 200, 245, 28);
        username.setBorder(border);
        mainPanel.add(username);

        // Password
        passTitle = new JLabel("Password:");
        passTitle.setFont(FONT);
        passTitle.setForeground(DARK_RED);
        passTitle.setBounds(70, 272, 140, 20);
        mainPanel.add(passTitle);

        // Password Field
        password = new JPasswordField();
        password.setFont(FONT);
        password.setForeground(DARK_RED);
        password.setBounds(190, 270, 245, 28);
        password.setBorder(border);
        mainPanel.add(password);

        // Message to display result of login
        MessRes = new JLabel();
        MessRes.setBounds(180, 320, 230, 25);
        MessRes.setFont(FONT);
        mainPanel.add(MessRes);

        // Ok Button
        OkButton = new JButton("Log in");
        OkButton.setBounds(70, 350, 365, 40);
        OkButton.setFont(FONT);
        OkButton.setForeground(Color.WHITE);
        OkButton.setBackground(Color.red);
        OkButton.setOpaque(true);
        mainPanel.add(OkButton);
        OkButton.addActionListener(this);
        OkButton.addMouseListener(this);

        frame.add(layeredPane);
        frame.setVisible(true);
    }

    // Method to create the user edit panel --------------------------------------------------------------
    private void editPanel() {
        editPanel = new JFrame("User Edit");
        editPanel.setSize(520, 700);
        editPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editPanel.setResizable(false);

        // Creating a tabbed pane for organizing multiple panels
        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(LIGHT_RED);
        tabbedPane.setForeground(Color.black);
        tabbedPane.setFont(FONT);

        // Creating the main panel for user-related actions
        mainPanelBG mainPanel = new mainPanelBG();
        tabbedPane.addTab("User", mainPanel);
        mainPanel.setLayout(null);

        // Adding Balance
        ammount = new JLabel("0.00");
        ammount.setFont(new Font("myFont", Font.PLAIN, 32));
        ammount.setBounds(-17, 108, 550, 100);
        ammount.setHorizontalAlignment(JLabel.CENTER);
        ammount.setForeground(DARK_RED);
        mainPanel.add(ammount);

        // Your current goal balance
        goalammount = new JLabel();
        goalammount.setFont(new Font("myFont", Font.PLAIN, 22));
        goalammount.setBounds(-17, 231, 550, 100);
        goalammount.setHorizontalAlignment(JLabel.CENTER);
        goalammount.setForeground(DARK_RED);
        mainPanel.add(goalammount);

        // Progress BaR
        GOAL = 0;
        progbar = new JProgressBar(0, (int)GOAL);
        progbar.setValue(0);
        progbar.setBounds(110, 214, 290, 37);
        progbar.setForeground(DARK_RED);
        progbar.setOpaque(false);
        progbar.setBorder(border);
        progbar.setStringPainted(true);
        updateProgressBarString();
        mainPanel.add(progbar);

        // Set Goal Button
        goalbut = new JButton("Set Goal");
        goalbut.setBounds(205, 357, 90, 20);
        goalbut.setBackground(Color.red);
        goalbut.setForeground(Color.white);
        goalbut.setOpaque(true);
        goalbut.addActionListener(this);
        goalbut.addMouseListener(this);
        mainPanel.add(goalbut);

        // Set Goal
        setgoal = new JTextField();
        setgoal.setBounds(130, 322, 240,30);
        setgoal.setOpaque(false);
        setgoal.setFont(FONT);
        setgoal.setBorder(underlineBorder);
        setgoal.setHorizontalAlignment(JTextField.CENTER);
        mainPanel.add(setgoal);

        // Log out
        logbut = new JButton("Log Out");
        logbut.setBounds(212, 557, 80, 15);
        logbut.setBackground(Color.red);
        logbut.setForeground(Color.white);
        logbut.setOpaque(true);
        logbut.addActionListener(this);
        logbut.addMouseListener(this);
        mainPanel.add(logbut);

        // Add Income Panel -----------------------------------
        PanelBG addIncPanel = new PanelBG();
        tabbedPane.addTab("Add Income", addIncPanel);
        addIncPanel.setLayout(null);

        // Amount Label
        amountLabel = new JLabel("Amount:");
        amountLabel.setFont(FONT);
        amountLabel.setBounds(60, 100, 100, 20);
        addIncPanel.add(amountLabel);

        // Amount Field
        amountField = new JTextField();
        amountField.setFont(FONT);
        amountField.setBounds(180, 100, 245, 28);
        amountField.setBorder(border);
        addIncPanel.add(amountField);

        // Date Label
        dateLabel = new JLabel("Date:");
        dateLabel.setFont(FONT);
        dateLabel.setBounds(60, 150, 100, 20);
        addIncPanel.add(dateLabel);

        // Date Field
        dateField = new JTextField();
        dateFormat = new SimpleDateFormat("MM/dd/yy hh:mm");
        dateField.setText(dateFormat.format(new Date()));
        dateField.setFont(FONT);
        dateField.setBounds(180, 150, 245, 28);
        dateField.setEditable(false);
        dateField.setBackground(Color.white);
        dateField.setBorder(border);
        addIncPanel.add(dateField);

        // Progress Button
        progbut = new JButton("Add Income");
        progbut.setBounds(175, 200, 120, 30);
        progbut.setBackground(Color.red);
        progbut.setForeground(Color.white);
        progbut.setOpaque(true);
        progbut.addActionListener(this);
        progbut.addMouseListener(this);
        addIncPanel.add(progbut);

        // Add expense panel --------------------------------
        PanelBG addExpPanel = new PanelBG();
        tabbedPane.addTab("Add Expense", addExpPanel);
        addExpPanel.setLayout(null);
        
        // Amount Label
        JLabel expamountLabel = new JLabel("Amount:");
        expamountLabel.setFont(FONT);
        expamountLabel.setBounds(60, 100, 100, 20);
        addExpPanel.add(expamountLabel);

        // Expense Field
        expenseField = new JTextField();
        expenseField.setFont(FONT);
        expenseField.setBounds(180, 100, 245, 28);
        expenseField.setBorder(border);
        addExpPanel.add(expenseField);

        // Date Label
        JLabel expdateLabel = new JLabel("Date:");
        expdateLabel.setFont(FONT);
        expdateLabel.setBounds(60, 150, 100, 20);
        addExpPanel.add(expdateLabel);

        // Date Field
        expdateField = new JTextField();
        dateFormat = new SimpleDateFormat("MM/dd/yy hh:mm");
        expdateField.setText(dateFormat.format(new Date()));
        expdateField.setFont(FONT);
        expdateField.setBounds(180, 150, 245, 28);
        expdateField.setEditable(false);
        expdateField.setBackground(Color.white);
        expdateField.setBorder(border);
        addExpPanel.add(expdateField);

        // Add Expense Button
        expprogbut = new JButton("Add Expense");
        expprogbut.setBounds(175, 200, 120, 30);
        expprogbut.setBackground(Color.red);
        expprogbut.setForeground(Color.white);
        expprogbut.setOpaque(true);
        expprogbut.addActionListener(this);
        expprogbut.addMouseListener(this);
        addExpPanel.add(expprogbut);

        PanelBG historyPanel = new PanelBG();
        tabbedPane.addTab("History", historyPanel);

        // Create the table model for the history panel
        tableModel = new DefaultTableModel(new Object[]{"Name", "Amount", "Date"}, 0);
        historyTable = new JTable(tableModel);
        historyTable.setFont(new Font("myFont", Font.PLAIN, 18));

        // Scroll Pane
        JScrollPane historyScrollPane = new JScrollPane(historyTable);
        historyScrollPane.setBounds(60, 100, 400, 200);
        historyPanel.add(historyScrollPane);

        // Comparison Panel ---------------------------------
        comparisonPanel = new ComparisonPanel();
        comparisonPanel.setBounds(60, 450, 400, 50);
        historyPanel.add(comparisonPanel);

        editPanel.add(tabbedPane);
        editPanel.setVisible(true);
    }

    // Method to show or hide the user edit panel
    private void editshow(boolean show) {
        editPanel.setVisible(show);
    }

    // Method to show or hide the login panel
    private void showmain(boolean show) {
        frame.setVisible(show);
    }
    
    // Method to validate username and password
    private boolean UserPass(String user, String pass) {
        return (user.equals("riki") && pass.equals("123")) ||
               (user.equals("") && pass.equals("")) ||
               (user.equals("asher") && pass.equals("456"));
    }

    // Custom JPanel class with background image
    class CustomPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // Custom JPanel class with background image
    class mainPanelBG extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(mapabg, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // Custom JPanel class with background image
    class PanelBG extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(pabg, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // Method to update the progress bar string -----------------------------------------------------
    private void updateProgressBarString() {
        int currentValue = progbar.getValue();

        goalammount.setText(String.format("%d / %d", currentValue, (int)GOAL));
    }

    // Bar Graph ------------------------------------------------------------------------------------
    class ComparisonPanel extends JPanel {
        private double income = 0;
        private double expense = 0;
    
        JLabel incomeText;
        JLabel expenseText;
        GoalPanel goalPanel;
    
        public ComparisonPanel() {
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(400, 100));
            setOpaque(false);
    
            incomeText = new JLabel();
            incomeText.setFont(new Font("Arial", Font.BOLD, 18));
            add(incomeText, BorderLayout.NORTH);
    
            expenseText = new JLabel();
            expenseText.setFont(new Font("Arial", Font.BOLD, 18));
            add(expenseText, BorderLayout.SOUTH);
    
            goalPanel = new GoalPanel();
            goalPanel.setOpaque(false);
            goalPanel.setBorder(new LineBorder(Color.BLACK, 1));
            add(goalPanel, BorderLayout.CENTER);
    
            updateLabels();
        }
    
        private void updateLabels() {
            double total = income;
            double incomePercentage = (income - expense) / total * 100;
            double expensePercentage = expense / total * 100;
            String formatincome = decfor.format(income);
            String formatexpense = decfor.format(expense);
            String formatincomePercent = decfor.format(incomePercentage);
            String formatexpensePercent = decfor.format(expensePercentage);
    
            incomeText.setText("Income: ₱ " + formatincome + " (" + formatincomePercent + "%)");
            incomeText.setForeground(DARK_GREEN);
    
            expenseText.setText("Expense: ₱ " + formatexpense + " (" + formatexpensePercent + "%)");
            expenseText.setForeground(DARK_RED);
    
            goalPanel.setIncome(incomePercentage);
            goalPanel.setExpense(expensePercentage);
        }

        public void updateIncome(double amount) {
            income += amount;
            updateLabels();
        }
    
        public void updateExpense(double amount) {
            expense += amount;
            updateLabels();
        }
    }

    class GoalPanel extends JPanel {
        private double income = 0;
        private double expense = 0;
    
        public GoalPanel() {
            setPreferredSize(new Dimension(400, 50));
        }
    
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
    
            double total = income + expense; // Total ng income at expense
            if (total > 0) {
                // Compute widths based on percentage
                int incomeWidth = (int) ((double) income / total * getWidth());
                int expenseWidth = (int) ((double) expense / total * getWidth());

                // Draw income bar
                g.setColor(DARK_GREEN);
                g.fillRect(0, 0, incomeWidth, getHeight());
    
                // Draw expense bar
                g.setColor(DARK_RED);
                g.fillRect(incomeWidth, 0, expenseWidth, getHeight());
            }
        }

        public void setIncome(double income) {
            this.income = income * getWidth() / 100; // Update income based on percentage of panel width
            repaint();
        }
    
        public void setExpense(double expense) {
            this.expense = expense * getWidth() / 100; // Update expense based on percentage of panel width
            repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == OkButton) {
            // Handle login button
            String user = username.getText();
            String pass = new String(password.getPassword());
    
            if (UserPass(user, pass)) {
                editshow(true);
                showmain(false);
                editPanel.setTitle(user + " Panel");
            } else {
                MessRes.setText("User Not Detected!");
                MessRes.setForeground(Color.RED);
            }
        } else if (e.getSource() == progbut) {
            // Handle Add Income button
            String amountStr = amountField.getText();
            if (!amountStr.isEmpty()) {
                try {
                    double amount = Double.parseDouble(amountStr);
        
                    // Update comparison panel
                    comparisonPanel.updateIncome(amount);
        
                    // Add the amount and date to the history table
                    String date = dateField.getText();
                    tableModel.addRow(new Object[]{"Income", amount, date});
        
                    // Update user balance
                    String amountText = ammount.getText();
                    double balance = Double.parseDouble(amountText);
                    double userBalance = balance + amount;
                    String formattedAmount = decfor.format(userBalance);
                    ammount.setText(formattedAmount);
        
                    // Update progress bar
                    double currentValue = progbar.getValue();
                    double newValue = currentValue + amount;
        
                    // Check if the goal is reached
                    if (GOAL > 0 && newValue >= GOAL || GOAL % 1 > 0 && newValue % 1 >= GOAL) {
                        JOptionPane.showMessageDialog(null, "You've reached your goal!", "Congratulations!", JOptionPane.INFORMATION_MESSAGE, like);
                        GOAL = 0; // Reset the goal after reaching it
                        progbar.setValue((int) GOAL); // Set progress bar to goal value
                        updateProgressBarString(); // Update progress bar string after resetting goal
                    } else {
                        // Only update progress bar if goal is not reached
                        if (newValue <= GOAL) {
                            progbar.setValue((int) newValue);
                            updateProgressBarString();
                        }
                    }
        
                    // Clear input fields
                    amountField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number for amount.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(editPanel, "Please enter an Income amount.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == expprogbut) {
            // Handle Add Expense button
            String amountStr = expenseField.getText();
            if (!amountStr.isEmpty()) {
                try {
                double amount = Double.parseDouble(amountStr);

                String amountText = ammount.getText();
                double balance = Double.parseDouble(amountText);
                double userBalance = balance - amount;

            // Validate user balance and adjust if necessary
            if (userBalance < 0 || (userBalance % 1 != userBalance && userBalance < 1)) {
                userBalance = balance;
                }

            String formattedAmount = decfor.format(userBalance);
            ammount.setText(formattedAmount);

            // Check if expense exceeds available amount
            if (amount > balance) {
                JOptionPane.showMessageDialog(editPanel, "Expense amount cannot exceed available amount.");
                expenseField.setText("");
                return;
            }

            // Update comparison panel
            comparisonPanel.updateExpense(amount);

            // Add the amount and date to the history table
            String date = expdateField.getText();
            tableModel.addRow(new Object[]{"Expense", amount, date});

            // Update progress bar
            double currentValue = progbar.getValue();
            double newValue = currentValue - amount;

            // Only update progress bar if goal is not reached
            if (newValue >= 0) {
                progbar.setValue((int) newValue);
                updateProgressBarString();
            } else {
                progbar.setValue(0); // Ensure progress bar does not go negative
                updateProgressBarString();
            }

            // Check if the expense exceed the goal
            if (GOAL > 0 && newValue <= 0) {
                JOptionPane.showMessageDialog(null, "You did not reach your goal, be wise and try again.", "You Failed!", JOptionPane.INFORMATION_MESSAGE, skeri);
                GOAL = 0; // Reset the goal after reaching it
                progbar.setValue((int) GOAL); // Set progress bar to goal value
                updateProgressBarString(); // Update progress bar string after resetting goal
            }

            // Clear input fields
            expenseField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid number for amount.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
                } else {
                    JOptionPane.showMessageDialog(editPanel, "Please enter an Expense amount.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    }
        } else if (e.getSource() == goalbut) {
            // Handle Set Goal button
            String goalStr = setgoal.getText().trim();
            if (!goalStr.isEmpty()) {
                try {
                    double goalDouble = Double.parseDouble(goalStr);
    
                    // Update the goal in the progress bar
                    GOAL = (int) goalDouble; // Update the global GOAL variable
                    progbar.setMaximum((int)GOAL); // Update the progress bar maximum
                    updateProgressBarString(); // Update progress bar string with new goal
    
                    JOptionPane.showMessageDialog(null, "Goal set successfully!", "WOAH!", JOptionPane.INFORMATION_MESSAGE, woah);
                    setgoal.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(editPanel, "Please enter a valid number for the goal.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(editPanel, "Please enter a goal amount.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == logbut) {
            editshow(false);
            showmain(true);
        }
    }      

    // Mouse event handling for button hover effect ------------------------------------------------------------
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof JButton button) {
            button.setBackground(Color.RED);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() instanceof JButton button) {
            button.setBackground(Color.RED);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof JButton button) {
            button.setBackground(DARK_RED);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof JButton button) {
            button.setBackground(Color.RED);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof JButton button) {
            button.setBackground(Color.RED);
        }
    }
    
}
