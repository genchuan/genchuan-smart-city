package cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.bizmngmatter;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 管理事项信息 DO
 *
 * @author 亘川智城
 */
@TableName("gc_biz_mng_matter")
@KeySequence("gc_biz_mng_matter_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BizMngMatterDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 事项ID，唯一编码，UUID生成
     */
    private String mngMatterId;
    /**
     * 事项标识码，格式6位行政码+2位大类码+3位小类码+5位顺序码，唯一
     */
    private String matterCode;
    /**
     * 事项名称，关联小类名称+位置，如“XX路私搭乱建”，关联管理事项小类表(gc_biz_mng_matter_minor)
     */
    private String matterName;
    /**
     * 关联管理事项小类ID，关联管理事项小类表(gc_biz_mng_matter_minor)
     */
    private String minorId;
    /**
     * 关联管理事项小类名称，与小类ID同步，不可修改，关联管理事项小类表(gc_biz_mng_matter_minor)
     */
    private String minorName;
    /**
     * 关联单元网格ID，关联网格信息表(biz_grid_info)
     */
    private String gridId;
    /**
     * 关联单元网格名称，与网格ID同步，关联网格信息表(biz_grid_info)
     */
    private String gridName;
    /**
     * 事项状态，如待处置/处置中/已办结/已驳回，关联问题状态字典表(sys_dict_problem_status)
     */
    private String matterStatus;
    /**
     * 事项等级，如一级/二级/三级，按影响范围判定
     */
    private String matterLevel;
    /**
     * 主管部门代码，关联主管部门信用代码，关联部门信息表(sys_org)
     */
    private String deptCode;
    /**
     * 主管部门名称，与部门代码同步，关联部门信息表(sys_org)
     */
    private String deptName;
    /**
     * 事发位置，详细位置描述，如“XX路与XX路交叉口东北侧”
     */
    private String incidentLocation;
    /**
     * 录入人账号，关联用户信息表(sys_user)
     */
    private String createUser;
    /**
     * 修改人账号，关联用户信息表(sys_user)
     */
    private String updateUser;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}