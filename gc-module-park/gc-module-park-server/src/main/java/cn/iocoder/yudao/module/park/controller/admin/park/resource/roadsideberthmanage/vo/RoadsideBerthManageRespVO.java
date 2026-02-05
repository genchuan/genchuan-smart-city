package cn.iocoder.yudao.module.park.controller.admin.park.resource.roadsideberthmanage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 路测泊位管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RoadsideBerthManageRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27797")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "车场ID", example = "1")
    @ExcelProperty("车场ID")
    private String parkId;

    @Schema(description = "泊位编号")
    @ExcelProperty("泊位编号")
    private String berthCode;

    @Schema(description = "路段名称", example = "赵六")
    @ExcelProperty("路段名称")
    private String roadName;

    @Schema(description = "位置描述")
    @ExcelProperty("位置描述")
    private String locationDesc;

    @Schema(description = "泊位类型", example = "2")
    @ExcelProperty("泊位类型")
    private String berthType;

    @Schema(description = "坐标X")
    @ExcelProperty("坐标X")
    private BigDecimal coordinateX;

    @Schema(description = "坐标Y")
    @ExcelProperty("坐标Y")
    private BigDecimal coordinateY;

    @Schema(description = "当前车辆")
    @ExcelProperty("当前车辆")
    private String currentCar;

    @Schema(description = "启用状态", example = "1")
    @ExcelProperty("启用状态")
    private String berthStatus;

    @Schema(description = "所属行政区划代码")
    @ExcelProperty("所属行政区划代码")
    private String areaCode;

    @Schema(description = "路侧管理信息")
    @ExcelProperty("路侧管理信息")
    private String roadsideInfo;

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