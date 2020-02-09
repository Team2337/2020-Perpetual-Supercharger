package frc.robot.subsystems;

import java.util.ArrayList;

import com.ctre.phoenix.music.Orchestra;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Top level Robot class
 */
public class MusicPlayer extends SubsystemBase {

    /* The orchestra object that holds all the instruments */
    Orchestra _orchestra;

    /*
     * Talon FXs to play music through. More complex music MIDIs will contain
     * several tracks, requiring multiple instruments.
     */
    TalonFX[] _fxes = { new TalonFX(15)};

    /*
     * An array of the songs. See deploy for the original files.
     */
    String[] _songs = new String[] {
        "imperial.chrp",
        "dotf.chrp",
        "tetris.chrp",
        "cantina.chrp"
    };

    /* track which song is selected for play */
    int _songSelection = 0;

    /* overlapped actions */
    int _timeToPlayLoops = 0;

    void LoadMusicSelection(int offset) {
        /* increment song selection */
        _songSelection += offset;
        /* wrap song index in case it exceeds boundary */
        if (_songSelection >= _songs.length) {
            _songSelection = 0;
        }
        if (_songSelection < 0) {
            _songSelection = _songs.length - 1;
        }
        /* load the chirp file */
        _orchestra.loadMusic(_songs[_songSelection]);

        /* print to console */
        System.out.println("Song selected is: " + _songs[_songSelection] + ".  Press left/right on d-pad to change.");

        /*
         * schedule a play request, after a delay. This gives the Orchestra service time
         * to parse chirp file. If play() is called immedietely after, you may get an
         * invalid action error code.
         */
        _timeToPlayLoops = 10;
    }

    
    public MusicPlayer() {
        /* A list of TalonFX's that are to be used as instruments */
        ArrayList<TalonFX> _instruments = new ArrayList<TalonFX>();

        /* Initialize the TalonFX's to be used */
        for (int i = 0; i < _fxes.length; ++i) {
            _instruments.add(_fxes[i]);
        }
        /* Create the orchestra with the TalonFX instruments */
        _orchestra = new Orchestra(_instruments);
        
    }


    @Override
    public void periodic() {

        /* if song selection changed, auto-play it */
        if (_timeToPlayLoops > 0) {
            --_timeToPlayLoops;
            if (_timeToPlayLoops == 0) {
                /* scheduled play request */
                System.out.println("Auto-playing song.");
                _orchestra.play();
            }
        }

    }

    /**
     * Plays a song in a list of songs.
     * @param song The track number to be played. See the list below (take item number and subtract by 1)
     * <ol>
     * <li>Imperial march</li>
     * <li>Duel of the Fates</li>
     * <li>Tetris Main Theme</li>
     * <li>Cantina Song (Star Wars)</li>
     * </ol>
     * @see #_songs
     */
    public void playSong(int song){
        /* load whatever file is selected */
        LoadMusicSelection(song);
    }
}