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
public class Person {
  int x;
  int y;
  int state;
  ArrayList<Coordinates> neighbors;
  int timeOfChangeState;
  
  public Person(int x, int y) {
    this.x = x;
    this.y = y;
    state = 1;
    neighbors = setNeighbors();
    timeOfChangeState = 0;
  }
  public Person(int x, int y, int state) {
    this.x = x;
    this.y = y;
    this.state = state;
    neighbors = setNeighbors();
  }
  
  public Person(int x, int y, int state, int timeOfChangeState) {
    this.x = x;
    this.y = y;
    this.state = state;
    this.timeOfChangeState = timeOfChangeState;
    neighbors = setNeighbors();
  }
  
  private ArrayList<Coordinates> setNeighbors() {
    neighbors = new ArrayList();
    
    neighbors.add(getTopLeft());
    neighbors.add(getTopMiddle());
    neighbors.add(getTopRight());
    neighbors.add(getLeft());
    neighbors.add(getRight());
    neighbors.add(getBottomLeft());
    neighbors.add(getBottomMiddle());
    neighbors.add(getBottomRight());
    
    return neighbors;
  }
  
  public void setNeighbors(ArrayList<Coordinates> coordinates) {
    neighbors.clear();
    
    for (Coordinates c : coordinates) {
      neighbors.add(c);
    }
  }
  
  public Coordinates getTopLeft() {
    return new Coordinates(x - 1, y - 1);
  }
  
  public Coordinates getTopMiddle() {
    return new Coordinates(x - 1, y);
  }
  
  public Coordinates getTopRight() {
    return new Coordinates(x - 1, y + 1);
  }
  
  public Coordinates getLeft() {
    return new Coordinates(x, y - 1);
  }
  
  public Coordinates getRight() {
    return new Coordinates(x, y + 1);
  }
  
  public Coordinates getBottomLeft() {
    return new Coordinates(x + 1, y - 1);
  }
  
  public Coordinates getBottomMiddle() {
    return new Coordinates(x + 1, y);
  }
  
  public Coordinates getBottomRight() {
    return new Coordinates(x + 1, y + 1);
  }
  
  public ArrayList<Coordinates> getNeighbors() {
    return neighbors;
  }
  
  public void setState(int state) {
    this.state = state;
  }
  
  public int getState() {
    return state;
  }
  
  public int getX(){
    return x;
  }
  
  public int getY(){
    return y;
  }
  
  public void setTime(int currentTime) {
    this.timeOfChangeState = currentTime;
  }
  
  public int getTime() {
    return timeOfChangeState;
  }
}
