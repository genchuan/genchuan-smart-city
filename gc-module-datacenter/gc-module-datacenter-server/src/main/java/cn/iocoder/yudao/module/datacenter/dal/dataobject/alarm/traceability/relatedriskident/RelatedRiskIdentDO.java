package cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.traceability.relatedriskident;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 关联风险识别 DO
 *
 * @author zcq
 */
@TableName("biz_related_risk_ident")
@KeySequence("biz_related_risk_ident_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelatedRiskIdentDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 识别ID
     */
    private String identId;
    /**
     * 触发预警ID
     */
    private String triggerWarnId;
    /**
     * 触发预警名称
     */
    private String triggerWarnName;
    /**
     * 触发预警类型
     */
    private String triggerWarnType;
    /**
     * 关联风险名称
     */
    private String relatedRiskName;
    /**
     * 关联风险类型
     */
    private String relatedRiskType;
    /**
     * 关联依据
     */
    private String relatedBasis;
    /**
     * 风险等级
     */
    private String riskLevel;
    /**
     * 识别时间
     */
    private LocalDateTime identTime;
    /**
     * 识别方式
     */
    private String identMethod;
    /**
     * 识别用户ID
     */
    private String identifyUserId;
    /**
     * 识别用户姓名
     */
    private String identifyUserName;
    /**
     * 是否已处置
     */
    private String isHandled;
    /**
     * 分类扩展字段1
     */
    private String extCat1;
    /**
     * 分类扩展字段2
     */
    private String extCat2;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;

}