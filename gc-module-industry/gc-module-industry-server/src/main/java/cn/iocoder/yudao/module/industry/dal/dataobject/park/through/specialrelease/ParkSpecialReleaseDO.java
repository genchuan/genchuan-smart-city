package cn.iocoder.yudao.module.industry.dal.dataobject.park.through.specialrelease;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 特殊放行 DO
 *
 * @author zhucongquan
 */
@TableName("park_special_release")
@KeySequence("park_special_release_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkSpecialReleaseDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 特殊放行ID（UUID）
     */
    private String releaseId;
    /**
     * 车牌
     */
    private String carNumber;
    /**
     * 放行类型：紧急开闸/特殊车辆/其他
     */
    private String releaseType;
    /**
     * 放行原因
     */
    private String reason;
    /**
     * 出入口ID
     */
    private String entryExitId;
    /**
     * 放行操作人ID
     */
    private Long releaseBy;
    /**
     * 放行时间
     */
    private LocalDateTime releaseTime;
    /**
     * 核验状态：已核验/未核验
     */
    private String verifyStatus;
    /**
     * 核验人ID
     */
    private Long verifyBy;
    /**
     * 核验时间
     */
    private LocalDateTime verifyTime;
    /**
     * 业务创建时间
     */
    private LocalDateTime releaseCreateTime;
    /**
     * 业务更新时间
     */
    private LocalDateTime releaseUpdateTime;
    /**
     * 业务备注
     */
    private String releaseRemark;

}