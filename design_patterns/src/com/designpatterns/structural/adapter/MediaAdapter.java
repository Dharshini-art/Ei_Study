package com.designpatterns.structural.adapter;

public class MediaAdapter {
    private AdvancedMediaPlayer advancedMediaPlayer;
    
    public MediaAdapter(String fileType) {
        if (fileType == null || fileType.isEmpty()) {
            throw new IllegalArgumentException("File type cannot be null or empty");
        }
        
        if (fileType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer = new VLCPlayer();
        } else if (fileType.equalsIgnoreCase("mp4")) {
            advancedMediaPlayer = new MP4Player();
        } else {
            throw new IllegalArgumentException("Unsupported file type: " + fileType);
        }
    }
    
    public void play(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            System.out.println("✗ Invalid file name");
            return;
        }
        
        String fileType = getFileExtension(fileName);
        
        if (fileType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer.playVlc(fileName);
        } else if (fileType.equalsIgnoreCase("mp4")) {
            advancedMediaPlayer.playMp4(fileName);
        }
    }
    
    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
            return fileName.substring(lastDotIndex + 1);
        }
        return "";
    }
}
