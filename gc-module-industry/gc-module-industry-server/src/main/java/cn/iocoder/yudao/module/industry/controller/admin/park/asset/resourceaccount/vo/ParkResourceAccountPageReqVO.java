package cn.iocoder.yudao.module.industry.controller.admin.park.asset.resourceaccount.vo;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资源台账分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkResourceAccountPageReqVO extends PageParam {

    @Schema(description = "台账ID（UUID）", example = "8895")
    private String accountId;

    @Schema(description = "资产类型", example = "1")
    private String assetType;

    @Schema(description = "关联ID", example = "31051")
    private String assetExtendId;

    @Schema(description = "台账生成日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] accountDate;

    @Schema(description = "更新日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] accountUpdateDate;

    @Schema(description = "台账数据")
    private String dataContent;

    @Schema(description = "生成人ID")
    private Long generateBy;

    @Schema(description = "状态：有效/过期", example = "1")
    private String accountStatus;

    @Schema(description = "业务创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] accountCreateTime;

    @Schema(description = "业务更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] accountUpdateTime;

    @Schema(description = "业务备注", example = "你猜")
    private String accountRemark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}