package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberpoint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 会员积分 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MemberPointRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4936")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "9042")
    @ExcelProperty("用户编号")
    private Long userId;

    @Schema(description = "变动积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("变动积分")
    private Integer changeAmount;

    @Schema(description = "变动后的总积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("变动后的总积分")
    private Integer totalPoint;

    @Schema(description = "变动类型：1-获取，2-消耗", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("变动类型：1-获取，2-消耗")
    private Integer changeType;

    @Schema(description = "变动原因", example = "不香")
    @ExcelProperty("变动原因")
    private String changeReason;

    @Schema(description = "记录状态：0-异常，1-正常", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("记录状态：0-异常，1-正常")
    private Integer status;

    @Schema(description = "核查结果")
    @ExcelProperty("核查结果")
    private String checkResult;

    @Schema(description = "核查时间")
    @ExcelProperty("核查时间")
    private LocalDateTime checkTime;

    @Schema(description = "核查人")
    @ExcelProperty("核查人")
    private String checkBy;

    @Schema(description = "业务编码", example = "25120")
    @ExcelProperty("业务编码")
    private String bizId;

    @Schema(description = "业务类型", example = "1")
    @ExcelProperty("业务类型")
    private Integer bizType;

    @Schema(description = "积分标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("积分标题")
    private String title;

    @Schema(description = "积分描述", example = "随便")
    @ExcelProperty("积分描述")
    private String description;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "变动时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("变动时间")
    private LocalDateTime createTime;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}