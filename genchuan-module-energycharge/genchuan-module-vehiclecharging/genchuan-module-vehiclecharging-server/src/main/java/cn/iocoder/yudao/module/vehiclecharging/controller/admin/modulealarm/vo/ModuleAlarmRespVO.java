package cn.iocoder.yudao.module.vehiclecharging.controller.admin.modulealarm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;
import com.fhs.core.trans.anno.Trans;
import com.fhs.core.trans.constant.TransType;
import com.fhs.core.trans.vo.VO;

import cn.iocoder.yudao.module.system.api.user.AdminUserApi;

@Schema(description = "汽车充电 - 模块告警记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ModuleAlarmRespVO implements VO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31961")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "告警编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("告警编号")
    private String alarmCode;

    @Schema(description = "模块名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("模块名称")
    private String moduleName;

    @Schema(description = "异常类型ID，关联 sys_dict_data 主键（接口故障/运行卡顿/上报异常）", requiredMode = Schema.RequiredMode.REQUIRED, example = "18842")
    @ExcelProperty("异常类型")
    private Long abnormalTypeId;

    @Schema(description = "异常类型名称")
    private String abnormalName;

    @Schema(description = "告警等级ID，关联 sys_dict_data 主键（一般/严重）", requiredMode = Schema.RequiredMode.REQUIRED, example = "18775")
    @ExcelProperty("告警等级ID，关联 sys_dict_data 主键（一般/严重）")
    private Long alarmLevelId;

    @Schema(description = "告警等级名称")
    private String alarmLevelName;

    @Schema(description = "告警时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("告警时间")
    private LocalDateTime alarmTime;

    @Schema(description = "服务器信息")
    @ExcelProperty("服务器信息")
    private String serverInfo;

    @Schema(description = "告警状态ID，关联 sys_dict_data 主键（未排查/已排查/修复中/已销账）", requiredMode = Schema.RequiredMode.REQUIRED, example = "11517")
    @ExcelProperty("告警状态ID，关联 sys_dict_data 主键（未排查/已排查/修复中/已销账）")
    private Long alarmStatusId;

    @Schema(description = "告警状态名称")
    private String alarmStatusName;

    @Schema(description = "排查原因", example = "不喜欢")
    @ExcelProperty("排查原因")
    private String checkReason;

    @Schema(description = "修复凭证")
    @ExcelProperty("修复凭证")
    private String repairVoucher;

    @Schema(description = "修复时间")
    @ExcelProperty("修复时间")
    private LocalDateTime repairTime;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    @Trans(type = TransType.AUTO_TRANS, key = AdminUserApi.PREFIX, fields = "nickname", ref = "creatorName")
    private String creator;

    @Schema(description = "创建者昵称")
    private String creatorName;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    @Trans(type = TransType.AUTO_TRANS, key = AdminUserApi.PREFIX, fields = "nickname", ref = "updaterName")
    private String updater;

    @Schema(description = "更新者昵称")
    private String updaterName;

    @Schema(description = "删除标识：0-未删除，1-已删除", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("删除标识：0-未删除，1-已删除")
    private Boolean deleted;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}
