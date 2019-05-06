package zeon.com.chatapplication.Games.Activity;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import zeon.com.chatapplication.R;

public class Xylophone extends AppCompatActivity {

    // Helpful Constants
    private final int NR_OF_SIMULTANEOUS_SOUNDS = 7;
    private final float LEFT_VOLUME = 1.0f;
    private final float RIGHT_VOLUME = 1.0f;
    private final int NO_LOOP = 0;
    private final int PRIORITY = 0;
    private final float NORMAL_PLAY_RATE = 1.0f;

    // TODO: Add member variables here
    private int mCSoundId;
    private int mDSoundId;
    private int mESoundId;
    private int mFSoundId;
    private int mGSoundId;
    private int mASoundId;
    private int mBSoundId;
    private SoundPool sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xylophone);
        // TODO: Create a new SoundPool
        sound = new SoundPool(NR_OF_SIMULTANEOUS_SOUNDS, AudioManager.STREAM_MUSIC,0);


        // TODO: Load and get the IDs to identify the sounds
        mCSoundId = sound.load(this,R.raw.note1_c,1);
         mASoundId = sound.load(this,R.raw.note6_a,1);
         mBSoundId = sound.load(this,R.raw.note7_b,1);
        mDSoundId = sound.load(this,R.raw.note2_d,1);
         mESoundId = sound.load(this,R.raw.note3_e,1);
         mFSoundId = sound.load(this,R.raw.note4_f,1);
    }

    // TODO: Add the play methods triggered by the buttons
    public void  playc(View v)
    {


        sound.play(mCSoundId,LEFT_VOLUME,RIGHT_VOLUME,1,0,1.0f);

    }

    public void  orange(View v)
    {
        sound.play(mASoundId,LEFT_VOLUME,RIGHT_VOLUME,1,0,1.0f);
    }
    public void  yellow(View v)
    {
            sound.play(mBSoundId,LEFT_VOLUME,RIGHT_VOLUME,1,0,1.0f);
    }
    public void  green(View v)
    {
        sound.play(mDSoundId,LEFT_VOLUME,RIGHT_VOLUME,1,0,1.0f);
    }
    public void  cyan(View v)
    {
        sound.play(mESoundId,LEFT_VOLUME,RIGHT_VOLUME,1,0,1.0f);
    }
    public void  blue(View v)
    {
        sound.play(mFSoundId,LEFT_VOLUME,RIGHT_VOLUME,1,0,1.0f);
    }

    public void  purpel(View v)
    {
        sound.play(mCSoundId,LEFT_VOLUME,RIGHT_VOLUME,1,0,1.0f);
    }


}
