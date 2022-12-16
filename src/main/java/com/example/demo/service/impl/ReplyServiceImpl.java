package com.example.demo.service.impl;

import com.example.demo.converter.ReplyConverter;
import com.example.demo.domain.Reply;
import com.example.demo.dto.ReplyEditDTO;
import com.example.demo.dto.ReplyDTO;
import com.example.demo.repository.ReplyRepository;
import com.example.demo.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private final ReplyRepository replyRepository;

    @Autowired
    private final ReplyConverter replyConverter;

    @Override
    public ReplyDTO create(ReplyDTO replyDTO) {
        return replyConverter.entityToDTO(replyRepository.save(replyConverter.mapToEntity(new Reply(), replyDTO)));
    }

    @Override
    public ReplyDTO update(ReplyEditDTO replyEditDTO) {
        Reply reply = replyRepository.getById(replyEditDTO.getId());
        return replyConverter.entityToDTO(replyRepository.save(replyConverter.mapToEntity(reply, replyEditDTO)));
    }

    @Override
    public ReplyDTO getById(long id) {
        return replyConverter.entityToDTO(replyRepository.getById(id));
    }

    @Override
    public void remove(long id) {
        replyRepository.deleteById(id);
    }
}
