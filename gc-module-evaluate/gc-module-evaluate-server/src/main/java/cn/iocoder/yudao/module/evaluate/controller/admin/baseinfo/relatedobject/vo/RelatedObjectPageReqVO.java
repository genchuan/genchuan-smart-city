package cn.iocoder.yudao.module.evaluate.controller.admin.baseinfo.relatedobject.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 关联对象分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RelatedObjectPageReqVO extends PageParam {

    @Schema(description = "关联对象ID（UUID）", example = "25166")
    private String relatedId;

    @Schema(description = "关联对象名称", example = "芋艿")
    private String relatedName;

    @Schema(description = "关联对象类型：关联sys_object_type.type_id", example = "1fd")
    private String relatedType;

    @Schema(description = "上级关联对象ID（关联eval_related_object.related_id）", example = "4100")
    private String parentId;

    @Schema(description = "关联对象编码",example = "ffff")
    private String relatedCode;

    @Schema(description = "更新人，关联sys_user.user_id",example = "dfdfdf1")
    private String updateBy;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "489")
    private Integer statusId;

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

}