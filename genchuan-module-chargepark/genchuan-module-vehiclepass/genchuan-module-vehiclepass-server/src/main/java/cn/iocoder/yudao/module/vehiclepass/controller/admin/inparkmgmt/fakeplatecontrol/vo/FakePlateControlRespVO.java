package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 套牌管控 Response VO")
@Data
@ExcelIgnoreUnannotated
public class FakePlateControlRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24345")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车牌")
    private String plateNo;

    @Schema(description = "识别时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("识别时间")
    private LocalDateTime identifyTime;

    @Schema(description = "匹配场景：同牌多停 / 车牌车型不匹配，关联字典fake_plate_control_match_scene", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("匹配场景：同牌多停 / 车牌车型不匹配，关联字典fake_plate_control_match_scene")
    private String matchScene;

    @Schema(description = "处置状态：未处理 / 处理中 / 已关闭，关联字典fake_plate_control_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("处置状态：未处理 / 处理中 / 已关闭，关联字典fake_plate_control_status")
    private String status;

    @Schema(description = "场站名称", example = "XX停车场")
    @ExcelProperty("场站名称")
    private String stationName;

    @Schema(description = "场站ID，关联场站表", requiredMode = Schema.RequiredMode.REQUIRED, example = "16619")
    @ExcelProperty("场站ID，关联场站表")
    private Long stationId;

    @Schema(description = "处置人ID，关联system_user用户表", example = "16042")
    @ExcelProperty("处置人ID，关联system_user用户表")
    private Long handleUserId;

    @Schema(description = "处置人名称")
    private String handleUserName;

    @Schema(description = "处置时间")
    @ExcelProperty("处置时间")
    private LocalDateTime handleTime;

    @Schema(description = "处置进度")
    @ExcelProperty("处置进度")
    private String handleProgress;

    @Schema(description = "处理类型：核查 / 忽略", example = "核查")
    @ExcelProperty("处理类型：核查 / 忽略")
    private String handleType;

    @Schema(description = "忽略理由", example = "不对")
    @ExcelProperty("忽略理由")
    private String ignoreReason;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}