package cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publicsnapshot.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 随手拍数据新增/修改 Request VO")
@Data
public class PublicSnapShotSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31815")
    private Long id;

    @Schema(description = "随手拍ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "28484")
    @NotEmpty(message = "随手拍ID，唯一编码，UUID生成不能为空")
    private String snapShotId;

    @Schema(description = "记录单号，格式“SP+YYYYMMDD+6位流水号”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "记录单号，格式“SP+YYYYMMDD+6位流水号”不能为空")
    private String snapNo;

    @Schema(description = "问题类型编码，问题类型，如“路灯故障”，字典表（sys_dict）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "问题类型编码，问题类型，如“路灯故障”，字典表（sys_dict）不能为空")
    private String problemTypeCode;

    @Schema(description = "问题类型名称，与类型编码同步，字典表（sys_dict）", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "问题类型名称，与类型编码同步，字典表（sys_dict）不能为空")
    private String problemTypeName;

    @Schema(description = "事发坐标X，GPS定位经度（2000国家大地坐标系），单位：度，空间数据表（sys_spatial）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "事发坐标X，GPS定位经度（2000国家大地坐标系），单位：度，空间数据表（sys_spatial）不能为空")
    private BigDecimal coordX;

    @Schema(description = "事发坐标Y，GPS定位纬度，单位：度，空间数据表（sys_spatial）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "事发坐标Y，GPS定位纬度，单位：度，空间数据表（sys_spatial）不能为空")
    private BigDecimal coordY;

    @Schema(description = "事发区域编码，由坐标反查生成，区域表（sys_area）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "事发区域编码，由坐标反查生成，区域表（sys_area）不能为空")
    private String areaCode;

    @Schema(description = "事发区域名称，与区域编码同步，区域表（sys_area）", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "事发区域名称，与区域编码同步，区域表（sys_area）不能为空")
    private String areaName;

    @Schema(description = "问题描述，补充问题详情")
    private String problemDesc;

    @Schema(description = "媒体路径，照片/视频存储路径（多个用逗号分隔）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "媒体路径，照片/视频存储路径（多个用逗号分隔）不能为空")
    private String mediaPath;

    @Schema(description = "媒体类型：1=照片，2=视频，标识媒体类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "媒体类型：1=照片，2=视频，标识媒体类型不能为空")
    private Integer mediaType;

    @Schema(description = "上报人姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "上报人姓名不能为空")
    private String rptName;

    @Schema(description = "上报人电话，加密存储", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "上报人电话，加密存储不能为空")
    private String rptPhone;

    @Schema(description = "处置状态：1=待处置，2=处置中，3=已办结，4=无效，流程状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "处置状态：1=待处置，2=处置中，3=已办结，4=无效，流程状态不能为空")
    private Integer handleStatus;

    @Schema(description = "处置结果，处置完成后填写")
    private String handleResult;

    @Schema(description = "上报时间，系统自动生成", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "上报时间，系统自动生成不能为空")
    private LocalDateTime rptTime;

    @Schema(description = "办结时间，处置完成时间")
    private LocalDateTime finishTime;

    @Schema(description = "分类扩展字段1，预留，标识问题等级，如轻微/严重")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，标识媒体数量，如1张/多张")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储定位精度")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储处置人员ID")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}