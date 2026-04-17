package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 追缴跟踪新增/修改 Request VO")
@Data
public class CollectTrackSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "追缴编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String trackNo;
    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    private String plateNo;
    @Schema(description = "追缴方式", requiredMode = Schema.RequiredMode.REQUIRED)
    private String collectMethod;
    @Schema(description = "追缴时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime collectTime;
    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED)
    private String status;
    @Schema(description = "片区ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long areaId;
    @Schema(description = "转派用户ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long transferUserId;
    @Schema(description = "追缴进度", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String collectProgress;
    @Schema(description = "操作人ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long operatorId;
}
