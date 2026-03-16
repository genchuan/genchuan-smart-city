package cn.iocoder.yudao.module.evaluate.dal.dataobject.report;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 评价报告 DO
 *
 * @author 亘川智城
 */
@TableName("eval_report")
@KeySequence("eval_report_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 报告UUID（主键，UUID）
     */
    private String reportId;
    /**
     * 报告编号
     */
    private String code;
    /**
     * 关联模板ID（关联eval_report_template.template_id）
     */
    private String templateId;
    /**
     * 评价对象ID（关联eval_object.object_id）
     */
    private String objectId;
    /**
     * 关联评价任务ID（关联eval_task.task_id）
     */
    private String taskId;
    /**
     * 报告状态ID（关联sys_report_status.status_id）
     */
    private String statusId;
    /**
     * 生成人（关联sys_user.user_id）
     */
    private String createBy;
    /**
     * 业务生成时间（生成时间）
     */
    private LocalDateTime bizCreateTime;
    /**
     * 生成方式（单份生成/批量生成）
     */
    private String createType;
    /**
     * 报告文件大小（字节）
     */
    private Long fileSize;
    /**
     * 下载次数
     */
    private Integer downloadCount;
    /**
     * 最新下载时间
     */
    private LocalDateTime latestDownloadTime;
    /**
     * 补充编辑次数
     */
    private Integer editCount;
    /**
     * 生成启动时间
     */
    private LocalDateTime startCreateTime;
    /**
     * 生成进度
     */
    private String createProgress;
    /**
     * 当前处理节点
     */
    private String processNode;
    /**
     * 预计完成时间
     */
    private LocalDateTime expectCompleteTime;
    /**
     * 数据同步状态（已同步/同步中/同步失败）
     */
    private String dataSyncStatus;
    /**
     * 生成失败原因
     */
    private String failReason;
    /**
     * 核心数据完整性校验结果（已通过/未通过）
     */
    private String dataCheckResult;
    /**
     * 补充编辑入口状态（可编辑/不可编辑）
     */
    private String editStatus;
    /**
     * 重新生成次数
     */
    private Integer recreateCount;
    /**
     * 最新重新生成时间
     */
    private LocalDateTime latestRecreateTime;
    /**
     * 预览次数
     */
    private Integer previewCount;
    /**
     * 待补充章节
     */
    private String needEditChapter;
    /**
     * 补充编辑状态（未开始/编辑中/待提交）
     */
    private String editProgress;
    /**
     * 已补充章节数
     */
    private Integer editedChapterNum;
    /**
     * 总待补充章节数
     */
    private Integer totalNeedChapterNum;
    /**
     * 附件上传状态（未上传/部分上传/已完成）
     */
    private String attachStatus;
    /**
     * 最晚补充完成时间
     */
    private LocalDateTime latestEditTime;
    /**
     * 完成时间
     */
    private LocalDateTime finishTime;
    /**
     * 分发部门数
     */
    private Integer distDeptNum;
    /**
     * 归档状态（已归档/待归档）
     */
    private String archiveStatus;
    /**
     * 补充编辑记录数
     */
    private Integer editRecordNum;
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