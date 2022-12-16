package com.example.demo.service.impl;

import com.example.demo.converter.BlogConverter;
import com.example.demo.domain.Blog;
import com.example.demo.domain.Reply;
import com.example.demo.dto.BlogEditDTO;
import com.example.demo.dto.BlogDTO;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.ReplyRepository;
import com.example.demo.service.BlogService;
import com.example.demo.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    @Autowired
    private final BlogRepository blogRepository;

    @Autowired
    private final BlogConverter blogConverter;


    @Override
    public BlogDTO create(BlogEditDTO blog) {
        return blogConverter.entityToDTO(blogRepository.save(blogConverter.mapToEntity(new Blog(), blog)));
    }

    @Override
    public List<BlogDTO> getByTitle(String title) {
        return blogRepository.findByTitle(title).stream().map(blog -> blogConverter.entityToDTO(blog)).collect(Collectors.toList());
    }

    @Override
    public List<BlogDTO> getAll() {
        return blogRepository.findAll().stream().map(blog -> blogConverter.entityToDTO(blog)).collect(Collectors.toList());
    }

    @Override
    public BlogDTO update(BlogEditDTO blogEditDTO){
        Blog blog = blogRepository.getById(blogEditDTO.getId());
        return blogConverter.entityToDTO(blogRepository.save(blogConverter.mapToEntity(blog, blogEditDTO)));
    }

    @Override
    public BlogDTO getById(long id) {
        return blogConverter.entityToDTO(blogRepository.findById(id).get());
    }

    @Override
    public void remove(long id) {
        blogRepository.deleteById(id);
    }


}
