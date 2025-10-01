package com.designpatterns.structural.adapter;

public class MediaPlayer {
    private MediaAdapter mediaAdapter;
    
    public void play(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            System.out.println("✗ Invalid file name");
            return;
        }
        
        String fileType = getFileExtension(fileName);
        
        if (fileType.equalsIgnoreCase("mp3")) {
            System.out.println("🎵 Playing MP3 file: " + fileName);
        } else if (fileType.equalsIgnoreCase("vlc") || fileType.equalsIgnoreCase("mp4")) {
            mediaAdapter = new MediaAdapter(fileType);
            mediaAdapter.play(fileName);
        } else {
            System.out.println("✗ Unsupported format: " + fileType);
            System.out.println("   Supported formats: mp3, vlc, mp4");
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
