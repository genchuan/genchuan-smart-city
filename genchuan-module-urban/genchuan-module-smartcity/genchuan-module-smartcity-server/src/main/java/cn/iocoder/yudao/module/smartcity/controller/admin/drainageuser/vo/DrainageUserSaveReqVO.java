package cn.iocoder.yudao.module.smartcity.controller.admin.drainageuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 排水户信息新增/修改 Request VO")
@Data
public class DrainageUserSaveReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "统一社会信用代码", example = "企业唯一标识")
    private String creditCode;

    @Schema(description = "排水户名称", example = "商户注册全称")
    private String userName;

    @Schema(description = "行业类别")
    private String industryType;

    @Schema(description = "排水户分类")
    private String userType;

    @Schema(description = "月均用水量（吨）")
    private String waterUsage;

    @Schema(description = "排水管网接入点坐标")
    private String drainagePoint;

    @Schema(description = "预处理设施清单")
    private String preTreatment;

}