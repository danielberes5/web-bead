package com.example.demo.service;

import com.example.demo.dto.ReplyEditDTO;
import com.example.demo.dto.ReplyDTO;

public interface ReplyService {
    ReplyDTO create(ReplyDTO replyDTO);
    ReplyDTO update(ReplyEditDTO replyEditDTO);
    ReplyDTO getById(long id);
    void remove(long id);
}
