package cn.iocoder.yudao.module.datacenter.dal.dataobject.bizservicedemand.publichotlinelink;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 热线对接 DO
 *
 * @author 亘川智城
 */
@TableName("biz_public_hotline_link")
@KeySequence("biz_public_hotline_link_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicHotlineLinkDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 热线ID，唯一编码，UUID生成
     */
    private String hotlineId;
    /**
     * 热线单号，热线系统原生单号，如“12345-20251014-001”
     */
    private String hotlineNo;
    /**
     * 平台单号，平台生成单号，格式“RL+YYYYMMDD+6位流水号”
     */
    private String platformNo;
    /**
     * 诉求类型编码，热线诉求类型，字典表（sys_dict）
     */
    private String appealTypeCode;
    /**
     * 诉求类型名称，与类型编码同步，字典表（sys_dict）
     */
    private String appealTypeName;
    /**
     * 事发区域编码，事发区域编码，区域表（sys_area）
     */
    private String areaCode;
    /**
     * 事发区域名称，与区域编码同步，区域表（sys_area）
     */
    private String areaName;
    /**
     * 诉求内容，市民诉求详情
     */
    private String appealContent;
    /**
     * 联系人姓名，热线记录的联系人姓名
     */
    private String contactName;
    /**
     * 联系人电话，加密存储
     */
    private String contactPhone;
    /**
     * 热线来源，如“12345”“12319”
     */
    private String hotlineSource;
    /**
     * 同步状态：1=待同步，2=已同步，3=同步失败，数据同步状态
     */
    private Integer syncStatus;
    /**
     * 处置状态：1=待受理，2=已受理，3=处置中，4=已办结，5=已驳回，处置流程状态
     */
    private Integer handleStatus;
    /**
     * 受理部门编码，受理部门，部门信息表（sys_org）
     */
    private String acceptDeptCode;
    /**
     * 受理部门名称，与部门编码同步，部门信息表（sys_org）
     */
    private String acceptDeptName;
    /**
     * 处置结果，处置完成后填写
     */
    private String handleResult;
    /**
     * 同步时间，数据同步至平台的时间
     */
    private LocalDateTime syncTime;
    /**
     * 办结时间，处置完成时间
     */
    private LocalDateTime finishTime;
    /**
     * 分类扩展字段1，预留，标识热线优先级，如普通/紧急
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，标识诉求来源，如电话/短信
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储热线受理时间
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储热线备注
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