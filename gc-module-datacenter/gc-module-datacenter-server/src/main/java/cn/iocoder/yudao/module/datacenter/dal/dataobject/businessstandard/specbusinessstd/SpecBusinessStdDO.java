package cn.iocoder.yudao.module.datacenter.dal.dataobject.businessstandard.specbusinessstd;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import java.time.LocalDate;

/**
 * 专项业务标准 DO
 *
 * @author 亘川智城
 */
@TableName("biz_spec_business_std")
@KeySequence("biz_spec_business_std_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecBusinessStdDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 标准ID，唯一编码，UUID生成
     */
    private String specBusinessStdId;
    /**
     * 标准名称，如《智慧城管事件处置标准》
     */
    private String stdName;
    /**
     * 业务领域代码，取值如01=城管，02=交通，03=环保，业务领域字典表（sys_dict_business_field）
     */
    private String businessFieldCode;
    /**
     * 业务领域名称，与业务领域代码同步，业务领域字典表（sys_dict_business_field）
     */
    private String businessFieldName;
    /**
     * 标准版本，如“V1.0”“V2.1”
     */
    private String stdVersion;
    /**
     * 生效时间，格式：YYYYMMDD
     */
    private LocalDate effectiveTime;
    /**
     * 标准核心条款（支持富文本，如“事件处置需在24小时内完成”）
     */
    private String stdClause;
    /**
     * 执行要求，执行该标准的具体要求
     */
    private String execRequirement;
    /**
     * 验收指标，如“处置合格率≥95%”
     */
    private String acceptIdx;
    /**
     * 标准文件ID，关联标准文件ID，文件存储表（sys_file_storage）
     */
    private String stdFileId;
    /**
     * 标准文件路径，标准文件存储路径，文件存储表（sys_file_storage）
     */
    private String stdFilePath;
    /**
     * 标准状态：现行/废止，标识标准当前状态
     */
    private String stdStatus;
    /**
     * 创建人，创建人ID，用户信息表（sys_user）
     */
    private String createUser;
    /**
     * 更新人，更新人ID，用户信息表（sys_user）
     */
    private String updateUser;
    /**
     * 分类扩展字段1，预留，如“标准等级：市级/区级”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“适用对象：工作人员/设备厂商”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，如“制定依据：XX政策”
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，如“修订记录：2024年修订”
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