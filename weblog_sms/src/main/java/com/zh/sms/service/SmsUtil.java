package com.zh.sms.service;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;

import java.io.IOException;

/**
 * 短信工具类
 * @author Administrator
 *
 */
public class SmsUtil {
    // 短信应用SDK AppID
    private static final int appid = 1400172039;

    // 短信应用SDK AppKey
    private static final String appkey = "f48b3be46b4115c33d8ed435d56a31a8";

    // 需要发送短信的手机号码
    String[] phoneNumbers = {"18627202390", "18694046573"};


    // 短信模板ID，需要在短信应用中申请
    private static final int templateId = 7839;
    // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请

    // 签名
    // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`
    private static final String smsSign = "腾讯云";

    /**
     * 单发短信
     */
    public void ff(){
        try {
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);

            SmsSingleSenderResult result = ssender.send(0, "86", phoneNumbers[0],
                    "【腾讯云】您的验证码是: 5678", "", "");
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
    }

    /**
     * 指定模板ID单发短信
     */
    public void ff2(){
        try {
            String[] params = {"5678"};
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers[0],
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.println(result);
        } catch (HTTPException e) {
            e.printStackTrace();    // HTTP响应码错误
        } catch (JSONException e) {
            e.printStackTrace();    // json解析错误
        } catch (IOException e) {
            e.printStackTrace();    // 网络IO错误
        }
    }
}