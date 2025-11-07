package cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.bizmngmatterminor;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 管理事项小类 DO
 *
 * @author 亘川智城
 */
@TableName("gc_biz_mng_matter_minor")
@KeySequence("gc_biz_mng_matter_minor_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BizMngMatterMinorDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 管理事项小类ID，唯一编码，UUID生成
     */
    private String mngMatterMinorId;
    /**
     * 所属大类ID，关联管理事项大类ID，关联管理事项大类表(gc_biz_mng_matter_major)
     */
    private String parentMajorId;
    /**
     * 所属大类名称，与所属大类ID同步，不可改，关联管理事项大类表(gc_biz_mng_matter_major)
     */
    private String parentMajorName;
    /**
     * 小类代码，3位字符，001 - 999，标准类001 - 079、扩展类080 - 999倒排，同一大类下唯一
     */
    private String matterMinorCode;
    /**
     * 小类名称，如私搭乱建/垃圾乱堆乱放等，国标名，扩展类加“（自定义）”
     */
    private String matterMinorName;
    /**
     * 小类说明，描述小类定义，如“私搭乱建：未经审批搭建”
     */
    private String matterMinorDesc;
    /**
     * 主管部门代码，主管部门信用代码，关联部门信息表(sys_org)
     */
    private String deptCode;
    /**
     * 主管部门名称，与主管部门代码同步，关联部门信息表(sys_org)
     */
    private String deptName;
    /**
     * 是否扩展类，0（标准类）/1（扩展类），按代码自动判断
     */
    private String isExt;
    /**
     * 启用状态，1（启用）/0（禁用），默认继承大类状态
     */
    private String enableStatus;
    /**
     * 创建人，配置人账号，关联用户信息表(sys_user)
     */
    private String createUser;
    /**
     * 更新人，修改人账号，关联用户信息表(sys_user)
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