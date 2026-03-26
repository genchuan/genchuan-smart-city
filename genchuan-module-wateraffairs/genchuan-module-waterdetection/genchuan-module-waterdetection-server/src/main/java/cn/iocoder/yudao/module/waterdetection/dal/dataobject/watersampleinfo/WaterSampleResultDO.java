package cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersampleinfo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 出厂水检测结果 DO
 *
 * @author 朱聪权
 */
@TableName("gc_water_sample_result")
@KeySequence("gc_water_sample_result_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WaterSampleResultDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 样品编号
     */
    private Long waterSampleId;
    /**
     * 指标
     */
    private String indexName;
    /**
     * 实测值
     */
    private Double actualValue;

}