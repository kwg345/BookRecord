package com.example.bookrecord;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {
    private final PostRepository postRepository;
    
    public PostController(PostRepository postRepository) {
    	this.postRepository = postRepository;
    }
    	
    // トップページ
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postRepository.searchDB());
        model.addAttribute("post", new Post()); // 新規投稿用
        return "form";
    }

    // 投稿処理
    @PostMapping("/add")
    public String addPost(@Valid Post post, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("posts", postRepository.searchDB());
            return "form";
        }
        // 返却予定日を自動計算（借りた日が入力されている場合）
	    if (post.getBorrowDate() != null) {
	        post.setReturnDate(post.getBorrowDate().plusDays(14));
	    }
        // お気に入り初期値
        post.setFavorite(false);

        postRepository.insertDB(post);
        return "redirect:/";
    }

    // 削除
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        postRepository.deleteById(id);
        return "redirect:/";
    }

    // お気に入り切替
    @PostMapping("/favorite/{id}")
    public String toggleFavorite(@PathVariable Long id) {
        postRepository.toggleFavorite(id);
        return "redirect:/";
    }
}