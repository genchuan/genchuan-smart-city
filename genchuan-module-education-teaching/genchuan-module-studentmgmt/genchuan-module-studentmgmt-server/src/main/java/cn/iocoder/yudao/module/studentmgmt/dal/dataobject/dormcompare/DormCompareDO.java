package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dormcompare;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 宿舍评比 DO
 *
 * @author 芋道源码
 */
@TableName("dorm_compare")
@KeySequence("dorm_compare_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DormCompareDO extends BaseDO {

    /**
     * 主键 ID
     */
    @TableId
    private Long id;
    /**
     * 宿舍 ID
     */
    private Long dormId;
    /**
     * 宿舍号
     */
    private String dormNum;
    /**
     * 评比周期：周/月/学期
     */
    private String cycle;
    /**
     * 得分
     */
    private BigDecimal score;
    /**
     * 排名
     */
    private Integer rankNo;
    /**
     * 打分人
     */
    private String scoreUser;
    /**
     * 汇总时间
     */
    private LocalDateTime sumTime;
    /**
     * 推送时间
     */
    private LocalDateTime pushTime;
    /**
     * 状态：打分中/已汇总
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
