package com.example.demo.test;

import com.example.demo.dto.*;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static com.example.demo.controller.BlogController.*;
import static com.example.demo.controller.ReplyController.*;

import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("integration")
public class BlogApiTest extends ApiTestBase{

    @Order(0)
    @Test
    void testBlog_create(){
        BlogDTO result = given()
                .contentType(ContentType.JSON)
                .body(BLOG_EDIT_DTO_BUILDER.build())
                .when()
                .post(blogURI(BLOG_CREATE))
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(new TypeRef<BlogDTO>() {});

        assertEquals(1L, result.getId());
        assertEquals("title 1", result.getTitle());
        assertEquals("content 1", result.getContent());
        assertTrue(result.getReplies().isEmpty());
    }

    @Order(1)
    @Test
    void testBlog_update(){
        BlogDTO result = given()
                .contentType(ContentType.JSON)
                .body(BLOG_EDIT_DTO_BUILDER_MODIFY.build())
                .when()
                .put(blogURI(BLOG_UPDATE))
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(new TypeRef<BlogDTO>(){});

        assertEquals(1L,result.getId());
        assertEquals("title 2", result.getTitle());
        assertEquals("content 2", result.getContent());
        assertTrue(result.getReplies().isEmpty());
    }

    @Order(2)
    @Test
    void testBlog_reply_create(){
        ReplyDTO result = given()
                .contentType(ContentType.JSON)
                .body(REPLY_DTO_BUILDER.build())
                .when()
                .post(replyUri(REPLY_CREATE))
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(new TypeRef<ReplyDTO>(){});

        assertEquals(1L, result.getId());
        assertEquals(1L, result.getBlogId());
        assertEquals("reply content 1",result.getContent());
    }



    @Order(3)
    @Test
    void testBlog_reply_modify(){
        ReplyDTO result = given()
                .contentType(ContentType.JSON)
                .body(REPLY_EDIT_DTO_BUILDER.build())
                .when()
                .put(replyUri(REPLY_UPDATE))
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(new TypeRef<ReplyDTO>(){});

        assertEquals(1L, result.getId());
        assertEquals(1L, result.getBlogId());
        assertEquals("modified reply",result.getContent());
    }

    @Order(4)
    @Test
    void testBlog_reply_getReplyById(){
        ReplyDTO result = given()
                .contentType(ContentType.JSON)
                .body(1L)
                .when()
                .get(replyUri(REPLY_SEARCH_ID))
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(new TypeRef<ReplyDTO>(){});
        assertEquals(1L, result.getId());
        assertEquals(1L, result.getBlogId());
        assertEquals("modified reply",result.getContent());
    }

    @Order(5)
    @Test
    void testBlog_getBlogById(){
        BlogDTO result = given()
                .contentType(ContentType.JSON)
                .body(1L)
                .when()
                .get(blogURI(BLOG_SEARCH_ID))
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(new TypeRef<BlogDTO>(){});

        assertEquals(1L, result.getId());
        assertEquals("title 2", result.getTitle());
        assertEquals("content 2",result.getContent());
        assertNotNull(result.getReplies());
    }

    @Order(6)
    @Test
    void testBlog_List(){
        given()
                .contentType(ContentType.JSON)
                .body(BLOG_EDIT_DTO_BUILDER.build())
                .when()
                .post(blogURI(BLOG_CREATE))
                .then()
                .statusCode(HttpStatus.OK.value());

        List<BlogDTO> result = given()
                .when()
                .get(blogURI(BLOG_LIST))
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(new TypeRef<List<BlogDTO>>(){});
        assertEquals(2, result.size());
    }

    @Order(7)
    @Test
    void testBlog_getByTitle(){
        List<BlogDTO> result = given()
                .contentType(ContentType.JSON)
                .body("title 2")
                .when()
                .get(blogURI(BLOG_SEARCH_TITLE))
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(new TypeRef<List<BlogDTO>>() {});

        assertEquals(1, result.size());
    }

    @Order(8)
    @Test
    void testBlog_deleteBlog() {

        List<BlogDTO> result = given()
                .when()
                .get(blogURI(BLOG_LIST))
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(new TypeRef<List<BlogDTO>>() {
                });
        assertEquals(2, result.size());

        given()
                .contentType(ContentType.JSON)
                .body(1L)
                .when()
                .delete(blogURI(BLOG_DELETE))
                .then()
                .statusCode(HttpStatus.OK.value());

        List<BlogDTO> resultAfter = given()
                .when()
                .get(blogURI(BLOG_LIST))
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(new TypeRef<List<BlogDTO>>() {
                });
        assertEquals(1, resultAfter.size());
    }

    @Order(9)
    @Test
    void testBlog_reply_deleteReply() {
        given()
                .contentType(ContentType.JSON)
                .body(REPLY_DTO_BUILDER_ALT.build())
                .when()
                .post(replyUri(REPLY_CREATE))
                .then()
                .statusCode(HttpStatus.OK.value());

        given()
                .contentType(ContentType.JSON)
                .body(2L)
                .when()
                .delete(replyUri(REPLY_DELETE))
                .then()
                .statusCode(HttpStatus.OK.value());

        BlogDTO result = given()
                .contentType(ContentType.JSON)
                .body(2L)
                .when()
                .get(blogURI(BLOG_SEARCH_ID))
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(new TypeRef<BlogDTO>(){});
        assertEquals(0, result.getReplies().size());
    }

    private static String blogURI(String uriEnd){
        return BLOG_BASE + uriEnd;
    }

    private static String replyUri(String uriEnd){
        return REPLY_BASE + uriEnd;
    }


    private static final BlogEditDTO.BlogEditDTOBuilder BLOG_EDIT_DTO_BUILDER = BlogEditDTO.builder()
            .title("title 1")
            .content("content 1");
    private static final BlogEditDTO.BlogEditDTOBuilder BLOG_EDIT_DTO_BUILDER_MODIFY = BlogEditDTO.builder()
            .id(1L)
            .title("title 2")
            .content("content 2");

    private static final ReplyDTO.ReplyDTOBuilder REPLY_DTO_BUILDER = ReplyDTO.builder()
            .blogId(1L)
            .content("reply content 1");

    private static final ReplyDTO.ReplyDTOBuilder REPLY_DTO_BUILDER_ALT = ReplyDTO.builder()
            .blogId(2L)
            .content("reply content 2");

    private static final ReplyEditDTO.ReplyEditDTOBuilder REPLY_EDIT_DTO_BUILDER = ReplyEditDTO.builder()
            .id(1L)
            .content("modified reply");

}
