package cn.iocoder.yudao.module.smartcity.dal.dataobject.classificationofexperienceinformation;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 经验信息分类 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_classification_of_experience_information")
@KeySequence("smartcity_classification_of_experience_information_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassificationOfExperienceInformationDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 所属行业
     */
    private String sector;
    /**
     * 应用场景
     */
    private String applicationScenarios;
    /**
     * 经验性质
     */
    private String empiricalNature;
    /**
     * 适用对象
     */
    private String applicableObjects;
    /**
     * 来源渠道
     */
    private String sourceChannel;

}