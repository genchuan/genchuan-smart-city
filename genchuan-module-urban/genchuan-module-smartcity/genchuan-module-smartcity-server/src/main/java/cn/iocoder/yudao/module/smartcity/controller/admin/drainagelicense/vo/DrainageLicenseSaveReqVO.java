package cn.iocoder.yudao.module.smartcity.controller.admin.drainagelicense.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 排水电子许可证信息新增/修改 Request VO")
@Data
public class DrainageLicenseSaveReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "269")
    private Long id;

    @Schema(description = "许可证编号")
    private String licenseNo;

    @Schema(description = "有效期开始日期")
    private LocalDateTime startDate;

    @Schema(description = "有效期结束日期")
    private LocalDateTime endDate;

    @Schema(description = "许可排水类型", example = "2")
    private String drainageType;

    @Schema(description = "审批单位")
    private String approvalUnit;

    @Schema(description = "状态", example = "1")
    private String licenseStatus;

}