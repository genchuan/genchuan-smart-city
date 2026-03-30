package cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 户外广告分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OutdoorAdPageReqVO extends PageParam {

    @Schema(description = "广告名称，支持模糊查询")
    private String adName;

    @Schema(description = "广告类型（立柱/墙面/灯箱/电子屏），精准匹配")
    private String adType;

    @Schema(description = "所属区域编码，精准匹配")
    private String areaCode;

    @Schema(description = "所属网格编码，精准匹配")
    private String gridCode;

    @Schema(description = "审批状态（待审批/已审批/已驳回），精准匹配")
    private String approvalStatus;

    @Schema(description = "数据状态 (0-未启用，1-已启用，2-已归档)")
    private Integer dataStatus;

    @Schema(description = "审批开始时间，格式yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime startApprovalTime;

    @Schema(description = "审批结束时间，格式yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endApprovalTime;
}