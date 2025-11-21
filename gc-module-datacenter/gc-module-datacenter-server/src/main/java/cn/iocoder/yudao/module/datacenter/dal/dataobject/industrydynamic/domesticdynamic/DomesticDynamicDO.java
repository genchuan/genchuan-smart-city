package cn.iocoder.yudao.module.datacenter.dal.dataobject.industrydynamic.domesticdynamic;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import java.time.LocalDate;

/**
 * 国内行业动态 DO
 *
 * @author 亘川智城
 */
@TableName("biz_domestic_dynamic")
@KeySequence("biz_domestic_dynamic_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DomesticDynamicDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 动态ID，唯一编码，UUID生成
     */
    private String domesticDynamicId;
    /**
     * 动态标题，如“某城市一网统管平台上线”
     */
    private String dynamicTitle;
    /**
     * 动态标签，取值如平台建设/技术创新/政策动态，动态标签字典表（sys_dict_dynamic_tag）
     */
    private String dynamicTag;
    /**
     * 动态来源，动态来源平台（如“智慧城市网”）
     */
    private String dynamicSource;
    /**
     * 发布时间，格式：YYYYMMDD
     */
    private LocalDateTime releaseTime;
    /**
     * 动态详细内容（支持富文本）
     */
    private String dynamicDetail;
    /**
     * 封面图片ID，关联图片存储ID，文件存储表（sys_file_storage）
     */
    private String coverImgId;
    /**
     * 封面图片路径，封面图片存储路径，文件存储表（sys_file_storage）
     */
    private String coverImgPath;
    /**
     * 动态状态：已发布/草稿，标识动态是否公开
     */
    private String dynamicStatus;
    /**
     * 创建人，创建人ID，用户信息表（sys_user）
     */
    private String createUser;
    /**
     * 更新人，更新人ID，用户信息表（sys_user）
     */
    private String updateUser;
    /**
     * 分类扩展字段1，预留，如“动态级别：国家级/省级”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“关联领域：城管/交通”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，如“原文链接”
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，如“收藏数”
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