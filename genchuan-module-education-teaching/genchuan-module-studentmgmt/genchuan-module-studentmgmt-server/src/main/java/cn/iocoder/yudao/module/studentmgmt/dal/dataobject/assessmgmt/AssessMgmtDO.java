package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.assessmgmt;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 考评管理 DO
 *
 * @author 芋道源码
 */
@TableName("assess_mgmt")
@KeySequence("assess_mgmt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssessMgmtDO extends BaseDO {

    /**
     * 主键 ID
     */
    @TableId
    private Long id;
    /**
     * 班级
     */
    private String className;
    /**
     * 考评类型：教室卫生/早操/文明班级/黑板报
     */
    private String assessType;
    /**
     * 统计周期：周/月/学期
     */
    private String cycle;
    /**
     * 考评得分
     */
    private BigDecimal score;
    /**
     * 班级排名
     */
    private Integer rank;
    /**
     * 考评人
     */
    private String assessUser;
    /**
     * 发布时间
     */
    private LocalDateTime publishTime;
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
