# hunt-the-wumpus
# BUMPELL
## Creating a readable readme.md
Go to [this guide](https://www.markdownguide.org/cheat-sheet/) for a detailed description of how to use markdown, which is what this readme file is in.    
<br>**Quick Tips**:
 - use two spaces at the end of a line to force a line break
 - #, ##, and ### are markers for big, medium, and small headers
 - *text* is italic
 - **text** is bold
 - `text` is code

## WUMPUS ROLES
| Role                                   | Name   |
|----------------------------------------|--------|
| Trivia Management                      | Jack   |
| Cave Object                            | Shunzo |
| GameLocations Object and Player Object | Pavan  |
| Graphical Interface Object             | Nathan |
| Game Control Object                    | Saanvi |
| High Score Management Object           | Yana   |
| Sound Object                           | Lily   |
| Wumpus Object                          | Lily   |
      
## Section Descriptions
>**Trivia**: Manages the trivia questions for the game, including asking questions and loading questions from a file.

>**Cave**: Keeps track of which rooms in the cave are connected to which other rooms.

>**GameLocations & Player**: Keeps track of the locations of the player, the Wumpus, and the hazards within the cave. Proves "hints" about nearby hazards. Player object keeps the player's inventory and score.

>**Graphical Interface**: Displays the state of the current game (the current room, connected rooms, inventory, etc).

>**Game Control**: Handles user input (except for High Score and Trivia), coordinates all the other parts of the game.

>**High Score**: Manages the high scores, including saving high schores and displaying a high score scoreboard.

>**Sound**: Plays all of the sounds for the game, and organizes groups of sounds into "themes."

>**Wumpus**: Tracks the state of the wumpus (that is, asleep, awake, moving) and controls wumpus movement. Program the "personality" of the wumpus.  

## Section Progress
*If it helps, use this area to log your progress and what you plan to do.*

### Trivia - Jack

### Cave - Shunzo
Each Hex in the cave is connected to its adjacent.
The Hex class has been created. Constructed with id or row,column.  
The Cave class draws the map of hexes with `draw(JFrame frame)`  
**complete** Currently working on: drawing a different map based on current location of player.  
- color current location a different color
- color options a different color
- enable clicking to move the player
  
Currently working on: create 5 different maps, or figure out how to randomly generate paths (3 paths per cave)
  - keep in mind: paths should be 2-way


To-do:  
- somehow work with GameLocations to keep track of Hazards

### GameLocations & Player - Pavan

### Graphical Interface - Nathan

### Game Control - Saanvi

### High Score - Yana

### Sound - Lily

### Wumpus - Lily
