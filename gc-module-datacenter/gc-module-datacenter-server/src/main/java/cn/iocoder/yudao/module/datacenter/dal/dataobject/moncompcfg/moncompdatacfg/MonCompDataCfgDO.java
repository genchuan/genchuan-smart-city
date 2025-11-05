package cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncompdatacfg;

import lombok.*;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 监测部件数据配置 DO
 *
 * @author 亘川智城
 */
@TableName("sys_mon_comp_data_cfg")
@KeySequence("sys_mon_comp_data_cfg_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonCompDataCfgDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 配置ID，唯一编码，UUID生成
     */
    private String monCompDataId;
    /**
     * 部件分类ID，关联监测部件分类表小类ID；监测部件分类配置表(sys_mon_comp_cat)
     */
    private String compCatId;
    /**
     * 部件分类名称，与分类ID同步，自动填充；监测部件分类配置表(sys_mon_comp_cat)
     */
    private String compCatName;
    /**
     * 数据类型，标识数据类型，空间对应坐标等，属性对应字段信息
     */
    private String dataType;
    /**
     * 坐标系类型，条件必选(数据类型为空间数据时)，固定为2000国家大地坐标系，不可修改
     */
    private String coordSystem;
    /**
     * 定位精度级别，条件必选(数据类型为空间数据时)，A类(±0.5m)、B类(±1.0m)，参照GB/T XXXXX.6
     */
    private String accuracyLevel;
    /**
     * 高程基准，条件必选(数据类型为空间数据时)，固定为1985国家高程基准，不可修改
     */
    private String elevationDatum;
    /**
     * 字段名称，条件必选（数据类型为属性数据时），部件标识码/主管部门代码等
     */
    private String fieldName;
    /**
     * 字段代码，条件必选（数据类型为属性数据时），采用蛇形命名法，与数据库字段一致
     */
    private String fieldCode;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}