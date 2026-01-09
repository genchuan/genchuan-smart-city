package cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkreleaserecord;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 放行记录 DO
 *
 * @author lxs
 */
@TableName("biz_park_release_record")
@KeySequence("biz_park_release_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkReleaseRecordDO extends BaseDO {

    /**
     * 主键，唯一标识
     */
    @TableId
    private Long id;
    /**
     * 关联缴费记录标识
     */
    private Long payId;
    /**
     * 放行指令唯一标识
     */
    private Long releaseCmdId;
    /**
     * 放行状态（未放行，已放行）
     */
    private String releaseStatus;
    /**
     * 实际放行时间
     */
    private LocalDateTime releaseTime;
    /**
     * 闸机唯一标识
     */
    private Long gateId;
    /**
     * 执行放行操作人员
     */
    private String operatorName;
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
