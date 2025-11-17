package cn.iocoder.yudao.module.datacenter.dal.dataobject.bizservicedemand.publiccomplain;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 投诉举报 DO
 *
 * @author 亘川智城
 */
@TableName("biz_public_complain")
@KeySequence("biz_public_complain_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicComplainDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 投诉ID，唯一编码，UUID生成
     */
    private String complainId;
    /**
     * 投诉单号，格式“TS+YYYYMMDD+6位流水号”
     */
    private String complainNo;
    /**
     * 诉求类型编码，投诉类型，如“违章停车”，字典表（sys_dict）
     */
    private String appealTypeCode;
    /**
     * 诉求类型名称，与类型编码同步，字典表（sys_dict）
     */
    private String appealTypeName;
    /**
     * 事发区域编码，事发区域国标编码，区域表（sys_area）
     */
    private String areaCode;
    /**
     * 事发区域名称，与区域编码同步，区域表（sys_area）
     */
    private String areaName;
    /**
     * 事发地址，详细地址，如“XX路12号门前”
     */
    private String incidentAddr;
    /**
     * 投诉内容，问题描述
     */
    private String complainContent;
    /**
     * 上传图片路径，多张图片用逗号分隔
     */
    private String imgPath;
    /**
     * 联系人姓名，投诉人姓名
     */
    private String contactName;
    /**
     * 联系人电话，加密存储
     */
    private String contactPhone;
    /**
     * 受理部门编码，受理部门编码，部门信息表（sys_org）
     */
    private String acceptDeptCode;
    /**
     * 受理部门名称，与部门编码同步，部门信息表（sys_org）
     */
    private String acceptDeptName;
    /**
     * 处置状态：1=待受理，2=已受理，3=处置中，4=已办结，5=已驳回，流程状态
     */
    private Integer handleStatus;
    /**
     * 处置结果，处置完成后填写结果
     */
    private String handleResult;
    /**
     * 提交时间，市民提交时间
     */
    private LocalDateTime submitTime;
    /**
     * 受理时间，受理部门接单时间
     */
    private LocalDateTime acceptTime;
    /**
     * 办结时间，处置完成时间
     */
    private LocalDateTime finishTime;
    /**
     * 分类扩展字段1，预留，标识问题等级，如一般/紧急
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，标识处置方式，如现场/线上
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储投诉来源，如APP/热线
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储处置人员ID
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