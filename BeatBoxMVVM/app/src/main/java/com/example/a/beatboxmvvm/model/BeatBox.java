package com.example.a.beatboxmvvm.model;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;

    private AssetManager mAssets;
    private SoundPool mSoundPool;

    private List<Sound> mSounds = new ArrayList<>();


    public BeatBox(Context context) {
        mAssets = context.getAssets();
        mSoundPool = new SoundPool(MAX_SOUNDS,AudioManager.STREAM_MUSIC,0);
        loadSound();
    }
    private void loadSound(){
        String[] soundsNames;
        try {
            soundsNames = mAssets.list(SOUNDS_FOLDER);
            for(String filename : soundsNames){
                String assetPath = SOUNDS_FOLDER+"/"+filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Sound> getSounds() {
        return mSounds;
    }

    private void load(Sound sound) throws IOException {
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(afd, 1);
        sound.setSoundId(soundId);
    }

    public void play(Sound sound){
        if(sound.getSoundId() != null){
            mSoundPool.play(sound.getSoundId(),1.0f,1.0f,1,0,1.0f);
        }
    }

    public void release(){
        mSoundPool.release();
    }
}
