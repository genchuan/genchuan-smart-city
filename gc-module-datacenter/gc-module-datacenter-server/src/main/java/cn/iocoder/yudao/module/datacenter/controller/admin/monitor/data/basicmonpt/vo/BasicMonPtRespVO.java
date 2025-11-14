package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 基础监测点位 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BasicMonPtRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "点位ID")
    @ExcelProperty("点位ID")
    private String ptId;

    @Schema(description = "点位名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("点位名称")
    private String ptName;

    @Schema(description = "点位编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("点位编码")
    private String ptCode;

    @Schema(description = "点位类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("点位类型")
    private String ptType;

    @Schema(description = "所属区域代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属区域代码")
    private String areaCode;

    @Schema(description = "所属区域名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属区域名称")
    private String areaName;

    @Schema(description = "关联设备编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联设备编码")
    private String deviceCode;

    @Schema(description = "关联设备名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联设备名称")
    private String deviceName;

    @Schema(description = "点位坐标X(度)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("点位坐标X(度)")
    private BigDecimal ptCoordX;

    @Schema(description = "点位坐标Y(度)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("点位坐标Y(度)")
    private BigDecimal ptCoordY;

    @Schema(description = "负责人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("负责人ID")
    private Long mngrId;

    @Schema(description = "负责人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("负责人姓名")
    private String mngrName;

    @Schema(description = "负责人联系方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("负责人联系方式")
    private String mngrPhone;

    @Schema(description = "点位状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("点位状态")
    private Boolean ptStatus;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
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