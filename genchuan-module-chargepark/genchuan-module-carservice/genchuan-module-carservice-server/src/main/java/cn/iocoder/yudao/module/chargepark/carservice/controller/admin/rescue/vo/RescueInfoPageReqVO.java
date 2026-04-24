package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 救援信息分页 Request VO")
@Data
public class RescueInfoPageReqVO extends PageParam {

    @Schema(description = "用户 ID", example = "1001")
    private Long userId;

    @Schema(description = "救援类型,关联字典 rescue_info_rescue_type", example = "道路救援",
            allowableValues = {"道路救援", "充电故障救援", "停车故障救援"})
    private String rescueType;

    @Schema(description = "救援状态,关联字典 rescue_info_status", example = "待派发",
            allowableValues = {"待派发", "待认领", "处理中", "已完成"})
    private String status;

    @Schema(description = "归档状态,关联字典 rescue_info_archive_status", example = "未归档",
            allowableValues = {"已归档", "未归档"})
    private String archiveStatus;

    @Schema(description = "救援位置汉字地址（模糊查询）", example = "泉州")
    private String locationName;

    @Schema(description = "创建时间范围(长度 2 的数组:[起始时间, 结束时间])。" +
            "前端示例:axios.get(url,{params:{createTime:[start,end]}}) — 不要 JSON.stringify,最终 HTTP 是两次同名 query:?createTime=start&createTime=end",
            example = "[\"2025-04-01 00:00:00\", \"2025-04-14 23:59:59\"]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "派发时间范围(折线图钻取专用;长度 2 的数组:[起, 止])。" +
            "前端示例:axios.get(url,{params:{dispatchTime:[start,end]}}) — 不要 JSON.stringify",
            example = "[\"2025-04-01 00:00:00\", \"2025-04-14 23:59:59\"]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] dispatchTime;

    @Schema(description = "完成时间范围(长度 2 的数组:[起, 止])。" +
            "前端示例:axios.get(url,{params:{finishTime:[start,end]}}) — 不要 JSON.stringify",
            example = "[\"2025-04-01 00:00:00\", \"2025-04-14 23:59:59\"]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] finishTime;

}
