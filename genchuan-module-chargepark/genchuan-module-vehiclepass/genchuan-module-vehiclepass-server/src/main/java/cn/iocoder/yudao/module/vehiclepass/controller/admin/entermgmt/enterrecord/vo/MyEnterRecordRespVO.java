package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.enterrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "入场记录分页响应 VO")
@Data
public class MyEnterRecordRespVO {

    @Schema(description = "主键 ID", example = "1")
    private Long id;

    @Schema(description = "车牌", example = "闽C12345")
    private String plateNo;

    @Schema(description = "车牌颜色：蓝牌/黄牌/绿牌/其他", example = "蓝牌")
    private String plateColor;

    @Schema(description = "车位编号", example = "A001")
    private String spaceNo;

    @Schema(description = "入场时间 时间戳", example = "1775011986")
    private LocalDateTime enterTime;

    @Schema(description = "记录类型：自动识别/人工补录", example = "自动识别")
    private String recordType;

    @Schema(description = "记录状态：正常记录/异常记录", example = "正常记录")
    private String status;

    @Schema(description = "场地名称", example = "XX停车场")
    private String stationName;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "佐证图片地址")
    private String proofImage;

    @Schema(description = "修正日志标记：false-未修正 true-已修正", example = "false")
    private Boolean isCorrected;

    @Schema(description = "备用字段 1")
    private String reserve1;


    @Schema(description = "备用字段 2")
    private String reserve2;

    @Schema(description = "创建者", example = "admin")
    private String creator;

    @Schema(description = "更新者", example = "admin")
    private String updater;

    @Schema(description = "创建时间 时间戳", example = "1775011986")
    private LocalDateTime createTime;

    @Schema(description = "更新时间 时间戳", example = "1775011986")
    private LocalDateTime updateTime;

}