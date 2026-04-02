package cn.iocoder.yudao.module.vehiclecharging.controller.admin.ratesetting.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 费率设置分页 Request VO")
@Data
public class RateSettingPageReqVO extends PageParam {

    @Schema(description = "[方案编号] 方案编号")
    private String rateCode;

    @Schema(description = "[方案名称] 方案名称", example = "王五")
    private String rateName;

    @Schema(description = "[适用场景] 适用场景")
    private String applyScene;

    @Schema(description = "[费率规则] 费率规则")
    private String rateRule;

    @Schema(description = "[生效时间] 生效时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] effectTime;

    @Schema(description = "[失效时间] 失效时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] expireTime;

    @Schema(description = "[适用场站] 适用场站")
    private String applyStation;

    @Schema(description = "[适用集团] 适用集团")
    private String applyGroup;

    @Schema(description = "[费率状态]如:未生效/已生效/已失效", example = "1")
    private String rateStatus;

    @Schema(description = "[备注] 备注", example = "你说的对")
    private String remark;

    @Schema(description = "[备用字段1] 备用字段1")
    private String reserve1;

    @Schema(description = "[备用字段2] 备用字段2")
    private String reserve2;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "创建时间-开始")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime startTime;

    @Schema(description = "创建时间-结束")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endTime;

}
