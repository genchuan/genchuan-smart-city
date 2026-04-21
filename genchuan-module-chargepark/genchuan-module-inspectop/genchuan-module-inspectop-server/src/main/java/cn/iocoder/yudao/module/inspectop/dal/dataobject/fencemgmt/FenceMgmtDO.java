package cn.iocoder.yudao.module.inspectop.dal.dataobject.fencemgmt;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 电子围栏 DO
 *
 * @author zhucongquan
 */
@TableName("fence_mgmt")
@KeySequence("fence_mgmt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FenceMgmtDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 围栏名称
     */
    private String name;
    /**
     * 围栏区域
     */
    private String area;
    /**
     * 关联巡检人员ID
     */
    private Long userId;
    /**
     * 围栏状态
     */
    private String status;
    /**
     * 告警触发数
     */
    private Integer alarmCount;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}