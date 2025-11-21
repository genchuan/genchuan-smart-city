package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.data.evalplatformrptdata;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 平台上报数据 DO
 *
 * @author zcq
 */
@TableName("sys_eval_platform_rpt_data")
@KeySequence("sys_eval_platform_rpt_data_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalPlatformRptDataDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 上报数据ID
     */
    private String platformRptDataId;
    /**
     * 关联任务ID
     */
    private String evalTaskId;
    /**
     * 关联任务名称
     */
    private String evalTaskName;
    /**
     * 评价对象ID
     */
    private String evalObjectId;
    /**
     * 评价对象名称
     */
    private String evalObjectName;
    /**
     * 关联指标项ID
     */
    private String idxItemId;
    /**
     * 关联指标项名称
     */
    private String idxItemName;
    /**
     * 数据值
     */
    private String dataValue;
    /**
     * 数据单位
     */
    private String dataUnit;
    /**
     * 上报时间
     */
    private LocalDateTime rptTime;
    /**
     * 上报人ID
     */
    private String rptUserId;
    /**
     * 上报人姓名
     */
    private String rptUserName;
    /**
     * 数据状态
     */
    private String dataStatus;
    /**
     * 校验失败原因
     */
    private String verifyFailReason;
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