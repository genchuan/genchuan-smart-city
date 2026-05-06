package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.newpush;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 迎新推送 DO
 *
 * @author 芋道源码
 */
@TableName("new_push")
@KeySequence("new_push_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewPushDO extends BaseDO {

    /**
     * 主键 ID
     */
    @TableId
    private Long id;
    /**
     * 推送任务名称
     */
    private String taskName;
    /**
     * 推送内容
     */
    private String pushContent;
    /**
     * 推送人数
     */
    private Integer pushNum;
    /**
     * 推送时间
     */
    private LocalDateTime pushTime;
    /**
     * 推送完成率
     */
    private BigDecimal finishRate;
    /**
     * 状态：未推送/已推送
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