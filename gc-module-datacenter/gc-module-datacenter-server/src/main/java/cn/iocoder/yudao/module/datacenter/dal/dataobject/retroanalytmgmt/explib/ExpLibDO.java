package cn.iocoder.yudao.module.datacenter.dal.dataobject.retroanalytmgmt.explib;

import lombok.*;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 经验库 DO
 *
 * @author 亘川智城
 */
@TableName("biz_exp_lib")
@KeySequence("biz_exp_lib_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpLibDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 经验ID，唯一编码，UUID生成
     */
    private String expId;
    /**
     * 经验编号，系统自动生成，格式“EXP+年份（4位）+6位流水号”
     */
    private String expNo;
    /**
     * 经验标题，如“城市内涝救援冲锋舟预置经验”
     */
    private String expTitle;
    /**
     * 关联事件类型ID，关联事件类型配置表小类ID，事件类型配置表（biz_evt_type_cfg）
     */
    private String evtTypeId;
    /**
     * 关联事件类型名称，与类型ID同步，事件类型配置表（biz_evt_type_cfg）
     */
    private String evtTypeName;
    /**
     * 经验类型，经验/教训，标识经验性质
     */
    private String expType;
    /**
     * 经验内容，详细经验/教训，如“经验：内涝前24小时，将冲锋舟预置至XX、XX易涝点，缩短响应时间”
     */
    private String expContent;
    /**
     * 关联评估ID，关联处置结果评估表，来源评估报告，处置结果评估表（biz_disposal_result_eval）
     */
    private String evalId;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}