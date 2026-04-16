package cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 整改工单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class OutdoorAdOrderPageReqVO extends PageParam {

    @Schema(description = "整改工单编码（精准匹配）", example = "AD-ORDER-202603-123456")
    private String orderCode;

    @Schema(description = "关联广告ID（精准匹配）", example = "k1l2m3n4-o5p6-7890-klmn-123456789012")
    private String adId;

    @Schema(description = "关联广告名称（模糊查询）", example = "商业广场广告")
    private String adName;

    @Schema(description = "工单状态（精准匹配）", example = "整改中")
    private String orderStatus;

    @Schema(description = "处理人ID（精准匹配）", example = "n4o5p6q7-r8s9-0123-nopq-456789012345")
    private String handlerId;

    @Schema(description = "创建开始时间", example = "2026-03-01 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startCreateTime;

    @Schema(description = "创建结束时间", example = "2026-03-31 23:59:59")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endCreateTime;
}