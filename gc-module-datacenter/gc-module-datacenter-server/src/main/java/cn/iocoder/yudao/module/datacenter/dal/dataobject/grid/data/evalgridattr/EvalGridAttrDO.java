package cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.data.evalgridattr;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 评价网格属性 DO
 *
 * @author zhucongquan
 */
@TableName("biz_eval_grid_attr")
@KeySequence("biz_eval_grid_attr_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalGridAttrDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 属性ID
     */
    private String evalAttrId;
    /**
     * 评价网格ID
     */
    private String evalGridId;
    /**
     * 评价网格编码
     */
    private String evalGridCode;
    /**
     * 面积(m²)
     */
    private Integer area;
    /**
     * 评价网格类型(A/B/C)
     */
    private String gridType;
    /**
     * 初始时间
     */
    private LocalDateTime initTime;
    /**
     * 终止时间
     */
    private LocalDateTime endTime;
    /**
     * 所含管理网格列表
     */
    private String mgGridList;
    /**
     * 关联评价指标ID
     */
    private String idxId;
    /**
     * 关联评价指标名称
     */
    private String idxName;
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

}