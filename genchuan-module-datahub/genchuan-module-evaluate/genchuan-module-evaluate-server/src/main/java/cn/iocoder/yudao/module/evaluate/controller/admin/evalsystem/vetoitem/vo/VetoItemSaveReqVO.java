package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.vetoitem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 否决项新增/修改 Request VO")
@Data
public class VetoItemSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15184")
    private Long id;

    @Schema(description = "否决项ID（UUID）", example = "10952")
    private String vetoItemId;

    @Schema(description = "否决项名称", example = "赵六")
    private String name;

    @Schema(description = "适用对象类型ID（关联sys_object_type.type_id）", example = "31212")
    private String objectTypeId;

    @Schema(description = "否决条件")
    private String condition;

    @Schema(description = "生效周期")
    private String validCycle;

    @Schema(description = "否决项数量", example = "20951")
    private Integer count;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "17190")
    private Integer statusId;

    @Schema(description = "更新人ID（关联sys_user.user_id）")
    private String updateBy;

    @Schema(description = "创建人ID（关联sys_user.user_id）")
    private String createBy;

    @Schema(description = "创建时间（业务字段）")
    private LocalDateTime bizCreateTime;

    @Schema(description = "更新时间（业务字段）")
    private LocalDateTime bizUpdateTime;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}