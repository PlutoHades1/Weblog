package com.zh.sms.service;

import java.io.File;

public interface EmailService {

    /**
     * 发送激活邮件
     * @param email
     * @return
     */
    boolean activeEmail(String actUrl,String email);

    /**
     * 发送其他邮件
     */
    boolean usualEmail(String title,String text,String[] emails);

}
