package com.example.demo.service;


import com.example.demo.dto.CommentsDto;
import com.example.demo.dto.CommunityDto;
import com.example.demo.model.Comments;
import com.example.demo.model.Community;
import com.example.demo.repository.CommentsRepository;
import com.example.demo.repository.CommunityRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
import org.springframework.web.bind.annotation.PathVariable;
import org.hibernate.criterion.Example;
 import java.util.Optional;
**/


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service

public class CommentsService {
    private CommentsRepository commentsRepository;
    private CommunityRepository communityRepository; // 댓글수 추가용

    /**
     *  list 에 표시되야할 댓글 정보 : writter (작성자), body(내용) , date(작성 날짜)
     *
     *  19.12.27 : univid별, postid별 나누어서 데이터 호출
     */
    @Transactional
    public List<CommentsDto> getCommentsList(int Univid,  int Postid) {
        List<Comments> boardEntities = commentsRepository.findAllByUnividAndPostid(Univid,Postid);
        List<CommentsDto> boardDtoList = new ArrayList<>();

      for ( Comments boardEntity : boardEntities) {
            CommentsDto boardDTO = CommentsDto.builder()
                    .id(boardEntity.getId())
                    .postid(boardEntity.getPostid())
                    .writer(boardEntity.getWriter())
                    .modifiedDate(boardEntity.getModifiedDate())
                    .body(boardEntity.getBody())
                    .univid(boardEntity.getUnivid())
                    .build();

         boardDtoList.add(boardDTO);
    }

    return boardDtoList;
}

    /**
    *   댓글 달기 기능 (저장)
     *   입력해야될 데이터 : writter (작성자), body(내용)
     */
    @Transactional
    public long SaveComment(int Univid,int Postid,String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CommentsDto commentsDto;
        commentsDto = objectMapper.readValue(json, CommentsDto.class);
        commentsDto.setUnivid(Univid);
        commentsDto.setPostid(Postid);

        Optional<Community> communityWrapper = communityRepository.findById((long)Postid);
        Community community = communityWrapper.get();

        CommunityDto communityDTO = CommunityDto.builder()
                .id(community.getId())
                .title(community.getTitle())
                .body(community.getBody())
                .writer(community.getWriter())
                .modifiedDate(community.getCreatedDate())
                .comments(community.getComments()+1)
                .univid(community.getUnivid())
                .views(community.getViews())
                .build();
        communityRepository.save(communityDTO.toEntity()).getId();


    return commentsRepository.save(commentsDto.toEntity()).getId(); // 잘모르겠음
    }


    /**
     *  수정 하기
     *  댓글의 고유번호 (id) 에 접근하여 수정
     */
    @Transactional
    public long rewriteComment(int univid,int postid,long id,String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CommentsDto commentsDto;
        commentsDto = objectMapper.readValue(json, CommentsDto.class);
        commentsDto.setUnivid(univid);
        commentsDto.setPostid(postid);
        commentsDto.setId(id);
        return commentsRepository.save(commentsDto.toEntity()).getId();
    }

    /**
    *   삭제하기
     *   댓글의 고유번호 (id) 에 접근하여 삭제
     */
    @Transactional
    public void deleteComment(long id, long Postid) {

        Optional<Community> communityWrapper = communityRepository.findById((long)Postid);
        Community community = communityWrapper.get();

        CommunityDto communityDTO = CommunityDto.builder()
                .id(community.getId())
                .title(community.getTitle())
                .body(community.getBody())
                .writer(community.getWriter())
                .modifiedDate(community.getCreatedDate())
                .comments(community.getComments()-1)
                .univid(community.getUnivid())
                .views(community.getViews())
                .build();
        communityRepository.save(communityDTO.toEntity()).getId();
        commentsRepository.deleteById(id)
        ;
    }
}

