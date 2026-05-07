package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 意见建议分页 Request VO")
@Data
public class SuggestionPageReqVO extends PageParam {

    @Schema(description = "用户 ID", example = "1001")
    private Long userId;

    @Schema(description = "意见内容(模糊匹配)", example = "充电桩")
    private String content;

    @Schema(description = "处理状态,关联字典 suggestion_status", example = "待处理",
            allowableValues = {"待处理", "处理中", "已完成"})
    private String status;

    @Schema(description = "提交时间范围(长度 2 的数组:[起始时间, 结束时间])。" +
            "前端示例:axios.get(url,{params:{submitTime:[start,end]}}) — 不要 JSON.stringify,最终 HTTP 是两次同名 query:?submitTime=start&submitTime=end",
            example = "[\"2025-04-01 00:00:00\", \"2025-04-14 23:59:59\"]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] submitTime;

}
