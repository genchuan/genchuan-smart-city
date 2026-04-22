package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 纠纷调解分页 Request VO")
@Data
public class DisputeMediatePageReqVO extends PageParam {

    @Schema(description = "用户 ID", example = "1001")
    private Long userId;

    @Schema(description = "商户 ID", example = "5001")
    private Long merchantId;

    @Schema(description = "调解状态,关联字典 dispute_mediate_status", example = "待调解",
            allowableValues = {"待调解", "调解中", "已完成"})
    private String status;

    @Schema(description = "发起时间范围(长度 2 的数组:[起始时间, 结束时间])。" +
            "前端示例:axios.get(url,{params:{submitTime:[start,end]}}) — 不要 JSON.stringify,最终 HTTP 是两次同名 query:?submitTime=start&submitTime=end",
            example = "[\"2025-04-01 00:00:00\", \"2025-04-14 23:59:59\"]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] submitTime;

}
