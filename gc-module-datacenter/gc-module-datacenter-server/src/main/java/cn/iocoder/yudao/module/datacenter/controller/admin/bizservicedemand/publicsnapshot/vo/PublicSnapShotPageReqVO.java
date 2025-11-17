package cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publicsnapshot.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 随手拍数据分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PublicSnapShotPageReqVO extends PageParam {

    @Schema(description = "随手拍ID，唯一编码，UUID生成", example = "28484")
    private String snapShotId;

    @Schema(description = "记录单号，格式“SP+YYYYMMDD+6位流水号”")
    private String snapNo;

    @Schema(description = "问题类型编码，问题类型，如“路灯故障”，字典表（sys_dict）")
    private String problemTypeCode;

    @Schema(description = "问题类型名称，与类型编码同步，字典表（sys_dict）", example = "赵六")
    private String problemTypeName;

    @Schema(description = "事发坐标X，GPS定位经度（2000国家大地坐标系），单位：度，空间数据表（sys_spatial）")
    private BigDecimal coordX;

    @Schema(description = "事发坐标Y，GPS定位纬度，单位：度，空间数据表（sys_spatial）")
    private BigDecimal coordY;

    @Schema(description = "事发区域编码，由坐标反查生成，区域表（sys_area）")
    private String areaCode;

    @Schema(description = "事发区域名称，与区域编码同步，区域表（sys_area）", example = "赵六")
    private String areaName;

    @Schema(description = "问题描述，补充问题详情")
    private String problemDesc;

    @Schema(description = "媒体路径，照片/视频存储路径（多个用逗号分隔）")
    private String mediaPath;

    @Schema(description = "媒体类型：1=照片，2=视频，标识媒体类型", example = "1")
    private Integer mediaType;

    @Schema(description = "上报人姓名", example = "李四")
    private String rptName;

    @Schema(description = "上报人电话，加密存储")
    private String rptPhone;

    @Schema(description = "处置状态：1=待处置，2=处置中，3=已办结，4=无效，流程状态", example = "1")
    private Integer handleStatus;

    @Schema(description = "处置结果，处置完成后填写")
    private String handleResult;

    @Schema(description = "上报时间，系统自动生成")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] rptTime;

    @Schema(description = "办结时间，处置完成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] finishTime;

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