package cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.personstatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 人员状态字典新增/修改 Request VO")
@Data
public class PersonStatusSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2934")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "27424")
    private String sysPersonStatusId;

    @Schema(description = "状态名称（可选值：在岗/休假/请假/离职/待入职/调岗/停薪留职）", example = "赵六")
    private String name;

    @Schema(description = "状态编码")
    private String code;

    @Schema(description = "状态（可选值：0-禁用/1-启用）", example = "1")
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