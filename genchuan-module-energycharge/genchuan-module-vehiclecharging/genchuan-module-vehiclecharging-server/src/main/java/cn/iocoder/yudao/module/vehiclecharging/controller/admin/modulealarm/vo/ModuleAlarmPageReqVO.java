package cn.iocoder.yudao.module.vehiclecharging.controller.admin.modulealarm.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 模块告警记录分页 Request VO")
@Data
public class ModuleAlarmPageReqVO extends PageParam {

    @Schema(description = "告警编号")
    private String alarmCode;

    @Schema(description = "模块名称", example = "王五")
    private String moduleName;

    @Schema(description = "异常类型ID，关联 sys_dict_data 主键（接口故障/运行卡顿/上报异常）", example = "18842")
    private Long abnormalTypeId;

    @Schema(description = "告警等级ID，关联 sys_dict_data 主键（一般/严重）", example = "18775")
    private Long alarmLevelId;

    @Schema(description = "告警时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] alarmTime;

    @Schema(description = "服务器信息")
    private String serverInfo;

    @Schema(description = "告警状态ID，关联 sys_dict_data 主键（未排查/已排查/修复中/已销账）", example = "11517")
    private Long alarmStatusId;

    @Schema(description = "排查原因", example = "不喜欢")
    private String checkReason;

    @Schema(description = "修复凭证")
    private String repairVoucher;

    @Schema(description = "修复时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] repairTime;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "删除标识：0-未删除，1-已删除")
    private Boolean deleted;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}