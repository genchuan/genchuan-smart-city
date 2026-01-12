package cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmaintainschedule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 运维排班 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkMaintainScheduleRespVO {

    @Schema(description = "[主键ID] 排班记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "12829")
    @ExcelProperty("[主键ID] 排班记录唯一标识")
    private Long id;

    @Schema(description = "[运维人员ID] 关联 park_maintain_user.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "25396")
    @ExcelProperty("[运维人员ID] 关联 park_maintain_user.id")
    private Long maintainUserId;

    @Schema(description = "[排班日期] 排班日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[排班日期] 排班日期")
    private LocalDate scheduleDate;

    @Schema(description = "[班次类型] 早班 / 中班 / 晚班 / 全天", example = "1")
    @ExcelProperty("[班次类型] 早班 / 中班 / 晚班 / 全天")
    private String shiftType;

    @Schema(description = "[上班时间] 上班时间")
    @ExcelProperty("[上班时间] 上班时间")
    private LocalTime startTime;

    @Schema(description = "[下班时间] 下班时间")
    @ExcelProperty("[下班时间] 下班时间")
    private LocalTime endTime;

    @Schema(description = "[状态] 排班状态：正常 / 调班 / 取消", example = "1")
    @ExcelProperty("[状态] 排班状态：正常 / 调班 / 取消")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[通用扩展字段1]")
    @ExcelProperty("[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    @ExcelProperty("[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    @ExcelProperty("[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    @ExcelProperty("[通用扩展字段4]")
    private String extCommon4;

    @Schema(description = "[备注] 排班相关备注说明", example = "随便")
    @ExcelProperty("[备注] 排班相关备注说明")
    private String remark;

}
