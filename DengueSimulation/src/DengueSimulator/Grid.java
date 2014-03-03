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
public class Grid {
  Person[][] grid;
  int rows;
  int columns;
  
  public Grid(int rows, int columns) {
    grid = new Person[rows][columns];
    this.rows = rows;
    this.columns = columns;
    initializeGrid();
  }
  
  private void initializeGrid() {
    for(int i = 0; i < rows; i++) {
      for(int j = 0; j < columns; j++) {
        grid[i][j] = new Person(i, j);
      }
    }
  }
  
  public ArrayList<Person> getNeighbors(Person person) {
    ArrayList<Coordinates> tempNeighbors = person.getNeighbors();
    ArrayList<Person> finalNeighbors = new ArrayList();
    
    for (Coordinates coor : tempNeighbors) {
      int x = coor.getX();
      int y = coor.getY();
      
      if (!(x < 0 || x >= rows || y < 0 || y >= columns)) finalNeighbors.add(getPerson(x, y));
    }
    
    return finalNeighbors;
  }
  
  public void changeState(int x, int y, int state, int currentTime) {
    grid[x][y].setState(state);
    grid[x][y].setTime(currentTime);
  }
  
  public int getMaxRows() {
    return rows;
  }
  
  public int getMaxColumns() {
    return columns;
  }
  
  public Person getPerson(int x, int y) {
    return grid[x][y];
  }
  
  public void display() {
    String display = "";
    
    for (int i = 0; i < rows; i++) {
      display += "[ ";
      for (int j = 0; j < columns; j++) {
        display += grid[i][j].getState() + " ";
      }
      display += "]\n";
    }
    
    System.out.println(display);
  }
}
