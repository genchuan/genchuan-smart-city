package cn.iocoder.yudao.module.envir.dal.dataobject.publictoilet;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 公厕 DO
 *
 * @author 芋道源码
 */
@TableName("public_toilet")
@KeySequence("public_toilet_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicToiletDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 业务主键（UUID）
     */
    private String publicToiletId;
    /**
     * 公厕名称
     */
    private String name;
    /**
     * 公厕位置（含经纬度）
     */
    private String location;
    /**
     * 所属区域（关联sys_area.area_code）
     */
    private String areaCode;
    /**
     * 开放时段
     */
    private String openHours;
    /**
     * 蹲位数量
     */
    private Integer stallCount;
    /**
     * 配套设施（关联sys_facility.sys_facility_id，多个用逗号分隔）
     */
    private String facilityIds;
    /**
     * 耗材字典表ID（多个用逗号分隔）
     */
    private String consumableIds;
    /**
     * 运营状态（关联sys_operation_status.sys_operation_status_id）
     */
    private String operationStatusId;
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
     * 保洁达标率
     */
    private BigDecimal cleaningRate;
    /**
     * 耗材库存预警数
     */
    private Integer warningCount;
    /**
     * 投诉办结率
     */
    private BigDecimal complaintRate;
    /**
     * 公厕现场照片URL（多个用逗号分隔）
     */
    private String photoUrl;
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