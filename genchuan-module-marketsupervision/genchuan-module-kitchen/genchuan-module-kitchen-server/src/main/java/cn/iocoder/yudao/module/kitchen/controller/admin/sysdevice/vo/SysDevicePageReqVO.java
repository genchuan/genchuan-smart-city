package cn.iocoder.yudao.module.kitchen.controller.admin.sysdevice.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 设备信息分页 Request VO")
@Data
public class SysDevicePageReqVO extends PageParam {

    @Schema(description = "[设备编号] 区域编码+设备类型+序号，唯一")
    private String deviceCode;

    @Schema(description = "[设备名称] 如：后厨摄像头、AI识别设备", example = "李四")
    private String deviceName;

    @Schema(description = "[设备类型] 如：摄像头、AI识别仪", example = "2")
    private String deviceType;

    @Schema(description = "[所属企业ID] 关联enterprise_info.id", example = "18117")
    private String entId;

    @Schema(description = "[所属区域ID] 关联sys_area.id", example = "14795")
    private Long areaId;

    @Schema(description = "[状态] 如：在线/离线/故障/停用/维修中", example = "1")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}
