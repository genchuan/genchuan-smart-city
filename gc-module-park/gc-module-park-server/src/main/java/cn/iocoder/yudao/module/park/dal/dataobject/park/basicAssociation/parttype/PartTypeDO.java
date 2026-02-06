package cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.parttype;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 管理部件类别 DO
 *
 * @author zhucongquan
 */
@TableName("manage_part_type")
@KeySequence("manage_part_type_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartTypeDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 上级部件类别ID
     */
    private Long parentTypeId;
    /**
     * 唯一类别编码
     */
    private String typeCode;
    /**
     * 类别名称
     */
    private String typeName;
    /**
     * 类别描述
     */
    private String typeDesc;
    /**
     * 所属业务域：基础关联域/停车资源域/设备运维域
     */
    private String bizDomain;
    /**
     * 状态：启用/停用
     */
    private String typeStatus;
    /**
     * 业务备注
     */
    private String typeRemark;

}
