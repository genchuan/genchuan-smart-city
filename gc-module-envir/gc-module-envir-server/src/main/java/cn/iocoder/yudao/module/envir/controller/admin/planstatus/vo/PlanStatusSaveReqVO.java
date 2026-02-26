package cn.iocoder.yudao.module.envir.controller.admin.planstatus.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 计划状态字典新增/修改 Request VO")
@Data
public class PlanStatusSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26565")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "27348")
    private String sysPlanStatusId;

    @Schema(description = "状态名称（如未执行/执行中/已完成/异常）", example = "张三")
    private String name;

    @Schema(description = "状态编码")
    private String code;

    @Schema(description = "状态：启用/禁用", example = "1")
    private Integer status;

    @Schema(description = "排序号")
    private Integer sort;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}