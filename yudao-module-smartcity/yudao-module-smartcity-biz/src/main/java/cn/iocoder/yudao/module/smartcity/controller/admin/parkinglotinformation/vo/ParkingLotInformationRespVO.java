package cn.iocoder.yudao.module.smartcity.controller.admin.parkinglotinformation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 停车场信息管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkingLotInformationRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "26393")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "名称", example = "李四")
    @ExcelProperty("名称")
    private String name;

    @Schema(description = "位置")
    @ExcelProperty("位置")
    private String position;

    @Schema(description = "联系电话")
    @ExcelProperty("联系电话")
    private String contactNumber;

    @Schema(description = "车位编号")
    @ExcelProperty("车位编号")
    private String parkingSpaceNumber;

    @Schema(description = "监控设备数量")
    @ExcelProperty("监控设备数量")
    private String numberDevices;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}