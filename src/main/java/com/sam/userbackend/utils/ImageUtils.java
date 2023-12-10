package com.sam.userbackend.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.UUID;

@Component
public class ImageUtils {

    private String secretId = "";

    private String secretKey = "";

    private String region = "";

    private String bucketName = "";

    private COSClient cosClient;

    /**
     * 初始化客户端
     */
    private void initCosClient() {
        BasicCOSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        // 生成 cos 客户端
        cosClient = new COSClient(cred, clientConfig);
    }

    /**
     * 上传文件
     */
    public String upload(String directory, File file) {
        initCosClient();
        try {
            String name = file.getName();
            String key = directory + UUID.randomUUID() + name.substring(name.lastIndexOf("."));
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
            cosClient.putObject(putObjectRequest);
            return "https://image-1309124269.cos.ap-nanjing.myqcloud.com/" + key;
        } catch (CosClientException clientException) {
            clientException.printStackTrace();
            return "";
        }
    }
}
