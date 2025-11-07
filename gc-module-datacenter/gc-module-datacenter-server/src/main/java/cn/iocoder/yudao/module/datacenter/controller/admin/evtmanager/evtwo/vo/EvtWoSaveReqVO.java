package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtwo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 事件工单新增/修改 Request VO")
@Data
public class EvtWoSaveReqVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16526")
    private Long id;

    @Schema(description = "工单ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "19126")
    @NotEmpty(message = "工单ID，唯一编码，UUID生成不能为空")
    private String woId;

    @Schema(description = "工单编号，系统自动生成，格式“WO+年份(4位)+月份(2位)+8位流水号”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "工单编号，系统自动生成，格式“WO+年份(4位)+月份(2位)+8位流水号”不能为空")
    private String woNo;

    @Schema(description = "事件编码，关联事件分级分拨表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "事件编码，关联事件分级分拨表不能为空")
    private String evtCode;

    @Schema(description = "事件类型名称，从分拨表同步", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "事件类型名称，从分拨表同步不能为空")
    private String evtTypeName;

    @Schema(description = "分拨部门代码，从分拨表同步", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分拨部门代码，从分拨表同步不能为空")
    private String allocateDeptCode;

    @Schema(description = "分拨部门名称，从分拨表同步", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "分拨部门名称，从分拨表同步不能为空")
    private String allocateDeptName;

    @Schema(description = "工单状态，待分配/处置中/已办结/已驳回，默认待分配", example = "1")
    private String woStatus;

    @Schema(description = "负责人ID，处置工作人员账号，关联用户信息表(sys_user)", example = "102")
    private String chargerId;

    @Schema(description = "负责人姓名，与负责人ID同步", example = "王五")
    private String chargerName;

}