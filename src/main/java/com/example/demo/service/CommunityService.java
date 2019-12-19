package com.example.demo.service;

import com.example.demo.dto.CommunityDto;
import com.example.demo.repository.CommunityRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class CommunityService {


    private CommunityRepository communityRepository;

    @Transactional
    public Long SavePost(int Univid,String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CommunityDto communityDto;
        communityDto = objectMapper.readValue(json, CommunityDto.class);
        communityDto.setUnivid(Univid);
        return communityRepository.save(communityDto.toEntity()).getId();
    }
}
