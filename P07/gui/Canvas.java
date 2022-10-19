package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.InputMismatchException; 
import java.util.NoSuchElementException;

import java.io.File;
import java.io.IOException;

class Line {
    public Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    public int x1, y1, x2, y2;
}

public class Canvas extends JPanel {
    public Canvas(String imageFile, String dataFile) {
        setBorder(new EmptyBorder(0,0,0,0));
        
        // Load image
        try {
            image = ImageIO.read(new File(imageFile));
        } catch (IOException e) {
            System.err.println("Abort in About screen: Unable to load " + imageFile + "\n" + e);
        }
        
        // Load background vector image
        int filePosition = 1;
        minX = Integer.MAX_VALUE;   // Determines minimum point in X direction
        minY = Integer.MAX_VALUE;   // Determines minimum point in Y direction
        maxX = Integer.MIN_VALUE;
        maxY = Integer.MIN_VALUE;
        lines = new ArrayList<>();
        
        try {
            // Load vectors to draw, remembering minimum and maximum values (that is, the drawing size)
            Scanner scanner = new Scanner(new File(dataFile));
            while(scanner.hasNextInt()) {
                Line line = new Line(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
                minX = Math.min(minX, Math.min(line.x1, line.x2));
                minY = Math.min(minY, Math.min(line.y1, line.y2));
                maxX = Math.max(maxX, Math.max(line.x1, line.x2));
                maxY = Math.max(maxY, Math.max(line.y1, line.y2));
                lines.add(line);
                ++filePosition;
            }
            // Collections.shuffle(lines);  // Draw in random order for fun visual effects
                        
        } catch(IllegalStateException e) {
            System.err.println("Abort in splash screen:\nScanner closed unexpectedly at line " + filePosition + "\n" + e);
        } catch(InputMismatchException e) {
            System.err.println("Abort in splash screen:\nMalformed input at line " + filePosition + "\n" + e);
        } catch(NoSuchElementException e) {
            System.err.println("Abort in splash screen:\nPartial line detected at " + filePosition + "\n" + e);        
        } catch(Exception e) {
            System.err.println("Abort in splash screen:\n" + e);
        }
        
        panelSize = new Dimension(Math.max(image.getWidth(),  maxX-minX),
                                  Math.max(image.getHeight(), maxY-minY));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension((int) panelSize.getWidth(), (int) panelSize.getHeight());
    }
    /* Also available to control the layout
    
    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }
    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }
    */

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics); 
        Graphics2D g = (Graphics2D) graphics;
       
        //java.awt.Rectangle size = getBounds();        
        //g.drawRect(0, 0, size.width-1, size.height-1);
/*      // equivalent to
        g.drawLine(0, 0, size.width-1, 0);
        g.drawLine(size.width-1, 0, size.width-1, size.height-1);
        g.drawLine(size.width-1, size.height-1, 0, size.height-1);
        g.drawLine(0, size.height-1, 0, 0);
*/       
        // The JPanel will resize with the dialog, so manually center the drawings
        int offsetX = Math.max(getWidth()  - (int) panelSize.getWidth(),  0) / 2;
        int offsetY = Math.max(getHeight() - (int) panelSize.getHeight(), 0) / 2;
        
        Color[] colors = {Color.RED,  Color.ORANGE,  Color.PINK, Color.GREEN,
                          Color.BLUE, Color.MAGENTA};

        int lineCounter = 0;
        for(Line line : lines) {
            g.setColor(colors[lineCounter++ % colors.length]);
            g.drawLine(line.x1 - minX + offsetX, line.y1 - minY + offsetY, 
                       line.x2 - minX + offsetX, line.y2 - minY + offsetY);
        }

        // add image in center
        g.drawImage(image, (int) ((panelSize.getWidth()  - image.getWidth())  * 0.4) + offsetX, 
                           (int) ((panelSize.getHeight() - image.getHeight()) * 0.4) + offsetY, this);
                           
        // Add "artist's signature"
        g.setColor(Color.RED);
        g.drawString("©2022 Prof Rice", (int) (panelSize.getWidth()*0.7), 
                                        (int) (panelSize.getHeight()));
    }
    
    Dimension panelSize;
    private BufferedImage image;
    private ArrayList<Line> lines;
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;
    
}
