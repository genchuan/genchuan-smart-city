package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexsystem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 指标体系新增/修改 Request VO")
@Data
public class IndexSystemSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15573")
    private Long id;

    @Schema(description = "指标体系ID（UUID）", example = "12425")
    private String systemId;

    @Schema(description = "体系名称", example = "芋艿")
    private String name;

    @Schema(description = "体系编码")
    private String code;

    @Schema(description = "适用对象类型ID（关联sys_object_type.type_id）", example = "21489")
    private String objectTypeId;

    @Schema(description = "版本号")
    private String version;

    @Schema(description = "描述信息")
    private String desc;

    @Schema(description = "分类总数", example = "31470")
    private Integer categoryCount;

    @Schema(description = "指标项总数", example = "24419")
    private Integer itemCount;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "14464")
    private Integer statusId;

    @Schema(description = "最近使用时间", example = "2024-29-56")
    private LocalDateTime lastUseTime;

    @Schema(description = "使用次数", example = "14")
    private Integer useCount;

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