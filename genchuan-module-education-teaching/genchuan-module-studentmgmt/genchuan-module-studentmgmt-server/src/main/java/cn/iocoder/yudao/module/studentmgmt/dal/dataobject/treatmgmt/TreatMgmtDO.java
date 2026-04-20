package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.treatmgmt;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 就诊管理 DO
 *
 * @author 芋道源码
 */
@TableName("treat_mgmt")
@KeySequence("treat_mgmt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreatMgmtDO extends BaseDO {

    /**
     * 主键 ID
     */
    @TableId
    private Long id;
    /**
     * 学生 ID
     */
    private Long studentId;
    /**
     * 就诊类型：门诊/急诊/其他
     */
    private String treatType;
    /**
     * 症状描述
     */
    private String symptom;
    /**
     * 就诊登记时间
     */
    private LocalDateTime registerTime;
    /**
     * 就诊内容
     */
    private String treatContent;
    /**
     * 预约时间
     */
    private LocalDateTime applyTime;
    /**
     * 审核人
     */
    private String auditUser;
    /**
     * 审核时间
     */
    private LocalDateTime auditTime;
    /**
     * 家长反馈时间
     */
    private LocalDateTime feedbackTime;
    /**
     * 状态：待审核/已就诊
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