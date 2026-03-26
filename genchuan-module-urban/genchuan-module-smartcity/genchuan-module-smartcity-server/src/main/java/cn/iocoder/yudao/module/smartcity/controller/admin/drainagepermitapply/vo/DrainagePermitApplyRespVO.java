package cn.iocoder.yudao.module.smartcity.controller.admin.drainagepermitapply.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 排水许可证申请 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DrainagePermitApplyRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "申请编号")
    @ExcelProperty("申请编号")
    private String applyNo;

    @Schema(description = "排水户名称")
    @ExcelProperty("排水户名称")
    private String userName;

    @Schema(description = "排水水质检测报告文件")
    @ExcelProperty("排水水质检测报告文件")
    private List<String> waterQualityReport;

    @Schema(description = "日均排水量（吨）")
    @ExcelProperty("日均排水量（吨）")
    private Double dailyDrainage;

    @Schema(description = "重点排污单位证明文件路径")
    @ExcelProperty("重点排污单位证明文件路径")
    private String pollutionProof;

    @Schema(description = "历史违规记录")
    @ExcelProperty("历史违规记录")
    private String violationHistory;

    @Schema(description = "申请状态")
    @ExcelProperty(value = "申请状态", converter = DictConvert.class)
    @DictFormat("crm_audit_status") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private String applyStatus;

    @Schema(description = "审核人")
    @ExcelProperty("审核人")
    private String approver;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime approveTime;

    @Schema(description = "审核意见")
    @ExcelProperty("审核意见")
    private String approveComment;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}