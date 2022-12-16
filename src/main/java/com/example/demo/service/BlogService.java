package com.example.demo.service;

import com.example.demo.dto.BlogEditDTO;
import com.example.demo.dto.BlogDTO;

import java.util.List;

public interface BlogService {
    BlogDTO create(BlogEditDTO blog);
    List<BlogDTO> getByTitle(String title);
    List<BlogDTO> getAll();
    BlogDTO update(BlogEditDTO blogEditDTO);
    BlogDTO getById(long id);
    void remove(long id);

}
