package cn.iocoder.yudao.module.studentmgmt.controller.admin.promotemgmt.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 宣传管理分页 Request VO")
@Data
public class PromoteMgmtPageReqVO extends PageParam {

    @Schema(description = "宣传任务名称", example = "王五")
    private String taskName;

    @Schema(description = "宣传站点")
    private String site;

    @Schema(description = "宣传人数")
    private Integer promoteNum;

    @Schema(description = "意向学生数")
    private Integer intentNum;

    @Schema(description = "执行人")
    private String executeUser;

    @Schema(description = "执行时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] executeTime;

    @Schema(description = "状态：未执行/已执行", example = "1")
    private String status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}