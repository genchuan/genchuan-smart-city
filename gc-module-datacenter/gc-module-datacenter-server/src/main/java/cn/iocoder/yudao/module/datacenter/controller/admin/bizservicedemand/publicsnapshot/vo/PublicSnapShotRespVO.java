package cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publicsnapshot.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 随手拍数据 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PublicSnapShotRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31815")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "随手拍ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "28484")
    @ExcelProperty("随手拍ID，唯一编码，UUID生成")
    private String snapShotId;

    @Schema(description = "记录单号，格式“SP+YYYYMMDD+6位流水号”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("记录单号，格式“SP+YYYYMMDD+6位流水号”")
    private String snapNo;

    @Schema(description = "问题类型编码，问题类型，如“路灯故障”，字典表（sys_dict）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("问题类型编码，问题类型，如“路灯故障”，字典表（sys_dict）")
    private String problemTypeCode;

    @Schema(description = "问题类型名称，与类型编码同步，字典表（sys_dict）", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("问题类型名称，与类型编码同步，字典表（sys_dict）")
    private String problemTypeName;

    @Schema(description = "事发坐标X，GPS定位经度（2000国家大地坐标系），单位：度，空间数据表（sys_spatial）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事发坐标X，GPS定位经度（2000国家大地坐标系），单位：度，空间数据表（sys_spatial）")
    private BigDecimal coordX;

    @Schema(description = "事发坐标Y，GPS定位纬度，单位：度，空间数据表（sys_spatial）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事发坐标Y，GPS定位纬度，单位：度，空间数据表（sys_spatial）")
    private BigDecimal coordY;

    @Schema(description = "事发区域编码，由坐标反查生成，区域表（sys_area）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事发区域编码，由坐标反查生成，区域表（sys_area）")
    private String areaCode;

    @Schema(description = "事发区域名称，与区域编码同步，区域表（sys_area）", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("事发区域名称，与区域编码同步，区域表（sys_area）")
    private String areaName;

    @Schema(description = "问题描述，补充问题详情")
    @ExcelProperty("问题描述，补充问题详情")
    private String problemDesc;

    @Schema(description = "媒体路径，照片/视频存储路径（多个用逗号分隔）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("媒体路径，照片/视频存储路径（多个用逗号分隔）")
    private String mediaPath;

    @Schema(description = "媒体类型：1=照片，2=视频，标识媒体类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("媒体类型：1=照片，2=视频，标识媒体类型")
    private Integer mediaType;

    @Schema(description = "上报人姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("上报人姓名")
    private String rptName;

    @Schema(description = "上报人电话，加密存储", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("上报人电话，加密存储")
    private String rptPhone;

    @Schema(description = "处置状态：1=待处置，2=处置中，3=已办结，4=无效，流程状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("处置状态：1=待处置，2=处置中，3=已办结，4=无效，流程状态")
    private Integer handleStatus;

    @Schema(description = "处置结果，处置完成后填写")
    @ExcelProperty("处置结果，处置完成后填写")
    private String handleResult;

    @Schema(description = "上报时间，系统自动生成", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("上报时间，系统自动生成")
    private LocalDateTime rptTime;

    @Schema(description = "办结时间，处置完成时间")
    @ExcelProperty("办结时间，处置完成时间")
    private LocalDateTime finishTime;

    @Schema(description = "分类扩展字段1，预留，标识问题等级，如轻微/严重")
    @ExcelProperty("分类扩展字段1，预留，标识问题等级，如轻微/严重")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，标识媒体数量，如1张/多张")
    @ExcelProperty("分类扩展字段2，预留，标识媒体数量，如1张/多张")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储定位精度")
    @ExcelProperty("通用扩展字段1，预留，存储定位精度")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储处置人员ID")
    @ExcelProperty("通用扩展字段2，预留，存储处置人员ID")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}