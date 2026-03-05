package cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.entryexit;

import lombok.*;

import java.time.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 出入口信息 DO
 *
 * @author zhucongquan
 */
@TableName("park_entry_exit")
@KeySequence("park_entry_exit_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkEntryExitDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 关联ID
     */
    private String assetExtendId;
    /**
     * 所属车场ID
     */
    private String lotId;
    /**
     * 方向：入口/出口/双向
     */
    private String direction;
    /**
     * 关联设备ID列表
     */
    private String deviceIds;
    /**
     * 通行规则ID
     */
    private String passRuleId;
    /**
     * 开放时间
     */
    private LocalTime openTime;
    /**
     * 关闭时间
     */
    private LocalTime closeTime;
    /**
     * 业务创建时间
     */
    private LocalDateTime entryExitCreateTime;
    /**
     * 业务更新时间
     */
    private LocalDateTime entryExitUpdateTime;
    /**
     * 业务备注
     */
    private String entryExitRemark;

}