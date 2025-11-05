package cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncompcat;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 监测部件分类配置 DO
 *
 * @author 亘川智城
 */
@TableName("sys_mon_comp_cat")
@KeySequence("sys_mon_comp_cat_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonCompCatDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 分类ID，唯一编码，UUID生成
     */
    private String monCompCatId;
    /**
     * 父类ID，关联本表格“分类ID”，大类父类ID为“0”；监测部件分类配置表（sys_mon_comp_cat）
     */
    private String parentCatId;
    /**
     * 分类层级，标识分类所处层级，1对应大类、2对应中类、3对应小类
     */
    private String catLevel;
    /**
     * 分类代码，大类/中类从01顺序编排，小类从001顺序编排，扩展类小类从080倒排
     */
    private String catCode;
    /**
     * 分类名称，参照GB/T XXXXX.6标准名称，扩展类标注“自定义”
     */
    private String catName;
    /**
     * 分类说明，描述分类包含的部件范围，如“燃气压力传感器：监测燃气管道压力的设备”
     */
    private String catDesc;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}