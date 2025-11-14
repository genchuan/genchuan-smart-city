package cn.iocoder.yudao.module.datacenter.dal.dataobject.retroanalytmgmt.disposalresulteval;

import lombok.*;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 处置结果评估 DO
 *
 * @author 亘川智城
 */
@TableName("biz_disposal_result_eval")
@KeySequence("biz_disposal_result_eval_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DisposalResultEvalDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 评估ID，唯一编码，UUID生成
     */
    private String evalId;
    /**
     * 关联回溯ID，关联事件过程回溯表（biz_evt_process_trace）
     */
    private String traceId;
    /**
     * 事件编码，与回溯ID同步，来自事件接报登记表（biz_evt_rpt_reg）
     */
    private String evtCode;
    /**
     * 事件名称，与事件编码同步，来自事件接报登记表（biz_evt_rpt_reg）
     */
    private String evtName;
    /**
     * 处置效率得分，1-10分，评估接报-完成耗时、各环节时效，如“8分(总耗时4小时,符合预期)”
     */
    private Integer efficacyScore;
    /**
     * 处置效率说明，效率评估理由，如“接报后30分钟内完成研判,效率较高”
     */
    private String efficacyDesc;
    /**
     * 资源利用得分，1-10分，评估资源调配合理性、使用率，如“9分(冲锋舟全部投入使用,无浪费)”
     */
    private Integer resScore;
    /**
     * 资源利用说明，资源评估理由，如“按需调配5辆冲锋舟,无闲置”
     */
    private String resDesc;
    /**
     * 效果达成得分，1-10分，评估事件处置目标达成度，如“10分(被困20人全部转移,无伤亡)”
     */
    private Integer effectScore;
    /**
     * 效果达成说明，效果评估理由，如“处置目标全部达成,无次生灾害”
     */
    private String effectDesc;
    /**
     * 综合得分，(效率 + 资源 + 效果) / 3，保留1位小数
     */
    private BigDecimal comprehensiveScore;
    /**
     * 改进建议，如“下次内涝救援可提前预置冲锋舟至易涝点”
     */
    private String improveSugg;
    /**
     * 评估人ID，评估管理员账号，用户信息表（sys_user）
     */
    private String evalUserId;
    /**
     * 评估人姓名，与评估人ID同步，用户信息表（sys_user）
     */
    private String evalUserName;
    /**
     * 评估时间，格式 yyyy-MM-dd HH:mm:ss，系统自动生成
     */
    private LocalDateTime evalTime;
    /**
     * 评估报告路径，PDF格式评估报告存储路径
     */
    private String rptPath;
    /**
     * 分类扩展字段1，预留，如“评估等级（优秀/良好/一般/差）”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“评估等级（优秀/良好/一般/差）”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储评估参考标准
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储评估参考标准
     */
    private String extCommon2;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}