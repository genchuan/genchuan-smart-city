package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.alarmtype;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 预警类型字典分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AlarmTypePageReqVO extends PageParam {

    @Schema(description = "预警类型主键（UUID）", example = "14444")
    private String alarmTypeId;

    @Schema(description = "预警类型名称", example = "张三")
    private String alarmName;

    @Schema(description = "描述", example = "随便")
    private String description;

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