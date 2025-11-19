package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.report.inspectproblemSb;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检问题上报记录 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_problem_rpt")
@KeySequence("biz_inspect_problem_rpt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectProblemSbDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 问题ID
     */
    private String problemId;
    /**
     * 问题编码
     */
    private String problemCode;
    /**
     * 问题名称
     */
    private String problemName;
    /**
     * 问题类型ID
     */
    private String matterTypeId;
    /**
     * 问题类型名称
     */
    private String matterTypeName;
    /**
     * 关联任务ID
     */
    private String taskId;
    /**
     * 关联任务编码
     */
    private String taskCode;
    /**
     * 上报人员ID
     */
    private String rptUserId;
    /**
     * 上报人员姓名
     */
    private String rptUserName;
    /**
     * 所属网格ID
     */
    private String gridId;
    /**
     * 所属网格名称
     */
    private String gridName;
    /**
     * 问题位置坐标X
     */
    private BigDecimal problemLocX;
    /**
     * 问题位置坐标Y
     */
    private BigDecimal problemLocY;
    /**
     * 问题位置描述
     */
    private String problemLocDesc;
    /**
     * 问题描述
     */
    private String problemDesc;
    /**
     * 问题照片URLs
     */
    private String problemPhotoUrls;
    /**
     * 上报时间
     */
    private LocalDateTime rptTime;
    /**
     * 问题状态
     */
    private String problemStatus;
    /**
     * 驳回原因
     */
    private String rejectReason;
    /**
     * 创建人
     */
    private String bizCreateUser;
    /**
     * 创建时间
     */
    private LocalDateTime bizCreateTime;
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