package cn.iocoder.yudao.module.kitchen.controller.admin.sysdevice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 设备信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SysDeviceRespVO {

    @Schema(description = "[主键ID] 设备唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "23373")
    @ExcelProperty("[主键ID] 设备唯一标识")
    private Long id;

    @Schema(description = "[设备编号] 区域编码+设备类型+序号，唯一", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[设备编号] 区域编码+设备类型+序号，唯一")
    private String deviceCode;

    @Schema(description = "[设备名称] 如：后厨摄像头、AI识别设备", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("[设备名称] 如：后厨摄像头、AI识别设备")
    private String deviceName;

    @Schema(description = "[设备类型] 如：摄像头、AI识别仪", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("[设备类型] 如：摄像头、AI识别仪")
    private String deviceType;

    @Schema(description = "[所属企业ID] 关联enterprise_info.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "18117")
    @ExcelProperty("[所属企业ID] 关联enterprise_info.id")
    private String entId;

    @Schema(description = "[所属区域ID] 关联sys_area.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "14795")
    @ExcelProperty("[所属区域ID] 关联sys_area.id")
    private Long areaId;

    @Schema(description = "[状态] 如：在线/离线/故障/停用/维修中", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("[状态] 如：在线/离线/故障/停用/维修中")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[通用扩展字段1]")
    @ExcelProperty("[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    @ExcelProperty("[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    @ExcelProperty("[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    @ExcelProperty("[通用扩展字段4]")
    private String extCommon4;

}
