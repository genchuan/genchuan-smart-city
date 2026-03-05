package cn.iocoder.yudao.module.industry.controller.admin.park.through.specialrelease.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 特殊放行 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkSpecialReleaseRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10994")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "特殊放行ID（UUID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "23222")
    @ExcelProperty("特殊放行ID（UUID）")
    private String releaseId;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车牌")
    private String carNumber;

    @Schema(description = "放行类型：紧急开闸/特殊车辆/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("放行类型：紧急开闸/特殊车辆/其他")
    private String releaseType;

    @Schema(description = "放行原因", requiredMode = Schema.RequiredMode.REQUIRED, example = "不对")
    @ExcelProperty("放行原因")
    private String reason;

    @Schema(description = "出入口ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8386")
    @ExcelProperty("出入口ID")
    private String entryExitId;

    @Schema(description = "放行操作人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("放行操作人ID")
    private Long releaseBy;

    @Schema(description = "放行时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("放行时间")
    private LocalDateTime releaseTime;

    @Schema(description = "核验状态：已核验/未核验", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("核验状态：已核验/未核验")
    private String verifyStatus;

    @Schema(description = "核验人ID")
    @ExcelProperty("核验人ID")
    private Long verifyBy;

    @Schema(description = "核验时间")
    @ExcelProperty("核验时间")
    private LocalDateTime verifyTime;

    @Schema(description = "业务创建时间")
    @ExcelProperty("业务创建时间")
    private LocalDateTime releaseCreateTime;

    @Schema(description = "业务更新时间")
    @ExcelProperty("业务更新时间")
    private LocalDateTime releaseUpdateTime;

    @Schema(description = "业务备注", example = "你猜")
    @ExcelProperty("业务备注")
    private String releaseRemark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}