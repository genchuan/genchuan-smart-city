package cn.iocoder.yudao.module.datacenter.dal.dataobject.businessguidance.nationalpolicy;

import lombok.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 国家政策信息 DO
 *
 * @author Gyh
 */
@TableName("gc_national_policy_info")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NationalPolicyInfoDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 政策ID，唯一编码，采用UUID
     */
    private String policyId;

    /**
     * 政策编号，如"国办发〔2025〕XX号"
     */
    private String policyNo;

    /**
     * 政策完整名称
     */
    private String policyName;

    /**
     * 政策类型，规划类/管理类/保障类/技术类
     */
    private String policyType;

    /**
     * 发布单位，如"国务院办公厅""国家发展改革委"
     */
    private String issueDept;

    /**
     * 发布时间
     */
    private LocalDateTime issueTime;

    /**
     * 生效时间
     */
    private LocalDate effectiveTime;

    /**
     * 失效时间，永久有效则为空
     */
    private LocalDate expireTime;

    /**
     * 政策原文在服务器的存储路径
     */
    private String policyFilePath;

    /**
     * 政策摘要，简要描述政策核心内容
     */
    private String policyAbstract;

    /**
     * 政策状态，未生效/有效/已失效
     */
    private String policyStatus;

    /**
     * 上传政策的管理员账号
     */
    private String uploadUserId;

    /**
     * 上传时间
     */
    private LocalDateTime uploadTime;

    /**
     * 修改政策信息的管理员账号
     */
    private String updateUserId;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 扩展分类字段，预留用于自定义标签
     */
    private String extendCategory;
}