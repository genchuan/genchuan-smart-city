package cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.bizmngcomp;

import lombok.*;

import java.time.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 管理部件信息管理 DO
 *
 * @author 亘川智城
 */
@TableName("gc_biz_mng_comp")
@KeySequence("gc_biz_mng_comp_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BizMngCompDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 部件ID，唯一编码，UUID生成
     */
    private String mngCompId;
    /**
     * 部件标识码，格式6位行政码+2位大类码+3位小类码+5位顺序码，唯一
     */
    private String compCode;
    /**
     * 部件名称，关联小类名称+位置，如“XX路电力井盖”，关联管理部件小类表(gc_biz_mng_comp_minor)
     */
    private String compName;
    /**
     * 关联管理部件小类ID，关联管理部件小类表(gc_biz_mng_comp_minor)
     */
    private String minorId;
    /**
     * 关联管理部件小类名称，与小类ID同步，不可改，关联管理部件小类表(gc_biz_mng_comp_minor)
     */
    private String minorName;
    /**
     * 主管部门代码，主管部门信用代码，关联部门信息表(sys_org)
     */
    private String deptCode;
    /**
     * 主管部门名称，与部门代码同步，关联部门信息表(sys_org)
     */
    private String deptName;
    /**
     * 关联单元网格ID，关联网格信息表(biz_grid_info)
     */
    private String gridId;
    /**
     * 关联单元网格名称，与网格ID同步，关联网格信息表(biz_grid_info)
     */
    private String gridName;
    /**
     * 部件状态，如完好/破损/丢失/废弃，关联部件状态字典表(sys_dict_mng_comp_state)
     */
    private String compStatus;
    /**
     * 部件普查日期，格式YYYYMMDD
     */
    private LocalDate initDate;
    /**
     * 状态/权属变更时更新日期，格式YYYYMMDD
     */
    private LocalDate changeDate;
    /**
     * 数据来源，如实测/人工上报/普查，关联数据来源字典表(sys_dict_data_source)
     */
    private String dataSource;
    /**
     * 录入人账号，关联用户信息表(sys_user)
     */
    private String createUser;
    /**
     * 修改人账号，关联用户信息表(sys_user)
     */
    private String updateUser;
    /**
     * 分类扩展字段1，预留，如“部件材质”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“部件材质”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，如“安装时间”
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，如“安装时间”
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