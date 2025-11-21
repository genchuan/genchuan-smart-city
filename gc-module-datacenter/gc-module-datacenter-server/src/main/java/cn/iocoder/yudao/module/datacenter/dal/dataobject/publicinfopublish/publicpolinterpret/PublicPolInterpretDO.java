package cn.iocoder.yudao.module.datacenter.dal.dataobject.publicinfopublish.publicpolinterpret;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 政策解读发布 DO
 *
 * @author 亘川智城
 */
@TableName("biz_public_pol_interpret")
@KeySequence("biz_public_pol_interpret_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicPolInterpretDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 解读ID，唯一编码，UUID生成
     */
    private String interpretId;
    /**
     * 解读标题，含政策名称，如“2025人才补贴政策解读”
     */
    private String interpretTitle;
    /**
     * 政策文号，关联政策的官方文号，如“XX人社〔2025〕12号”，政策文件表（biz_public_pol）
     */
    private String polNo;
    /**
     * 政策名称，与政策文号同步，政策文件表（biz_public_pol）
     */
    private String polName;
    /**
     * 解读内容，分点拆解政策，支持图文
     */
    private String interpretContent;
    /**
     * 发布部门编码，发布解读的部门编码，部门信息表（sys_org）
     */
    private String publishDepartmentCode;
    /**
     * 发布部门名称，与部门编码同步，部门信息表（sys_org）
     */
    private String publishDepartmentName;
    /**
     * 发布人ID，发布人ID，用户表（sys_user）
     */
    private Long publishUserId;
    /**
     * 发布时间，系统自动生成
     */
    private LocalDateTime publishTime;
    /**
     * 解读状态：1=草稿，2=已发布，3=已下架，控制展示状态
     */
    private Integer interpretStatus;
    /**
     * 生效时间，解读生效时间
     */
    private LocalDateTime effectiveTime;
    /**
     * 附件路径，政策原文附件路径
     */
    private String attachPath;
    /**
     * 备注，补充说明，如“配套XX政策使用”
     */
    private String remark;
    /**
     * 分类扩展字段1，预留，标识政策领域，如社保/人才
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，标识解读类型，如图文/视频
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储视频解读链接
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储政策原文链接
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