package cn.iocoder.yudao.module.waterdetection.dal.dataobject.pollutionsourcearchive;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 周边污染源档案管理 DO
 *
 * @author zcq
 */
@TableName("gc_pollution_source_archive")
@KeySequence("gc_pollution_source_archive_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PollutionSourceArchiveDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 污染源编号
     */
    private String pollutionNo;
    /**
     * 污染源类型
     */
    private String pollutionType;
    /**
     * 经度
     */
    private Double longitude;
    /**
     * 纬度
     */
    private Double latitude;
    /**
     * 污染程度
     */
    private String pollutionLevel;
    /**
     * 治理措施
     */
    private String treatmentMeasures;
    /**
     * 治理状态
     */
    private String treatmentStatus;
    /**
     * 排查时间
     */
    private LocalDateTime inspectionTime;

}