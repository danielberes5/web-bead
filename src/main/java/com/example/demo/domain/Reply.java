package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "replies")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "blog_id")
    private long blogId;

    @Column(name = "content")
    private String content;
}
