import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;


// <a href="https://www.flaticon.com/free-icons/next" title="next icons">Next icons created by Pixel perfect - Flaticon</a>

public class Path extends JFrame {
    public static final int WIDTH  = 400;
    public static final int HEIGHT = 300;
    public static final int CANVAS_WIDTH  = (int) (0.8 * WIDTH);
    public static final int CANVAS_HEIGHT = (int) (0.8 * HEIGHT);
    
    public static final int STEP   =  10;
    
    public Path() {
        super("Draw a Path");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);

        // Create Menu
        JMenuBar menubar = new JMenuBar();
        
        JMenu     file       = new JMenu("File");
        JMenuItem open       = new JMenuItem("Open");
        JMenuItem save       = new JMenuItem("Save");
        JMenuItem quit       = new JMenuItem("Quit");

        open.addActionListener(event -> onOpenClick());
        save.addActionListener(event -> onSaveClick());
        quit.addActionListener(event -> System.exit(0));
        
        file.add(open);
        file.add(save);
        file.add(quit);
        
        menubar.add(file);
        setJMenuBar(menubar);
        
        // Create Toolbar
        
        JToolBar toolbar = new JToolBar("Nim Controls");
        JPanel buttons = new JPanel();

        // Add control buttons
        JButton left  = new JButton(new ImageIcon("left.png"));
          left.setToolTipText("Draw left " + STEP + " pixels");
          buttons.add(left);
          left.addActionListener(event -> moveLeft());
          
        JButton up  = new JButton(new ImageIcon("up.png"));
          up.setToolTipText("Draw up " + STEP + " pixels");
          buttons.add(up);
          up.addActionListener(event -> moveUp());
          
        JButton down  = new JButton(new ImageIcon("down.png"));
          down.setToolTipText("Draw down " + STEP + " pixels");
          buttons.add(down);
          down.addActionListener(event -> moveDown());
          
        JButton right  = new JButton(new ImageIcon("right.png"));
          right.setToolTipText("Draw right " + STEP + " pixels");
          buttons.add(right);
          right.addActionListener(event -> moveRight());
          
        toolbar.add(buttons);
        getContentPane().add(toolbar, BorderLayout.PAGE_START);
        
        // Add canvas and start drawing in the center of it
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        x = CANVAS_WIDTH  / 2;
        y = CANVAS_HEIGHT / 2;
        canvas.addPoint(x, y);
        getContentPane().add(canvas, BorderLayout.CENTER);
        
        // Acknowledge the icons
        JLabel msg = new JLabel("<html><size=-2>Next icons created by Pixel perfect - Flaticon: https://www.flaticon.com/free-icons/next</size></html>");
        getContentPane().add(msg, BorderLayout.PAGE_END);
        
        // Display window and let's go!
        setVisible(true);
    }
    
    private void moveLeft() {
        x -= STEP;
        canvas.addPoint(x, y);
    }
    private void moveRight() {
        x += STEP;
        canvas.addPoint(x, y);
    }
    private void moveUp() {
        y -= STEP;
        canvas.addPoint(x, y);
    }
    private void moveDown() {
        y += STEP;
        canvas.addPoint(x, y);
    }
    
    private void onSaveClick() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("untitled.path"))) {
            canvas.save(bw);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Unable to Save", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void onOpenClick() {
        try (BufferedReader br = new BufferedReader(new FileReader("untitled.path"))) {
            canvas.open(br);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Unable to Open", JOptionPane.ERROR_MESSAGE);
            canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        }        
    }
    
    private Canvas canvas;
    private int x;
    private int y;
    
    public static void main(String[] args) {
        new Path();
    }
}
        

