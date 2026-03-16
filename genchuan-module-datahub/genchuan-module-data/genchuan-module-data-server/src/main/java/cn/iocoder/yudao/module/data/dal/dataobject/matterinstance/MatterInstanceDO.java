package cn.iocoder.yudao.module.data.dal.dataobject.matterinstance;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 管理事项实例 DO
 *
 * @author zhucongquan
 */
@TableName("matter_instance")
@KeySequence("matter_instance_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatterInstanceDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 管理事项实例ID
     */
    private String matterInstanceId;
    /**
     * 事项名称
     */
    private String name;
    /**
     * 16位标识码
     */
    private String uniqueCode;
    /**
     * 所属分类ID
     */
    private String categoryId;
    /**
     * 所属分类名称
     */
    private String categoryName;
    /**
     * 上级分类ID
     */
    private String parentCategoryId;
    /**
     * 事发位置
     */
    private String location;
    /**
     * 所在网格ID
     */
    private String gridId;
    /**
     * 所在网格名称
     */
    private String gridName;
    /**
     * 描述信息
     */
    private String description;
    /**
     * 状态ID
     */
    private String statusId;
    /**
     * 状态名称
     */
    private String status;
    /**
     * 主管部门ID
     */
    private String deptId;
    /**
     * 主管部门名称
     */
    private String deptName;
    /**
     * 附件信息列表
     */
    private String attachmentInfo;
    /**
     * 关联管理部件ID列表
     */
    private String partIds;
    /**
     * 关联部件数
     */
    private Integer partCount;
    /**
     * 超时标识
     */
    private Boolean timeoutFlag;
    /**
     * 超时时长（分钟）
     */
    private Integer timeoutDuration;
    /**
     * 处置意见
     */
    private String dealOpinion;
    /**
     * 处置人ID
     */
    private String dealBy;
    /**
     * 处置人名称
     */
    private String handler;
    /**
     * 处置时间
     */
    private LocalDateTime dealTime;
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