package cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceclientattr.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 设备客户端属性配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DeviceClientAttrRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "属性配置ID")
    @ExcelProperty("属性配置ID")
    private String clientAttrCfgId;

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备ID")
    private String deviceId;

    @Schema(description = "设备名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备名称")
    private String deviceName;

    @Schema(description = "设备编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备编码")
    private String deviceCode;

    @Schema(description = "属性名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("属性名称")
    private String attrName;

    @Schema(description = "属性代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("属性代码")
    private String attrCode;

    @Schema(description = "属性值", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("属性值")
    private String attrValue;

    @Schema(description = "数据类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("数据类型")
    private String dataType;

    @Schema(description = "是否必选", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否必选")
    private String isRequired;

    @Schema(description = "属性说明")
    @ExcelProperty("属性说明")
    private String attrDesc;

    @Schema(description = "配置时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("配置时间")
    private LocalDateTime cfgTime;

    @Schema(description = "配置人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("配置人")
    private String cfgUser;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTimeSys;

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