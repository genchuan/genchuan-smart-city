package cn.iocoder.yudao.module.smartcity.dal.dataobject.workarea;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 作业区域 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_work_area")
@KeySequence("smartcity_work_area_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkAreaDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 区域编号
     */
    private String areaNumber;
    /**
     * 区域名称
     */
    private String areaName;
    /**
     * 区域位置
     */
    private String regionalLocation;
    /**
     * 区域面积
     */
    private String regionalArea;
    /**
     * 人员容量
     */
    private String personnelCapacity;
    /**
     * 环境风险因素
     */
    private String environmentalFactors;
    /**
     * 防护措施要求
     */
    private String requirementsMeasures;

}