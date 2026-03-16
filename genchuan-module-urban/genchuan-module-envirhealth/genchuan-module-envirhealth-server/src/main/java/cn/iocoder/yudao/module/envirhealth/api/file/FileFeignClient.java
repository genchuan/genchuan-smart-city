package cn.iocoder.yudao.module.envirhealth.api.file;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "infra-service",
        url = "http://localhost:48082",  // infra服务地址
        path = "/admin-api/infra/file")
public interface FileFeignClient {

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    CommonResult<String> uploadFile(@RequestPart("file") MultipartFile file);
}