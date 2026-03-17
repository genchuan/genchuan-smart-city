package cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.operationstatus;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 运营状态字典 DO
 *
 * @author 芋道源码
 */
@TableName("sys_operation_status")
@KeySequence("sys_operation_status_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationStatusDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 业务主键（UUID）
     */
    private String sysOperationStatusId;
    /**
     * 状态名称（如正常运营/暂停开放/维修中）
     */
    private String name;
    /**
     * 状态编码
     */
    private String code;
    /**
     * 状态：启用/禁用
     */
    private Integer status;
    /**
     * 排序号
     */
    private Integer sort;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    private String extCommon4;

}