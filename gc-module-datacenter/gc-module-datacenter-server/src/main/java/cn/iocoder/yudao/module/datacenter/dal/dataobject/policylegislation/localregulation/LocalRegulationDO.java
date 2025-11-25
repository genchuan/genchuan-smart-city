package cn.iocoder.yudao.module.datacenter.dal.dataobject.policylegislation.localregulation;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import java.time.LocalDate;

/**
 * 地方法规信息 DO
 *
 * @author 亘川智城
 */
@TableName("biz_local_regulation")
@KeySequence("biz_local_regulation_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocalRegulationDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 法规ID，唯一编码，UUID生成
     */
    private String localRegulationId;
    /**
     * 法规标题，法规完整标题，如《XX市一网统管实施细则》
     */
    private String regulationTitle;
    /**
     * 法规文号，地方发布文号（如“XX政发〔2024〕5号”）
     */
    private String regulationDocNo;
    /**
     * 行政区划代码，符合GB/T 2260，如330100=杭州市，行政区划表（sys_area）
     */
    private String regionCode;
    /**
     * 行政区划名称，与行政区划代码同步，行政区划表（sys_area）
     */
    private String regionName;
    /**
     * 发布部门代码，发布部门统一社会信用代码，部门信息表（sys_org）
     */
    private String issueDeptCode;
    /**
     * 发布部门名称，与发布部门代码同步，部门信息表（sys_org）
     */
    private String issueDeptName;
    /**
     * 发布时间，格式：YYYYMMDD
     */
    private LocalDate issueTime;
    /**
     * 实施时间，格式：YYYYMMDD，可为空
     */
    private LocalDate implementTime;
    /**
     * 法规文件ID，关联文件存储表的文件ID，文件存储表（sys_file_storage）
     */
    private String regulationFileId;
    /**
     * 法规文件路径，法规文件存储路径，文件存储表（sys_file_storage）
     */
    private String regulationFilePath;
    /**
     * 法规状态：有效/失效，标识法规当前状态
     */
    private String regulationStatus;
    /**
     * 法规摘要，法规核心内容摘要
     */
    private String regulationSummary;
    /**
     * 创建人，创建人ID，用户信息表（sys_user）
     */
    private String createUser;
    /**
     * 更新人，更新人ID，用户信息表（sys_user）
     */
    private String updateUser;
    /**
     * 分类扩展字段1，预留，如“法规类型：管理办法/实施细则”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“适用层级：市级/区级”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，如“地方政策链接”
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，如“修订记录：2024年第一次修订”
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