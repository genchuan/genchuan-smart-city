package cn.iocoder.yudao.module.park.dal.dataobject.park.user.certification;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 认证记录 DO
 *
 * @author 亘川智城
 */
@TableName("park_certification")
@KeySequence("park_certification_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificationDO extends BaseDO {

    /**
     * [主键ID]
     */
    @TableId
    private Long id;
    /**
     * [用户ID] 关联park_user.id
     */
    private Long userId;
    /**
     * [用户类型] 如:个人/企业
     */
    private String userType;
    /**
     * [认证类型] 如:身份认证/企业认证
     */
    private String certType;
    /**
     * [认证材料] JSON格式varchar
     */
    private String certFiles;
    /**
     * [身份证号] 个人认证
     */
    private String idCard;
    /**
     * [统一社会信用代码] 企业认证
     */
    private String creditCode;
    /**
     * [申请时间]
     */
    private LocalDateTime applyTime;
    /**
     * [审核人ID] 关联park_user.id
     */
    private Long auditBy;
    /**
     * [审核时间]
     */
    private LocalDateTime auditTime;
    /**
     * [审核结果] 如:通过/驳回
     */
    private String auditResult;
    /**
     * [审核意见] 可为NULL
     */
    private String auditOpinion;
    /**
     * [备注]
     */
    private String remark;
    /**
     * [通用扩展字段1]
     */
    private String extCommon1;
    /**
     * [通用扩展字段2]
     */
    private String extCommon2;
    /**
     * [通用扩展字段3]
     */
    private String extCommon3;
    /**
     * [通用扩展字段4]
     */
    private String extCommon4;

}
