package cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.relmngmattermajmin;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 管理事项大小类关联 DO
 *
 * @author 亘川智城
 */
@TableName("gc_rel_mng_matter_maj_min")
@KeySequence("gc_rel_mng_matter_maj_min_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelMngMatterMajMinDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 关联ID，唯一编码，UUID生成
     */
    private String mngMatterMajorMinorId;
    /**
     * 关联管理事项大类ID，关联管理事项大类表(gc_biz_mng_matter_major)
     */
    private String majorId;
    /**
     * 关联管理事项大类名称，与大类ID同步，不可手动修改，关联管理事项大类表(gc_biz_mng_matter_major)
     */
    private String majorName;
    /**
     * 关联管理事项小类ID，关联管理事项小类表(gc_biz_mng_matter_minor)
     */
    private String minorId;
    /**
     * 关联管理事项小类名称，与小类ID同步，不可手动修改，关联管理事项小类表(gc_biz_mng_matter_minor)
     */
    private String minorName;
    /**
     * 关联状态，1（有效）/0（无效），1表示正常关联，0表示已解除关联
     */
    private String relStatus;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}