package com.xuecheng.manage_cms.web.controller;

import com.xuecheng.api.controller.PostPageControllerApi;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.service.PostPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 页面发布的接口
 * @author: DaiXG
 * @createTime: 2021-06-30 16:02:00 周三
 */
@RestController
public class PostPageController implements PostPageControllerApi {

    @Autowired
    private PostPageService postPageService;

    @Override
    @GetMapping("/cms/post/{pageId}")
    public ResponseResult postPage(@PathVariable("pageId") String pageId) {
        return postPageService.postPage(pageId);
    }

}
