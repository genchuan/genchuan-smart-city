package cn.iocoder.yudao.module.data.dal.dataobject.instance;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 管理部件实例 DO
 *
 * @author zhucongquan
 */
@TableName("part_instance")
@KeySequence("part_instance_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstanceDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 部件名称
     */
    private String partName;
    /**
     * 16位标识码
     */
    private String uniqueCode;
    /**
     * 关联分类ID
     */
    private String parentCategoryId;

    /**
     * 分类名称（非数据库字段，用于关联查询结果）
     */
    @TableField(exist = false)
    private String categoryName;

    /**
     * 关联网格ID
     */
    private String gridId;
    /**
     * 所在网格
     */
    private String gridName;
    /**
     * 经度
     */
    private BigDecimal longitude;
    /**
     * 纬度
     */
    private BigDecimal latitude;
    /**
     * 坐标校验标识
     */
    private Boolean coordVerifyFlag;
    /**
     * 坐标信息
     */
    private String coordinate;
    /**
     * 关联运行状态ID
     */
    private String runStatus;
    /**
     * 主管部门
     */
    private String deptName;
    /**
     * 关联行政区划代码
     */
    private String areaCode;
    /**
     * 行政区划归属
     */
    private String areaName;
    /**
     * 关联监测部件ID列表
     */
    private String monitorIds;
    /**
     * 关联监测部件数
     */
    private Integer monitorCount;
    /**
     * 关联事件数
     */
    private Integer eventCount;
    /**
     * 备注
     */
    private String remark;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 创建人
     */
    private String creator;
}