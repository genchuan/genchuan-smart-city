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
    private String name;
    /**
     * 16位标识码
     */
    private String uniqueCode;
    /**
     * 关联分类ID
     */
    private String categoryId;
    /**
     * 关联网格ID
     */
    private String gridId;
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
     * 关联运行状态ID
     */
    private String runStatusId;
    /**
     * 关联部门ID
     */
    private String deptId;
    /**
     * 关联行政区划代码
     */
    private String areaCode;
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

}