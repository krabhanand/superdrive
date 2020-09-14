package com.udacity.jwdnd.course1.cloudstorage.models;

public class Note {
    private int noteId;
    private String noteTitle;
    private String noteDescription;
    private Integer userId;


    public Note(Object o,String noteTitle, String noteDescription, Integer userId) {
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.userId=userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }
}
