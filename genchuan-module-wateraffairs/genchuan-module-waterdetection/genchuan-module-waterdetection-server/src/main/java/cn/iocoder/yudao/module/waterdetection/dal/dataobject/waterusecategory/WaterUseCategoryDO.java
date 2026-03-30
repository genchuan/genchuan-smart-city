package cn.iocoder.yudao.module.waterdetection.dal.dataobject.waterusecategory;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 用水性质分类管理 DO
 *
 * @author zcq
 */
@TableName("gc_water_use_category")
@KeySequence("gc_water_use_category_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WaterUseCategoryDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 用户编号
     */
    private String userCode;
    /**
     * 用水性质
     */
    private String waterUseType;
    /**
     * 用水定额(立方米)
     */
    private Double waterQuota;
    /**
     * 分类日期
     */
    private LocalDateTime categoryDate;

}