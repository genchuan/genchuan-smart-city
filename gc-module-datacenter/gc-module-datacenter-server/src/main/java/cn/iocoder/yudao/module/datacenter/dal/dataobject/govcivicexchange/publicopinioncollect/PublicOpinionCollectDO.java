package cn.iocoder.yudao.module.datacenter.dal.dataobject.govcivicexchange.publicopinioncollect;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 民意征集 DO
 *
 * @author 亘川智城
 */
@TableName("biz_public_opinion_collect")
@KeySequence("biz_public_opinion_collect_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicOpinionCollectDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 征集ID，唯一编码，UUID生成
     */
    private String collectId;
    /**
     * 征集主题，征集核心主题，如“XX片区改造规划征集”
     */
    private String collectTopic;
    /**
     * 征集单位编码，发布征集的部门编码，部门信息表（sys_org）
     */
    private String deptCode;
    /**
     * 征集单位名称，与部门编码同步，部门信息表（sys_org）
     */
    private String deptName;
    /**
     * 征集范围编码，征集覆盖区域编码，如“330100”=杭州市，区域表（sys_area）
     */
    private String areaCode;
    /**
     * 征集范围名称，与区域编码同步，区域表（sys_area）
     */
    private String areaName;
    /**
     * 征集内容，征集背景、需反馈的问题
     */
    private String collectContent;
    /**
     * 开始时间，征集开始时间
     */
    private LocalDateTime startTime;
    /**
     * 结束时间，征集结束时间
     */
    private LocalDateTime endTime;
    /**
     * 征集状态：1=未开始，2=征集ing，3=已结束，4=结果公示，流程状态
     */
    private Integer collectStatus;
    /**
     * 结果公示内容，征集结束后公示的汇总结果
     */
    private String resultContent;
    /**
     * 公示时间，结果公示时间
     */
    private LocalDateTime publishResultTime;
    /**
     * 发布人ID，发布征集的人员ID，用户表（sys_user）
     */
    private Long publishUserId;
    /**
     * 发布时间，发布征集的时间
     */
    private LocalDateTime publishTime;
    /**
     * 备注，补充说明，如“纸质意见可邮寄至XX地址”
     */
    private String remark;
    /**
     * 分类扩展字段1，预留，标识征集类型，如规划/政策
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，标识征集级别，如市级/区级
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储意见提交方式，如线上/线下
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储意见统计数量
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