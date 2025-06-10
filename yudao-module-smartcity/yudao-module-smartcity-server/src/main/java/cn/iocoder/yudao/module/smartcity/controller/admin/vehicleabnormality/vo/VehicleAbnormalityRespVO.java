package cn.iocoder.yudao.module.smartcity.controller.admin.vehicleabnormality.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 车辆异常 Response VO")
@Data
@ExcelIgnoreUnannotated
public class VehicleAbnormalityRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "29549")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "车辆编号")
    @ExcelProperty("车辆编号")
    private String vehicleNumber;

    @Schema(description = "异常发生时间")
    @ExcelProperty("异常发生时间")
    private LocalDateTime abnormalOccurrenceTime;

    @Schema(description = "异常地点")
    @ExcelProperty("异常地点")
    private String abnormalLocation;

    @Schema(description = "异常描述", example = "你猜")
    @ExcelProperty("异常描述")
    private String abnormalDescription;

    @Schema(description = "车辆状态", example = "1")
    @ExcelProperty("车辆状态")
    private String vehicleStatus;

    @Schema(description = "影响程度")
    @ExcelProperty("影响程度")
    private String degreeOfImpact;

    @Schema(description = "处理措施")
    @ExcelProperty("处理措施")
    private String handlingMeasures;

    @Schema(description = "处理时间")
    @ExcelProperty("处理时间")
    private LocalDateTime processingTime;

    @Schema(description = "处理人员")
    @ExcelProperty("处理人员")
    private String processingPersonnel;

    @Schema(description = "维修费用")
    @ExcelProperty("维修费用")
    private String maintenanceCosts;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}