package cn.iocoder.yudao.module.industry.dal.dataobject.park.through.trafficinspection;

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
 * 通行稽查 DO
 *
 * @author zhucongquan
 */
@TableName("park_traffic_inspection")
@KeySequence("park_traffic_inspection_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkTrafficInspectionDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 稽查记录ID（UUID）
     */
    private String inspectionId;
    /**
     * 车牌
     */
    private String carNumber;
    /**
     * 入场记录ID
     */
    private String entryId;
    /**
     * 离场记录ID
     */
    private String exitId;
    /**
     * 违规类型：套牌/逃费/无权限通行/其他
     */
    private String violationType;
    /**
     * 违规时间
     */
    private LocalDateTime violationTime;
    /**
     * 证据ID列表
     */
    private String evidenceIds;
    /**
     * 处置状态：未处置/处置中/已处置
     */
    private String disposalStatus;
    /**
     * 处置内容
     */
    private String disposalContent;
    /**
     * 处置人ID
     */
    private Long disposalBy;
    /**
     * 处置时间
     */
    private LocalDateTime disposalTime;
    /**
     * 业务创建时间
     */
    private LocalDateTime inspectionCreateTime;
    /**
     * 业务更新时间
     */
    private LocalDateTime inspectionUpdateTime;
    /**
     * 业务备注
     */
    private String inspectionRemark;

}