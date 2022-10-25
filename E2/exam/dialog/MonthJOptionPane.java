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


class MonthJOptionPane extends JFrame {
    private final static int width = 200;  // Default main window size
    private final static int height = 100;
        
    public MonthJOptionPane() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        JButton button = new JButton("Pick a Month");
        button.setBounds(20,20,120, 30);  // position & size
        button.addActionListener(event -> onButtonClick());
        add(button);                  
		
        setSize(width,height);
        setVisible(true); 
    }

    public void onButtonClick() {
        String[] monthArray = {"January", "February", "March",
                               "April",   "May",      "June",
                               "July",    "August",   "September",
                               "October", "November", "December"                            
        };
        JComboBox<String> months = new JComboBox<>(monthArray);
        int button = JOptionPane.showConfirmDialog( // Show the dialog
            this,
            months,
            "Pick a Month",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if(button == JOptionPane.OK_OPTION)
            JOptionPane.showMessageDialog(this, 
                "You picked " + months.getSelectedItem());

    }
    public static void main(String[] args) {
        new MonthJOptionPane();
    }
}

