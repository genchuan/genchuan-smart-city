package cn.iocoder.yudao.module.ordertrade.dal.dataobject.merchantreconcile;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@TableName("reconcile_record")
@KeySequence("reconcile_record_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ReconcileRecordDO extends BaseDO {

    @TableId
    private Long id;

    /** 关联单据ID */
    private Long billId;

    /** 状态：normal/abnormal */
    private String status;

    /** 状态更新时间（业务字段） */
    private LocalDateTime updateTime;

    /** 异常原因 */
    private String errorReason;

    /** 核查人ID */
    private Long checkerId;

    /** 核查时间 */
    private LocalDateTime checkTime;

    /** 备注 */
    private String remark;

    private String reserve1;

    private String reserve2;
}
