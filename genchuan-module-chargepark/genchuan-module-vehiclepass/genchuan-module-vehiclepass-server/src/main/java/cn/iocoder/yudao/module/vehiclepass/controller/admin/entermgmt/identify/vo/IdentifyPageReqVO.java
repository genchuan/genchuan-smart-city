package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.identify.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @Schema(description = "场站名称，支持模糊查询")
    private String stationName;

    @Schema(description = "备注，支持模糊查询", example = "")
    private String remark;

    @Schema(description = "修正记录标记（0-未修正 / 1-已修正 / 2-已确认）", example = "0")
    private Integer isCorrected;

    @Schema(description = "创建时间范围。支持 yyyy-MM-dd HH:mm:ss、yyyy-MM-ddTHH:mm:ss、10位秒级时间戳、13位毫秒时间戳；GET 请求需传两个同名 createTimeRange 参数", example = "2026-04-14 00:00:00,2026-04-14 23:59:59")
    private LocalDateTime[] createTimeRange;

}