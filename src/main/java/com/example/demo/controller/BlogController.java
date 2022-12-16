package com.example.demo.controller;

import com.example.demo.dto.BlogEditDTO;
import com.example.demo.dto.BlogDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public interface BlogController {

    public static final String BLOG_BASE = "/api/blog";
    public static final String BLOG_LIST = "/all";
    public static final String BLOG_SEARCH = "/search";
    public static final String BLOG_SEARCH_TITLE = "/title";
    public static final String BLOG_SEARCH_ID =  BLOG_SEARCH + "/id";
    public static final String BLOG_CREATE = "/create";
    public static final String BLOG_UPDATE = "/update";
    public static final String BLOG_DELETE = "/delete";


    @ApiOperation(value="Fetch all the blogs", nickname="list")
    @GetMapping(BLOG_LIST)
    ResponseEntity<List<BlogDTO>> getAllBlogs ();

    @ApiOperation(value="Fetch all the blogs with the given title", nickname = "getByTitle")
    @GetMapping(BLOG_SEARCH_TITLE)
    ResponseEntity<List<BlogDTO>> getBlogsByTitle(@ApiParam("Title of the searched blogs") @RequestBody String title);

    @ApiOperation(value="Fetch the blog with the given id", nickname = "getById")
    @GetMapping(BLOG_SEARCH_ID)
    ResponseEntity<BlogDTO> getById(@ApiParam("ID of the searched blog") @RequestBody long id);

    @ApiOperation(value="Create a blog entry", nickname="create")
    @PostMapping(BLOG_CREATE)
    ResponseEntity<BlogDTO> saveBlog(@ApiParam("Body of the blog") @RequestBody BlogEditDTO blog);

    @ApiOperation(value="Modify a given blog by ID", nickname="update")
    @PutMapping(BLOG_UPDATE)
    ResponseEntity<BlogDTO> update(@ApiParam("Modified body of the blog ") @RequestBody BlogEditDTO blog);

    @ApiOperation(value = "Delete the blog with the given id", nickname ="remove")
    @DeleteMapping(BLOG_DELETE)
    ResponseEntity<?> remove(@ApiParam("ID of the blog wished to be deleted")@RequestBody long id);
}
