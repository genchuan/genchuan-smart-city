package cn.iocoder.yudao.module.evaluate.dal.dataobject.reporttemplate;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 报告模板 DO
 *
 * @author 亘川智城
 */
@TableName("eval_report_template")
@KeySequence("eval_report_template_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportTemplateDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 模板UUID（主键，UUID）
     */
    private String templateId;
    /**
     * 模板编号
     */
    private String code;
    /**
     * 模板名称
     */
    private String name;
    /**
     * 适用任务类型ID（关联sys_task_type.type_id）
     */
    private String taskTypeId;
    /**
     * 版本号
     */
    private String version;
    /**
     * 模板状态ID（关联sys_template_status.status_id）
     */
    private String statusId;
    /**
     * 创建人（关联sys_user.user_id）
     */
    private String createBy;
    /**
     * 业务创建时间（创建时间）
     */
    private LocalDateTime bizCreateTime;
    /**
     * 使用次数
     */
    private Integer useCount;
    /**
     * 最近生成时间
     */
    private LocalDateTime latestUseTime;
    /**
     * 字段映射规则数
     */
    private Integer mapRuleNum;
    /**
     * 模板文件大小（字节）
     */
    private Long fileSize;
    /**
     * 模板文件格式（Word/PDF）
     */
    private String fileFormat;
    /**
     * 版本迭代记录
     */
    private String versionLog;
    /**
     * 模板使用率（%）
     */
    private BigDecimal useRate;
    /**
     * 字段映射完整度（%）
     */
    private BigDecimal mapCompleteRate;
    /**
     * 业务模板更新时间（模板更新时间）
     */
    private LocalDateTime bizUpdateTime;
    /**
     * 使用部门分布
     */
    private String deptDist;
    /**
     * 停用操作人（关联sys_user.user_id）
     */
    private String stopBy;
    /**
     * 停用时间
     */
    private LocalDateTime stopTime;
    /**
     * 停用原因
     */
    private String stopReason;
    /**
     * 停用时长（小时）
     */
    private BigDecimal stopHour;
    /**
     * 模板文件状态（正常/损坏/缺失）
     */
    private String fileStatus;
    /**
     * 字段映射规则有效性（有效/无效）
     */
    private String mapValid;
    /**
     * 最新版本迭代记录
     */
    private String latestVersionLog;
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