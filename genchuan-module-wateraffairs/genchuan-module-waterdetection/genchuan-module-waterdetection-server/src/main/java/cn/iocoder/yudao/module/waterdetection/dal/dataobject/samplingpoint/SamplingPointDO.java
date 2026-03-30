package cn.iocoder.yudao.module.waterdetection.dal.dataobject.samplingpoint;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 采样点规划 DO
 *
 * @author zcq
 */
@TableName("gc_sampling_point")
@KeySequence("gc_sampling_point_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SamplingPointDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 采样点编号
     */
    private String pointCode;
    /**
     * 经度
     */
    private Double longitude;
    /**
     * 纬度
     */
    private Double latitude;
    /**
     * 类型(水源/水厂/管网/末梢)
     */
    private String pointType;
    /**
     * 覆盖人口
     */
    private Double coveredPopulation;
    /**
     * 周边环境描述
     */
    private String surroundingDesc;
    /**
     * 规划依据
     */
    private String planningBasis;

}