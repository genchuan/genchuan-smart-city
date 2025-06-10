package cn.iocoder.yudao.module.smartcity.dal.dataobject.policyandregulationinput;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 政策法规录入 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_policy_and_regulation_input")
@KeySequence("smartcity_policy_and_regulation_input_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicyAndRegulationInputDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 制定机关
     */
    private String developingAgencies;
    /**
     * 政策法规名称
     */
    private String nameOfPolicyAndRegulation;
    /**
     * 文号
     */
    private String documentNumber;
    /**
     * 发布日期
     */
    private LocalDateTime releaseDate;
    /**
     * 生效日期
     */
    private LocalDateTime effectiveDate;
    /**
     * 失效日期
     */
    private String expiringDate;
    /**
     * 法规类别
     */
    private String regulatoryCategory;
    /**
     * 所属领域
     */
    private String isArea;
    /**
     * 适用范围
     */
    private String scopeOfApplication;
    /**
     * 正文内容
     */
    private String mainContent;

}