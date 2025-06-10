package cn.iocoder.yudao.module.smartcity.dal.dataobject.patrolresources;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查资源 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_patrol_resources")
@KeySequence("smartcity_patrol_resources_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatrolResourcesDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 资源名称
     */
    private String resourceName;
    /**
     * 资源编号
     */
    private String resourceNumber;
    /**
     * 资源类型
     */
    private String resourceType;
    /**
     * 地理位置
     */
    private String geographicLocation;
    /**
     * 区域划分
     */
    private String regionalDivision;
    /**
     * 规格型号
     */
    private String specificationAndModel;

}