package com.example.demo.repository;

import com.example.demo.model.Comments;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments,Long> {

    List<Comments> findAllByUnividAndPostid(int Univid,int postid);


}
