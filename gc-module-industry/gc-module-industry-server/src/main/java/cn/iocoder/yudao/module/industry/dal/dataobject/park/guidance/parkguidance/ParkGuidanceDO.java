package cn.iocoder.yudao.module.industry.dal.dataobject.park.guidance.parkguidance;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 停车诱导服务 DO
 *
 * @author lxs
 */
@TableName("gen_park_guidance")
@KeySequence("gen_park_guidance_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkGuidanceDO extends BaseDO {

    /**
     * 记录唯一标识
     */
    @TableId
    private Long id;
    /**
     * 停车诱导业务唯一标识
     */
    private String guidanceId;
    /**
     * 用户唯一标识
     */
    private Long userId;
    /**
     * 用户当前位置X坐标
     */
    private BigDecimal userPosX;
    /**
     * 用户当前位置Y坐标
     */
    private BigDecimal userPosY;
    /**
     * 推荐的停车场唯一标识
     */
    private Long recommendParkId;
    /**
     * 推荐的停车场名称
     */
    private String recommendParkName;
    /**
     * 推荐停车场剩余泊位数量
     */
    private Integer remainBerths;
    /**
     * 到达推荐停车场的预计行驶时间（秒）
     */
    private Integer estDriveTime;
    /**
     * 推荐停车场收费标准
     */
    private String chargeStd;
    /**
     * 停车诱导采用的方式
     */
    private String guidanceMethod;
    /**
     * 执行停车诱导的时间
     */
    private LocalDateTime guidanceTime;
    /**
     * 用户在诱导过程中的行为记录
     */
    private String userBehavior;
    /**
     * 所属行政区划名称
     */
    private String regionName;
    /**
     * 所属网格名称
     */
    private String gridName;
    /**
     * 关联的管理事项名称
     */
    private String matterName;
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
