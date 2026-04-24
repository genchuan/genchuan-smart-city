package cn.iocoder.yudao.module.vehiclepass.dal.dataobject.siteinput.spacequery;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 泊位查询 DO
 *
 * @author 亘川智城
 */
@TableName("space_query")
@KeySequence("space_query_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceQueryDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 泊位编号
     */
    private String spaceNo;
    /**
     * 查询时间
     */
    private LocalDateTime queryTime;
    /**
     * 查询人ID，关联芋道用户表system_user
     */
    private Long queryUserId;
    /**
     * 片区ID，关联片区表
     */
    private Long areaId;
    /**
     * 泊位状态：空闲/占用，关联字典space_query_space_status
     */
    private String spaceStatus;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}