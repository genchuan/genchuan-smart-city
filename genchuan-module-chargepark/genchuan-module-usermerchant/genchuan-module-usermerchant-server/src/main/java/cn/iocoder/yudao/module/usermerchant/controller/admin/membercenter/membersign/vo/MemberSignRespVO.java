package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membersign.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 会员签到 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MemberSignRespVO {

    @Schema(description = "签到记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1622")
    @ExcelProperty("签到记录ID")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28909")
    @ExcelProperty("用户ID")
    private Long userId;

    @Schema(description = "签到日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("签到日期")
    private LocalDate signDate;

    @Schema(description = "连续签到天数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("连续签到天数")
    private Integer continuousDays;

    @Schema(description = "本次签到获得积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("本次签到获得积分")
    private Integer point;

    @Schema(description = "本次签到获得经验", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("本次签到获得经验")
    private Integer experience;

    @Schema(description = "记录状态：1-正常，0-异常", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("记录状态：1-正常，0-异常")
    private Integer status;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "签到时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("签到时间")
    private LocalDateTime createTime;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}