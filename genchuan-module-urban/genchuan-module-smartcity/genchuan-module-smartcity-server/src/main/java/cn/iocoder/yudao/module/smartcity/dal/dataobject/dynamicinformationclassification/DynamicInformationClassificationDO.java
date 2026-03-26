package cn.iocoder.yudao.module.smartcity.dal.dataobject.dynamicinformationclassification;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 动态信息分类 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_dynamic_information_classification")
@KeySequence("smartcity_dynamic_information_classification_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DynamicInformationClassificationDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 信息主题
     */
    private String messageSubject;
    /**
     * 行业领域
     */
    private String industrySector;
    /**
     * 信息来源
     */
    private String informationSources;
    /**
     * 紧急程度
     */
    private String urgency;

}