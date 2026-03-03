package cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehicletype;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 车辆类型字典 Response VO")
@Data
@ExcelIgnoreUnannotated
public class VehicleTypeRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13455")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "15117")
    @ExcelProperty("业务主键（UUID）")
    private String sysVehicleTypeId;

    @Schema(description = "类型名称（可选值：清运车/清扫车/洒水车/洗扫车/垃圾转运车/吸污车/巡查车）", example = "赵六")
    @ExcelProperty("类型名称（可选值：清运车/清扫车/洒水车/洗扫车/垃圾转运车/吸污车/巡查车）")
    private String name;

    @Schema(description = "类型编码")
    @ExcelProperty("类型编码")
    private String code;

    @Schema(description = "状态（可选值：0-禁用/1-启用）", example = "1")
    @ExcelProperty("状态（可选值：0-禁用/1-启用）")
    private Integer status;

    @Schema(description = "类型描述", example = "你说的对")
    @ExcelProperty("类型描述")
    private String description;

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