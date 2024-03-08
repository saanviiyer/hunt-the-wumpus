// Lily Holdhus
// Wumpus Object (Hunt the Wumpus)
// 2/12/2024
// Period 5

class Wumpus{

    // PROPERTIES ////
  
    // determines if the wumpus is asleep/awake
    boolean awake;
    // determines the wumpus's mood
    boolean mood;
    // true = wumpus has been defeated
    boolean isAlive;
  
    // CONSTRUCTORS //
    
    public Wumpus(){
      
    }
  
    // METHODS ///////

    public boolean getAwake() {
        return awake;
    }

    public void setAwake(boolean awake) {
        this.awake = awake;
    }
  
    public boolean getMood() {
      return mood;
    }
  
    public void setMood(boolean mood) {
      this.mood = mood;
    }
  
    public boolean getAlive(boolean isAlive) {
      return isAlive;
    }
  
    public void wakeUp(boolean awake)
    {
      // changes the wumpus from asleep to awake (boolean awake: false ---> true)
    }

    public void fallAsleep(boolean awake)
    {
        // changes the wumpus from awake to asleep (boolean awake: true ---> false)
    }

    public void moodSwing(/* mood factors */)
    {
      // determines if certain factors would change the Wumpus' mood
    }
  
    public void run()
    {
      // the Wumpus moves to another room when defeated in trivia
    }
  }