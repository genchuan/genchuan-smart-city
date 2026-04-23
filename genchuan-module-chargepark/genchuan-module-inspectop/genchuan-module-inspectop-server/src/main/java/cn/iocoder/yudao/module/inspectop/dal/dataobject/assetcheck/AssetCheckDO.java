package cn.iocoder.yudao.module.inspectop.dal.dataobject.assetcheck;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 资产盘点 DO
 *
 * @author zhucongquan
 */
@TableName("asset_check")
@KeySequence("asset_check_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetCheckDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 盘点类型
     */
    private String type;
    /**
     * 盘点时间
     */
    private LocalDateTime checkTime;
    /**
     * 盘点进度
     */
    private Integer progress;
    /**
     * 盘点状态
     */
    private String status;
    /**
     * 确认人ID
     */
    private Long confirmUserId;
    /**
     * 确认时间
     */
    private LocalDateTime confirmTime;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}