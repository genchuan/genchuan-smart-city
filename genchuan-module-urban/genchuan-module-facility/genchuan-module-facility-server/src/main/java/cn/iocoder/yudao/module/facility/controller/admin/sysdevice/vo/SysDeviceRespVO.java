package cn.iocoder.yudao.module.facility.controller.admin.sysdevice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 设备 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SysDeviceRespVO {

    @Schema(description = "[主键ID] 主键，设备唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "22461")
    @ExcelProperty("[主键ID] 主键，设备唯一标识")
    private Long id;

    @Schema(description = "[设备编码] UUID格式", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[设备编码] UUID格式")
    private String deviceCode;

    @Schema(description = "[设备名称] 设备名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("[设备名称] 设备名称")
    private String name;

    @Schema(description = "[设备在线状态] 如:在线/离线/异常", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("[设备在线状态] 如:在线/离线/异常")
    private String onlineStatus;

    @Schema(description = "[设备分类] 如道路设施监测等等")
    @ExcelProperty("[设备分类] 如道路设施监测等等")
    private String category;

    @Schema(description = "[设备描述] 设备描述", example = "你说的对")
    @ExcelProperty("[设备描述] 设备描述")
    private String description;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    @ExcelProperty("[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    @ExcelProperty("[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    @ExcelProperty("[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    @ExcelProperty("[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}
