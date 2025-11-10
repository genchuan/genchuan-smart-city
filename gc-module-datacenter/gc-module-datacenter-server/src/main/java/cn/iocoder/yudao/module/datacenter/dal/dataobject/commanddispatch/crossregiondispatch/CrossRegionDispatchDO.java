package cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.crossregiondispatch;

import lombok.*;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 跨域调度协调 DO
 *
 * @author 亘川智城
 */
@TableName("biz_cross_region_dispatch")
@KeySequence("biz_cross_region_dispatch_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrossRegionDispatchDO extends BaseDO {

    /**
     * 自增主键ID
     */
    @TableId
    private Long id;
    /**
     * 协调ID，唯一编码，UUID生成
     */
    private String coordinateId;
    /**
     * 协调编号，系统自动生成，格式“CROSS_DISP+年份（4位）+6位流水号”
     */
    private String coordinateNo;
    /**
     * 申请单位ID，发起申请的本地联动单位ID，关联联动单位信息表（biz_coop_unit_info）
     */
    private String applyUnitId;
    /**
     * 申请单位名称，与申请单位ID同步，关联联动单位信息表（biz_coop_unit_info）
     */
    private String applyUnitName;
    /**
     * 协作单位ID，跨域协作单位ID，关联联动单位信息表（biz_coop_unit_info）
     */
    private String cooperUnitId;
    /**
     * 协作单位名称，与协作单位ID同步，关联联动单位信息表（biz_coop_unit_info）
     */
    private String cooperUnitName;
    /**
     * 申请理由，如“XX市遭遇特大洪涝，本地冲锋舟仅3辆，需邻市支援5辆”
     */
    private String applyReason;
    /**
     * 所需资源类型，应急物资/救援队伍/救援车辆/救援设备
     */
    private String resType;
    /**
     * 所需资源名称，如“冲锋舟”“专业抗洪队伍”
     */
    private String resName;
    /**
     * 所需数量，资源需求数量
     */
    private Integer requireQuantity;
    /**
     * 协作区域，如“XX市XX区”，关联行政区划表（sys_area）
     */
    private String cooperRegion;
    /**
     * 申请状态，待审核/已通过/已驳回，默认待审核
     */
    private String applyStatus;
    /**
     * 申请人ID，申请管理员账号，关联用户信息表（sys_user）
     */
    private String applyUserId;
    /**
     * 申请人姓名，与申请人ID同步，关联用户信息表（sys_user）
     */
    private String applyUserName;
    /**
     * 申请时间，格式yyyy-MM-dd HH:mm:ss，系统自动生成
     */
    private LocalDateTime applyTime;
    /**
     * 审核人ID，协作单位审核人账号，审核后必填，关联用户信息表（sys_user）
     */
    private String auditUserId;
    /**
     * 审核人姓名，与审核人ID同步，关联用户信息表（sys_user）
     */
    private String auditUserName;
    /**
     * 审核时间，格式yyyy-MM-dd HH:mm:ss，审核后必填
     */
    private LocalDateTime auditTime;
    /**
     * 审核意见，审核反馈，如“同意支援5辆冲锋舟，24小时内到位”
     */
    private String auditOpinion;
    /**
     * 分类扩展字段1，预留，如“协调优先级”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“协调优先级”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储协作协议路径
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储协作协议路径
     */
    private String extCommon2;

}