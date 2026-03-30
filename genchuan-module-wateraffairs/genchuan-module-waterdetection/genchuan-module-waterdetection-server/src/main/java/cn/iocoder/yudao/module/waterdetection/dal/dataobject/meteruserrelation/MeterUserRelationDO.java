package cn.iocoder.yudao.module.waterdetection.dal.dataobject.meteruserrelation;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 户表关联及变更管理 DO
 *
 * @author zcq
 */
@TableName("gc_meter_user_relation")
@KeySequence("gc_meter_user_relation_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeterUserRelationDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 户表编号
     */
    private String meterCode;
    /**
     * 原用户编号
     */
    private String oldUserCode;
    /**
     * 新用户编号
     */
    private String newUserCode;
    /**
     * 变更原因
     */
    private String changeReason;
    /**
     * 变更时间
     */
    private LocalDateTime changeTime;
    /**
     * 经办人
     */
    private String operator;

}