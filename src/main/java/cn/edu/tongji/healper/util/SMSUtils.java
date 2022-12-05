package cn.edu.tongji.healper.util;

import cn.edu.tongji.healper.config.SMSConfiguration;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 短信发送工具类
 */
public class SMSUtils {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    private static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public static String sendMessage(String userphone) throws Exception {
        Client smsClient = SMSConfiguration.getSMSClient();
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        SendSmsRequest sendSmsRequest = SMSConfiguration.getRequestWithPhone(userphone)
                .setTemplateParam("{\"code\":" + code + "}");
        SendSmsResponse sendSmsResponse = smsClient.sendSms(sendSmsRequest);
        if ("OK".equals(sendSmsResponse.body.code)) {
            return code;
        } else {
            throw new RuntimeException(sendSmsResponse.body.getMessage());
        }
    }

    public static String getCode(int length) {
        Random random = new Random();
        List<Character> code = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            String s = String.valueOf(random.nextInt(9));
            code.add(s.charAt(0));
        }
        return String.valueOf(code);
    }

}