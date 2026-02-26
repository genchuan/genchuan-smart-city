package cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 公厕设施维修 DO
 *
 * @author 芋道源码
 */
@TableName("public_toilet_facility_repair")
@KeySequence("public_toilet_facility_repair_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToiletFacilityRepairDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 维修主键（UUID）
     */
    private String repairId;
    /**
     * 关联public_toilet.toilet_id
     */
    private String toiletId;
    /**
     * 关联sys_facility.id
     */
    private String facilityId;
    /**
     * 损坏情况
     */
    private String damageDesc;
    /**
     * 关联sys_user.id
     */
    private String reportBy;
    /**
     * 上报时间
     */
    private LocalDateTime reportTime;
    /**
     * 现场照片URL
     */
    private String photoUrl;
    /**
     * 关联sys_user.id
     */
    private String repairBy;
    /**
     * 维修状态：待维修/维修中/已完成/不合格
     */
    private String repairStatus;
    /**
     * 预计完成时间
     */
    private LocalDateTime expectedCompleteTime;
    /**
     * 验收结果：合格/不合格
     */
    private String acceptResult;
    /**
     * 验收意见
     */
    private String acceptOpinion;
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

}