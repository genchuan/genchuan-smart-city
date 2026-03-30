package cn.iocoder.yudao.module.waterdetection.controller.admin.waterusecategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 用水性质分类管理新增/修改 Request VO")
@Data
public class WaterUseCategorySaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "用户编号不能为空")
    private String userCode;

    @Schema(description = "用水性质", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "用水性质不能为空")
    private String waterUseType;

    @Schema(description = "用水定额(立方米)")
    private Double waterQuota;

    @Schema(description = "分类日期")
    private LocalDateTime categoryDate;

}