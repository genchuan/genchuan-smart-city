package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.garbagetransfer;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "环境卫生管理 - 垃圾转运站 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GarbageTransferRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "转运站主键（UUID）", example = "27803")
    @ExcelProperty("转运站ID")
    private String transferId;

    @Schema(description = "转运站名称", example = "王五")
    @ExcelProperty("转运站名称")
    private String name;

    @Schema(description = "转运站位置")
    @ExcelProperty("转运站位置")
    private String location;

    @Schema(description = "关联sys_area.area_code")
    @ExcelProperty("区域编码")
    private String areaCode;

    @Schema(description = "核心设备IDs，JSON")
    @ExcelProperty("核心设备编码")
    private String equipmentIds;

    @Schema(description = "关联sys_operation_status.id", example = "10758")
    @ExcelProperty("运行状态编码")
    private String operationStatusId;

    @Schema(description = "关联sys_user.id", example = "14798")
    @ExcelProperty("负责人员")
    private String managerId;

    @Schema(description = "日转运量（单位：吨）")
    @ExcelProperty("日转运量")
    private BigDecimal dailyTransferVolume;

    @Schema(description = "设备正常运行率")
    @ExcelProperty("设备正常运行率")
    private BigDecimal equipmentRate;

    @Schema(description = "环境达标率")
    @ExcelProperty("环境达标率")
    private BigDecimal environmentRate;

    @Schema(description = "预警未处理数", example = "19699")
    @ExcelProperty("预警未处理数")
    private Integer unhandledAlarmCount;

    @Schema(description = "设备待维护数", example = "19292")
    @ExcelProperty("设备待维护数")
    private Integer pendingMaintenanceCount;

    @Schema(description = "实时环境数据，JSON")
    @ExcelProperty("实时环境数据")
    private String environmentData;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}