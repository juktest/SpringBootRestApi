package com.example.demo.model;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table
public class Community extends Time{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
        private long id;

    @Column(length = 100, nullable =false)
        private String title;

    @Column(length = 30, nullable =false)
        private String writer;

    @Column(columnDefinition = "TEXT",nullable = false)
        private String body;
    @Column(nullable=false)
        private int univid;
    @Column(nullable=false)
        private int views;
    @Column(nullable=false)
        private int comments;

    @Builder
    public Community(long id, String title, String writer, String body, int univid, int views,int comments){
        this.id = id;
        this.title = title;
        this. writer = writer;
        this.body = body;
        this.univid= univid;
        this.views=views;
        this.comments=comments;
    }


}
