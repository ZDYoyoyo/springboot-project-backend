package com.zdy.service;

import com.zdy.pojo.Article;
import com.zdy.pojo.PageBean;

public interface ArticleService {
    // 新增文章
    void add(Article article);

    // 條件分頁列表查詢
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    void deleteById(Integer id);

    void update(Article article);

    Article findById(Integer id);
}
