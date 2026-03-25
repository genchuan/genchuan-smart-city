package cn.iocoder.yudao.module.smartcity.dal.dataobject.experienceinformationinput;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 经验信息录入 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_experience_information_input")
@KeySequence("smartcity_experience_information_input_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceInformationInputDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 经验主题
     */
    private String experienceTheme;
    /**
     * 所属领域
     */
    private String isArea;
    /**
     * 经验提供方
     */
    private String experienceProvider;
    /**
     * 实施时间
     */
    private LocalDateTime implementationTime;
    /**
     * 实施地点
     */
    private String implementationLocation;
    /**
     * 详细步骤
     */
    private String detailSteps;
    /**
     * 取得成效
     */
    private String achieveResults;
    /**
     * 可借鉴要点
     */
    private String keyPointsForReference;

}