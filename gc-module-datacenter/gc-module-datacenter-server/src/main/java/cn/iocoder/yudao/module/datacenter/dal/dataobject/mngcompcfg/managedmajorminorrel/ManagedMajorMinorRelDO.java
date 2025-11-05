package cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.managedmajorminorrel;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 管理部件大类小类关联 DO
 *
 * @author 亘川智城
 */
@TableName("gc_managed_major_minor_rel")
@KeySequence("gc_managed_major_minor_rel_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagedMajorMinorRelDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 关联ID，唯一编码，采用UUID
     */
    private String relId;
    /**
     * 关联的大类ID
     */
    private String majorId;
    /**
     * 关联的小类ID
     */
    private String minorId;
    /**
     * 关联状态：0-无效/1-有效
     */
    private String relStatus;
    /**
     * 关联时间，格式：yyyy-MM-dd HH:mm:ss
     */
    private LocalDateTime relTime;
    /**
     * 解除关联时间，格式：yyyy-MM-dd HH:mm:ss
     */
    private LocalDateTime unrelTime;
    /**
     * 操作人ID
     */
    private String operateUser;
    /**
     * 备注信息
     */
    private String remark;
    /**
     * 扩展字段1
     */
    private String extField1;
    /**
     * 扩展字段2
     */
    private String extField2;
    /**
     * 扩展字段3
     */
    private String extField3;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}