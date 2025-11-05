package cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncompext;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 监测部件扩展配置 DO
 *
 * @author 亘川智城
 */
@TableName("sys_mon_comp_ext")
@KeySequence("sys_mon_comp_ext_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonCompExtDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 扩展配置ID，唯一编码，UUID生成
     */
    private String extId;
    /**
     * 部件ID，关联监测部件信息表ID；监测部件信息表(biz_mon_comp_info)
     */
    private String compId;
    /**
     * 扩展键
     */
    private String extKey;
    /**
     * 扩展值
     */
    private String extValue;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}