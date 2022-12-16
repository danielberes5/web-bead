package com.example.demo.converter;

import com.example.demo.domain.Reply;
import com.example.demo.dto.ReplyEditDTO;
import com.example.demo.dto.ReplyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReplyConverter {
    public ReplyDTO entityToDTO(Reply reply) {
        return new ReplyDTO(reply.getId(), reply.getBlogId(), reply.getContent());
    }

    public Reply mapToEntity(Reply reply, ReplyDTO replyDTO)
    {
        reply.setBlogId(replyDTO.getBlogId());
        reply.setContent(replyDTO.getContent());
        return reply;
    }

    public Reply mapToEntity(Reply reply, ReplyEditDTO replyDTO)
    {
        reply.setContent(replyDTO.getContent());
        return reply;
    }
}
