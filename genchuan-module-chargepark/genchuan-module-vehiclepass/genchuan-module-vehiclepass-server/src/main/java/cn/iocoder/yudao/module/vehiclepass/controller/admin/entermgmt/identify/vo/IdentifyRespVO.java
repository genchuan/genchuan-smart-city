package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.identify.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 车牌识别 Response VO")
@Data
@ExcelIgnoreUnannotated
public class IdentifyRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED, example = "闽C12345")
    @ExcelProperty("车牌")
    private String plateNo;

    @Schema(description = "车牌颜色（蓝牌/黄牌/绿牌/其他）", example = "蓝牌")
    @ExcelProperty("车牌颜色")
    private String plateColor;

    @Schema(description = "置信度", example = "98.50")
    @ExcelProperty("置信度")
    private BigDecimal confidence;

    @Schema(description = "抓拍图片地址", example = "/genchuan/chargePark/vehiclePass/enterMgmt/plateIdentify/2025/04/13/123456.jpg")
    @ExcelProperty("抓拍图片地址")
    private String imageUrl;

    @Schema(description = "识别状态（识别成功/识别失败）", example = "识别成功")
    @ExcelProperty("识别状态")
    private String status;

    @Schema(description = "场站ID")
    private Long stationId;

    @Schema(description = "场站名称", example = "XX停车场")
    @ExcelProperty("场站名称")
    private String stationName;

    @Schema(description = "备注", example = "")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "修正记录标记", example = "0")
    @ExcelProperty("修正记录标记")
    private Integer isCorrected;

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

    @Schema(description = "创建时间", example = "1775011986")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", example = "1775011986")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}