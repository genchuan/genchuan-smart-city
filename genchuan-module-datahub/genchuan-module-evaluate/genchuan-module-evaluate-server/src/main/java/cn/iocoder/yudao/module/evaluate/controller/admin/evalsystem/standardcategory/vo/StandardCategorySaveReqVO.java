package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.standardcategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 标准分类新增/修改 Request VO")
@Data
public class StandardCategorySaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14997")
    private Long id;

    @Schema(description = "标准分类ID（UUID）", example = "15881")
    private String standardCategoryId;

    @Schema(description = "标准分类名称", example = "张三")
    private String name;

    @Schema(description = "适用指标体系ID（关联eval_index_system.system_id）", example = "26662")
    private String systemId;

    @Schema(description = "标准项数量", example = "28016")
    private Integer itemCount;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "7106")
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

    @Schema(description = "变更日志")
    private String changeLog;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}