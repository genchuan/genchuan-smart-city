package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.vetoitem.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 否决项分页 Request VO")
@Data
public class VetoItemPageReqVO extends PageParam {

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