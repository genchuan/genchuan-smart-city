package cn.iocoder.yudao.module.envir.dal.dataobject.urbanvillage;

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
 * 城中村 DO
 *
 * @author 芋道源码
 */
@TableName("urban_village")
@KeySequence("urban_village_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrbanVillageDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 业务主键（UUID）
     */
    private String urbanVillageId;
    /**
     * 城中村名称
     */
    private String name;
    /**
     * 城中村地址
     */
    private String address;
    /**
     * 所属区域（关联sys_area.area_code）
     */
    private String areaCode;
    /**
     * 责任区域数量
     */
    private Integer responsibilityAreas;
    /**
     * 责任区域划分规则（含划分依据、区域边界、责任人等）
     */
    private String areaDivideRule;
    /**
     * 道路保洁频次（可选值：每小时/每日2次/每日1次/隔日1次/每周2次）
     */
    private String roadCleaningFrequency;
    /**
     * 垃圾收集时段（如：07:00-08:00/19:00-20:00）
     */
    private String wasteCollectionTime;
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
     * 考核得分（0.00-100.00）
     */
    private BigDecimal assessmentScore;
    /**
     * 问题上报照片URL（多个用逗号分隔）
     */
    private String problemPhotoUrl;
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