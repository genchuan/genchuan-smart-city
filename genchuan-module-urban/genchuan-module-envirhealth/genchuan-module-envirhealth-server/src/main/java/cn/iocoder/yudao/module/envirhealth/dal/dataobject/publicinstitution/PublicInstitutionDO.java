package cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.math.BigDecimal;

/**
 * 公共机构 DO
 *
 * @author 芋道源码
 */
@TableName("public_institution")
@KeySequence("public_institution_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicInstitutionDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 机构主键（UUID）
     */
    private String institutionId;
    /**
     * 机构名称
     */
    private String name;
    /**
     * 关联sys_institution_type.id
     */
    private String institutionTypeId;
    /**
     * 机构地址
     */
    private String address;
    /**
     * 关联sys_area.area_code
     */
    private String areaCode;
    /**
     * 关联sys_user.id
     */
    private String managerId;
    /**
     * 关联sys_operation_status.id
     */
    private String operationStatusId;
    /**
     * 保洁达标率
     */
    private BigDecimal cleaningRate;
    /**
     * 问题办结率
     */
    private BigDecimal problemRate;
    /**
     * 垃圾清运量（单位：吨）
     */
    private BigDecimal wasteVolume;
    /**
     * 核查通过率
     */
    private BigDecimal inspectionPassRate;
    /**
     * 保洁标准
     */
    private String cleaningStandard;
    /**
     * 保洁频次
     */
    private String cleaningFrequency;
    /**
     * 保洁时段
     */
    private String cleaningTime;
    /**
     * 保洁人员IDs，JSON
     */
    private String cleanerIds;
    /**
     * 责任区域
     */
    private String responsibilityArea;
    /**
     * 通用扩展字段1
     */
    @JsonIgnore
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    @JsonIgnore
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    @JsonIgnore
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    @JsonIgnore
    private String extCommon4;

}