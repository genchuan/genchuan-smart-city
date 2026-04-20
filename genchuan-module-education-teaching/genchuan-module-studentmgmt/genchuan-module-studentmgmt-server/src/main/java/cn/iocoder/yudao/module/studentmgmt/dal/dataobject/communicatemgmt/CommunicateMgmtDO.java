package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.communicatemgmt;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 沟通管理 DO
 *
 * @author 芋道源码
 */
@TableName("communicate_mgmt")
@KeySequence("communicate_mgmt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunicateMgmtDO extends BaseDO {

    /**
     * 主键 ID
     */
    @TableId
    private Long id;
    /**
     * 消息标题
     */
    private String title;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 发布人
     */
    private String sendUser;
    /**
     * 发布时间
     */
    private LocalDateTime sendTime;
    /**
     * 家长反馈内容
     */
    private String replyContent;
    /**
     * 反馈时间
     */
    private LocalDateTime replyTime;
    /**
     * 互动率
     */
    private BigDecimal interactRate;
    /**
     * 状态：未发布/已发布
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备用字段 1
     */
    private String reserve1;
    /**
     * 备用字段 2
     */
    private String reserve2;


}