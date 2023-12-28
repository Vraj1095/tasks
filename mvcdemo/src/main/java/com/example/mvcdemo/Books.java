package com.example.mvcdemo;

public class Books {
    protected int id;
    protected String name;
    protected String author;
//    protected int publications_id;

    public Books() {
    }

    public Books(int id, String name, String author, int publications_id) {
        this.id = id;
        this.name = name;
        this.author = author;
//        this.publications_id = publications_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

//    public int getPublications_id() {
//        return publications_id;
//    }
//
//    public void setPublications_id(int publications_id) {
//        this.publications_id = publications_id;
//    }
}
