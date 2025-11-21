package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectdeviceinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查巡检设备信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectDeviceInfoRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "设备ID")
    @ExcelProperty("设备ID")
    private String deviceId;

    @Schema(description = "设备编码")
    @ExcelProperty("设备编码")
    private String deviceCode;

    @Schema(description = "设备名称")
    @ExcelProperty("设备名称")
    private String deviceName;

    @Schema(description = "设备类型")
    @ExcelProperty("设备类型")
    private String deviceType;

    @Schema(description = "所属区域代码")
    @ExcelProperty("所属区域代码")
    private String areaCode;

    @Schema(description = "所属区域名称")
    @ExcelProperty("所属区域名称")
    private String areaName;

    @Schema(description = "所属网格ID")
    @ExcelProperty("所属网格ID")
    private String gridId;

    @Schema(description = "所属网格名称")
    @ExcelProperty("所属网格名称")
    private String gridName;

    @Schema(description = "关联人员ID")
    @ExcelProperty("关联人员ID")
    private String personId;

    @Schema(description = "关联人员姓名")
    @ExcelProperty("关联人员姓名")
    private String personName;

    @Schema(description = "设备状态")
    @ExcelProperty("设备状态")
    private String deviceStatus;

    @Schema(description = "安装时间")
    @ExcelProperty("安装时间")
    private LocalDate installTime;

    @Schema(description = "保修截止时间")
    @ExcelProperty("保修截止时间")
    private LocalDate warrantyEndTime;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String createUser;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime bizCreateTime;

    @Schema(description = "更新人")
    @ExcelProperty("更新人")
    private String updateUser;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime bizUpdateTime;

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