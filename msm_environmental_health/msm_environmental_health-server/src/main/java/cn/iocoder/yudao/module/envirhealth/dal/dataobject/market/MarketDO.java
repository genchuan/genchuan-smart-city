package cn.iocoder.yudao.module.envirhealth.dal.dataobject.market;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
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
     * 主键（UUID）
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
     * 关联sys_area.area_code
     */
    private String areaCode;
    /**
     * 摊位数量
     */
    private Integer stallCount;
    /**
     * 关联sys_user.id
     */
    private String managerId;
    /**
     * 关联sys_operation_status.id
     */
    private String operationStatusId;
    /**
     * 卫生达标率
     */
    private BigDecimal hygieneRate;
    /**
     * 收运完成率
     */
    private BigDecimal wasteTransferRate;
    /**
     * 污水处置合格率
     */
    private BigDecimal sewageRate;
    /**
     * 未完成任务数
     */
    private Integer unfinishedTaskCount;
    /**
     * 保洁频次
     */
    private String cleaningFrequency;
    /**
     * 保洁时段，JSON
     */
    private String cleaningTime;
    /**
     * 保洁区域
     */
    private String cleaningArea;
    /**
     * 负责人员IDs，JSON
     */
    private String staffIds;
    /**
     * 保洁标准
     */
    private String cleaningStandard;
    /**
     * 垃圾类型IDs，JSON
     */
    private String garbageTypeIds;
    /**
     * 收集容器数量
     */
    private Integer garbageContainerCount;
    /**
     * 收运间隔
     */
    private String wasteTransferInterval;
    /**
     * 收运时段
     */
    private String wasteTransferTime;
    /**
     * 关联sys_vehicle.id
     */
    private String vehicleId;
    /**
     * 污水排放区域
     */
    private String sewageDischargeArea;
    /**
     * 污水处置方式
     */
    private String sewageDisposalWay;
    /**
     * 清理频次
     */
    private String sewageCleaningFrequency;
    /**
     * 问题描述
     */
    private String sewageProblemDesc;
    /**
     * 上次清理时间
     */
    private LocalDateTime lastSewageCleaningTime;
    /**
     * 下次清理时间
     */
    private LocalDateTime nextSewageCleaningTime;
    /**
     * 处置日志
     */
    private String sewageDisposalLog;
    /**
     * 核查时段
     */
    private String hygieneCheckTime;
    /**
     * 关联sys_user.id
     */
    private String checkBy;
    /**
     * 核查日期
     */
    private LocalDateTime hygieneCheckDate;
    /**
     * 前期问题
     */
    private String previousProblem;
    /**
     * 达标项数
     */
    private Integer qualifiedItemCount;
    /**
     * 不达标项数
     */
    private Integer unqualifiedItemCount;
    /**
     * 整改要求
     */
    private String reformRequire;
    /**
     * 整改期限
     */
    private LocalDateTime reformDeadline;
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

    private String handleStatusId;

    private String checkResultId;

    private String taskTypeId;
}