package com.macrobios.blocn.model;

public class Note {

    private int id;
    private String title;
    private String noteBody;

    public Note() {
    }

    public Note(int id, String title, String noteBody) {
        this.id = id;
        this.title = title;
        this.noteBody = noteBody;
    }

    //GETTERS
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getNoteBody() {
        return noteBody;
    }

    //SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }
}
