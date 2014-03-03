/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DengueSimulator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/**
 *
 * @author MarcTheMarcian
 */
public class DrawGraph extends JPanel{
  ArrayList<Integer> data1;
  ArrayList<Integer> data2;
  ArrayList<Integer> data3;
  int max_score;
  final int pref_w = 1000;
  final int pref_h = 700;
  final int border_gap = 50;
  Color graph_color1 = new Color(00, 239, 64, 180);
  Color graph_point_color1 = new Color(36, 215, 92, 180);
  Color graph_color2 = new Color(255, 49, 0, 180);
  Color graph_point_color2 = new Color(255, 49, 49, 180);
  Color graph_color3 = new Color(43, 80, 211, 180);
  Color graph_point_color3 = new Color(15, 79, 168, 180);
  Stroke graph_stroke = new BasicStroke(3f);
  int graph_point_width = 5;
  int y_hatch_cnt = 10;
  
  public DrawGraph(ArrayList<Integer> susceptible, ArrayList<Integer> infected, ArrayList<Integer> resistant, int max_score) {
    this.data1 = susceptible;
    this.data2 = infected;
    this.data3 = resistant;
    this.max_score = max_score;    
  }
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g1 = (Graphics2D)g;
    g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
    double xScale = ((double) getWidth() - 2 * border_gap) / (data1.size());
    double yScale = ((double) getHeight() - 2 * border_gap) / (max_score);
    
    ArrayList<Point> graphPoints1 = new ArrayList();
    for (int i = 0; i < data1.size(); i++) {
      int x1 = (int) (i * xScale + border_gap);
      int y1 = (int) ((max_score - data1.get(i)) * yScale + border_gap);
      graphPoints1.add(new Point(x1, y1));
    }
    
    ArrayList<Point> graphPoints2 = new ArrayList();
    for (int i = 0; i < data2.size(); i++) {
      int x1 = (int) (i * xScale + border_gap);
      int y1 = (int) ((max_score - data2.get(i)) * yScale + border_gap);
      graphPoints2.add(new Point(x1, y1));
    }
    
    ArrayList<Point> graphPoints3 = new ArrayList();
    for (int i = 0; i < data3.size(); i++) {
      int x1 = (int) (i * xScale + border_gap);
      int y1 = (int) ((max_score - data3.get(i)) * yScale + border_gap);
      graphPoints3.add(new Point(x1, y1));
    }
    
    g1.drawLine(border_gap, getHeight() - border_gap, border_gap, border_gap);
    g1.drawLine(border_gap, getHeight() - border_gap, getWidth() - border_gap, getHeight() - border_gap);
    g1.drawLine(border_gap, getHeight() - border_gap, border_gap, border_gap);
    g1.drawLine(border_gap, getHeight() - border_gap, getWidth() - border_gap, getHeight() - border_gap);
    g1.drawLine(border_gap, getHeight() - border_gap, border_gap, border_gap);
    g1.drawLine(border_gap, getHeight() - border_gap, getWidth() - border_gap, getHeight() - border_gap);
    
    for (int i = 0; i < y_hatch_cnt; i++) {
      int x0 = border_gap;
      int x1 = graph_point_width + border_gap;
      int y0 = getHeight() - (((i+1) * (getHeight() - border_gap * 2)) / y_hatch_cnt + border_gap);
      int y1 = y0;
      g1.drawLine(x0, y0, x1, y1);
      g1.drawLine(x0, y0, x1, y1);
      g1.drawLine(x0, y0, x1, y1);
    }
    
    for (int i = 0; i < data1.size() - 1; i++) {
      int x0 = (i + 1) * (getWidth() - border_gap * 2) / (data1.size() - 1) + border_gap;
      int x1 = x0;
      int y0 = getHeight() - border_gap;
      int y1 = y0 - graph_point_width;
      g1.drawLine(x0, y0, x1, y1);
    }
    
    for (int i = 0; i < data2.size() - 1; i++) {
      int x0 = (i + 1) * (getWidth() - border_gap * 2) / (data2.size() - 1) + border_gap;
      int x1 = x0;
      int y0 = getHeight() - border_gap;
      int y1 = y0 - graph_point_width;
      g1.drawLine(x0, y0, x1, y1);
    }
    
