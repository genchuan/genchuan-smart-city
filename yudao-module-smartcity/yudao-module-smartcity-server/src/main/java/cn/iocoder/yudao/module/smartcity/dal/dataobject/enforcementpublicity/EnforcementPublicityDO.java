package cn.iocoder.yudao.module.smartcity.dal.dataobject.enforcementpublicity;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 执法公示 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_enforcement_publicity")
@KeySequence("smartcity_enforcement_publicity_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnforcementPublicityDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 执法公示编号
     */
    private String publicityNumber;
    /**
     * 公示标题
     */
    private String publicityTitle;
    /**
     * 公示内容
     */
    private String publicityContent;
    /**
     * 公示类型
     */
    private String publicityType;
    /**
     * 执法部门
     */
    private String enforcement;
    /**
     * 执法地点
     */
    private String enforcementLocation;
    /**
     * 执法依据
     */
    private String enforcementBasis;
    /**
     * 执法结果
     */
    private String enforcementResults;
    /**
     * 公示开始时间
     */
    private LocalDateTime announcementStartTime;
    /**
     * 公示截止时间
     */
    private LocalDateTime announcementDeadline;

}