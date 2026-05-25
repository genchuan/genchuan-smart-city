package cn.iocoder.yudao.module.enterprisesvc.dal.dataobject.staffmgmt;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 企业员工 DO
 *
 * @author zhucongquan
 */
@TableName("staff_mgmt")
@KeySequence("staff_mgmt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffMgmtDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 员工姓名
     */
    private String staffName;
    /**
     * 企业ID
     */
    private Long enterpriseId;
    /**
     * 所属部门
     */
    private String deptName;
    /**
     * 岗位
     */
    private String postName;
    /**
     * 权限状态
     */
    private String authStatus;
    /**
     * 通行区域
     */
    private String accessArea;
    /**
     * 授权人账号
     */
    private String authUser;
    /**
     * 操作人账号
     */
    private String handleUser;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}