package cn.iocoder.yudao.module.envirhealth.controller.admin.handlestatus.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 处置状态字典表【通用复用】新增/修改 Request VO")
@Data
public class HandleStatusSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31515")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "20147")
    private String sysHandleStatusId;

    @Schema(description = "处置状态名称（可选值：待处置/处置中/已办结/已退回/超时未处置）", example = "赵六")
    private String name;

    @Schema(description = "处置状态编码")
    private String code;

    @Schema(description = "状态（可选值：0-禁用/1-启用）", example = "2")
    private Integer status;

    @Schema(description = "排序值")
    private Integer sort;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}