package cn.iocoder.yudao.module.smartcity.dal.dataobject.classificationofpoliciesandregulations;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 政策法规分类 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_classification_of_policies_and_regulations")
@KeySequence("smartcity_classification_of_policies_and_regulations_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassificationOfPoliciesAndRegulationsDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 法规类别
     */
    private String regulatoryCategory;
    /**
     * 所属领域
     */
    private String isArea;
    /**
     * 主题
     */
    private String theme;
    /**
     * 适用对象
     */
    private String applicableObjects;
    /**
     * 效力层级
     */
    private String levelOfEffectiveness;
    /**
     * 发布部门
     */
    private String departmentOfPublication;
    /**
     * 实施时间
     */
    private String implementationTime;
    /**
     * 废止时间 
     */
    private String revocatoryDate;

}