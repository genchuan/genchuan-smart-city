package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexsystem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 指标体系分页列表 VO（带关联信息）")
@Data
public class IndexSystemPageItemVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15573")
    private Long id;

    @Schema(description = "指标体系ID（UUID）", example = "12425")
    private String systemId;

    @Schema(description = "体系名称", example = "综合绩效评价指标体系")
    private String name;

    @Schema(description = "体系编码", example = "XT001")
    private String code;

    @Schema(description = "版本号", example = "V1.0")
    private String version;

    @Schema(description = "描述信息")
    private String description;

    @Schema(description = "分类总数", example = "5")
    private Integer categoryCount;

    @Schema(description = "指标项总数", example = "50")
    private Integer itemCount;

    @Schema(description = "适用对象类型ID", example = "1")
    private String objectTypeId;

    @Schema(description = "适用对象类型名称", example = "政府部门")
    private String objectTypeName;

    @Schema(description = "状态ID", example = "1")
    private Integer statusId;

    @Schema(description = "状态名称", example = "启用")
    private String statusName;

    @Schema(description = "最近使用时间", example = "2024-29-56")
    private LocalDateTime lastUseTime;

    @Schema(description = "使用次数", example = "14")
    private Integer useCount;

    @Schema(description = "更新人ID（关联sys_user.user_id）")
    private String updateBy;

    @Schema(description = "创建人ID")
    private String createBy;

    @Schema(description = "创建人姓名", example = "张三")
    private String createByName;

    @Schema(description = "创建时间（业务字段）")
    private LocalDateTime bizCreateTime;

    @Schema(description = "更新时间（业务字段）")
    private LocalDateTime bizUpdateTime;
}