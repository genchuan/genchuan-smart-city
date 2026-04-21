package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 押金方案分页 Request VO")
@Data
public class DepositPlanPageReqVO extends PageParam {

    @Schema(description = "[所属场站] 关联场站信息表 station_info", example = "652")
    private Long stationId;

    @Schema(description = "[押金金额] 单位：元")
    private BigDecimal depositAmount;

    @Schema(description = "[适用场景] 如：预约停车/预约充电/临时停车")
    private String scene;

    @Schema(description = "[状态] 如：待生效/已生效/已禁用", example = "2")
    private String status;

    @Schema(description = "[审核时间] 审核通过的时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] auditTime;

    @Schema(description = "[审核人] 关联芋道用户表 system_user", example = "31342")
    private Long auditUserId;

    @Schema(description = "[押金订单量] 产生押金的订单总数", example = "29840")
    private Integer depositOrderCount;

    @Schema(description = "[备注] 扩展说明", example = "你说的对")
    private String remark;

    @Schema(description = "[备用字段1] 预留扩展")
    private String reserve1;

    @Schema(description = "[备用字段2] 预留扩展")
    private String reserve2;

    @Schema(description = "[创建者] 创建人账号/姓名")
    private String creator;

    @Schema(description = "[更新者] 更新人账号/姓名")
    private String updater;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[更新时间] 记录最后更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}
