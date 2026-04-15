package cn.iocoder.yudao.module.vehiclecharging.controller.admin.interconnection.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "汽车充电 - 互联互通表 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InterconnectionRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28503")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "对接编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("对接编号")
    private String connectCode;

    @Schema(description = "第三方平台名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("第三方平台名称")
    private String thirdPlatform;

    @Schema(description = "对接类型", example = "1")
    @ExcelProperty("对接类型")
    private String connectType;

    @Schema(description = "API参数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("API参数")
    private String apiParam;

    @Schema(description = "同步频率，单位：分钟", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("同步频率，单位：分钟")
    private Integer syncFreq;

    @Schema(description = "同步成功率，单位：%")
    @ExcelProperty("同步成功率，单位：%")
    private BigDecimal syncSuccessRate;

    @Schema(description = "对接状态：未申请/审核中/已开通/已关闭", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("对接状态：未申请/审核中/已开通/已关闭")
    private String connectStatus;

    @Schema(description = "审核人员")
    @ExcelProperty("审核人员")
    private String auditUser;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "审核备注", example = "你说的对")
    @ExcelProperty("审核备注")
    private String auditRemark;

    @Schema(description = "关闭原因", example = "不喜欢")
    @ExcelProperty("关闭原因")
    private String closeReason;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}