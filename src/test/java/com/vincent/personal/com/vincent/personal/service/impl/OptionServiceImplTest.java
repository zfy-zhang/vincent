package com.vincent.personal.com.vincent.personal.service.impl;

import com.vincent.personal.dto.Types;
import com.vincent.personal.service.IOptionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created with IDEA
 * author:vincent
 * Date:2018/11/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OptionServiceImplTest {

    @Resource
    IOptionService optionService;

    @Test
    public void testInsertOption(){
        optionService.insertOption(Types.BLOCK_IPS.getType(),"123");
    }
}
