package cn.iocoder.yudao.module.envir.dal.dataobject.market;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 集贸市场 DO
 *
 * @author 芋道源码
 */
@TableName("market")
@KeySequence("market_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 业务主键（UUID）
     */
    private String marketId;
    /**
     * 市场名称
     */
    private String name;
    /**
     * 市场地址
     */
    private String address;
    /**
     * 所属区域（关联sys_area.area_code）
     */
    private String areaCode;
    /**
     * 摊位数量
     */
    private Integer stallCount;
    /**
     * 摊位保洁责任划分规则（含区域、摊主责任、保洁员责任、考核标准等）
     */
    private String stallCleaningRule;
    /**
     * 保洁频次（可选值：每小时/每日3次/每日2次/每日1次/营业前/营业后）
     */
    private String cleaningFrequency;
    /**
     * 垃圾清运间隔（单位：小时）
     */
    private Integer wasteTransferInterval;
    /**
     * 污水处置方式（可选值：统一管网排放/化粪池处理/第三方清运/就地净化）
     */
    private String sewageDisposalWay;
    /**
     * 负责人（关联sys_user.id）
     */
    private String managerId;
    /**
     * 业务创建人（关联sys_user.id）
     */
    private String abnormalCreateBy;
    /**
     * 业务创建时间
     */
    private LocalDateTime abnormalCreateTime;
    /**
     * 业务更新时间
     */
    private LocalDateTime abnormalUpdateTime;
    /**
     * 卫生达标率（0.00-100.00）
     */
    private BigDecimal hygieneRate;
    /**
     * 垃圾清运量（单位：立方米/日）
     */
    private BigDecimal wasteVolume;
    /**
     * 污水处置达标率（0.00-100.00）
     */
    private BigDecimal sewageRate;
    /**
     * 污水处置对比照片URL（多个用逗号分隔）
     */
    private String sewagePhotoUrl;
    /**
     * 卫生核查照片URL（多个用逗号分隔）
     */
    private String hygieneCheckPhotoUrl;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    private String extCommon4;

}