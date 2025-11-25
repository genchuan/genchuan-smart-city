package cn.iocoder.yudao.module.datacenter.dal.dataobject.policylegislation.polinterpretation;

import lombok.*;

import java.time.*;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 政策解读信息 DO
 *
 * @author 亘川智城
 */
@TableName("biz_pol_interpretation")
@KeySequence("biz_pol_interpretation_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolInterpretationDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 解读ID，唯一编码，UUID生成
     */
    private String polInterpretationId;
    /**
     * 关联政策类型，取值为国家政策/地方法规，标识关联政策类型
     */
    private String relatedPolType;
    /**
     * 关联政策ID，关联国家政策/地方法规ID，国家政策信息表（biz_national_pol）/地方法规信息表（biz_local_regulation）
     */
    private String relatedPolId;
    /**
     * 关联政策标题，与关联政策ID同步，国家政策信息表（biz_national_pol）/地方法规信息表（biz_local_regulation）
     */
    private String relatedPolTitle;
    /**
     * 解读标题，如《“十四五”智慧城市发展规划解读》
     */
    private String interpretationTitle;
    /**
     * 解读详细内容（支持富文本）
     */
    private String interpretationContent;
    /**
     * 解读人ID，解读人账号，用户信息表（sys_user）
     */
    private String interpreterId;
    /**
     * 解读人姓名，与解读人ID同步，用户信息表（sys_user）
     */
    private String interpreterName;
    /**
     * 解读时间，格式：YYYYMMDD
     */
    private LocalDateTime interpretationTime;
    /**
     * 解读状态：已发布/未发布，标识解读是否公开
     */
    private String interpretationStatus;
    /**
     * 附件ID，关联解读附件ID，文件存储表（sys_file_storage）
     */
    private String attachId;
    /**
     * 附件路径，解读附件存储路径，文件存储表（sys_file_storage）
     */
    private String attachPath;
    /**
     * 创建人，创建人ID，用户信息表（sys_user）
     */
    private String createUser;
    /**
     * 更新人，更新人ID，用户信息表（sys_user）
     */
    private String updateUser;
    /**
     * 分类扩展字段1，预留，如“解读类型：官方/专家”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“解读范围：全国/地方”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，如“解读来源：政府官网”
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，如“关联业务：设备部署”
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