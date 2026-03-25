package cn.iocoder.yudao.module.smartcity.dal.dataobject.lawdocument;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 执法文书 DO
 *
 * @author 朱聪权
 */
@TableName("smartcity_law_document")
@KeySequence("smartcity_law_document_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LawDocumentDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 案件ID
     */
    private String caseId;
    /**
     * 文书类型
     */
    private String documentType;
    /**
     * 文书编号
     */
    private String documentCode;
    /**
     * 文书标题
     */
    private String documentTitle;
    /**
     * 文书内容
     */
    private String documentContent;
    /**
     * 创建人
     */
    private String documentCreator;
    /**
     * 审批人
     */
    private String approver;
    /**
     * 审批时间
     */
    private LocalDateTime approvalTime;
    /**
     * 审批状态
     */
    private String approvalStatus;
    /**
     * 签署人
     */
    private String signatory;
    /**
     * 签署时间
     */
    private LocalDateTime signTime;
    /**
     * 盖章状态
     */
    private String sealStatus;
    /**
     * 盖章时间
     */
    private LocalDateTime sealTime;
    /**
     * 打印状态
     */
    private String printStatus;
    /**
     * 打印次数
     */
    private Integer printTimes;

}