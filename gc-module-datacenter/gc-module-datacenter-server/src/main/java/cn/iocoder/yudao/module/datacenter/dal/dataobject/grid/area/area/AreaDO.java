package cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.area.area;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 统一行政区划配置表（树形结构） DO
 *
 * @author zcq
 */
@TableName("sys_area")
@KeySequence("sys_area_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 上级行政区划ID（0表示根节点）
     */
    private Long parentId;
    /**
     * 完整行政区划代码（12位）
     */
    private String fullCode;
    /**
     * 短代码（省/市/县6位，乡镇/社区3位）
     */
    private String shortCode;
    /**
     * 行政区划名称
     */
    private String name;
    /**
     * 层级：1-省级 2-市级 3-县级 4-乡镇 5-社区
     */
    private Integer level;
    /**
     * 类型（街道/镇/乡/社区/村）
     */
    private String areaType;
    /**
     * 社区ID（对应单元网格中的comm_id）
     */
    private String commId;
    /**
     * 生效时间
     */
    private LocalDateTime effectiveTime;
    /**
     * 失效时间
     */
    private LocalDateTime invalidTime;
    /**
     * 社区边界坐标（2000坐标系）
     */
    private String boundary;
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