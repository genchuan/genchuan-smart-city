package cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.disposalresulteval.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 处置结果评估 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DisposalResultEvalRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21555")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "评估ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "3577")
    @ExcelProperty("评估ID，唯一编码，UUID生成")
    private String evalId;

    @Schema(description = "关联回溯ID，关联事件过程回溯表（biz_evt_process_trace）", requiredMode = Schema.RequiredMode.REQUIRED, example = "29653")
    @ExcelProperty("关联回溯ID，关联事件过程回溯表（biz_evt_process_trace）")
    private String traceId;

    @Schema(description = "事件编码，与回溯ID同步，来自事件接报登记表（biz_evt_rpt_reg）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事件编码，与回溯ID同步，来自事件接报登记表（biz_evt_rpt_reg）")
    private String evtCode;

    @Schema(description = "事件名称，与事件编码同步，来自事件接报登记表（biz_evt_rpt_reg）", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("事件名称，与事件编码同步，来自事件接报登记表（biz_evt_rpt_reg）")
    private String evtName;

    @Schema(description = "处置效率得分，1-10分，评估接报-完成耗时、各环节时效，如“8分(总耗时4小时,符合预期)”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("处置效率得分，1-10分，评估接报-完成耗时、各环节时效，如“8分(总耗时4小时,符合预期)”")
    private Integer efficacyScore;

    @Schema(description = "处置效率说明，效率评估理由，如“接报后30分钟内完成研判,效率较高”")
    @ExcelProperty("处置效率说明，效率评估理由，如“接报后30分钟内完成研判,效率较高”")
    private String efficacyDesc;

    @Schema(description = "资源利用得分，1-10分，评估资源调配合理性、使用率，如“9分(冲锋舟全部投入使用,无浪费)”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("资源利用得分，1-10分，评估资源调配合理性、使用率，如“9分(冲锋舟全部投入使用,无浪费)”")
    private Integer resScore;

    @Schema(description = "资源利用说明，资源评估理由，如“按需调配5辆冲锋舟,无闲置”")
    @ExcelProperty("资源利用说明，资源评估理由，如“按需调配5辆冲锋舟,无闲置”")
    private String resDesc;

    @Schema(description = "效果达成得分，1-10分，评估事件处置目标达成度，如“10分(被困20人全部转移,无伤亡)”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("效果达成得分，1-10分，评估事件处置目标达成度，如“10分(被困20人全部转移,无伤亡)”")
    private Integer effectScore;

    @Schema(description = "效果达成说明，效果评估理由，如“处置目标全部达成,无次生灾害”")
    @ExcelProperty("效果达成说明，效果评估理由，如“处置目标全部达成,无次生灾害”")
    private String effectDesc;

    @Schema(description = "综合得分，(效率 + 资源 + 效果) / 3，保留1位小数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("综合得分，(效率 + 资源 + 效果) / 3，保留1位小数")
    private BigDecimal comprehensiveScore;

    @Schema(description = "改进建议，如“下次内涝救援可提前预置冲锋舟至易涝点”")
    @ExcelProperty("改进建议，如“下次内涝救援可提前预置冲锋舟至易涝点”")
    private String improveSugg;

    @Schema(description = "评估人ID，评估管理员账号，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "20388")
    @ExcelProperty("评估人ID，评估管理员账号，用户信息表（sys_user）")
    private String evalUserId;

    @Schema(description = "评估人姓名，与评估人ID同步，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("评估人姓名，与评估人ID同步，用户信息表（sys_user）")
    private String evalUserName;

    @Schema(description = "评估时间，格式 yyyy-MM-dd HH:mm:ss，系统自动生成", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评估时间，格式 yyyy-MM-dd HH:mm:ss，系统自动生成")
    private LocalDateTime evalTime;

    @Schema(description = "评估报告路径，PDF格式评估报告存储路径")
    @ExcelProperty("评估报告路径，PDF格式评估报告存储路径")
    private String rptPath;

    @Schema(description = "分类扩展字段1，预留，如“评估等级（优秀/良好/一般/差）”")
    @ExcelProperty("分类扩展字段1，预留，如“评估等级（优秀/良好/一般/差）”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“评估等级（优秀/良好/一般/差）”")
    @ExcelProperty("分类扩展字段2，预留，如“评估等级（优秀/良好/一般/差）”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储评估参考标准")
    @ExcelProperty("通用扩展字段1，预留，存储评估参考标准")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储评估参考标准")
    @ExcelProperty("通用扩展字段2，预留，存储评估参考标准")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}