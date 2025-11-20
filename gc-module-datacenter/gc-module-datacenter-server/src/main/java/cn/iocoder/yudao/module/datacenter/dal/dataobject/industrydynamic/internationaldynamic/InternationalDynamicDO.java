package cn.iocoder.yudao.module.datacenter.dal.dataobject.industrydynamic.internationaldynamic;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import java.time.LocalDate;

/**
 * 国际行业动态 DO
 *
 * @author 亘川智城
 */
@TableName("biz_international_dynamic")
@KeySequence("biz_international_dynamic_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternationalDynamicDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 动态ID，唯一编码，UUID生成
     */
    private String internationalDynamicId;
    /**
     * 动态标题（中文），如“新加坡推出智慧国2.0计划”
     */
    private String dynamicTitle;
    /**
     * 英文标题，动态标题（英文）
     */
    private String dynamicTitleEn;
    /**
     * 来源国家地区，如“新加坡”“欧盟”
     */
    private String sourceCountry;
    /**
     * 动态来源，如“Smart City World”
     */
    private String dynamicSource;
    /**
     * 发布时间，格式：YYYYMMDD
     */
    private LocalDateTime releaseTime;
    /**
     * 动态详细内容（中文，支持富文本）
     */
    private String dynamicDetail;
    /**
     * 英文详情，动态详细内容（英文）
     */
    private String dynamicDetailEn;
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
     * 分类扩展字段1，预留，如“动态类型：政策/技术”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“关联领域：数据安全/平台建设”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，如“原文链接（英文）”
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，如“翻译人”
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