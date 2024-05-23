// Lily Holdhus
// Wumpus Object
// 2/12/2024
// Period 5

// LAST UPDATED: 3/29/2024 during class
// CURRENT STATUS OF CODE: runs

class Wumpus{

  // PROPERTIES ////
  
  // determines if the wumpus is asleep/awake (returns true for trivia)
  boolean awake;    
  // determines the wumpus's mood
  boolean mood;
  // true = wumpus has been defeated
  boolean isAlive;
  
  // loaction variable
  
  // CONSTRUCTORS //
  
  public Wumpus(){}
  
  // METHODS ///////

  public boolean getAwake()
  {
    return awake;
  }

  public void setAwake(boolean awake)
  {
    this.awake = awake;
  }
  
  public boolean getMood()
  {
    return mood;
  }
  
  public void setMood(boolean mood)
  {
    this.mood = mood;
  }
  
  public boolean getAlive(boolean isAlive)
  {
    return isAlive;
  }
  
  public void wakeUp(boolean awake)
  {
    /*
     * The Wumpus is woken up when:
     * - an arrow is shot into a room
     * the wumpus will have an x% chance of running into another room
     * - the player enter's its room
     * triggers a trivia battle 
     */
    // changes the wumpus from asleep to awake (boolean awake: false ---> true)
  }

  public void moodSwing(/* mood factors */)
  {
    // determines if certain factors would change the Wumpus' mood
  }
  
  public void run()
  {
    /* 
     * the Wumpus will run when defeated in Trivia
     * at least 2 rooms, as many as 4
    */
    // determine whether or not trivia was won
    // gets a random room in Cave
    // moves wumpus into that room
    // the Wumpus moves to another room when defeated in trivia
  }
}