package com.example.demo.dto;

import com.example.demo.model.Community;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommunityDto {
    private long id;
    private long previd;
    private long nextid;
    private String title;
    private String writer;
    private String body;
    private int univid;
    private int views;
    private int comments;
    private LocalDateTime modifiedDate;

    public Community toEntity(){
        Community build = Community.builder()
                .id(id)
                .title(title)
                .writer(writer)
                .body(body)
                .univid(univid)
                .views(views)
                .comments(comments)
                .build();
        return build;
    }
    public class BoardDetailDto{
        private long id;
        private String title;
        private String writer;
        private int views;
        private int comments;

        public Community toEntity(){
            Community build = Community.builder()
                    .id(id)
                    .title(title)
                    .writer(writer)
                    .views(views)
                    .comments(comments)
                    .build();
            return build;
        }
    }
    @Builder
    public CommunityDto(long id, long previd, long nextid,String title, String writer, String body, int univid, int views,int comments,LocalDateTime modifiedDate){
        this.id = id;
        this.previd = previd;
        this.nextid = nextid;
        this.title = title;
        this. writer = writer;
        this.body = body;
        this.univid= univid;
        this.views=views;
        this.comments=comments;
        this.modifiedDate = modifiedDate;
    }



}
