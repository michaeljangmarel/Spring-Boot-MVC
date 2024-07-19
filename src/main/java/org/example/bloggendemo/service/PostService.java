package org.example.bloggendemo.service;

import lombok.RequiredArgsConstructor;
import org.example.bloggendemo.dao.CategoryDao;
import org.example.bloggendemo.dao.PostDao;
import org.example.bloggendemo.dao.UserDao;
import org.example.bloggendemo.entity.Category;
import org.example.bloggendemo.entity.Post;
import org.example.bloggendemo.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.catalog.Catalog;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final CategoryDao categoryDao;
    private final PostDao postDao;
    private final UserDao userDao;

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public void saveUser(User user) {
        userDao.save(user);
    }
    public void saveCategory(Category category) {
        categoryDao.save(category);
    }

    public List<Category> getCategories() {
        return categoryDao.findAll();
    }

    public Category getCategory(int id) {
        return categoryDao.findById(id)
                .get();
    }
    public List<Post> getAllPosts() {
        return postDao.findAll();
    }
    @Transactional
    public void savePost(Post post) {
        Category category=categoryDao.findById(post.getCategory()
                .getId()).get();
        User user=userDao.findById(post.getUser().getId()).get();
        post.setCategory(category);
        post.setUser(user);
        postDao.save(post);
    }
}
