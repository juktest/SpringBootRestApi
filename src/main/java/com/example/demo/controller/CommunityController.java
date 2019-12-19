package com.example.demo.controller;

import com.example.demo.dto.CommunityDto;
import com.example.demo.model.Community;
import com.example.demo.repository.CommunityRepository;
import com.example.demo.service.CommunityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CommunityController {


    private CommunityService communityService;

    @PostMapping("Community/{Univid}")
    public Long write(@PathVariable("Univid") int Univid, @RequestBody String json) throws JsonProcessingException {
        return communityService.SavePost(Univid, json);


    }

}
