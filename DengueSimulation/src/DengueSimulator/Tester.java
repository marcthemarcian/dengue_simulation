/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DengueSimulator;

import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
/**
 *
 * @author MarcTheMarcian
 */
public class Tester {
  public static void main(String[] args) {    
    System.out.println("Initializing model... (0/4)");
    int population = acceptInput("Population: ");
    System.out.println("(1/4)");
    int infected = acceptInput("Initial Infected Population: ");
    System.out.println("(2/4)");
    int infectionProbability = acceptInput("Infection Probability (0-100): ");
    System.out.println("(3/4)");
    int time = acceptInput("Time Steps: ");
    System.out.println("(4/4)\nDONE");
    int rowcolumn = (int) Math.round(Math.sqrt(population));
    
    System.out.print("Creating Simulator... ");
    final DengueSimulator simulator = new DengueSimulator (population, infected, infectionProbability, time, rowcolumn, rowcolumn);
    System.out.println("DONE\nSimulating model...");
    simulator.simulate();
    
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        simulator.createGUI();
      }    
    });
  }
  
  public static int acceptInput(String prompt) {
    String input = JOptionPane.showInputDialog(prompt);
    int result = 0;
    boolean done = false;
    
    while(!done) {
      boolean error = false;
      for (int i = 0; i < input.length(); i++) {
        if (!Character.isDigit(input.charAt(i))) {
          error = true;
        }
        
      }
      
      if (error) {
        input = JOptionPane.showInputDialog(prompt);
      } else {
        result = Integer.parseInt(input);
        done = true;
      }
    }
    
    return result;
  }
}
