package cn.iocoder.yudao.module.studentmgmt.controller.admin.coopenterprise.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 校企合作维护 Request VO")
@Data
public class CoopEnterpriseMaintainReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17018")
    private Long[] ids;

    @Schema(description = "合作开始时间，时间戳格式", requiredMode = Schema.RequiredMode.REQUIRED, example = "1744088400000")
    private LocalDateTime coopStartTime;

    @Schema(description = "合作结束时间，时间戳格式", requiredMode = Schema.RequiredMode.REQUIRED, example = "1745088400000")
    private LocalDateTime coopEndTime;


    @Schema(description = "负责系部", example = "22973")
    private Long deptId;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}