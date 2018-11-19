package com.vincent.personal.com.vincent.personal.scheduletask;

import com.vincent.personal.scheduletask.ScheduleTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created with IDEA
 * author:vincent
 * Date:2018/11/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleTaskTest {

    @Resource
    private ScheduleTask scheduleTask;

    @Test
    public void testSendString(){
        scheduleTask.process();
    }
}
