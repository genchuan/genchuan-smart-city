package cn.iocoder.yudao.module.smartcity.controller.admin.pointinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 监测点位 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PointInfoRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "11457")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "点位编号")
    @ExcelProperty("点位编号")
    private String pointNumber;

    @Schema(description = "点位名称", example = "王五")
    @ExcelProperty("点位名称")
    private String pointName;

    @Schema(description = "点位类型", example = "1")
    @ExcelProperty("点位类型")
    private String pointType;

    @Schema(description = "所属区域")
    @ExcelProperty("所属区域")
    private String belongingArea;

    @Schema(description = "经纬度坐标")
    @ExcelProperty("经纬度坐标")
    private String latitudeLongitudeCoordinate;

    @Schema(description = "海拔高度")
    @ExcelProperty("海拔高度")
    private String altitude;

    @Schema(description = "设备信息")
    @ExcelProperty("设备信息")
    private String deviceInfo;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}