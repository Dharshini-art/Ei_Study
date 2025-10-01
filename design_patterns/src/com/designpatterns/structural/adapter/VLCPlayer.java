package com.designpatterns.structural.adapter;

public class VLCPlayer implements AdvancedMediaPlayer {
    
    @Override
    public void playVlc(String fileName) {
        System.out.println("🎬 Playing VLC file: " + fileName);
    }
    
    @Override
    public void playMp4(String fileName) {
        // VLC player doesn't support MP4 directly
    }
}
