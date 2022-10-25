import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;

import java.util.ArrayList;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class Canvas extends JPanel {

    private class Point {
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        int x;
        int y;
    }

    private ArrayList<Point> points = new ArrayList<>();

    public Canvas(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.WHITE);
    }
    public void open(BufferedReader br) throws IOException {
        points.clear();
        String line;
        while((line = br.readLine()) != null) 
            addPoint(Integer.parseInt(line), Integer.parseInt(br.readLine()));
    }
    
    public void save(BufferedWriter bw) throws IOException {
        for(Point p : points) {
            bw.write("" + p.x + "\n");
            bw.write("" + p.y + "\n");
        }
    }

    public void addPoint(int x, int y) {
        points.add(new Point(x, y));
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics.create();
        g.setColor(Color.BLACK);
        
        for(int i=1; i<points.size(); ++i)
            g.drawLine(points.get(i-1).x, points.get(i-1).y, points.get(i).x, points.get(i).y);
            
        g.drawString("Professor Rice", 0, getBounds().height);
    }
}
