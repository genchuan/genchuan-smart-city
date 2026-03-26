package cn.iocoder.yudao.module.smartcity.dal.dataobject.denselypopulatedareas;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 人员密集场所安全 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_densely_populated_areas")
@KeySequence("smartcity_densely_populated_areas_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DenselyPopulatedAreasDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 所名称
     */
    private String theName;
    /**
     * 场所类型
     */
    private String typeOfVenue;
    /**
     * 地址
     */
    private String address;
    /**
     * 实时人数
     */
    private String realTimeNumberOfPeople;
    /**
     * 人员密度
     */
    private String occupantDensity;
    /**
     * 人员流动速度
     */
    private String personnelTurnoverSpeed;
    /**
     * 人员分布区域
     */
    private String personnelDistributionArea;
    /**
     * 预警级别
     */
    private String warningLevel;

}