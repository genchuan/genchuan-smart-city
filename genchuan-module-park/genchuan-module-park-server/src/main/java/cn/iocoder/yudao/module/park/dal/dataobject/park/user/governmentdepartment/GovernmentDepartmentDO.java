package cn.iocoder.yudao.module.park.dal.dataobject.park.user.governmentdepartment;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 政府部门 DO
 *
 * @author 亘川智城
 */
@TableName("park_government_department")
@KeySequence("park_government_department_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GovernmentDepartmentDO extends BaseDO {

    /**
     * [主键ID]
     */
    @TableId
    private Long id;
    /**
     * [部门名称]
     */
    private String deptName;
    /**
     * [部门编码]
     */
    private String deptCode;
    /**
     * [联系人]
     */
    private String contactPerson;
    /**
     * [联系电话]
     */
    private String contactPhone;
    /**
     * [负责区域编码] 关联park_area.area_code
     */
    private String regionCode;
    /**
     * [职责范围]
     */
    private String responsibility;
    /**
     * [备注]
     */
    private String remark;
    /**
     * [通用扩展字段1]
     */
    private String extCommon1;
    /**
     * [通用扩展字段2]
     */
    private String extCommon2;
    /**
     * [通用扩展字段3]
     */
    private String extCommon3;
    /**
     * [通用扩展字段4]
     */
    private String extCommon4;

}
