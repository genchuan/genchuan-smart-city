package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtrptreg.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 事件接报登记分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvtRptRegPageReqVO extends PageParam {

    @Schema(description = "接报ID，唯一编码，UUID", example = "9713")
    private String rptId;

    @Schema(description = "事件编码，系统自动生成，格式“6位区域码+2位大类码+3位小类码+7位顺序码”")
    private String evtCode;

    @Schema(description = "事件类型ID，关联事件类型配置表小类ID", example = "16836")
    private String evtTypeId;

    @Schema(description = "事件类型名称，与类型ID同步", example = "王五")
    private String evtTypeName;

    @Schema(description = "事发区域代码，关联行政区划表")
    private String incidentRegionCode;

    @Schema(description = "事发区域名称，与区域代码同步", example = "张三")
    private String incidentRegionName;

    @Schema(description = "事发位置，详细位置，如“XX路与XX路交叉口东北侧”")
    private String incidentLocation;

    @Schema(description = "事发坐标X，度，2000国家大地坐标系经度")
    private BigDecimal incidentCoordX;

    @Schema(description = "事发坐标Y，度，2000国家大地坐标系纬度")
    private BigDecimal incidentCoordY;

    @Schema(description = "事件描述，事件详情，如“商贩占用人行道售卖水果”")
    private String evtDesc;

}