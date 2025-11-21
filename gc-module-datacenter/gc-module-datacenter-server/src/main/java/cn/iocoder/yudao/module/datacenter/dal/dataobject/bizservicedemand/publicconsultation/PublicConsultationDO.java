package cn.iocoder.yudao.module.datacenter.dal.dataobject.bizservicedemand.publicconsultation;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 咨询建议 DO
 *
 * @author 亘川智城
 */
@TableName("biz_public_consultation")
@KeySequence("biz_public_consultation_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicConsultationDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 咨询ID，唯一编码，UUID生成
     */
    private String consultId;
    /**
     * 咨询单号，格式“ZX+YYYYMMDD+6位流水号”
     */
    private String consultNo;
    /**
     * 咨询类型编码，类型（如“政策咨询”“建设建议”），字典表（sys_dict）
     */
    private String consultTypeCode;
    /**
     * 咨询类型名称，与类型编码同步，字典表（sys_dict）
     */
    private String consultTypeName;
    /**
     * 咨询内容，市民咨询或建议内容
     */
    private String consultContent;
    /**
     * 联系人姓名，咨询人姓名
     */
    private String contactName;
    /**
     * 联系人电话，加密存储
     */
    private String contactPhone;
    /**
     * 受理部门编码，受理部门，部门信息表（sys_org）
     */
    private String acceptDeptCode;
    /**
     * 受理部门名称，与部门编码同步，部门信息表（sys_org）
     */
    private String acceptDeptName;
    /**
     * 回复内容，部门回复内容
     */
    private String replyContent;
    /**
     * 咨询状态：1=待回复，2=已回复，3=已采纳（建议类），流程状态
     */
    private Integer consultStatus;
    /**
     * 提交时间，市民提交时间
     */
    private LocalDateTime submitTime;
    /**
     * 回复时间，部门回复时间
     */
    private LocalDateTime replyTime;
    /**
     * 备注，补充说明，如“建议已转规划部门”
     */
    private String remark;
    /**
     * 分类扩展字段1，预留，标识咨询紧急程度
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，标识建议领域，如交通/绿化
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储附件路径，如咨询材料
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储回复附件路径
     */
    private String extCommon2;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}