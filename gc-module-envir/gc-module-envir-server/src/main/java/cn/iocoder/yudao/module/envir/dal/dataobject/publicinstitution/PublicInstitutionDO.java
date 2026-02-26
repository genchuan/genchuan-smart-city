package cn.iocoder.yudao.module.envir.dal.dataobject.publicinstitution;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

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
     * 业务主键（UUID）
     */
    private String publicInstitutionId;
    /**
     * 机构名称
     */
    private String name;
    /**
     * 机构类型（关联sys_institution_type.sys_institution_type_id）
     */
    private String institutionTypeId;
    /**
     * 机构地址
     */
    private String address;
    /**
     * 所属区域（关联sys_area.area_code）
     */
    private String areaCode;
    /**
     * 保洁标准（如：一级保洁/二级保洁/三级保洁）
     */
    private String cleaningStandard;
    /**
     * 保洁频次（可选值：每小时/每日/每周/每月/不定期）
     */
    private String cleaningFrequency;
    /**
     * 垃圾收集时段（如：08:00-09:00/18:00-19:00）
     */
    private String collectionTime;
    /**
     * 负责人（关联sys_user.id）
     */
    private String managerId;
    /**
     * 业务创建人（关联sys_user.id）
     */
    private String abnormalCreateBy;
    /**
     * 业务创建时间
     */
    private LocalDateTime abnormalCreateTime;
    /**
     * 业务更新时间
     */
    private LocalDateTime abnormalUpdateTime;
    /**
     * 保洁达标率（0.00-100.00）
     */
    private BigDecimal cleaningRate;
    /**
     * 问题处置完成率（0.00-100.00）
     */
    private BigDecimal problemRate;
    /**
     * 垃圾清运量（单位：立方米）
     */
    private BigDecimal wasteVolume;
    /**
     * 问题上报位置信息（含经纬度、具体地址）
     */
    private String problemLocation;
    /**
     * 保洁对比照片URL（多个用逗号分隔）
     */
    private String cleaningPhotoUrl;
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