    for (int i = 0; i < data3.size() - 1; i++) {
      int x0 = (i + 1) * (getWidth() - border_gap * 2) / (data3.size() - 1) + border_gap;
      int x1 = x0;
      int y0 = getHeight() - border_gap;
      int y1 = y0 - graph_point_width;
      g1.drawLine(x0, y0, x1, y1);
    }
    
    Stroke oldStroke1 = g1.getStroke();
    g1.setColor(graph_color1);
    g1.setStroke(graph_stroke);
    for (int i = 0; i < graphPoints1.size() - 1; i++) {
      int x0 = graphPoints1.get(i).x;
      int y0 = graphPoints1.get(i).y;
      int x1 = graphPoints1.get(i + 1).x;
      int y1 = graphPoints1.get(i + 1).y;
      g1.drawLine(x0, y0, x1, y1);
    }
    
    Stroke oldStroke2 = g1.getStroke();
    g1.setColor(graph_color2);
    g1.setStroke(graph_stroke);
    for (int i = 0; i < graphPoints2.size() - 1; i++) {
      int x0 = graphPoints2.get(i).x;
      int y0 = graphPoints2.get(i).y;
      int x1 = graphPoints2.get(i + 1).x;
      int y1 = graphPoints2.get(i + 1).y;
      g1.drawLine(x0, y0, x1, y1);
    }    
    
    Stroke oldStroke3 = g1.getStroke();
    g1.setColor(graph_color3);
    g1.setStroke(graph_stroke);
    for (int i = 0; i < graphPoints3.size() - 1; i++) {
      int x0 = graphPoints3.get(i).x;
      int y0 = graphPoints3.get(i).y;
      int x1 = graphPoints3.get(i + 1).x;
      int y1 = graphPoints3.get(i + 1).y;
      g1.drawLine(x0, y0, x1, y1);
    }
    
    g1.setStroke(oldStroke1);
    g1.setColor(graph_point_color1);
    for (int i = 0; i < graphPoints1.size(); i++) {
      int x = graphPoints1.get(i).x - graph_point_width / 2;
      int y = graphPoints1.get(i).y - graph_point_width / 2;
      int ovalW = graph_point_width;
      int ovalH = graph_point_width;
      g1.fillOval(x, y, ovalW, ovalH);
    }
    
    g1.setStroke(oldStroke2);
    g1.setColor(graph_point_color2);
    for (int i = 0; i < graphPoints2.size(); i++) {
      int x = graphPoints2.get(i).x - graph_point_width / 2;
      int y = graphPoints2.get(i).y - graph_point_width / 2;
      int ovalW = graph_point_width;
      int ovalH = graph_point_width;
      g1.fillOval(x, y, ovalW, ovalH);
    }
    
    g1.setStroke(oldStroke3);
    g1.setColor(graph_point_color3);
    for (int i = 0; i < graphPoints3.size(); i++) {
      int x = graphPoints3.get(i).x - graph_point_width / 2;
      int y = graphPoints3.get(i).y - graph_point_width / 2;
      int ovalW = graph_point_width;
      int ovalH = graph_point_width;
      g1.fillOval(x, y, ovalW, ovalH);
    }
        
    Graphics2D label = (Graphics2D)g;
    
    label.setColor(graph_point_color1);
    label.fillOval(700,300,10,10);
    label.setColor(graph_color1);
    label.drawLine(690, 305, 720, 305);
    label.setColor(Color.BLACK);
    label.drawString("Susceptible", 725, 310);
    label.setColor(graph_point_color2);
    label.fillOval(700,320,10,10);
    label.setColor(graph_color2);
    label.drawLine(690, 325, 720, 325);
    label.setColor(Color.BLACK);
    label.drawString("Infected", 725, 330);
    label.setColor(graph_point_color3);
    label.fillOval(700,340,10,10);
    label.setColor(graph_color3);
    label.drawLine(690, 345, 720, 345);
    label.setColor(Color.BLACK);
    label.drawString("Resistant", 725, 350);
  }
   
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(pref_w, pref_h);
  }
  
  public void constructGUI(DrawGraph dg) {
    JFrame frame = new JFrame("Dengue Simulator");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(dg);
    frame.pack();
    frame.setLocationByPlatform(true);
    frame.setVisible(true);
  }
}
