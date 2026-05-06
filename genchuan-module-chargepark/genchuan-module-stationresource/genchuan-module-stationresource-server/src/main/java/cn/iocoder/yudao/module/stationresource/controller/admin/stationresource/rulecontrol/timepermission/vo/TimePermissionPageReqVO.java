package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 时段权限分页 Request VO")
@Data
public class TimePermissionPageReqVO extends PageParam {

    @Schema(description = "[所属场站] 关联场站信息表station_info的ID", example = "30323")
    private Long stationId;

    @Schema(description = "场站名称")
    private String stationName;

    @Schema(description = "[生效时段] 时段权限生效的时间段描述")
    private String timeRange;

    @Schema(description = "[准入权限] 如：内部车辆/外部车辆/无牌车/会员车辆")
    private String permission;

    @Schema(description = "[状态] 如：待生效/已生效/已禁用", example = "1")
    private String status;

    @Schema(description = "[审核时间] 权限审核通过的时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] auditTime;

    @Schema(description = "[审核人] 关联芋道用户表system_user的ID", example = "118")
    private Long auditUserId;

    @Schema(description = "审核人名称")
    private String auditUserName;

    @Schema(description = "[使用次数] 该权限被使用的累计次数", example = "12744")
    private Integer useCount;

    @Schema(description = "[最长停留时长] 单位：分钟")
    private Integer maxStay;

    @Schema(description = "[工作日配置] 工作日时段细分配置，varchar")
    private String workdayConfig;

    @Schema(description = "[节假日配置] 节假日时段细分配置，varchar")
    private String holidayConfig;

    @Schema(description = "[高峰配置] 高峰时段细分配置，varchar")
    private String peakConfig;

    @Schema(description = "[平峰配置] 平峰时段细分配置，varchar")
    private String offpeakConfig;

    @Schema(description = "[备注] 时段权限相关备注说明", example = "随便")
    private String remark;

    @Schema(description = "[备用字段1] 预留扩展字段1")
    private String reserve1;

    @Schema(description = "[备用字段2] 预留扩展字段2")
    private String reserve2;

    @Schema(description = "[创建者] 创建人账号/姓名")
    private String creator;

    @Schema(description = "[更新者] 更新人账号/姓名")
    private String updater;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[更新时间] 记录最后更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}
