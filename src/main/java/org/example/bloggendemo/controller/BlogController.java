package org.example.bloggendemo.controller;

import lombok.RequiredArgsConstructor;
import org.example.bloggendemo.entity.Category;
import org.example.bloggendemo.entity.Post;
import org.example.bloggendemo.entity.User;
import org.example.bloggendemo.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class BlogController {
    private final PostService postService;
    @GetMapping("/")
    public String index(){
        return "redirect:/home";
    }
    @RequestMapping("/home")
    public String home(Model model){
        model.addAttribute("category",
                new Category());
        model.addAttribute("user", new User());
        model.addAttribute("post", new Post());
        model.addAttribute("users", postService.getAllUsers());
        model.addAttribute("categories", postService.getCategories());
        return "pages/home";
    }
    @PostMapping("/save-post")
    public String savePost(Post post,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "pages/home";
        }
        postService.savePost(post);
        return "redirect:/home";
    }
    @GetMapping("/list-post")
    public String listPost(Model model){
        model.addAttribute("posts"
                ,postService.getAllPosts());
        return "pages/posts";
    }

    @PostMapping("/save-category")
    public String saveCategory(Category category,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "pages/home";
        }
        postService.saveCategory(category);
        return "redirect:/home";
    }
    @PostMapping("/save-user")
    public String saveUser(User user,BindingResult result){
        if(result.hasErrors()){
            return "pages/home";
        }
        postService.saveUser(user);
        return "redirect:/home";
    }
    @GetMapping("/list-category")
    public String listCategory(Model model){
        model.addAttribute("categories",
                postService.getCategories());

        return "pages/category";
    }
    @GetMapping("/list-user")
    public String listUser(Model model){
        model.addAttribute("users",
                postService.getAllUsers());
        return "pages/user";
    }
    @GetMapping("/update-category")
    public String updateCategory(@RequestParam("id")int id,
                                 Model model){
        model.addAttribute("category",postService.getCategory(id));
        return "pages/updateCategory";
    }


}
