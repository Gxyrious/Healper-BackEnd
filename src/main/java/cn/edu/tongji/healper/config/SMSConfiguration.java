package cn.edu.tongji.healper.config;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Configuration
@Component
public class SMSConfiguration {

    private static Client smsClient;

    private static String endPoint;

    private static String accessKeyId;

    private static String accessKeySecret;

    private static String signName;

    private static String templateCode;

    @Value("${aliyun.sms.endPoint}")
    public void setEndPoint(String endPoint) {
        SMSConfiguration.endPoint = endPoint;
    }

    @Value("${aliyun.sms.accessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
        SMSConfiguration.accessKeyId = accessKeyId;
    }

    @Value("${aliyun.sms.accessKeySecret}")
    public void setAccessKeySecret(String accessKeySecret) {
        SMSConfiguration.accessKeySecret = accessKeySecret;
    }

    @Value("${aliyun.sms.signName}")
    public void setSignName(String signName) {
        SMSConfiguration.signName = URLDecoder.decode(signName, StandardCharsets.UTF_8);
    }

    @Value("${aliyun.sms.templateCode}")
    public void setTemplateCode(String templateCode) {
        SMSConfiguration.templateCode = templateCode;
    }

    public static Client getSMSClient() throws Exception {
        if (smsClient == null) {
            smsClient = new Client(
                    new Config()
                            .setAccessKeyId(accessKeyId)
                            .setAccessKeySecret(accessKeySecret)
                            .setEndpoint(endPoint)
            );
        }
        return smsClient;
    }

    public static SendSmsRequest getRequestWithPhone(String phone) {
        return new SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName(signName)
                .setTemplateCode(templateCode);
    }
}
