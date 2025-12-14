package com.example.bookrecord;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {
    private Long id;

    @NotBlank(message = "タイトルは必須です")
    private String title;

    @NotBlank(message = "著者は必須です")
    private String author;
    
    @Size(max = 50, message = "メモは50文字以内で入力してください")
    private String memo;

    private LocalDate borrowDate;

    private LocalDate returnDate;

    private Boolean favorite;
}
