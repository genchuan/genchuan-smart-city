package cn.iocoder.yudao.module.datacenter.dal.dataobject.sceneconfig;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 场景分类 DO
 *
 * @author zcq
 */
@TableName("gc_scene_config")
@KeySequence("gc_scene_config_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SceneConfigDO extends BaseDO {

    public static final Long PID_ROOT = 0L;

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 父级ID
     */
    private Long pid;

    /**
     * 场景名称
     */
    private String name;

    /**
     * 设备配置名称
     */
    private String deviceConfigName;

    /**
     * 资产配置名称
     */
    private String assetConfigName;

    /**
     * 流程配置名称
     */
    private String flowConfigName;

    /**
     * 备注
     */
    private String info;

    /**
     * 备用1
     */
    @TableField("info_1")
    private String info1;

    /**
     * 备用2
     */
    @TableField("info_2")
    private String info2;

    /**
     * 备用3
     */
    @TableField("info_3")
    private String info3;

    /**
     * 备用4
     */
    @TableField("info_4")
    private String info4;

}