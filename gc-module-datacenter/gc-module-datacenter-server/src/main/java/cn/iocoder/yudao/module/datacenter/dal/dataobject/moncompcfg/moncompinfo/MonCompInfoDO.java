package cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncompinfo;

import lombok.*;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 监测部件信息 DO
 *
 * @author 亘川智城
 */
@TableName("biz_mon_comp_info")
@KeySequence("biz_mon_comp_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonCompInfoDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 部件ID，唯一编码，UUID生成
     */
    private String monCompId;
    /**
     * 部件标识码，按18位规则生成，如110101010200100001；监测部件标识码规则表(sys_mon_comp_code_rule)
     */
    private String compCode;
    /**
     * 部件名称，关联分类名称，如“燃气压力传感器”；监测部件分类配置表(sys_mon_comp_cat)
     */
    private String compName;
    /**
     * 部件分类ID，关联监测部件分类表小类ID；监测部件分类配置表(sys_mon_comp_cat)
     */
    private String compCatId;
    /**
     * 主管部门代码，主管部门统一社会信用代码；部门信息表(sys_org)
     */
    private String deptCode;
    /**
     * 主管部门名称，与部门代码同步，自动填充；部门信息表(sys_org)
     */
    private String deptName;
    /**
     * 所在行政区域代码，符合GB/T 2260，县级行政区域代码；行政区划表(sys_area)
     */
    private String regionCode;
    /**
     * 所在行政区域名称，与行政代码同步，自动填充；行政区划表(sys_area)
     */
    private String regionName;
    /**
     * 所在网格ID，关联单元网格ID；单元网格划分表(biz_unit_grid_div)
     */
    private String gridId;
    /**
     * 所在网格名称，与网格ID同步，自动填充；单元网格划分表(biz_unit_grid_div)
     */
    private String gridName;
    /**
     * 坐标X，经度，2000国家大地坐标系
     */
    private BigDecimal coordX;
    /**
     * 坐标Y，纬度，2000国家大地坐标系
     */
    private BigDecimal coordY;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}