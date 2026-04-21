package cn.iocoder.yudao.module.inspectop.controller.admin.fencemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 电子围栏新增/修改 Request VO")
@Data
public class FenceMgmtSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "围栏名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "围栏名称不能为空")
    private String name;

    @Schema(description = "围栏区域")
    private String area;

    @Schema(description = "关联巡检人员ID")
    private Long userId;

    @Schema(description = "围栏状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "围栏状态不能为空")
    private String status;

    @Schema(description = "告警触发数")
    private Integer alarmCount;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}