package cn.iocoder.yudao.module.energymgmt.controller.admin.energymonitor.datacollect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 能耗采集 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EnergyCollectPageRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30458")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "设备名称", example = "李四")
    @ExcelProperty("设备名称")
    private String deviceName;

    @Schema(description = "设备类型：电表/水表/气表", example = "2")
    @ExcelProperty("设备类型：电表/水表/气表")
    private String deviceType;

    @Schema(description = "能耗类型：电/水/气/热", example = "1")
    @ExcelProperty("能耗类型：电/水/气/热")
    private String energyType;

    @Schema(description = "采集时间")
    @ExcelProperty("采集时间")
    private LocalDateTime collectTime;

    @Schema(description = "采集状态：采集正常/采集异常", example = "2")
    @ExcelProperty("采集状态：采集正常/采集异常")
    private String collectStatus;

    @Schema(description = "能耗数值")
    @ExcelProperty("能耗数值")
    private BigDecimal energyValue;

    @Schema(description = "采集频率，单位分钟")
    @ExcelProperty("采集频率，单位分钟")
    private Integer collectFreq;

    @Schema(description = "异常次数", example = "26647")
    @ExcelProperty("异常次数")
    private Integer exceptionCount;

    @Schema(description = "操作人账号")
    @ExcelProperty("操作人账号")
    private String handleUser;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}