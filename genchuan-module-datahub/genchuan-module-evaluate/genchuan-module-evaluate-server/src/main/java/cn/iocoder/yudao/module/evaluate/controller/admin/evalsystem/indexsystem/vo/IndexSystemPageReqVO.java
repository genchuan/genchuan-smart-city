package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexsystem.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 指标体系分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IndexSystemPageReqVO extends PageParam {
    // 添加租户ID字段
    @Schema(description = "租户编号", example = "1024")
    private Long tenantId;
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
    @TableField(value = "`desc`")
    private String desc;

    @Schema(description = "分类总数", example = "31470")
    private Integer categoryCount;

    @Schema(description = "指标项总数", example = "24419")
    private Integer itemCount;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "14464")
    private Integer statusId;

    @Schema(description = "最近使用时间", example = "2024-29-56")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastUseTime;

    @Schema(description = "使用次数", example = "14")
    private Integer useCount;

    @Schema(description = "更新人ID（关联sys_user.user_id）")
    private String updateBy;

    @Schema(description = "创建人ID（关联sys_user.user_id）")
    private String createBy;

    @Schema(description = "创建时间（业务字段）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bizCreateTime;

    @Schema(description = "更新时间（业务字段）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bizUpdateTime;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;
    // 以下为关联子表的筛选条件
    @Schema(description = "分类名称（模糊匹配，体系下至少有一个分类匹配）")
    private String categoryName;

    @Schema(description = "指标项名称（模糊匹配，体系下至少有一个指标项匹配）")
    private String itemName;
}