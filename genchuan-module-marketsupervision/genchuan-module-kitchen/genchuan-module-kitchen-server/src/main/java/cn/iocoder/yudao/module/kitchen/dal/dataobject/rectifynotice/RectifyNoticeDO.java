package cn.iocoder.yudao.module.kitchen.dal.dataobject.rectifynotice;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 整改通知书 DO
 *
 * @author 亘川智城
 */
@TableName("rectify_notice")
@KeySequence("rectify_notice_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RectifyNoticeDO extends BaseDO {

    /**
     * [主键ID] 整改通知书唯一标识
     */
    @TableId
    private Long id;
    /**
     * [整改通知书编号] 唯一编号
     */
    private String noticeCode;
    /**
     * [整改复审台账ID] 关联park_rectify_review.id，唯一
     */
    private Long rectifyReviewId;
    /**
     * [下发时间] 通知书正式下发时间
     */
    private LocalDateTime issueTime;
    /**
     * [整改期限] 要求完成整改的截止日期
     */
    private LocalDate rectifyDeadline;
    /**
     * [送达状态] 如：未送达/已送达/拒收
     */
    private String receiveStatus;
    /**
     * [送达时间] 实际送达或拒收时间
     */
    private LocalDateTime receiveTime;
    /**
     * [通知书原件内容] 富文本内容
     */
    private String noticeContent;
    /**
     * [通用扩展字段1] 预留
     */
    private String extCommon1;
    /**
     * [通用扩展字段2] 预留
     */
    private String extCommon2;
    /**
     * [通用扩展字段3] 预留
     */
    private String extCommon3;
    /**
     * [通用扩展字段4] 预留
     */
    private String extCommon4;

}
