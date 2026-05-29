package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.enterrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;

import java.time.LocalDateTime;

@Schema(description = "入场记录分页响应 VO")
@Data
@ExcelIgnoreUnannotated
public class MyEnterRecordRespVO {

    @Schema(description = "主键 ID", example = "1")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "车牌", example = "闽C12345")
    @ExcelProperty("车牌号")
    private String plateNo;

    @Schema(description = "车牌颜色：蓝牌/黄牌/绿牌/其他", example = "蓝牌")
    @ExcelProperty("车牌颜色")
    private String plateColor;

    @Schema(description = "车位编号", example = "A001")
    @ExcelProperty("车位编号")
    private String spaceNo;

    @Schema(description = "入场时间 时间戳", example = "1775011986")
    @ExcelProperty("入场时间")
    private LocalDateTime enterTime;

    @Schema(description = "记录类型：自动识别/人工补录", example = "自动识别")
    @ExcelProperty("记录类型")
    private String recordType;

    @Schema(description = "记录状态：正常记录/异常记录", example = "正常记录")
    @ExcelProperty("记录状态")
    private String status;

    @Schema(description = "场站ID")
    @ExcelProperty("场站ID")
    private Long stationId;

    @Schema(description = "场地名称", example = "XX停车场")
    @ExcelProperty("场地名称")
    private String stationName;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "佐证图片地址")
    @ExcelProperty("佐证图片")
    private String proofImage;

    @Schema(description = "修正日志标记：0-未修正 1-已修正 2-已确认", example = "0")
    @ExcelProperty("修正标记")
    private Integer isCorrected;

    @Schema(description = "备用字段 1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者", example = "admin")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者", example = "admin")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间 时间戳", example = "1775011986")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间 时间戳", example = "1775011986")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}