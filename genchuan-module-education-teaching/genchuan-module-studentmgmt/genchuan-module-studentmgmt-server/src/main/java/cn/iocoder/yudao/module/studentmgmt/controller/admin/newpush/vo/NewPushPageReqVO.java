package cn.iocoder.yudao.module.studentmgmt.controller.admin.newpush.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 迎新推送分页 Request VO")
@Data
public class NewPushPageReqVO extends PageParam {

    @Schema(description = "推送任务名称", example = "张三")
    private String taskName;

    @Schema(description = "推送内容")
    private String pushContent;

    @Schema(description = "推送人数")
    private Integer pushNum;

    @Schema(description = "推送时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] pushTime;

    @Schema(description = "推送完成率")
    private BigDecimal finishRate;

    @Schema(description = "状态：未推送/已推送", example = "2")
    private String status;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}