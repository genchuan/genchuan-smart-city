package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import cn.idev.excel.annotation.ExcelIgnore;



@Schema(description = "管理后台 - 时段权限新增 Request VO")
@Data
public class TimePermissionCreateReqVO {

    @Schema(description = "所属场站，关联station_info.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "所属场站不能为空")
    private Long stationId;

    @Schema(description = "生效时段", requiredMode = Schema.RequiredMode.REQUIRED, example = "20:00-08:00")
    @NotBlank(message = "生效时段不能为空")
    private String timeRange;

    @Schema(description = "准入权限：内部车辆/外部车辆/无牌车/会员车辆", requiredMode = Schema.RequiredMode.REQUIRED, example = "全部车辆")
    @NotBlank(message = "准入权限不能为空")
    private String permission;

    @Schema(description = "最长停留时长，单位：分钟", example = "720")
    private Integer maxStay;

    @Schema(description = "工作日配置", example = "周一至周五")
    private String workdayConfig;

    @Schema(description = "节假日配置", example = "法定节假日")
    private String holidayConfig;

    @Schema(description = "高峰配置", example = "07:00-09:00")
    private String peakConfig;

    @Schema(description = "平峰配置", example = "09:00-17:00")
    private String offpeakConfig;

    @Schema(description = "备注", example = "夜间时段全部车辆准入")
    private String remark;

    @Schema(description = "备用字段1", example = "备用信息1")
    @ExcelIgnore
    private String reserve1;

    @Schema(description = "备用字段2", example = "备用信息2")
    @ExcelIgnore
    private String reserve2;
}
