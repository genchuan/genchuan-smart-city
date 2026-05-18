package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 油车占位处置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class OilCarHandleRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18580")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车牌")
    private String plateNo;

    @Schema(description = "车位名称", example = "A区001")
    @ExcelProperty("车位名称")
    private String spaceName;

    @Schema(description = "识别时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("识别时间")
    private LocalDateTime identifyTime;

    @Schema(description = "占位类型：燃油车占位 / 其他，关联字典oil_car_handle_occupy_type", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("占位类型：燃油车占位 / 其他，关联字典oil_car_handle_occupy_type")
    private String occupyType;

    @Schema(description = "处置状态：未处理 / 处理中 / 已关闭，关联字典oil_car_handle_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("处置状态：未处理 / 处理中 / 已关闭，关联字典oil_car_handle_status")
    private String status;

    @Schema(description = "场站ID")
    private Long stationId;

    @Schema(description = "场站名称", example = "XX停车场")
    @ExcelProperty("场站名称")
    private String stationName;

    @Schema(description = "处置人姓名", example = "管理员")
    @ExcelProperty("处置人姓名")
    private String handleUserName;

    @Schema(description = "处置时间")
    @ExcelProperty("处置时间")
    private LocalDateTime handleTime;

    @Schema(description = "处置方式")
    @ExcelProperty("处置方式")
    private String handleMethod;

    @Schema(description = "忽略理由", example = "不喜欢")
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