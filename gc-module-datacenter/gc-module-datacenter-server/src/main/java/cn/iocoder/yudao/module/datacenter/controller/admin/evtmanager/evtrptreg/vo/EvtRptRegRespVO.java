package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtrptreg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 事件接报登记 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvtRptRegRespVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15115")
    @ExcelProperty("自增主键ID")
    private Long id;

    @Schema(description = "接报ID，唯一编码，UUID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9713")
    @ExcelProperty("接报ID，唯一编码，UUID")
    private String rptId;

    @Schema(description = "事件编码，系统自动生成，格式“6位区域码+2位大类码+3位小类码+7位顺序码”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事件编码，系统自动生成，格式“6位区域码+2位大类码+3位小类码+7位顺序码”")
    private String evtCode;

    @Schema(description = "事件类型ID，关联事件类型配置表小类ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16836")
    @ExcelProperty("事件类型ID，关联事件类型配置表小类ID")
    private String evtTypeId;

    @Schema(description = "事件类型名称，与类型ID同步", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("事件类型名称，与类型ID同步")
    private String evtTypeName;

    @Schema(description = "事发区域代码，关联行政区划表", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事发区域代码，关联行政区划表")
    private String incidentRegionCode;

    @Schema(description = "事发区域名称，与区域代码同步", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("事发区域名称，与区域代码同步")
    private String incidentRegionName;

    @Schema(description = "事发位置，详细位置，如“XX路与XX路交叉口东北侧”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事发位置，详细位置，如“XX路与XX路交叉口东北侧”")
    private String incidentLocation;

    @Schema(description = "事发坐标X，度，2000国家大地坐标系经度")
    @ExcelProperty("事发坐标X，度，2000国家大地坐标系经度")
    private BigDecimal incidentCoordX;

    @Schema(description = "事发坐标Y，度，2000国家大地坐标系纬度")
    @ExcelProperty("事发坐标Y，度，2000国家大地坐标系纬度")
    private BigDecimal incidentCoordY;

    @Schema(description = "事件描述，事件详情，如“商贩占用人行道售卖水果”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事件描述，事件详情，如“商贩占用人行道售卖水果”")
    private String evtDesc;

}