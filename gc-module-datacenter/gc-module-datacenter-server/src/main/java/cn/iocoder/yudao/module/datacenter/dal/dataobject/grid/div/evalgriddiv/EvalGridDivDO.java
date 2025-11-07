package cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.div.evalgriddiv;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 评价网格划分 DO
 *
 * @author zcq
 */
@TableName("biz_eval_grid_div")
@KeySequence("biz_eval_grid_div_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalGridDivDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 评价网格ID
     */
    private String evalGridId;
    /**
     * 评价网格名称
     */
    private String evalGridName;
    /**
     * 所属乡镇ID
     */
    private String townStreetId;
    /**
     * 评价网格类型(A/B/C)
     */
    private String gridType;
    /**
     * 所含管理网格IDs
     */
    private String includedMgIds;
    /**
     * 面积(m²)
     */
    private Integer area;
    /**
     * 划分时间
     */
    private LocalDateTime divTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 分类扩展字段1
     */
    private String extCat1;
    /**
     * 分类扩展字段2
     */
    private String extCat2;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}