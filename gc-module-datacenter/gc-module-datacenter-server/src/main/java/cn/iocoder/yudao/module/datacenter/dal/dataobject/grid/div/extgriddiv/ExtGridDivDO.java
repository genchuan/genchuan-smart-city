package cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.div.extgriddiv;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 扩展网格划分 DO
 *
 * @author zcq
 */
@TableName("biz_ext_grid_div")
@KeySequence("biz_ext_grid_div_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExtGridDivDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 扩展网格ID
     */
    private String extGridId;
    /**
     * 扩展网格名称
     */
    private String extGridName;
    /**
     * 扩展网格类型
     */
    private String extType;
    /**
     * 所含基础网格IDs
     */
    private String includedBasicIds;
    /**
     * 基础网格类型
     */
    private String basicGridType;
    /**
     * 面积(m²)
     */
    private Integer area;
    /**
     * 边界坐标
     */
    private String boundaryCoords;
    /**
     * 申请原因
     */
    private String applyReason;
    /**
     * 审核状态
     */
    private String auditStatus;
    /**
     * 申请人ID
     */
    private String applyUserId;
    /**
     * 申请时间
     */
    private LocalDateTime applyTime;
    /**
     * 审核人ID
     */
    private String auditUserId;
    /**
     * 审核时间
     */
    private LocalDateTime auditTime;
    /**
     * 审核意见
     */
    private String auditOpinion;
    /**
     * 分类扩展字段1
     */
    private String extCat1;
    /**
     * 分类扩展字段2
     */
    private String extCat2;

}