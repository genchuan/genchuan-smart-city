package cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.data.mnggridattr;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 管理网格属性 DO
 *
 * @author zhucongquan
 */
@TableName("biz_mng_grid_attr")
@KeySequence("biz_mng_grid_attr_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MngGridAttrDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 属性ID
     */
    private String mgAttrId;
    /**
     * 管理网格ID
     */
    private String mngGridId;
    /**
     * 管理网格编码
     */
    private String mgGridCode;
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
     * 所含单元网格列表
     */
    private String unitGridList;
    /**
     * 网格员ID
     */
    private String gridUserId;
    /**
     * 网格员姓名
     */
    private String gridUserName;
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