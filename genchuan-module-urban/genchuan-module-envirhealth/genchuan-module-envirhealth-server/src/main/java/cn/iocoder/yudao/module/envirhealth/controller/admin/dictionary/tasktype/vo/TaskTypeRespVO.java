package cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.tasktype.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 任务类型字典表【通用复用】 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TaskTypeRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18789")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "13557")
    @ExcelProperty("业务主键（UUID）")
    private String sysTaskTypeId;

    @Schema(description = "任务类型名称（可选值：保洁任务/收运任务/设施维修任务/问题处置任务/核查任务/监测任务/养护任务/污水处置任务）", example = "张三")
    @ExcelProperty("任务类型名称（可选值：保洁任务/收运任务/设施维修任务/问题处置任务/核查任务/监测任务/养护任务/污水处置任务）")
    private String name;

    @Schema(description = "任务类型编码")
    @ExcelProperty("任务类型编码")
    private String code;

    @Schema(description = "状态（可选值：0-禁用/1-启用）", example = "2")
    @ExcelProperty("状态（可选值：0-禁用/1-启用）")
    private Integer status;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}