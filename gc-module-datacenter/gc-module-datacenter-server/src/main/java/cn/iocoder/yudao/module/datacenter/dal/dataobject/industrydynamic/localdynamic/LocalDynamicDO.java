package cn.iocoder.yudao.module.datacenter.dal.dataobject.industrydynamic.localdynamic;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import java.time.LocalDate;

/**
 * 本地行业动态 DO
 *
 * @author 亘川智城
 */
@TableName("biz_local_dynamic")
@KeySequence("biz_local_dynamic_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocalDynamicDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 动态ID，唯一编码，UUID生成
     */
    private String localDynamicId;
    /**
     * 动态标题，如“XX市一网统管平台新增交通监测功能”
     */
    private String dynamicTitle;
    /**
     * 行政区划代码，符合GB/T 2260，行政区划表（sys_area）
     */
    private String regionCode;
    /**
     * 行政区划名称，与行政区划代码同步，行政区划表（sys_area）
     */
    private String regionName;
    /**
     * 动态来源，如“XX市政府官网”
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
     * 分类扩展字段1，预留，如“动态类型：功能更新/成效展示”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“关联业务：事件处置/设备监测”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，如“原文链接”
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，如“阅读量”
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