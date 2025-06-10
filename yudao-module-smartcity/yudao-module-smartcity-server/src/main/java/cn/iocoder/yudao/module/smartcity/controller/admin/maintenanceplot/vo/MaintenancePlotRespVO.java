package cn.iocoder.yudao.module.smartcity.controller.admin.maintenanceplot.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 养护地块 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MaintenancePlotRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "15624")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "地块名称", example = "芋艿")
    @ExcelProperty("地块名称")
    private String plotName;

    @Schema(description = "地块描述", example = "你猜")
    @ExcelProperty("地块描述")
    private String plotDescription;

    @Schema(description = "归属信息")
    @ExcelProperty("归属信息")
    private String ownership;

    @Schema(description = "地块面积")
    @ExcelProperty("地块面积")
    private String acreage;

    @Schema(description = "纬度")
    @ExcelProperty("纬度")
    private String latitude;

    @Schema(description = "经度")
    @ExcelProperty("经度")
    private String longitude;

    @Schema(description = "绿化类型", example = "2")
    @ExcelProperty("绿化类型")
    private String greeningType;

    @Schema(description = "上次养护的日期")
    @ExcelProperty("上次养护的日期")
    private LocalDateTime lastMaintenanceDate;

    @Schema(description = "下次养护的计划日期")
    @ExcelProperty("下次养护的计划日期")
    private LocalDateTime nextMaintenanceDate;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}