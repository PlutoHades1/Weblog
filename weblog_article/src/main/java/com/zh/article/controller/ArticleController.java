package com.zh.article.controller;

import com.zh.article.entity.Article;
import com.zh.article.exception.NotDataException;
import com.zh.article.service.ArticleService;
import com.zh.common.entity.RResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @DeleteMapping("/delete{id}")
    public RResult delete(@PathVariable("id") Integer id){
        articleService.delete(id);
        return RResult.success("删除成功");
    }

    @PostMapping("/add")
    public RResult add(@RequestAttribute Map<String,String> loginInfo, @Valid Article article, Errors errors){
        //校验数据
        if (errors.hasErrors())
            throw new NotDataException("发表失败,再试一次吧");

        //添加作者信息
        article.setAuthorId(Integer.parseInt(loginInfo.get("id")));
        article.setAuthorName(loginInfo.get("username"));
        articleService.insert(article);

        return RResult.success("发表成功");
    }

    @PutMapping("/update")
    public RResult update(@Valid Article article,Errors errors){
        //校验数据
        if (errors.hasErrors())
            throw new NotDataException("保存失败,再试一次吧");

        articleService.update(article);
        return RResult.success("修改成功");
    }



}
