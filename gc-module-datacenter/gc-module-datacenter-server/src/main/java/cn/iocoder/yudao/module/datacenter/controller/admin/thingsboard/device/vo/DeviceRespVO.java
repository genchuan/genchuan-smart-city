package cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo;


import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 设备 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DeviceRespVO {

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19775")
    @ExcelProperty("设备ID")
    private String id;

    @Schema(description = "TB租户", requiredMode = Schema.RequiredMode.REQUIRED, example = "10102")
    @ExcelProperty("TB租户")
    private String tenantId;

    @Schema(description = "客户ID", example = "4677")
    @ExcelProperty("客户ID")
    private String customerId;

    @Schema(description = "设备名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("设备名称")
    private String name;

    @Schema(description = "设备类型", example = "2")
    @ExcelProperty("设备类型")
    private String type;

    @Schema(description = "标签")
    @ExcelProperty("标签")
    private String label;

    @Schema(description = "设备实体ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28197")
    @ExcelProperty("设备实体ID")
    private String deviceProfileId;

    @Schema(description = "设备配置")
    @ExcelProperty("设备配置")
    private byte[] deviceData;

    @Schema(description = "firmware", example = "7123")
    @ExcelProperty("firmware")
    private String firmwareId;

    @Schema(description = "software", example = "23691")
    @ExcelProperty("software")
    private String softwareId;

    @Schema(description = "Additional", example = "21772")
    @ExcelProperty("Additional")
    private String externalId;

    @Schema(description = "版本")
    @ExcelProperty("版本")
    private Long version;

    // 新增属性字段
    @Schema(description = "属性列表")
    @ExcelProperty("属性列表")
    private List<DeviceAttributeRespVO> attributes;

    // 新增扩展字段
    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}