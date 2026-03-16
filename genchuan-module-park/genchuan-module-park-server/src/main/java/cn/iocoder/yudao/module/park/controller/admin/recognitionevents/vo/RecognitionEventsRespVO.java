package cn.iocoder.yudao.module.park.controller.admin.recognitionevents.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 车牌识别事件 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RecognitionEventsRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25642")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "事件类型", example = "2")
    @ExcelProperty("事件类型")
    private String type;

    @Schema(description = "协议模式")
    @ExcelProperty("协议模式")
    private Integer mode;

    @Schema(description = "协议版本号")
    @ExcelProperty("协议版本号")
    private String protoVer;

    @Schema(description = "车牌号码")
    @ExcelProperty("车牌号码")
    private String plateNum;

    @Schema(description = "车牌底色")
    @ExcelProperty("车牌底色")
    private String plateColor;

    @Schema(description = "是否真牌")
    @ExcelProperty("是否真牌")
    private Boolean plateVal;

    @Schema(description = "置信度")
    @ExcelProperty("置信度")
    private Integer confidence;

    @Schema(description = "车辆品牌")
    @ExcelProperty("车辆品牌")
    private String carLogo;

    @Schema(description = "车辆子品牌")
    @ExcelProperty("车辆子品牌")
    private String carSublogo;

    @Schema(description = "车辆颜色")
    @ExcelProperty("车辆颜色")
    private String carColor;

    @Schema(description = "车辆类型", example = "1")
    @ExcelProperty("车辆类型")
    private String vehicleType;

    @Schema(description = "识别时间戳")
    @ExcelProperty("识别时间戳")
    private Integer startTime;

    @Schema(description = "车场ID", example = "29568")
    @ExcelProperty("车场ID")
    private String parkId;

    @Schema(description = "相机ID", example = "30851")
    @ExcelProperty("相机ID")
    private String camId;

    @Schema(description = "相机IP地址")
    @ExcelProperty("相机IP地址")
    private String camIp;

    @Schema(description = "出入口类型", example = "1")
    @ExcelProperty("出入口类型")
    private String vdcType;

    @Schema(description = "是否白名单车辆")
    @ExcelProperty("是否白名单车辆")
    private Boolean isWhitelist;

    @Schema(description = "触发类型", example = "1")
    @ExcelProperty("触发类型")
    private String trigerType;

    @Schema(description = "加密验证是否成功")
    @ExcelProperty("加密验证是否成功")
    private Boolean encryptVerify;

    @Schema(description = "全景图的BASE64编码")
    @ExcelProperty("全景图的BASE64编码")
    private String picture;

    @Schema(description = "车牌特写图的BASE64编码")
    @ExcelProperty("车牌特写图的BASE64编码")
    private String closeupPic;

    @Schema(description = "记录创建时间")
    @ExcelProperty("记录创建时间")
    private LocalDateTime createdAt;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
