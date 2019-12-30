package com.example.demo.dto;


import com.example.demo.model.Comments;
import lombok.*;


import java.time.LocalDateTime;

// data to object

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommentsDto {
    private long id;
    private String writer;
    private String body;
    private int univid;
    private int postid;
    private LocalDateTime modifiedDate;

    /**
     * class -> Entity
     */

    public Comments toEntity() {
        Comments build = Comments.builder()
                .id(id)
                .writer(writer)
                .body(body)
                .univid(univid)
                .postid(postid)
                .build();
        return build;
    }

    /**
     *  Entity -> class
     */

    @Builder
    public CommentsDto(long id,String writer, String body,int univid,int postid,LocalDateTime modifiedDate) {
        this.id=id;
        this.writer=writer;
        this.body=body;
        this.univid=univid;
        this.postid=postid;
        this.modifiedDate = modifiedDate;
    }
}
