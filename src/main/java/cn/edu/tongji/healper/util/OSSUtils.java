package cn.edu.tongji.healper.util;

import cn.edu.tongji.healper.config.OSSConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectResult;

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

    public static byte[] string2bytes(String str) {
        return str.getBytes();
    }

    public static InputStream bytes2InputStream(byte[] bytes) {
        return new ByteArrayInputStream(bytes);
    }

}
