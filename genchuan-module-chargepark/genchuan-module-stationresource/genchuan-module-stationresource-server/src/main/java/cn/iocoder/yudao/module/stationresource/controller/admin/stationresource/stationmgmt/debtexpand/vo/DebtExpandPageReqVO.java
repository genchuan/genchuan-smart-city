package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 联合追缴拓场配置分页 Request VO")
@Data
public class DebtExpandPageReqVO extends PageParam {

    @Schema(description = "[合作场站] 关联场站信息表 station_info", example = "27111")
    private Long stationId;

    @Schema(description = "[合作类型] 如：社会停车场拓场/联合追缴", example = "2")
    private String type;

    @Schema(description = "[追缴范围] 如：本区域/跨区域/全平台")
    private String range;

    @Schema(description = "[拓场进度] 单位：%")
    private Integer progress;

    @Schema(description = "[状态] 如：未生效/已生效/已禁用", example = "1")
    private String status;

    @Schema(description = "[审核时间] 审核通过的时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] auditTime;

    @Schema(description = "[审核人] 关联芋道用户表 system_user", example = "8820")
    private Long auditUserId;

    @Schema(description = "[完成时间] 拓场完成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] finishTime;

    @Schema(description = "[追缴完成率] 追缴完成比例")
    private BigDecimal recoveryRate;

    @Schema(description = "[备注] 补充说明", example = "你猜")
    private String remark;

    @Schema(description = "[备用字段1]")
    private String reserve1;

    @Schema(description = "[备用字段2]")
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
