package cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.gridmanage;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 网格管理 DO
 *
 * @author zhucongquan
 */
@TableName("biz_grid_manage")
@KeySequence("biz_grid_manage_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridManageDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 行政区划节点ID
     */
    private String treeNodeId;
    /**
     * 行政区划节点编码
     */
    private String nodeCode;
    /**
     * 行政区划节点名称
     */
    private String nodeName;
    /**
     * 网格类型
     */
    private String gridType;
    /**
     * 网格名称
     */
    private String gridName;
    /**
     * 唯一网格编码
     */
    private String gridCode;
    /**
     * 边界坐标
     */
    private String boundaryCoords;
    /**
     * 面积（m²）
     */
    private BigDecimal gridArea;
    /**
     * 状态
     */
    private String gridStatus;
    /**
     * 网格员ID
     */
    private Long gridUserId;
    /**
     * 网格员姓名
     */
    private String gridUserName;
    /**
     * 划分时间
     */
    private LocalDateTime divTime;
    /**
     * 业务备注
     */
    private String gridRemark;

}