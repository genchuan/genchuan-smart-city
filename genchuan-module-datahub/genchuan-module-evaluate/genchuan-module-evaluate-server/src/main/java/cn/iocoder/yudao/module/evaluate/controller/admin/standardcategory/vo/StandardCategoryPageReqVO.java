package cn.iocoder.yudao.module.evaluate.controller.admin.standardcategory.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 标准分类分页 Request VO")
@Data
public class StandardCategoryPageReqVO extends PageParam {

    @Schema(description = "标准分类名称", example = "芋艿")
    private String name;

    @Schema(description = "适用指标体系ID（关联eval_index_system.system_id）", example = "27764")
    private Long systemId;

    @Schema(description = "指标体系名称", example = "教育评估")
    private String systemName;

    @Schema(description = "标准项数量", example = "15548")
    private Integer itemCount;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "17182")
    private Integer statusId;

    @Schema(description = "最近使用时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastUseTime;

    @Schema(description = "使用次数", example = "29832")
    private Integer useCount;

    @Schema(description = "创建时间（业务字段）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bizCreateTime;

    @Schema(description = "更新时间（业务字段）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bizUpdateTime;

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

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "创建人姓名", example = "张三")
    private String creatorName;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "更新人姓名", example = "李四")
    private String updaterName;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}