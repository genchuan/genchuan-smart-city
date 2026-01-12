package cn.iocoder.yudao.module.industry.controller.admin.park.through.carentry.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 入场记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkCarEntryRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14030")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "入场记录ID（UUID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "25052")
    @ExcelProperty("入场记录ID（UUID）")
    private String entryId;

    @Schema(description = "车牌号码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车牌号码")
    private String carNumber;

    @Schema(description = "车辆类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("车辆类型")
    private String carType;

    @Schema(description = "入场时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("入场时间")
    private LocalDateTime entryTime;

    @Schema(description = "入场出入口ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26377")
    @ExcelProperty("入场出入口ID")
    private String entryExitId;

    @Schema(description = "所属车场ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2671")
    @ExcelProperty("所属车场ID")
    private String lotId;

    @Schema(description = "分配车位ID", example = "18134")
    @ExcelProperty("分配车位ID")
    private String spaceId;

    @Schema(description = "识别设备", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("识别设备")
    private String deviceCode;

    @Schema(description = "入场类型：正常识别/无牌车/特殊放行", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("入场类型：正常识别/无牌车/特殊放行")
    private String entryType;

    @Schema(description = "预约用户ID", example = "11265")
    @ExcelProperty("预约用户ID")
    private Long userId;

    @Schema(description = "预约ID", example = "7069")
    @ExcelProperty("预约ID")
    private String reservationId;

    @Schema(description = "业务创建时间")
    @ExcelProperty("业务创建时间")
    private LocalDateTime entryCreateTime;

    @Schema(description = "业务更新时间")
    @ExcelProperty("业务更新时间")
    private LocalDateTime entryUpdateTime;

    @Schema(description = "业务备注", example = "随便")
    @ExcelProperty("业务备注")
    private String entryRemark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}