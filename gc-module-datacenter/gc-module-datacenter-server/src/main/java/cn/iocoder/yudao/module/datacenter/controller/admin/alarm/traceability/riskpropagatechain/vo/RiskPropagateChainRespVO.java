package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.riskpropagatechain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 风险传播链 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RiskPropagateChainRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "传播链ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("传播链ID")
    private String propagateChainId;

    @Schema(description = "传播链编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("传播链编号")
    private String propagateChainNo;

    @Schema(description = "源头预警ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("源头预警ID")
    private String sourceWarnId;

    @Schema(description = "源头预警名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("源头预警名称")
    private String sourceWarnName;

    @Schema(description = "传播节点序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("传播节点序号")
    private Integer nodeSeq;

    @Schema(description = "传播节点内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("传播节点内容")
    private String nodeContent;

    @Schema(description = "节点类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("节点类型")
    private String nodeType;

    @Schema(description = "发生时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发生时间")
    private LocalDateTime occurTime;

    @Schema(description = "影响范围")
    @ExcelProperty("影响范围")
    private String impactRange;

    @Schema(description = "关联设备ID")
    @ExcelProperty("关联设备ID")
    private String deviceId;

    @Schema(description = "关联设备名称")
    @ExcelProperty("关联设备名称")
    private String deviceName;

    @Schema(description = "记录人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("记录人ID")
    private String recUserId;

    @Schema(description = "记录人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("记录人姓名")
    private String recUserName;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "create_time")
    @ExcelProperty("create_time")
    private LocalDateTime createTime;

}