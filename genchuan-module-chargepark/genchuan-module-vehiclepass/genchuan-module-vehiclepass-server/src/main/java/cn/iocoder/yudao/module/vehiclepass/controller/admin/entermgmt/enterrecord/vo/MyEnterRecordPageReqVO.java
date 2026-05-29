package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.enterrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

@Schema(description = "管理后台 - 入场记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class MyEnterRecordPageReqVO extends PageParam {

    @Schema(description = "车牌，模糊匹配", example = "闽C12345")
    private String plateNo;

    @Schema(description = "车牌颜色，参见 enter_record_plate_color 字典", example = "蓝牌")
    private String plateColor;

    @Schema(description = "车位编号，模糊匹配", example = "A001")
    private String spaceNo;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "入场时间，时间范围", example = "[2024-01-01 00:00:00, 2024-01-02 00:00:00]")
    private LocalDateTime[] enterTime;

    @Schema(description = "入场小时筛选，格式如 08:00、14:30")
    private String enterTimeHour;

    @Schema(description = "记录类型，参见 enter_record_record_type 字典", example = "自动识别")
    private String recordType;

    @Schema(description = "记录状态，参见 enter_record_status 字典", example = "正常记录")
    private String status;

    @Schema(description = "场站编号", example = "1")
    private Long stationId;

    @Schema(description = "场站名称，支持模糊查询")
    private String stationName;

    @Schema(description = "备注，模糊匹配", example = "测试备注")
    private String remark;

    @Schema(description = "修正日志标记 0-未修正 1-已修正 2-已确认", example = "0")
    private Integer isCorrected;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime[] createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    private LocalDateTime[] updateTime;

}