package com.example.demo.dto;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardlistDto {
    private long id;
    private String title;
    private String writer;
    private int univid;
    private int views;
    private int comments;
    private LocalDateTime modifiedDate;



    @Builder
    public BoardlistDto(long id, String title, String writer, int univid, int views,int comments,LocalDateTime modifiedDate){
        this.id = id;
        this.title = title;
        this. writer = writer;
        this.univid= univid;
        this.views=views;
        this.comments=comments;
        this.modifiedDate = modifiedDate;
    }



}
