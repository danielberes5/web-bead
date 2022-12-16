package com.example.demo.converter;

import com.example.demo.domain.Blog;
import com.example.demo.dto.BlogEditDTO;
import com.example.demo.dto.BlogDTO;
import com.example.demo.dto.ReplyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogConverter {

    private final ReplyConverter replyConverter;

    public BlogDTO entityToDTO(Blog blog){
        return BlogDTO.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .content(blog.getContent())
                .replies(
                        blog.getReplies().stream().map(reply ->
                                new ReplyDTO(reply.getId(), reply.getBlogId(), reply.getContent())
                        ).collect(Collectors.toList())).build();
    }

    public Blog mapToEntity(Blog blog, BlogEditDTO blogDTO){
        blog.setTitle(blogDTO.getTitle());
        blog.setContent(blogDTO.getContent());
        return blog;
    }

}
