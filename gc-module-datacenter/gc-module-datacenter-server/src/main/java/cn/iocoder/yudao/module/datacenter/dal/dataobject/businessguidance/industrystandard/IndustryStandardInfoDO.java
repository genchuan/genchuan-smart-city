package cn.iocoder.yudao.module.datacenter.dal.dataobject.businessguidance.industrystandard;

import lombok.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 行业规范信息 DO
 *
 * @author Gyh
 */
@TableName("gc_industry_standard_info")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndustryStandardInfoDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 规范ID，唯一编码，采用UUID
     */
    private String standardId;

    /**
     * 规范编号，如"GB/T XXXXX-2025""CJ/T XXXXX-2025"
     */
    private String standardNo;

    /**
     * 规范名称，如"智慧城市数据安全规范"
     */
    private String standardName;

    /**
     * 行业领域，数据安全/市政设施/交通管理/环境保护
     */
    private String industryField;

    /**
     * 发布机构，如"国家市场监督管理总局""中国城市规划协会"
     */
    private String issueOrganization;

    /**
     * 发布时间
     */
    private LocalDateTime issueTime;

    /**
     * 实施时间
     */
    private LocalDate implementationTime;

    /**
     * 替代旧规范号，如"替代GB/T XXXXX-2020"
     */
    private String replaceOldNo;

    /**
     * PDF格式原文路径
     */
    private String standardFilePath;

    /**
     * 配套解读文件路径，多个用英文分号分隔
     */
    private String interpretationFilePath;

    /**
     * 核心内容摘要
     */
    private String standardAbstract;

    /**
     * 规范状态，未实施/实施中/已替代/已废止
     */
    private String standardStatus;

    /**
     * 关联业务模块，如"管理部件事项管理/监测部件事件管理"
     */
    private String relatedBusinessModule;

    /**
     * 上传管理员账号
     */
    private String uploadUserId;

    /**
     * 上传时间
     */
    private LocalDateTime uploadTime;

    /**
     * 修改管理员账号
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