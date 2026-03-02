package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.point;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 点位 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PointRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8002")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "点位主键（UUID）", example = "27395")
    @ExcelProperty("点位主键（UUID）")
    private String pointId;

    @Schema(description = "点位名称", example = "李四")
    @ExcelProperty("点位名称")
    private String pointName;

    @Schema(description = "关联sys_area.area_code")
    @ExcelProperty("关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "点位地址")
    @ExcelProperty("点位地址")
    private String pointAddress;

    @Schema(description = "经度")
    @ExcelProperty("经度")
    private BigDecimal longitude;

    @Schema(description = "纬度")
    @ExcelProperty("纬度")
    private BigDecimal latitude;

    @Schema(description = "状态：启用/停用", example = "2")
    @ExcelProperty("状态：启用/停用")
    private String status;

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