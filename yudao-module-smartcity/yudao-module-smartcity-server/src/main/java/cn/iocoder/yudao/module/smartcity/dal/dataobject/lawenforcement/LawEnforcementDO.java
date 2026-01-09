package cn.iocoder.yudao.module.smartcity.dal.dataobject.lawenforcement;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 城市管理执法 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_law_enforcement")
@KeySequence("smartcity_law_enforcement_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LawEnforcementDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 执法案件编号
     */
    private String lawEnforcementNumber;
    /**
     * 案件发生地点
     */
    private String locationTheCase;
    /**
     * 发生时间
     */
    private String occurrenceTime;
    /**
     * 涉事主体信息
     */
    private String informationInvolved;
    /**
     * 案件详情描述
     */
    private String caseDetailsDescription;
    /**
     * 案件处理时长
     */
    private String durationOfCaseHandling;
    /**
     * 投诉举报次数
     */
    private String numberComplaintsReports;
    /**
     * 投诉内容
     */
    private String complaintContent;

}