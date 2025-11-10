package cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.evtprocesstrace.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 事件过程回溯新增/修改 Request VO")
@Data
public class EvtProcessTraceSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22963")
    private Long id;

    @Schema(description = "回溯ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "4302")
    @NotEmpty(message = "回溯ID，唯一编码，UUID生成不能为空")
    private String traceId;

    @Schema(description = "事件编码，关联事件接报登记表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "事件编码，关联事件接报登记表不能为空")
    private String evtCode;

    @Schema(description = "事件名称，与事件编码同步，来自事件接报登记表", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "事件名称，与事件编码同步，来自事件接报登记表不能为空")
    private String evtName;

    @Schema(description = "事件类型名称，与事件编码同步，来自事件类型配置表", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "事件类型名称，与事件编码同步，来自事件类型配置表不能为空")
    private String evtTypeName;

    @Schema(description = "接报环节，接报时间、接报人、事发位置、描述，如“2025-10-01 08:00，张三，XX路内涝”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "接报环节，接报时间、接报人、事发位置、描述，如“2025-10-01 08:00，张三，XX路内涝”不能为空")
    private String rptLink;

    @Schema(description = "研判环节，研判时间、研判人、原因、紧急程度，如“2025-10-01 08:30，李四，降雨量过大，紧急”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "研判环节，研判时间、研判人、原因、紧急程度，如“2025-10-01 08:30，李四，降雨量过大，紧急”不能为空")
    private String analysisLink;

    @Schema(description = "分拨环节，分拨时间、分拨人、分拨部门，如“2025-10-01 09:00，王五，应急管理局”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分拨环节，分拨时间、分拨人、分拨部门，如“2025-10-01 09:00，王五，应急管理局”不能为空")
    private String allocateLink;

    @Schema(description = "工单环节，工单编号、处置人、状态流转，如“WO20251000001，赵六，待分配→执行中→已完成”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "工单环节，工单编号、处置人、状态流转，如“WO20251000001，赵六，待分配→执行中→已完成”不能为空")
    private String woLink;

    @Schema(description = "调度环节，调度方案/指令、资源类型/数量、负责人，如“DISP2025000001，冲锋舟5辆，孙七”")
    private String dispatchLink;

    @Schema(description = "回溯人ID，回溯报告生成人账号，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "31469")
    @NotEmpty(message = "回溯人ID，回溯报告生成人账号，用户信息表（sys_user）不能为空")
    private String traceUserId;

    @Schema(description = "回溯人姓名，与回溯人ID同步，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "回溯人姓名，与回溯人ID同步，用户信息表（sys_user）不能为空")
    private String traceUserName;

    @Schema(description = "回溯时间，格式：yyyy-MM-dd HH:mm:ss，系统自动生成", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "回溯时间，格式：yyyy-MM-dd HH:mm:ss，系统自动生成不能为空")
    private LocalDateTime traceTime;

    @Schema(description = "回溯报告路径，PDF格式回溯报告存储路径")
    private String rptPath;

    @Schema(description = "分类扩展字段1，预留，如“回溯批次”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“回溯批次”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储回溯备注")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储回溯备注")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}