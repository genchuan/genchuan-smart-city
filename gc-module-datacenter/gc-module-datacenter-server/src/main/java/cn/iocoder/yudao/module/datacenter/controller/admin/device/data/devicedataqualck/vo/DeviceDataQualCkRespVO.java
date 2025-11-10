package cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicedataqualck.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 设备数据质量检查 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DeviceDataQualCkRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "质量检查ID")
    @ExcelProperty("质量检查ID")
    private String qualCkId;

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备ID")
    private String deviceId;

    @Schema(description = "设备名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备名称")
    private String deviceName;

    @Schema(description = "设备编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备编码")
    private String deviceCode;

    @Schema(description = "检查批次", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("检查批次")
    private String ckBatch;

    @Schema(description = "检查时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("检查时间")
    private LocalDateTime ckTime;

    @Schema(description = "检查项", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("检查项")
    private String ckItem;

    @Schema(description = "检查结果", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("检查结果")
    private String ckResult;

    @Schema(description = "错误描述")
    @ExcelProperty("错误描述")
    private String errorDesc;

    @Schema(description = "整改建议")
    @ExcelProperty("整改建议")
    private String rectifySugg;

    @Schema(description = "检查人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("检查人")
    private String ckUser;

    @Schema(description = "整改状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("整改状态")
    private String rectifyStatus;

    @Schema(description = "整改时间")
    @ExcelProperty("整改时间")
    private LocalDateTime rectifyTime;

    @Schema(description = "整改人")
    @ExcelProperty("整改人")
    private String rectifyUser;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}