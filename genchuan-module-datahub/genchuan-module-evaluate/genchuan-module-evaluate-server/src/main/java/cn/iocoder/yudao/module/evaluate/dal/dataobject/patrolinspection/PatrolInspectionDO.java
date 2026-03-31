package cn.iocoder.yudao.module.evaluate.dal.dataobject.patrolinspection;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 巡查巡检 DO
 *
 * @author 亘川智城
 */
@TableName("eval_patrol_inspection")
@KeySequence("eval_patrol_inspection_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatrolInspectionDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 巡检人ID(关联sys_user.id)
     */
    private Long userId;
    /**
     * 体系ID (关联eval_index_system.id)
     */
    private Long systemId;
    /**
     * 评价对象ID (关联eval_object.id)
     */
    private Long objectId;
    /**
     * 指标项ID(关联eval_index_item)
     */
    private Long itemId;
    /**
     * 规则分类ID
     */
    private Long categoryId;
    /**
     * 评价说明
     */
    private String details;
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
    /**
     * 变更日志
     */
    private String changeLog;
    /**
     * 状态: 1：待审核中，2：审核通过，3：不用审核
     */
    private String status;
    /**
     * 图片
     */
    private byte[] image;
    /**
     * 图片URL
     */
    private String imageUrl;
    /**
     * 地址编码
     */
    private String addressCoding;

}