package cn.iocoder.yudao.module.vehiclecharging.controller.admin.interconnection.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 互联互通表分页 Request VO")
@Data
public class InterconnectionApplyReqVO {

    @Schema(description = "对接编号", example = "IC20250301002")
    private String connectCode;

    @Schema(description = "第三方平台名称", example = "特来电")
    private String thirdPlatform;

    @Schema(description = "对接类型", example = "data")
    private String connectType;

    @Schema(description = "API参数", example = "{\"appId\":\"ttt\",\"secret\":\"ttt123\"}")
    private String apiParam;

    @Schema(description = "同步频率，单位：分钟", example = "10")
    private Integer syncFreq;

    @Schema(description = "备注", example = "与特来电数据互联互通对接申请")
    private String remark;

    @Schema(description = "备用字段1", example = "default_reserve1")
    private String reserve1;

    @Schema(description = "备用字段2", example = "default_reserve2")
    private String reserve2;

}