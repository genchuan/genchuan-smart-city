package cn.iocoder.yudao.module.system.framework.file.config;


import io.minio.MinioClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Bean
    @ConfigurationProperties(prefix = "minio")
    public MinioProperties minioProperties() {
        return new MinioProperties();
    }

    @Bean
    public MinioClient minioClient(MinioProperties minioProperties) {
        return MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getAccessSecret())
                .build();
    }

    public static class MinioProperties {
        private String endpoint = "http://127.0.0.18:9000";
        private String publicEndpoint = "http://cloud.genchuan.cn:9000";
        private String accessKey = "minioadmin";
        private String accessSecret = "minioadmin123";
        private String bucket = "shunchang";

        // getter和setter方法
        public String getEndpoint() { return endpoint; }
        public void setEndpoint(String endpoint) { this.endpoint = endpoint; }
        public String getAccessKey() { return accessKey; }
        public void setAccessKey(String accessKey) { this.accessKey = accessKey; }
        public String getAccessSecret() { return accessSecret; }
        public void setAccessSecret(String accessSecret) { this.accessSecret = accessSecret; }
        public String getBucket() { return bucket; }
        public void setBucket(String bucket) { this.bucket = bucket; }
        public String getPublicEndpoint() { return publicEndpoint; }
        public void setPublicEndpoint(String publicEndpoint) { this.publicEndpoint = publicEndpoint; }
    }
}