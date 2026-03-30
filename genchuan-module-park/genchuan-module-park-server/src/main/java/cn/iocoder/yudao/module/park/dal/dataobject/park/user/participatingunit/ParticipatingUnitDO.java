package cn.iocoder.yudao.module.park.dal.dataobject.park.user.participatingunit;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 参与单位 DO
 *
 * @author 亘川智城
 */
@TableName("park_participating_unit")
@KeySequence("park_participating_unit_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipatingUnitDO extends BaseDO {

    /**
     * [主键ID]
     */
    @TableId
    private Long id;
    /**
     * [单位名称]
     */
    private String unitName;
    /**
     * [单位类型] 如:技术支持/运维服务/合作商户
     */
    private String unitType;
    /**
     * [联系人]
     */
    private String contactPerson;
    /**
     * [联系电话]
     */
    private String contactPhone;
    /**
     * [合作内容]
     */
    private String cooperationContent;
    /**
     * [合作开始时间]
     */
    private LocalDateTime startTime;
    /**
     * [合作结束时间] 长期合作为NULL
     */
    private LocalDateTime endTime;
    /**
     * [合作状态] 如:进行中/已终止/待开始
     */
    private String status;
    /**
     * [备注]
     */
    private String remark;
    /**
     * [通用扩展字段1]
     */
    private String extCommon1;
    /**
     * [通用扩展字段2]
     */
    private String extCommon2;
    /**
     * [通用扩展字段3]
     */
    private String extCommon3;
    /**
     * [通用扩展字段4]
     */
    private String extCommon4;

}
