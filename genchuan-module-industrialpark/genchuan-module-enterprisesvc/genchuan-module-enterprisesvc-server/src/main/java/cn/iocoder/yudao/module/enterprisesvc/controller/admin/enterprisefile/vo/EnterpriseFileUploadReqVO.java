package cn.iocoder.yudao.module.enterprisesvc.controller.admin.enterprisefile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Schema(description = "管理后台 - 企业档案资质文件上传 Request VO")
@Data
public class EnterpriseFileUploadReqVO {

    @Schema(description = "企业档案 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "企业档案 ID 不能为空")
    private Long id;

    @Schema(description = "资质文件", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "资质文件不能为空")
    private MultipartFile file;

}
