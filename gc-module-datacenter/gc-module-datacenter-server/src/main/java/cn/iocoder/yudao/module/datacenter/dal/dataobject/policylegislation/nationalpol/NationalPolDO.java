package cn.iocoder.yudao.module.datacenter.dal.dataobject.policylegislation.nationalpol;

import lombok.*;

import java.time.*;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 国家政策信息 DO
 *
 * @author 亘川智城
 */
@TableName("biz_national_pol")
@KeySequence("biz_national_pol_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NationalPolDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 政策ID，唯一编码，UUID生成
     */
    private String nationalPolId;
    /**
     * 政策标题，政策完整标题，如《“十四五”智慧城市发展规划》
     */
    private String polTitle;
    /**
     * 政策文号，政府发布文号（如“发改高技〔2021〕1602号”）
     */
    private String polDocNo;
    /**
     * 发布部门代码，发布部门统一社会信用代码，部门信息表（sys_org）
     */
    private String issueDeptCode;
    /**
     * 发布部门名称，与发布部门代码同步，部门信息表（sys_org）
     */
    private String issueDeptName;
    /**
     * 发布时间，格式：YYYYMMDD，政策发布日期
     */
    private LocalDate issueTime;
    /**
     * 实施时间，格式：YYYYMMDD，政策实施日期（未明确则为空）
     */
    private LocalDate implementTime;
    /**
     * 政策文件ID，关联文件存储表的文件ID，文件存储表（sys_file_storage）
     */
    private String polFileId;
    /**
     * 政策文件路径，政策文件服务器存储路径，文件存储表（sys_file_storage）
     */
    private String polFilePath;
    /**
     * 政策状态：有效/失效，标识政策当前状态
     */
    private String polStatus;
    /**
     * 政策摘要，政策核心内容摘要（≤500字）
     */
    private String polSummary;
    /**
     * 创建人，创建人ID，用户信息表（sys_user）
     */
    private String createUser;
    /**
     * 更新人，更新人ID，用户信息表（sys_user）
     */
    private String updateUser;
    /**
     * 分类扩展字段1，预留，如“政策分类：发展规划/技术标准”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“适用领域：城管/交通/环保”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，如“政策来源链接”
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，如“版本号：V1.0”
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