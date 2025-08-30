package com.oocl.training.Entitiy;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "todo")
public class Todo {

    private String title;
    private Integer id;
    private String status;

    public Todo() {

    }

    public Todo(Integer id, String titles, String status) {
        this.status = status;
        this.id = id;
        this.title = titles;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String titles) {
        this.title = titles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
