package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.warningalertlisttable.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 现场照片上传请求VO
 */
@Data
@Builder  // 添加这个注解
@NoArgsConstructor  // 需要无参构造器
@AllArgsConstructor // 需要全参构造器
@Schema(description = "现场照片上传请求VO")
public class ScenePhotosUploadReqVO {


    @Schema(description = "图片文件列表")
    private List<MultipartFile> files;

}
