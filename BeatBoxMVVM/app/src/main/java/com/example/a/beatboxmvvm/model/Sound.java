package com.example.a.beatboxmvvm.model;

public class Sound {

    private String mAssetPath;
    private String mName;
    private Integer mSoundId;

    public Sound(String assetPath) {
        mAssetPath = assetPath;
        String[] components = assetPath.split("/");
        String filename = components[components.length-1];
        mName = filename.replace(".wav","");
    }

    public void setAssetPath(String assetPath) {
        mAssetPath = assetPath;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }

    public String getAssetPath() {
        return mAssetPath;
    }
}
