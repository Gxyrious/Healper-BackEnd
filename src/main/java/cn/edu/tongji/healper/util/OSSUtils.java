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

    public static InputStream base64ToInputStream(String base64) {
        return OSSUtils.bytesToInputStream(OSSUtils.base64ToBytes(OSSUtils.getSourceFromBase64(base64)));
    }

    public static byte[] base64ToBytes(String base64) {
        return Base64.decode(base64);
    }

    public static InputStream bytesToInputStream(byte[] bytes) {
        return new ByteArrayInputStream(bytes);
    }

    public static String getImageTypeFromBase64(String imageBase64) {
        return imageBase64
                .split("/", 3)[1]
                .split(";", 2)[0];
    }

    public static String getSourceFromBase64(String imageBase64) {
        return imageBase64.split("base64,")[1];
    }
}
