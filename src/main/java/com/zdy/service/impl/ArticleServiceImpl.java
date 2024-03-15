package com.zdy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zdy.mapper.ArticleMapper;
import com.zdy.pojo.Article;
import com.zdy.pojo.PageBean;
import com.zdy.service.ArticleService;
import com.zdy.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        //補充屬性值
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);

        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // 1. 创建 PageBean 對象
        PageBean<Article> pb = new PageBean<>();

        // 2. 開啟分頁查詢 PageHelper
        PageHelper.startPage(pageNum, pageSize);

        // 3. 調用mapper
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Article> as = articleMapper.list(userId, categoryId, state);
        // Page中提供了方法,可以獲取PageHelper分頁查詢後得到的總記錄條數和當前頁數據
        Page<Article> p = (Page<Article>) as;

        // 將數據填充到PageBean對象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }


    @Override
    public Article findById(Integer id) {
        Article c = articleMapper.findById(id);
        return c;
    }

    @Override
    public void update(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.update(article);
    }

    @Override
    public void deleteById(Integer id) {articleMapper.deleteById(id);
    }
}
