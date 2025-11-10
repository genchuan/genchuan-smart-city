package cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.evtprocesstrace.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 事件过程回溯 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvtProcessTraceRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22963")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "回溯ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "4302")
    @ExcelProperty("回溯ID，唯一编码，UUID生成")
    private String traceId;

    @Schema(description = "事件编码，关联事件接报登记表", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事件编码，关联事件接报登记表")
    private String evtCode;

    @Schema(description = "事件名称，与事件编码同步，来自事件接报登记表", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("事件名称，与事件编码同步，来自事件接报登记表")
    private String evtName;

    @Schema(description = "事件类型名称，与事件编码同步，来自事件类型配置表", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("事件类型名称，与事件编码同步，来自事件类型配置表")
    private String evtTypeName;

    @Schema(description = "接报环节，接报时间、接报人、事发位置、描述，如“2025-10-01 08:00，张三，XX路内涝”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("接报环节，接报时间、接报人、事发位置、描述，如“2025-10-01 08:00，张三，XX路内涝”")
    private String rptLink;

    @Schema(description = "研判环节，研判时间、研判人、原因、紧急程度，如“2025-10-01 08:30，李四，降雨量过大，紧急”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("研判环节，研判时间、研判人、原因、紧急程度，如“2025-10-01 08:30，李四，降雨量过大，紧急”")
    private String analysisLink;

    @Schema(description = "分拨环节，分拨时间、分拨人、分拨部门，如“2025-10-01 09:00，王五，应急管理局”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分拨环节，分拨时间、分拨人、分拨部门，如“2025-10-01 09:00，王五，应急管理局”")
    private String allocateLink;

    @Schema(description = "工单环节，工单编号、处置人、状态流转，如“WO20251000001，赵六，待分配→执行中→已完成”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("工单环节，工单编号、处置人、状态流转，如“WO20251000001，赵六，待分配→执行中→已完成”")
    private String woLink;

    @Schema(description = "调度环节，调度方案/指令、资源类型/数量、负责人，如“DISP2025000001，冲锋舟5辆，孙七”")
    @ExcelProperty("调度环节，调度方案/指令、资源类型/数量、负责人，如“DISP2025000001，冲锋舟5辆，孙七”")
    private String dispatchLink;

    @Schema(description = "回溯人ID，回溯报告生成人账号，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "31469")
    @ExcelProperty("回溯人ID，回溯报告生成人账号，用户信息表（sys_user）")
    private String traceUserId;

    @Schema(description = "回溯人姓名，与回溯人ID同步，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("回溯人姓名，与回溯人ID同步，用户信息表（sys_user）")
    private String traceUserName;

    @Schema(description = "回溯时间，格式：yyyy-MM-dd HH:mm:ss，系统自动生成", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("回溯时间，格式：yyyy-MM-dd HH:mm:ss，系统自动生成")
    private LocalDateTime traceTime;

    @Schema(description = "回溯报告路径，PDF格式回溯报告存储路径")
    @ExcelProperty("回溯报告路径，PDF格式回溯报告存储路径")
    private String rptPath;

    @Schema(description = "分类扩展字段1，预留，如“回溯批次”")
    @ExcelProperty("分类扩展字段1，预留，如“回溯批次”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“回溯批次”")
    @ExcelProperty("分类扩展字段2，预留，如“回溯批次”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储回溯备注")
    @ExcelProperty("通用扩展字段1，预留，存储回溯备注")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储回溯备注")
    @ExcelProperty("通用扩展字段2，预留，存储回溯备注")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}