package cn.iocoder.yudao.module.waterdetection.controller.admin.testingagency.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 检测机构资质管理新增/修改 Request VO")
@Data
public class TestingAgencySaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "机构编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "机构编号不能为空")
    private String agencyCode;

    @Schema(description = "机构名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "机构名称不能为空")
    private String agencyName;

    @Schema(description = "资质证书编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "资质证书编号不能为空")
    private String certificateNo;

    @Schema(description = "检测范围")
    private String testingScope;

    @Schema(description = "有效期至")
    private LocalDateTime validDate;

    @Schema(description = "发证单位")
    private String issuingAuthority;

}