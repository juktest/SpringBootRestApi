package com.example.demo.controller;

import com.example.demo.dto.BoardlistDto;
import com.example.demo.dto.CommunityDto;
import com.example.demo.model.Community;
import com.example.demo.repository.CommunityRepository;
import com.example.demo.service.CommunityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CommunityController {


    private CommunityService communityService;
    @CrossOrigin(origins="*")
    @GetMapping("Community/{Univid}")
    public List list(Model model){
        List<BoardlistDto>boardList = communityService.getCommunitylist();
        return boardList;
    }
    @CrossOrigin(origins="*")
    @GetMapping("Community/{Univid}/{Postid}")
    public CommunityDto Post(@PathVariable("Univid")int Univid, @PathVariable("Postid") Long Postid){
        CommunityDto postdata = communityService.getPost(Univid, Postid);
        return postdata;
    }
    @CrossOrigin(origins="*")
    @PostMapping("Community/{Univid}")
    public Long write(@PathVariable("Univid") int Univid, @RequestBody String json) throws JsonProcessingException {
        return communityService.SavePost(Univid, json);

    }
    @CrossOrigin(origins="*")
    @PutMapping("Community/{Univid}/{Postid}")
    public Long update(@PathVariable("Univid")int Univid, @PathVariable("Postid")Long Postid, @RequestBody String json) throws JsonProcessingException {
        return  communityService.rewritePost(Univid,Postid,json);
    }
    @CrossOrigin(origins="*")
    @DeleteMapping("Community/{Univid}/{Postid}")
    public String delete(@PathVariable("Univid")int Univid, @PathVariable("Postid")Long Postid){
        communityService.deletePost(Postid);
        return "success";
    }

}
