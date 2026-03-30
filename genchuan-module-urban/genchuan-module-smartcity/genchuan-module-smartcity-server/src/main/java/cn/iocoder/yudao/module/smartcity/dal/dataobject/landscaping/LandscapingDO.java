package cn.iocoder.yudao.module.smartcity.dal.dataobject.landscaping;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 园林绿化 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_landscaping")
@KeySequence("smartcity_landscaping_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LandscapingDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 绿地名称
     */
    private String greenSpaceName;
    /**
     * 绿地类型
     */
    private String greenbeltType;
    /**
     * 位置
     */
    private String position;
    /**
     * 面积
     */
    private String area;
    /**
     * 绿地率
     */
    private String greeningRate;
    /**
     * 绿化覆盖率
     */
    private String greenCoverageRate;
    /**
     * 植物名称
     */
    private String plantName;
    /**
     * 科属
     */
    private String familyAndGenus;
    /**
     * 数量
     */
    private String quantity;
    /**
     * 种植密度
     */
    private String plantingDensity;
    /**
     * 项目总投资
     */
    private String totalInvestmentOfTheProject;
    /**
     * 土地成本
     */
    private String landCost;
    /**
     * 苗木采购成本
     */
    private String seedlingProcurementCost;

}