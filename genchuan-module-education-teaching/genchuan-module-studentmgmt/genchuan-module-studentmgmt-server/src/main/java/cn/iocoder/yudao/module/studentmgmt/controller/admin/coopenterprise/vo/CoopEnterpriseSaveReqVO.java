package cn.iocoder.yudao.module.studentmgmt.controller.admin.coopenterprise.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 校企合作新增/修改 Request VO")
@Data
public class CoopEnterpriseSaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17018")
    private Long id;

    @Schema(description = "企业名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "企业名称不能为空")
    private String enterpriseName;

    @Schema(description = "企业类型：国企/民企/外企", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "企业类型：国企/民企/外企不能为空")
    private String enterpriseType;

    @Schema(description = "负责系部", requiredMode = Schema.RequiredMode.REQUIRED, example = "22973")
    @NotNull(message = "负责系部不能为空")
    private Long deptId;

    @Schema(description = "联系人")
    private String contactUser;

    @Schema(description = "联系电话")
    private String contactPhone;

    @Schema(description = "合作开始时间")
    private LocalDateTime coopStartTime;

    @Schema(description = "合作结束时间")
    private LocalDateTime coopEndTime;

    @Schema(description = "状态：合作中/已结束", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "状态：合作中/已结束不能为空")
    private String status;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}