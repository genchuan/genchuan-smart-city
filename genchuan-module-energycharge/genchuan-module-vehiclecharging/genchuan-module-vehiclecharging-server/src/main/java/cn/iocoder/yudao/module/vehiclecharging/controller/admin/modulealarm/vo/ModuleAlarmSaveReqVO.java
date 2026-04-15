package cn.iocoder.yudao.module.vehiclecharging.controller.admin.modulealarm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "汽车充电 - 模块告警记录新增/修改 Request VO")
@Data
public class ModuleAlarmSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31961")
    private Long id;

    @Schema(description = "告警编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "告警编号不能为空")
    private String alarmCode;

    @Schema(description = "模块名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "模块名称不能为空")
    private String moduleName;

    @Schema(description = "异常类型ID，关联 sys_dict_data 主键（接口故障/运行卡顿/上报异常）", requiredMode = Schema.RequiredMode.REQUIRED, example = "18842")
    @NotNull(message = "异常类型ID，关联 sys_dict_data 主键（接口故障/运行卡顿/上报异常）不能为空")
    private Long abnormalTypeId;

    @Schema(description = "告警等级ID，关联 sys_dict_data 主键（一般/严重）", requiredMode = Schema.RequiredMode.REQUIRED, example = "18775")
    @NotNull(message = "告警等级ID，关联 sys_dict_data 主键（一般/严重）不能为空")
    private Long alarmLevelId;

    @Schema(description = "告警时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "告警时间不能为空")
    private LocalDateTime alarmTime;

    @Schema(description = "服务器信息")
    private String serverInfo;

    @Schema(description = "告警状态ID，关联 sys_dict_data 主键（未排查/已排查/修复中/已销账）", requiredMode = Schema.RequiredMode.REQUIRED, example = "11517")
    @NotNull(message = "告警状态ID，关联 sys_dict_data 主键（未排查/已排查/修复中/已销账）不能为空")
    private Long alarmStatusId;

    @Schema(description = "排查原因", example = "不喜欢")
    private String checkReason;

    @Schema(description = "修复凭证")
    private String repairVoucher;

    @Schema(description = "修复时间")
    private LocalDateTime repairTime;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}