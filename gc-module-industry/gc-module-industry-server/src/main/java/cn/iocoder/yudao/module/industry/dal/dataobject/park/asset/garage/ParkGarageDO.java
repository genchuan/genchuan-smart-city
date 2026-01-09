package cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.garage;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 车库信息 DO
 *
 * @author zhucongquan
 */
@TableName("park_garage")
@KeySequence("park_garage_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkGarageDO extends BaseDO {

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
     * 关联车场ID
     */
    private String lotId;
    /**
     * 楼层数
     */
    private Integer floorCount;
    /**
     * 总车位数
     */
    private Integer totalSpace;
    /**
     * 当前可用车位数
     */
    private Integer availableSpace;
    /**
     * 门禁类型：车牌识别/刷卡/人脸识别
     */
    private String accessControlType;
    /**
     * 业务创建时间
     */
    private LocalDateTime garageCreateTime;
    /**
     * 业务更新时间
     */
    private LocalDateTime garageUpdateTime;
    /**
     * 业务备注
     */
    private String garageRemark;

}