package cn.edu.tongji.healper.util;

import cn.edu.tongji.healper.config.OSSConfiguration;
import com.aliyun.oss.OSS;
import org.bouncycastle.util.encoders.Base64;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


public class OSSUtils {

    public static String uploadStream(InputStream inputStream, String filename)  {
        OSS ossClient = OSSConfiguration.initOSSClient();
        ossClient.putObject(
                OSSConfiguration.getBucketName(),
                filename, inputStream);
        return "https://" + OSSConfiguration.getBucketName() + '.' + OSSConfiguration.getEndpoint() + '/' + filename;
    }

    public static byte[] base64ToBytes(String str) {
        return Base64.decode(str);
    }

    public static InputStream bytesToInputStream(byte[] bytes) {
        return new ByteArrayInputStream(bytes);
    }

}
