package com.akasham.myapp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "journal")
public class JournalEntry {

    // @Document is a MongoDB annotation used to mark a class as a document which is mapped to a collection in the database
    // @Id is a MongoDB annotation used to mark a field as the document's unique identifier

    @Id
    private String id;
    private String title;
    private String content;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
