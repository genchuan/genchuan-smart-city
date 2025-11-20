package cn.iocoder.yudao.module.datacenter.dal.dataobject.policylegislation.industrystd;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import java.time.LocalDate;

/**
 * 行业规范信息 DO
 *
 * @author 亘川智城
 */
@TableName("biz_industry_std")
@KeySequence("biz_industry_std_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndustryStdDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 规范ID，唯一编码，UUID生成
     */
    private String industryStdId;
    /**
     * 规范标题，规范完整标题，如《城市物联网设备通用规范》
     */
    private String stdTitle;
    /**
     * 行业分类代码，取值如01=物联网，02=城管，03=交通，行业分类字典表（sys_dict_industry_type）
     */
    private String industryTypeCode;
    /**
     * 行业分类名称，与行业分类代码同步，行业分类字典表（sys_dict_industry_type）
     */
    private String industryTypeName;
    /**
     * 规范编号，行业标准编号（如“GB/T 39409-2020”）
     */
    private String stdNo;
    /**
     * 发布机构，规范发布机构（如“中国城市科学研究会”）
     */
    private String issueOrg;
    /**
     * 发布时间，格式：YYYYMMDD
     */
    private LocalDate issueTime;
    /**
     * 规范文件ID，关联文件存储表的文件ID，文件存储表（sys_file_storage）
     */
    private String stdFileId;
    /**
     * 规范文件路径，规范文件存储路径，文件存储表（sys_file_storage）
     */
    private String stdFilePath;
    /**
     * 规范状态：现行/废止，标识规范当前状态
     */
    private String stdStatus;
    /**
     * 核心条款，规范核心条款摘要（≤1000字）
     */
    private String coreClause;
    /**
     * 创建人，创建人ID，用户信息表（sys_user）
     */
    private String createUser;
    /**
     * 更新人，更新人ID，用户信息表（sys_user）
     */
    private String updateUser;
    /**
     * 分类扩展字段1，预留，如“规范等级：国家/行业”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“适用场景：设备采购/数据录入”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，如“规范下载链接”
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，如“替代旧规范编号”
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