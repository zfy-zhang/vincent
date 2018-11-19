package com.vincent.personal.com.vincent.personal.service.impl;

import com.github.pagehelper.PageInfo;
import com.vincent.personal.modal.vo.ContentVo;
import com.vincent.personal.service.IContentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created with IDEA
 * author:vincent
 * Date:2018/11/02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ContentServcieImplTest {

    @Autowired
    private IContentService contentService;


    @Test
    public void  testConten(){
        PageInfo<ContentVo> contentVoPageInfo =  contentService.getContents(1,5);
        System.out.println(contentVoPageInfo);
    }
}
