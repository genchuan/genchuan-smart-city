package cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivappscene;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 应用场景分域 DO
 *
 * @author zcq
 */
@TableName("stat_dom_div_app_scene")
@KeySequence("stat_dom_div_app_scene_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DomDivAppSceneDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 分域ID
     */
    private String domId;
    /**
     * 分域名称
     */
    private String domName;
    /**
     * 分域编码
     */
    private String domCode;
    /**
     * 应用场景大类ID
     */
    private String appSceneMajorId;
    /**
     * 应用场景大类名称
     */
    private String appSceneMajorName;
    /**
     * 应用场景小类ID
     */
    private String appSceneMinorId;
    /**
     * 应用场景小类名称
     */
    private String appSceneMinorName;
    /**
     * 关联点位数量(个)
     */
    private Integer relPtCount;
    /**
     * 关联设备数量(个)
     */
    private Integer relDeviceCount;
    /**
     * 关联事件数量(件)
     */
    private Integer relEvtCount;
    /**
     * 场景负责人ID
     */
    private Long sceneMngrId;
    /**
     * 场景负责人姓名
     */
    private String sceneMngrName;
    /**
     * 主管部门代码
     */
    private String deptCode;
    /**
     * 主管部门名称
     */
    private String deptName;
    /**
     * 分域状态
     */
    private Boolean domStatus;
    /**
     * 创建时间(业务)
     */
    private LocalDateTime createTimeBiz;
    /**
     * 更新时间(业务)
     */
    private LocalDateTime updateTimeBiz;
    /**
     * 分类扩展字段1
     */
    private String extCat1;
    /**
     * 分类扩展字段2
     */
    private String extCat2;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;

}