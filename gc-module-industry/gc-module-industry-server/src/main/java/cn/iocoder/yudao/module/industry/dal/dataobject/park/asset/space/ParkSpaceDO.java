package cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.space;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 车位信息 DO
 *
 * @author zhucongquan
 */
@TableName("park_space")
@KeySequence("park_space_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkSpaceDO extends BaseDO {

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
     * 所属车库ID
     */
    private String garageId;
    /**
     * 唯一车位编号
     */
    private String spaceNumber;
    /**
     * 车位类型：普通/新能源/残疾人专用/子母位
     */
    private String spaceType;
    /**
     * 绑定车牌列表
     */
    private String bindCarList;
    /**
     * 是否可预约：0-否/1-是
     */
    private Boolean isReservable;
    /**
     * 状态：空闲/占用/预约/故障/禁用
     */
    private String status;
    /**
     * 业务创建时间
     */
    private LocalDateTime spaceCreateTime;
    /**
     * 业务更新时间
     */
    private LocalDateTime spaceUpdateTime;
    /**
     * 业务备注
     */
    private String spaceRemark;

}