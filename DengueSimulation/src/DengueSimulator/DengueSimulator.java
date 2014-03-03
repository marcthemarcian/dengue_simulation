/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DengueSimulator;

import java.util.ArrayList;

/**
 *
 * @author MarcTheMarcian
 */
public class DengueSimulator {
  int population;
  int initialInfectedPopulation;
  int infectionProbability;
  int timeEnd;
  Grid prevGrid;
  Grid newGrid;;
  ArrayList<Integer> susceptibleCounter;
  ArrayList<Integer> infectedCounter;
  ArrayList<Integer> resistantCounter;
  int currentTime;
  
  public DengueSimulator (int population, int initialInfectedPopulation, int infectionProbability, int timeEnd, int rows, int columns){
    this.population = population;
    this.initialInfectedPopulation = initialInfectedPopulation;
    this.infectionProbability = infectionProbability;
    this.timeEnd = timeEnd;
    prevGrid = new Grid(rows, columns);
    newGrid = new Grid(rows, columns);
    initializePopulation();
    infectedCounter = new ArrayList();
    infectedCounter.add(initialInfectedPopulation);
    susceptibleCounter = new ArrayList();
    susceptibleCounter.add(population - initialInfectedPopulation);
    resistantCounter = new ArrayList();
    resistantCounter.add(0);
    currentTime = 0;
  }
  
  private void initializePopulation() {    
    for (int i = 0; i < initialInfectedPopulation; i++) {
      int randomX = (int) (Math.random() * prevGrid.getMaxRows());
      int randomY = (int) (Math.random() * prevGrid.getMaxColumns());
      
      while (prevGrid.getPerson(randomX, randomY).getState() == 2) {
        randomX = (int) Math.random() * prevGrid.getMaxRows();
        randomY = (int) Math.random() * prevGrid.getMaxColumns(); 
      }
      
      prevGrid.changeState(randomX, randomY, 2, currentTime);
    }
    
    copyPrevToNew();
  }
  
  public void simulate() {
    currentTime = 0;
    System.out.println("huh");
    while(currentTime++ != timeEnd) {
      int newlyInfected = 0;
      
      for (int i = 0; i < prevGrid.getMaxRows(); i++) {
        for (int j = 0; j < prevGrid.getMaxColumns(); j++) {
          ArrayList<Person> neighbors = prevGrid.getNeighbors(prevGrid.getPerson(i, j));
          
          for (Person p : neighbors) {
            if (p.getState() == 2) {
              int probability = (int)(Math.random() * 101);
              
              if (probability < infectionProbability) {             
                if (prevGrid.getPerson(i, j).getState() != 2 && prevGrid.getPerson(i, j).getState() != 3) {
                  newlyInfected++;
                  newGrid.changeState(i, j, 2, currentTime);
                }   
                break;
              }
              
            }
          }          
        }
      }                  
                  
      susceptibleCounter.add(susceptibleCounter.get(susceptibleCounter.size()-1) - newlyInfected);
      infectedCounter.add(infectedCounter.get(infectedCounter.size()-1) + newlyInfected); 
      
      int newlyResistant = 0;
      
      for (int i = 0; i < newGrid.getMaxRows(); i++) {
        for (int j = 0; j < newGrid.getMaxColumns(); j++) {
          int randomDay = 20 + (int)(Math.random() * ((30 - 20) + 1));
            
          if (newGrid.getPerson(i, j).getState() == 2) {
            if (currentTime - newGrid.getPerson(i, j).getTime() >= randomDay) {
              newGrid.changeState(i, j, 3, currentTime);
              newlyResistant++;
            }
          }
        }
      }
      
      infectedCounter.set(infectedCounter.size()-1, infectedCounter.get(infectedCounter.size()-1) - newlyResistant);
      resistantCounter.add(resistantCounter.get(resistantCounter.size()-1) + newlyResistant);
      copyNewToPrev();
      
      System.out.println(currentTime);
    }
  }
  
  private void copyPrevToNew() {
    for (int i = 0; i < prevGrid.getMaxRows(); i++) {
      for (int j = 0; j < newGrid.getMaxColumns(); j++) {
        newGrid.grid[i][j] = new Person(i, j, prevGrid.getPerson(i, j).getState(), prevGrid.getPerson(i, j).getTime());
      }
    }
  }
  
  private void copyNewToPrev() {
    for (int i = 0; i < newGrid.getMaxRows(); i++) {
      for (int j = 0; j < newGrid.getMaxColumns(); j++) {
        prevGrid.grid[i][j] = new Person(i, j, newGrid.getPerson(i, j).getState(), newGrid.getPerson(i, j).getTime());
      }
    }
  }
  
  public void createGUI(){
    DrawGraph dg = new DrawGraph(susceptibleCounter, infectedCounter, resistantCounter, population);
    dg.constructGUI(dg);
  }
}
