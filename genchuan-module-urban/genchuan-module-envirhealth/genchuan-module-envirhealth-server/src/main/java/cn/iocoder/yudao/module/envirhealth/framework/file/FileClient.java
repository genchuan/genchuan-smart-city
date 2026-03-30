package cn.iocoder.yudao.module.envirhealth.framework.file;

import cn.iocoder.yudao.module.infra.api.file.FileApi;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;
import java.io.IOException;

/**
 * 适配你当前最新版 Yudao 的文件上传客户端
 * 完全匹配你提供的 FileApi 接口
 */
@Component
public class FileClient {

    @Resource
    private FileApi fileApi;

    /**
     * 上传图片（完全匹配官方API）
     */
    public String uploadFile(MultipartFile file) {
        try {
            // 直接使用官方提供的 default 方法！最简单、最稳
            return fileApi.createFile(
                    file.getBytes(),        // 文件内容
                    file.getOriginalFilename(), // 文件名
                    null,                   // 目录（不传）
                    file.getContentType()   // 文件类型
            );
        } catch (IOException e) {
            throw new RuntimeException("图片上传失败：" + e.getMessage());
        }
    }
}