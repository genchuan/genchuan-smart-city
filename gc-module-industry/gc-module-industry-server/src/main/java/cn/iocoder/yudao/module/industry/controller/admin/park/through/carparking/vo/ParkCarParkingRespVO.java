package cn.iocoder.yudao.module.industry.controller.admin.park.through.carparking.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 在停车辆 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkCarParkingRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2295")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "在停记录ID（UUID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "7852")
    @ExcelProperty("在停记录ID（UUID）")
    private String parkingId;

    @Schema(description = "入场记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8656")
    @ExcelProperty("入场记录ID")
    private String entryId;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车牌")
    private String carNumber;

    @Schema(description = "所属车场ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9294")
    @ExcelProperty("所属车场ID")
    private String lotId;

    @Schema(description = "车位ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30221")
    @ExcelProperty("车位ID")
    private String spaceId;

    @Schema(description = "已停放时长（分钟）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("已停放时长（分钟）")
    private Integer parkingTime;

    @Schema(description = "状态：正常/疑似套牌/异常", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态：正常/疑似套牌/异常")
    private String parkingStatus;

    @Schema(description = "疑似套牌原因", example = "不喜欢")
    @ExcelProperty("疑似套牌原因")
    private String suspiciousReason;

    @Schema(description = "异常原因", example = "不香")
    @ExcelProperty("异常原因")
    private String abnormalReason;

    @Schema(description = "业务更新时间")
    @ExcelProperty("业务更新时间")
    private LocalDateTime parkingUpdateTime;

    @Schema(description = "业务备注", example = "你猜")
    @ExcelProperty("业务备注")
    private String parkingRemark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}