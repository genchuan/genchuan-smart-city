package cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

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
     * 公厕编码，业务唯一标识
     */
    private String toiletId;

    /**
     * 公厕名称
     */
    private String name;

    /**
     * 公厕位置/地址
     */
    private String location;

    /**
     * 所属区域编码，关联sys_area.area_code
     */
    private String areaCode;

    /**
     * 开放时段，如06:00-22:00
     */
    private String openHours;

    /**
     * 蹲位数量
     */
    private Integer stallCount;

    /**
     * 运营状态ID，关联sys_operation_status.id
     */
    private String operationStatusId;

    /**
     * 负责人ID，关联sys_user.id
     */
    private String managerId;

    /**
     * 保洁达标率，%
     */
    private BigDecimal cleaningRate;

    /**
     * 投诉办结率，%
     */
    private BigDecimal complaintRate;

    /**
     * 设施完好率，%
     */
    private BigDecimal facilityRate;

    /**
     * 耗材库存预警数
     */
    private Integer warningCount;

}