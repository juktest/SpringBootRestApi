package com.example.demo.service;

import com.example.demo.dto.CommunityDto;
import com.example.demo.model.Community;
import com.example.demo.repository.CommunityRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CommunityService {


    private CommunityRepository communityRepository;




    @Transactional
    public List<CommunityDto> getCommunitylist(){
        List<Community> boardEntities = communityRepository.findAll();
        List<CommunityDto> boardDtoList = new ArrayList<>();

        for ( Community boardEntity : boardEntities) {
            CommunityDto boardDTO = CommunityDto.builder()
                    .id(boardEntity.getId())
                    .title(boardEntity.getTitle())
                    .body(boardEntity.getBody())
                    .writer(boardEntity.getWriter())
                    .modifiedDate(boardEntity.getModifiedDate())
                    .comments(boardEntity.getComments())
                    .univid(boardEntity.getUnivid())
                    .views(boardEntity.getViews())
                    .build();

            boardDtoList.add(boardDTO);
        }

        return boardDtoList;
    }

    @Transactional
    public Long SavePost(int Univid,String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CommunityDto communityDto;
        communityDto = objectMapper.readValue(json, CommunityDto.class);
        communityDto.setUnivid(Univid);
        return communityRepository.save(communityDto.toEntity()).getId();
    }

    @Transactional
    public CommunityDto getPost(int Univid, Long Postid) {
        Optional<Community> communityWrapper = communityRepository.findById(Postid);
        Community community = communityWrapper.get();

       CommunityDto communityDTO = CommunityDto.builder()
               .id(community.getId())
               .title(community.getTitle())
               .body(community.getBody())
               .writer(community.getWriter())
               .modifiedDate(community.getModifiedDate())
               .comments(community.getComments())
               .univid(community.getUnivid())
               .views(community.getViews())
                .build();


        return communityDTO;
    }

    @Transactional
    public Long rewritePost(int Univid,Long Postid,String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CommunityDto communityDto;
        communityDto = objectMapper.readValue(json, CommunityDto.class);
        communityDto.setUnivid(Univid);
        communityDto.setId(Postid);
        return communityRepository.save(communityDto.toEntity()).getId();
    }
    @Transactional
    public void deletePost(Long Postid) {
        communityRepository.deleteById(Postid);
    }

}
