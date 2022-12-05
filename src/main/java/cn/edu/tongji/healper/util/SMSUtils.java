package cn.edu.tongji.healper.util;

import cn.edu.tongji.healper.config.SMSConfiguration;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.concurrent.TimeUnit;

@Service
public class SMSUtils {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public String sendMessage(String userphone) throws Exception {
        Client smsClient = SMSConfiguration.getSMSClient();
        String code = SMSUtils.getCode();
        SendSmsRequest sendSmsRequest = SMSConfiguration.getRequestWithPhone(userphone)
                .setTemplateParam("{\"code\":" + code + "}");
        SendSmsResponse sendSmsResponse = smsClient.sendSms(sendSmsRequest);
        this.redisTemplate.opsForValue().set(userphone, code, 5, TimeUnit.MINUTES);
        if ("OK".equals(sendSmsResponse.body.code)) {
            return code;
        } else {
            throw new RuntimeException(sendSmsResponse.body.getMessage());
        }
    }

    public Boolean checkCaptcha(String userphone, String code) {
        if (code.equals(this.redisTemplate.opsForValue().get(userphone))) {
            return this.redisTemplate.delete(userphone);
        } else {
            return false;
        }
    }

    private static String getCode() {
        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }

}