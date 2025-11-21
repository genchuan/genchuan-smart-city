package cn.iocoder.yudao.module.datacenter.dal.dataobject.publicinfopublish.publicsvcinfo;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 服务信息发布 DO
 *
 * @author 亘川智城
 */
@TableName("biz_public_svc_info")
@KeySequence("biz_public_svc_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicSvcInfoDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 服务信息ID，唯一编码，UUID生成
     */
    private String svcInfoId;
    /**
     * 服务标题，明确服务类型，如“XX公交3路站点调整”
     */
    private String svcTitle;
    /**
     * 服务类型编码，服务分类编码，如“交通”“医疗”，字典表（sys_dict）
     */
    private String svcTypeCode;
    /**
     * 服务类型名称，与类型编码同步，字典表（sys_dict）
     */
    private String svcTypeName;
    /**
     * 服务内容，服务详情，如调整后的站点列表
     */
    private String svcContent;
    /**
     * 服务提供单位，提供服务的单位，如“XX公交公司”
     */
    private String svcProvider;
    /**
     * 联系电话，服务咨询电话，加密存储
     */
    private String contactPhone;
    /**
     * 生效时间，服务信息生效时间
     */
    private LocalDateTime effectiveTime;
    /**
     * 失效时间，服务信息失效时间
     */
    private LocalDateTime expireTime;
    /**
     * 发布人ID，发布人ID，用户表（sys_user）
     */
    private Long publishUserId;
    /**
     * 发布时间，系统自动生成
     */
    private LocalDateTime publishTime;
    /**
     * 信息状态：1=草稿，2=已发布，3=已过期，控制展示
     */
    private Integer infoStatus;
    /**
     * 备注，补充说明，如“临时调整，有效期1个月”
     */
    private String remark;
    /**
     * 分类扩展字段1，预留，标识服务级别，如常规/临时
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，标识服务区域，如全市/某区
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储服务地点，如“XX医院门诊楼”
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储服务时间，如“每日8:00-17:00”
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