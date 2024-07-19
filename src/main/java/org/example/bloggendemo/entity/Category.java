package org.example.bloggendemo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category extends IdClass{
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datePosted;
    @OneToMany(mappedBy = "category",
            cascade = CascadeType.ALL,
    orphanRemoval = true)
    private List<Post> posts =
            new ArrayList<>();

    public void addPost(Post post) {
        post.setCategory(this);
        posts.add(post);
    }
}
