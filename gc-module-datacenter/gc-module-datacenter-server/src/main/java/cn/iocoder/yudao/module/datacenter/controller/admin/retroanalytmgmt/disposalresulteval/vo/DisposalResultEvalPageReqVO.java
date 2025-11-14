package cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.disposalresulteval.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 处置结果评估分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DisposalResultEvalPageReqVO extends PageParam {

    @Schema(description = "评估ID，唯一编码，UUID生成", example = "3577")
    private String evalId;

    @Schema(description = "关联回溯ID，关联事件过程回溯表（biz_evt_process_trace）", example = "29653")
    private String traceId;

    @Schema(description = "事件编码，与回溯ID同步，来自事件接报登记表（biz_evt_rpt_reg）")
    private String evtCode;

    @Schema(description = "事件名称，与事件编码同步，来自事件接报登记表（biz_evt_rpt_reg）", example = "王五")
    private String evtName;

    @Schema(description = "处置效率得分，1-10分，评估接报-完成耗时、各环节时效，如“8分(总耗时4小时,符合预期)”")
    private Integer efficacyScore;

    @Schema(description = "处置效率说明，效率评估理由，如“接报后30分钟内完成研判,效率较高”")
    private String efficacyDesc;

    @Schema(description = "资源利用得分，1-10分，评估资源调配合理性、使用率，如“9分(冲锋舟全部投入使用,无浪费)”")
    private Integer resScore;

    @Schema(description = "资源利用说明，资源评估理由，如“按需调配5辆冲锋舟,无闲置”")
    private String resDesc;

    @Schema(description = "效果达成得分，1-10分，评估事件处置目标达成度，如“10分(被困20人全部转移,无伤亡)”")
    private Integer effectScore;

    @Schema(description = "效果达成说明，效果评估理由，如“处置目标全部达成,无次生灾害”")
    private String effectDesc;

    @Schema(description = "综合得分，(效率 + 资源 + 效果) / 3，保留1位小数")
    private BigDecimal comprehensiveScore;

    @Schema(description = "改进建议，如“下次内涝救援可提前预置冲锋舟至易涝点”")
    private String improveSugg;

    @Schema(description = "评估人ID，评估管理员账号，用户信息表（sys_user）", example = "20388")
    private String evalUserId;

    @Schema(description = "评估人姓名，与评估人ID同步，用户信息表（sys_user）", example = "王五")
    private String evalUserName;

    @Schema(description = "评估时间，格式 yyyy-MM-dd HH:mm:ss，系统自动生成")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] evalTime;

    @Schema(description = "评估报告路径，PDF格式评估报告存储路径")
    private String rptPath;

    @Schema(description = "分类扩展字段1，预留，如“评估等级（优秀/良好/一般/差）”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“评估等级（优秀/良好/一般/差）”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储评估参考标准")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储评估参考标准")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}