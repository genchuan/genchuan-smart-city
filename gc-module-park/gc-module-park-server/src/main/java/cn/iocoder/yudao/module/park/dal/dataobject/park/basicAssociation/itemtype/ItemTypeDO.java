package cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.itemtype;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 管理事项类别 DO
 *
 * @author zhucongquan
 */
@TableName("manage_item_type")
@KeySequence("manage_item_type_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemTypeDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 上级事项类别ID
     */
    private Long parentTypeId;
    /**
     * 唯一事项编码
     */
    private String itemCode;
    /**
     * 事项名称
     */
    private String itemName;
    /**
     * 所属业务域
     */
    private String bizDomain;
    /**
     * 处理流程配置
     */
    private String processConfig;
    /**
     * 可处理角色ID列表
     */
    private String handleRoleIds;
    /**
     * 状态：启用/停用
     */
    private String itemStatus;
    /**
     * 业务备注
     */
    private String itemRemark;

}