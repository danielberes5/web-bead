package com.example.demo.controller;

import com.example.demo.dto.ReplyDTO;
import com.example.demo.dto.ReplyEditDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blog/reply")
public interface ReplyController {

    public static final String REPLY_BASE = "/api/blog/reply";
    public static final String REPLY_SEARCH_ID = "/search/id";
    public static final String REPLY_CREATE = "/create";
    public static final String REPLY_UPDATE = "/update";
    public static final String REPLY_DELETE = "/delete";

    @ApiOperation(value = "Fetch a reply with the given ID", nickname = "getById")
    @GetMapping(REPLY_SEARCH_ID)
    ResponseEntity<ReplyDTO> getById(@ApiParam("ID of the given reply") @RequestBody long replyId);

    @ApiOperation(value = "Create a reply entry", nickname="create")
    @PostMapping(REPLY_CREATE)
    ResponseEntity<ReplyDTO> create(@ApiParam("Reply which will be created") @RequestBody ReplyDTO reply);

    @ApiOperation(value = "Modify the given reply", nickname = "update")
    @PutMapping(REPLY_UPDATE)
    ResponseEntity<ReplyDTO> update(@ApiParam("Content of the reply") @RequestBody ReplyEditDTO reply);

    @ApiOperation(value = "Removes a reply with the given ID", nickname="remove")
    @DeleteMapping(REPLY_DELETE)
    ResponseEntity<?> delete(@ApiParam("ID of the reply wished to be removed") @RequestBody long replyId);
}
