package cn.iocoder.yudao.module.envirhealth.controller.admin.planstatus.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 计划状态字典新增/修改 Request VO")
@Data
public class PlanStatusSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16239")
    private Long id;

    @Schema(description = "计划状态主键（UUID）", example = "28005")
    private String sysPlanStatusId;

    @Schema(description = "状态名称", example = "张三")
    private String name;

    @Schema(description = "状态编码")
    private String code;

    @Schema(description = "状态：1-启用/0-禁用", example = "2")
    private Integer status;

    @Schema(description = "排序")
    private Integer sort;

}