package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.identify.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 车牌识别分页 Request VO")
@Data
public class IdentifyPageReqVO extends PageParam {

    @Schema(description = "车牌，支持模糊查询", example = "闽C12345")
    private String plateNo;

    @Schema(description = "车牌颜色（蓝牌/黄牌/绿牌/其他）", example = "蓝牌")
    private String plateColor;

    @Schema(description = "置信度", example = "98.50")
    private BigDecimal confidence;

    @Schema(description = "识别状态（识别成功/识别失败）", example = "识别成功")
    private String status;

    @Schema(description = "场站ID", example = "1")
    private Long stationId;

    @Schema(description = "备注，支持模糊查询", example = "")
    private String remark;

    @Schema(description = "修正记录标记（0-未修正 / 1-已修正 / 2-已确认）", example = "0")
    private Integer isCorrected;

}