package com.example.demo.controller.impl;

import com.example.demo.controller.ReplyController;
import com.example.demo.dto.ReplyEditDTO;
import com.example.demo.dto.ReplyDTO;
import com.example.demo.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReplyControllerImpl implements ReplyController {

    @Autowired
    private final ReplyService replyService;

    @Override
    public ResponseEntity<ReplyDTO> getById(@RequestBody long replyId){
        return ResponseEntity.ok().body(replyService.getById(replyId));
    }

    @Override
    public ResponseEntity<ReplyDTO> create(@RequestBody ReplyDTO reply) {
        return ResponseEntity.ok().body(replyService.create(reply));
    }

    @Override
    public ResponseEntity<ReplyDTO> update(@RequestBody ReplyEditDTO reply) {
        return ResponseEntity.ok().body(replyService.update(reply));
    }

    @Override
    public ResponseEntity<?> delete(@RequestBody long replyId){
        replyService.remove(replyId);
        return ResponseEntity.ok().build();
    }
}
