package cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.data.unitgridattr;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 单元网格属性 DO
 *
 * @author zhucongquan
 */
@TableName("biz_unit_grid_attr")
@KeySequence("biz_unit_grid_attr_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnitGridAttrDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 属性ID
     */
    private String unitAttrId;
    /**
     * 单元网格ID
     */
    private String unitGridId;
    /**
     * 单元网格编码
     */
    private String unitGridCode;
    /**
     * 面积(m²)
     */
    private Integer area;
    /**
     * 初始时间
     */
    private LocalDateTime initTime;
    /**
     * 终止时间
     */
    private LocalDateTime endTime;
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