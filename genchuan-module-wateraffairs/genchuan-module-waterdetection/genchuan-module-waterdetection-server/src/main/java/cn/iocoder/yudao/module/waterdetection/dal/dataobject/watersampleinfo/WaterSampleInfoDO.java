package cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersampleinfo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 水质检测信息 DO
 *
 * @author 朱聪权
 */
@TableName("gc_water_sample_info")
@KeySequence("gc_water_sample_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WaterSampleInfoDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 样品编号
     */
    private String sampleNo;
    /**
     * 样品类型
     */
    private String sampleType;
    /**
     * 样品名称
     */
    private String sampleName;
    /**
     * 检测性质
     */
    private String sampleNature;
    /**
     * 样品状态
     */
    private String sampleStatus;
    /**
     * 采样方式
     */
    private String deliveryMethod;
    /**
     * 采样时间
     */
    private LocalDateTime samplingDate;
    /**
     * 采样地点
     */
    private String samplingLocation;
    /**
     * 联系电话
     */
    private String contactPhone;
    /**
     * 检测开始日期
     */
    private LocalDateTime startDate;
    /**
     * 检测结束日期
     */
    private LocalDateTime endDate;
    /**
     * 检测依据
     */
    private String standard;
    /**
     * 结果报告
     */
    private String sampleResult;
    /**
     * 检测结论
     */
    private String conclusion;

}