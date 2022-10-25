import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.ImageIcon;


class DateJOptionPane extends JFrame {
    private final static int width = 200;  // Default main window size
    private final static int height = 100;
        
    public DateJOptionPane() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        JButton button = new JButton("Select a Date");
        button.setBounds(20,20,120, 30);  // position & size
        button.addActionListener(event -> onButtonClick());
        add(button);                  
		
        setSize(width,height);
        setVisible(true); 
    }

    public void onButtonClick() {
        // Month
        JLabel month = new JLabel("Month");

        String[] monthArray = {"January", "February", "March",
                            "April",   "May",      "June",
                            "July",    "August",   "September",
                            "October", "November", "December"                            
        };
        JComboBox<String> months = new JComboBox<>(monthArray);

        // Day of the Month
        JLabel day = new JLabel("<HTML><br/>Day</HTML>");
        SpinnerModel range = new SpinnerNumberModel(1, 1, 31, 1);
        JSpinner days = new JSpinner(range);
               
        // Year
        JLabel year = new JLabel("<HTML><br/>Year 2022</HTML>");
        JSlider years = new JSlider(2022, 2042, 2022);
        years.addChangeListener(event -> 
            year.setText("<HTML><br/>Year " + years.getValue() + "</HTML>"));

        // Event
        JLabel event = new JLabel("<HTML><br/>Event</HTML>");
        JTextField events = new JTextField(20);

        // Display the dialog
        Object[] objects = {  // Array of widgets to display
            month, months, 
            day,   days, 
            year,  years, 
            event, events};
        int button = JOptionPane.showConfirmDialog( // Show the dialog
            this,
            objects,
            "Create Event",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if(button == JOptionPane.OK_OPTION)  // If OK clicked, show data
            JOptionPane.showMessageDialog(
                this, "<html>On "
              + (String) months.getSelectedItem() + " "
              + days.getValue() + ", " 
              + years.getValue() 
              + " is scheduled<br/>" + events.getText() + "</html>");
    }
    public static void main(String[] args) {
        new DateJOptionPane();
    }
}

