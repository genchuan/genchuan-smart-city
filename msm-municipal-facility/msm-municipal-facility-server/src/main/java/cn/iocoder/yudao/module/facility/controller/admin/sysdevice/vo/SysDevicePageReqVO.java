package cn.iocoder.yudao.module.facility.controller.admin.sysdevice.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 设备分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SysDevicePageReqVO extends PageParam {

    @Schema(description = "[设备编码] UUID格式")
    private String deviceCode;

    @Schema(description = "[设备名称] 设备名称", example = "赵六")
    private String name;

    @Schema(description = "[设备在线状态] 如:在线/离线/异常", example = "2")
    private String onlineStatus;

    @Schema(description = "[设备分类] 如道路设施监测等等")
    private String category;

    @Schema(description = "[设备描述] 设备描述", example = "你说的对")
    private String description;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}
