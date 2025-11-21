package cn.iocoder.yudao.module.datacenter.dal.dataobject.publicinfopublish.publicnotice;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 通知公告发布 DO
 *
 * @author 亘川智城
 */
@TableName("biz_public_notice")
@KeySequence("biz_public_notice_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicNoticeDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 通知ID，唯一编码，UUID生成
     */
    private String noticeId;
    /**
     * 通知标题，通知核心标题，需明确信息类型，如“XX区域停水通知”
     */
    private String noticeTitle;
    /**
     * 通知内容，通知详细内容，支持富文本（图片、表格）
     */
    private String noticeContent;
    /**
     * 发布部门编码，发布通知的部门编码，部门信息表（sys_org）
     */
    private String deptCode;
    /**
     * 发布部门名称，与部门编码同步，不可手动修改，部门信息表（sys_org）
     */
    private String deptName;
    /**
     * 发布人ID，发布操作人ID，用户表（sys_user）
     */
    private Long publishUserId;
    /**
     * 发布人姓名，与发布人ID同步，用户表（sys_user）
     */
    private String publishUserName;
    /**
     * 发布时间，点击“发布”时系统自动生成，格式yyyy-MM-dd HH:mm:ss
     */
    private LocalDateTime publishTime;
    /**
     * 通知状态：1=草稿，2=已发布，3=已撤回，控制通知展示状态
     */
    private Integer noticeStatus;
    /**
     * 生效时间，通知开始向公众展示的时间
     */
    private LocalDateTime effectiveTime;
    /**
     * 失效时间，通知停止展示的时间，需晚于生效时间
     */
    private LocalDateTime expireTime;
    /**
     * 附件路径，通知附件（如检修方案）存储路径
     */
    private String attachPath;
    /**
     * 备注，补充说明，如“紧急通知，请优先展示”
     */
    private String remark;
    /**
     * 分类扩展字段1，预留，标识通知类型（停水/停电/活动）
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，标识通知级别（普通/紧急）
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储附件名称
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储发布终端（PC/APP）
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