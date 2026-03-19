package cn.iocoder.yudao.module.data.framework.file;


import cn.iocoder.yudao.module.data.framework.file.config.MinioConfig;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

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
            InternalException, io.minio.errors.InsufficientDataException, io.minio.errors.ErrorResponseException {

        // 验证文件类型
        String contentType = file.getContentType();
//        if (!isImageFile(contentType)) {
////            throw exten("只支持图片文件上传");
//            throw exception(500,"只支持图片文件上传");
//        }
        if (!isAllowedFile(contentType)) {
            throw exception(400,"只支持图片或Excel文件或DOC、DOCX文件上传");
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

        // 根据类型决定返回 URL
        if (isImageFile(contentType)) {
            // 图片走外网
            return minioProperties.getPublicEndpoint() + "/" + minioProperties.getBucket() + "/" + fileName;
        } else {
            // 非图片走内网
            return minioProperties.getEndpoint() + "/" + minioProperties.getBucket() + "/" + fileName;
        }
        // 返回文件访问URL
//        return minioProperties.getPublicEndpoint() + "/" + minioProperties.getBucket() + "/" + fileName;
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

    /**
     * 校验文件类型
     */
//    private boolean isAllowedFile(String contentType) {
//
//        if (contentType == null) {
//            return false;
//        }
//
//        // 图片
//        if (contentType.startsWith("image/")) {
//            return true;
//        }
//
//        // Excel
//        if (contentType.equals("application/vnd.ms-excel")
//                || contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
//            return true;
//        }
//
//        return false;
//    }

    /**
     * 校验文件类型
     */
    private boolean isAllowedFile(String contentType) {

        if (contentType == null) {
            return false;
        }

        // 图片
        if (contentType.startsWith("image/")) {
            return true;
        }

        // Excel
        if (contentType.equals("application/vnd.ms-excel") // .xls
                || contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) { // .xlsx
            return true;
        }

        // Word
        if (contentType.equals("application/msword") // .doc
                || contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) { // .docx
            return true;
        }

        return false;
    }
}
