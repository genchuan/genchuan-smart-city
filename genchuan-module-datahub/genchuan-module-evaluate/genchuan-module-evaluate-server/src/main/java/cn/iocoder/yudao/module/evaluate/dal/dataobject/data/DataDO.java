package cn.iocoder.yudao.module.evaluate.dal.dataobject.data;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 上报数据 DO
 *
 * @author 芋道源码
 */
@TableName("report_data")
@KeySequence("report_data_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 上报数据ID（UUID）
     */
    private String reportId;
    /**
     * 关联评价任务ID（关联eval_task.task_id）
     */
    private String taskId;
    /**
     * 评价对象ID（关联eval_object.object_id）
     */
    private String objectId;
    /**
     * 指标项ID（关联eval_index_item.item_id）
     */
    private String indexId;
    /**
     * 数据值
     */
    private String dataValue;
    /**
     * 上报时间
     */
    private LocalDateTime reportTime;
    /**
     * 上报人ID（关联sys_user.user_id）
     */
    private String reportBy;
    /**
     * 数据状态ID（关联sys_data_status.status_id）
     */
    private String dataStatusId;
    /**
     * 校验结果ID（关联sys_verify_result.result_id）
     */
    private String verifyResultId;
    /**
     * 错误原因
     */
    private String errorReason;
    /**
     * 处理时间
     */
    private LocalDateTime processTime;
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
