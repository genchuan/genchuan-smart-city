package cn.iocoder.yudao.module.data.dal.dataobject.eventcategory;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 监测事件分类 DO
 *
 * @author zhucongquan
 */
@TableName("event_category")
@KeySequence("event_category_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventCategoryDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 分类代码
     */
    private String categoryCode;
    /**
     * 上级分类ID
     */
    private String parentId;
    /**
     * 上级分类名称
     */
    private String parentCategory;
    /**
     * 关联监测分类ID
     */
    private String monitorTypeId;
    /**
     * 监测部件类型
     */
    private String relatedMonitorType;
    /**
     * 管理事项类型ID
     */
    private String matterTypeId;
    /**
     * 管理事项类型
     */
    private String relatedMatterType;
    /**
     * 事件等级
     */
    private String eventLevel;
    /**
     * 推送规则
     */
    private String pushRule;
    /**
     * 状态
     */
    private String status;
    /**
     * 审核状态
     */
    private String auditStatus;
    /**
     * 关联实例数
     */
    private Integer instanceCount;
    /**
     * 用途说明
     */
    private String purpose;
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