package com.example.demo.domain;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Table(name = "blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "blogId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replies = new ArrayList<Reply>(){};
}

