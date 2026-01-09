package cn.iocoder.yudao.module.system.framework.file;


import cn.iocoder.yudao.module.system.framework.file.config.MinioConfig;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
@Slf4j
public class FileUploadService {

    @Resource
    private MinioClient minioClient;

    @Resource
    private MinioConfig.MinioProperties minioProperties;

    public String uploadAvatar(MultipartFile file) throws IOException, ServerException,
            InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException,
            InvalidKeyException, InvalidResponseException, XmlParserException,
            InternalException {

        // 验证文件类型
        String contentType = file.getContentType();
        if (!isImageFile(contentType)) {
            throw new RuntimeException("只支持图片文件上传");
        }

        // 生成唯一文件名
        String originalFilename = file.getOriginalFilename();
        String fileExtension = getFileExtension(originalFilename);
        String fileName = "avatar/" + UUID.randomUUID() + fileExtension;

        // 上传到MinIO
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(minioProperties.getBucket())
                        .object(fileName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(contentType)
                        .build()
        );

        // 返回文件访问URL
        return minioProperties.getPublicEndpoint() + "/" + minioProperties.getBucket() + "/" + fileName;
    }

    private boolean isImageFile(String contentType) {
        return contentType != null && contentType.startsWith("image/");
    }

    private String getFileExtension(String filename) {
        if (filename == null || filename.lastIndexOf(".") == -1) {
            return ".jpg"; // 默认扩展名
        }
        return filename.substring(filename.lastIndexOf("."));
    }
}
