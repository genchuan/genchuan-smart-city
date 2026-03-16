package cn.iocoder.yudao.module.data.dal.dataobject.sceneinstance;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 应用场景实例 DO
 *
 * @author zhucongquan
 */
@TableName("scene_instance")
@KeySequence("scene_instance_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SceneInstanceDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 场景名称
     */
    private String sceneName;
    /**
     * 场景编码
     */
    private String sceneCode;
    /**
     * 关联场景分类ID
     */
    private String categoryId;
    /**
     * 所属分类
     */
    private String categoryName;
    /**
     * 所在网格ID列表
     */
    private String gridIds;
    /**
     * 所在网格
     */
    private String gridName;
    /**
     * 涉及设施ID列表
     */
    private String facilityIds;
    /**
     * 涉及设施
     */
    private String facilities;
    /**
     * 关联监测部件ID列表
     */
    private String monitorIds;
    /**
     * 关联监测部件
     */
    private String monitorName;
    /**
     * 关联监测事件类型ID列表
     */
    private String eventTypeIds;
    /**
     * 关联监测事件类型
     */
    private String eventType;
    /**
     * 关联资产设备ID列表
     */
    private String assetIds;
    /**
     * 负责人
     */
    private String manager;
    /**
     * 处置流程
     */
    private String process;
    /**
     * 状态
     */
    private String status;
    /**
     * 配置触发标识
     */
    private Boolean configTriggerFlag;
    /**
     * 关联部件数
     */
    private Integer partCount;
    /**
     * 关联事件数
     */
    private Integer eventCount;
    /**
     * 启用/停用时间
     */
    private LocalDateTime statusTime;
    /**
     * 运行日志
     */
    private String runLog;
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