package com.vincent.personal.service.impl;

import com.vincent.personal.service.IMailService;
import org.springframework.stereotype.Service;

/**
 * Created with IDEA
 * author:vincent
 * Date:2018/11/8
 */
@Service
public class MailServiceImpl implements IMailService {
    @Override
    public void sendSimpleEmail(String to, String subject, String content) {

    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) {

    }

    @Override
    public void sendFileMail(String to, String subject, String content, String filepath) {

    }
}
