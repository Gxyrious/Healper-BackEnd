package cn.edu.tongji.healper.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Configuration
@Component
public class OSSConfiguration {

    private volatile static OSS ossClient;

    private volatile static OSSClientBuilder ossClientBuilder;

    @Getter
    private static String endpoint;

    private static String accessKeyId;

    private static String accessKeySecret;

    @Getter
    private static String bucketName;

    @Value("${aliyun.endpoint}")
    public void setEndpoint(String endpoint) {
        OSSConfiguration.endpoint = endpoint;
    }

    @Value("${aliyun.accessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
        OSSConfiguration.accessKeyId = accessKeyId;
    }

    @Value("${aliyun.accessKeySecret}")
    public void setAccessKeySecret(String accessKeySecret) {
        OSSConfiguration.accessKeySecret = accessKeySecret;
    }

    @Value("${aliyun.bucketName}")
    public void setBucketName(String bucketName) {
        OSSConfiguration.bucketName = bucketName;
    }

    @Bean
    @Scope("prototype")
    public static OSS initOSSClient() {
        if (ossClient == null) {
            synchronized (OSSConfiguration.class) {
                if (ossClient == null) {
                    ossClient = initOSSClientBuilder()
                            .build(endpoint, accessKeyId, accessKeySecret);
                }
            }
        }
        return ossClient;
    }

    public static OSSClientBuilder initOSSClientBuilder() {
        if (ossClientBuilder == null) {
            synchronized (OSSConfiguration.class) {
                if (ossClientBuilder == null) {
                    ossClientBuilder = new OSSClientBuilder();
                }
            }
        }
        return ossClientBuilder;
    }

}
