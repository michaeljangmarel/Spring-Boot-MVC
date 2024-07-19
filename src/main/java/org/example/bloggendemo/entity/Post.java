package org.example.bloggendemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Post extends IdClass{
    private String title;
    @Column(columnDefinition = "text")
    private String body;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datePosted;
    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;
}
