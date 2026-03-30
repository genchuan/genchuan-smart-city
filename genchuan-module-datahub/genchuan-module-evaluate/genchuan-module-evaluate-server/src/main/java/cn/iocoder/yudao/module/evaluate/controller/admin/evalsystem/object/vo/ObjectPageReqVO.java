package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 评价对象分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ObjectPageReqVO extends PageParam {

    @Schema(description = "评价对象ID（UUID）", example = "22397")
    private String objectId;

    @Schema(description = "对象名称", example = "芋艿")
    private String name;

    @Schema(description = "对象编码")
    private String code;

    @Schema(description = "所属区域编码（关联sys_area.area_code）")
    private String areaCode;

    @Schema(description = "对象类型ID（关联sys_object_type.type_id）", example = "10168")
    private String objectTypeId;

    @Schema(description = "负责人ID（关联sys_user.user_id）", example = "16200")
    private String managerId;

    @Schema(description = "关联网格/部门ID（关联eval_related_object.related_id）", example = "24916")
    private String relatedId;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "16713")
    private String statusId;

    @Schema(description = "创建人ID（关联sys_user.user_id）")
    private String createBy;

    @Schema(description = "创建人（系统字段）")
    private String creator;
    @Schema(description = "更新人（系统字段）")
    private String updater;

    @Schema(description = "创建时间（业务字段）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bizCreateTime;

    @Schema(description = "更新人ID（关联sys_user.user_id）")
    private String updateBy;

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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] ureateTime;
    // 添加分页偏移量计算（用于MyBatis）
    public Integer getOffset() {
        if (getPageNo() == null || getPageSize() == null) {
            return 0;
        }
        return (getPageNo() - 1) * getPageSize();
    }
    // ========== 关联表字段（精确匹配，用于钻取筛选） ==========
    @Schema(description = "所属区域名称")
    private String areaName;        // 所属区域名称（精确匹配）

    @Schema(description = "对象类型名称")
    private String objectTypeName;  // 对象类型名称（精确匹配）

    @Schema(description = "负责人姓名")
    private String managerName;     // 负责人姓名（精确匹配）

    @Schema(description = "联系电话")
    private String managerPhone;    // 联系电话（精确匹配）

    @Schema(description = "关联网格/部门名称")
    private String relatedName;     // 关联网格/部门名称（精确匹配）

    @Schema(description = "状态名称")
    private String statusName;      // 状态名称（精确匹配）
}