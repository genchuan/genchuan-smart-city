package cn.iocoder.yudao.module.facility.controller.admin.sysarchive.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "管理后台 - 工单全流程记录 Response VO")
public class FlowRecordRespVO {

    @Schema(description = "流程步骤序号")
    private Integer stepNo;

    @Schema(description = "流程节点名称，例如：预警触发/工单创建/派单/处置完成/归档")
    private String nodeName;

    @Schema(description = "节点时间")
    private LocalDateTime nodeTime;

    @Schema(description = "操作人")
    private String operatorName;

    @Schema(description = "节点描述")
    private String nodeDesc;

    @Schema(description = "关联编号，例如预警编号/工单编号/归档编号")
    private String refNo;

}
