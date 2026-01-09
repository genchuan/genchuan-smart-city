package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkberthlock.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 泊位锁定记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkBerthLockRespVO {

    @Schema(description = "主键，唯一标识", example = "29444")
    @ExcelProperty("主键，唯一标识")
    private Long id;

    @Schema(description = "关联预约ID", example = "18569")
    @ExcelProperty("关联预约ID")
    private Long reservationId;

    @Schema(description = "锁定的泊位唯一标识", example = "10041")
    @ExcelProperty("锁定的泊位唯一标识")
    private Long berthId;

    @Schema(description = "锁定开始时间")
    @ExcelProperty("锁定开始时间")
    private LocalDateTime lockStartTime;

    @Schema(description = "锁定结束时间")
    @ExcelProperty("锁定结束时间")
    private LocalDateTime lockEndTime;

    @Schema(description = "泊位锁定状态", example = "2")
    @ExcelProperty("泊位锁定状态")
    private String lockStatus;

    @Schema(description = "泊位解锁原因", example = "不香")
    @ExcelProperty("泊位解锁原因")
    private String unlockReason;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

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

}
