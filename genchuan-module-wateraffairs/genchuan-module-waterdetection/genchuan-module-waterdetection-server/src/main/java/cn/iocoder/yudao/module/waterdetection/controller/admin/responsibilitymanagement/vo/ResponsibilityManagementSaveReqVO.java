package cn.iocoder.yudao.module.waterdetection.controller.admin.responsibilitymanagement.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 责任单位及责任人管理新增/修改 Request VO")
@Data
public class ResponsibilityManagementSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "责任类型(主体责任/监管责任/运行管理责任)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "责任类型(主体责任/监管责任/运行管理责任)不能为空")
    private String responsibilityType;

    @Schema(description = "责任单位", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "责任单位不能为空")
    private String responsibleUnit;

    @Schema(description = "责任人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "责任人姓名不能为空")
    private String responsiblePerson;

    @Schema(description = "职务")
    private String position;

    @Schema(description = "联系方式")
    private String contactInfo;

    @Schema(description = "责任范围")
    private String responsibilityScope;

}