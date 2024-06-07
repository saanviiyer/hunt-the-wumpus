// Lily Holdhus
// Sound Object (Hunt the Wumpus)
// 2/12/2024
// Period 5

// LAST UPDATED: 5/23/2024
// CURRENT STATUS: runs
package Sound;
import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JOptionPane;

// coin, falling into a pit, shooting an arrow, wumpus, win/loose, bats, cold draft, hint


public class Sound{

  public Sound()
  {
    System.out.println("SOUND");
  }

  public static void playSound(String path) throws Exception {
    AudioInputStream stream = AudioSystem.getAudioInputStream(new File("path/to/your/sound.wav"));
    Clip clip = AudioSystem.getLine(new DataLine.Info(Clip.class, stream.getFormat()));
    clip.open(stream);
    clip.start();
  }
  
}