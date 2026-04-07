package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingratio.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 分账比例分页 Request VO")
@Data
public class SharingRatioPageReqVO extends PageParam {

    @Schema(description = "方案编号，唯一")
    private String sharingCode;

    @Schema(description = "方案名称", example = "芋艿")
    private String sharingName;

    @Schema(description = "合作方")
    private String cooperator;

    @Schema(description = "分账类型", example = "2")
    private String sharingType;

    @Schema(description = "分账比例（%）")
    private BigDecimal sharingRatio;

    @Schema(description = "适用场站，多个用逗号分隔")
    private String applyStation;

    @Schema(description = "适用渠道，多个用逗号分隔")
    private String applyChannel;

    @Schema(description = "生效时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] effectTime;

    @Schema(description = "失效时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] expireTime;

    @Schema(description = "分账状态：未生效/已生效/已失效", example = "1")
    private String sharingStatus;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}