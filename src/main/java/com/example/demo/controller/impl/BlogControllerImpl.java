package com.example.demo.controller.impl;

import com.example.demo.controller.BlogController;
import com.example.demo.dto.BlogEditDTO;
import com.example.demo.dto.BlogDTO;
import com.example.demo.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogControllerImpl implements BlogController {

    @Autowired
    private final BlogService blogservice;

    @Override
    public ResponseEntity<List<BlogDTO>> getAllBlogs(){
        return ResponseEntity.ok().body(blogservice.getAll());
    }

    @Override
    public ResponseEntity<BlogDTO> getById(@RequestBody long id){
        return ResponseEntity.ok().body(blogservice.getById(id));
    }

    @Override
    public ResponseEntity<List<BlogDTO>> getBlogsByTitle(@RequestBody String title){
        return ResponseEntity.ok().body(blogservice.getByTitle(title));
    }

    @Override
    public ResponseEntity<BlogDTO> saveBlog(@RequestBody BlogEditDTO blog){
        return ResponseEntity.ok().body(blogservice.create(blog));
    }

    @Override
    public ResponseEntity<BlogDTO> update(@RequestBody BlogEditDTO blog){
        return ResponseEntity.ok().body(blogservice.update(blog));
    }

    @Override
    public ResponseEntity<?> remove(@RequestBody long id){
        blogservice.remove(id);
        return ResponseEntity.ok().build();
    }

}
