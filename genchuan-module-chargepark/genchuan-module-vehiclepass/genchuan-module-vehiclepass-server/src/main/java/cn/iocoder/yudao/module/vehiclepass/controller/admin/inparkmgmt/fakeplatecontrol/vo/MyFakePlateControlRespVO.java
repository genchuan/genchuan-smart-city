package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "套牌管控 分页回显 VO")
@ExcelIgnoreUnannotated
public class MyFakePlateControlRespVO {

    @Schema(description = "主键ID", example = "1")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "车牌", example = "闽C12345")
    @ExcelProperty("车牌")
    private String plateNo;

    @Schema(description = "识别时间（时间戳）", example = "1775011986")
    @ExcelProperty("识别时间")
    private String identifyTime;

    @Schema(description = "匹配场景", example = "同牌多停")
    @ExcelProperty("匹配场景")
    private String matchScene;

    @Schema(description = "处置状态", example = "未处理")
    @ExcelProperty("处置状态")
    private String status;

    @Schema(description = "场站ID")
    @ExcelProperty("场站ID")
    private Long stationId;

    @Schema(description = "场站名称", example = "XX停车场")
    @ExcelProperty("场站名称")
    private String stationName;

    @Schema(description = "处置人姓名", example = "管理员")
    @ExcelProperty("处置人姓名")
    private String handleUserName;

    @Schema(description = "处置时间（时间戳）", example = "1775011986")
    @ExcelProperty("处置时间")
    private String handleTime;

    @Schema(description = "处置进度", example = "")
    @ExcelProperty("处置进度")
    private String handleProgress;

    @Schema(description = "处理类型：核查 / 忽略", example = "核查")
    @ExcelProperty("处理类型")
    private String handleType;

    @Schema(description = "忽略理由", example = "")
    @ExcelProperty("忽略理由")
    private String ignoreReason;

    @Schema(description = "备注", example = "")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1", example = "")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2", example = "")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者", example = "admin")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者", example = "admin")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间（时间戳）", example = "1775011986")
    @ExcelProperty("创建时间")
    private String createTime;

    @Schema(description = "更新时间（时间戳）", example = "1775011986")
    @ExcelProperty("更新时间")
    private String updateTime;
}