package cn.iocoder.yudao.module.smartcity.dal.dataobject.lawenforcementsupervision;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 执法监督 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_law_enforcement_supervision")
@KeySequence("smartcity_law_enforcement_supervision_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LawEnforcementSupervisionDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 监督编号
     */
    private String supervisionId;
    /**
     * 执法事件编号
     */
    private String eventNumber;
    /**
     * 执法人员
     */
    private String officials;
    /**
     * 监督人员
     */
    private String personnel;
    /**
     * 监督时间
     */
    private String time;
    /**
     * 监督方式
     */
    private String method;
    /**
     * 证据采集完整性
     */
    private String integrityCollection;
    /**
     * 当事人满意度
     */
    private String satisfactionInvolved;
    /**
     * 监督结果评价
     */
    private String resultEvaluation;
    /**
     * 违规行为描述
     */
    private String violationDescription;

}