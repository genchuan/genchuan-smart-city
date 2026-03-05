package cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkinduction;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 停车诱导配置 DO
 *
 * @author lxs
 */
@TableName("park_induction")
@KeySequence("park_induction_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkInductionDO extends BaseDO {

    /**
     * [主键ID] 停车诱导配置唯一标识
     */
    @TableId
    private Long id;
    /**
     * [诱导名称] 停车诱导名称
     */
    private String inductionName;
    /**
     * [覆盖区域] JSON格式varchar经纬度范围数据
     */
    private String region;
    /**
     * [关联车场ID列表] 如：1,2,3
     */
    private String relatedLotIds;
    /**
     * [推送策略] 如：实时推送/定时推送/按需推送
     */
    private String pushStrategy;
    /**
     * [状态] 如：0-禁用/1-启用
     */
    private String status;
    /**
     * [备注] 停车诱导配置相关备注说明
     */
    private String remark;
    /**
     * [通用扩展字段1] 通用扩展字段1
     */
    private String extCommon1;
    /**
     * [通用扩展字段2] 通用扩展字段2
     */
    private String extCommon2;
    /**
     * [通用扩展字段3] 通用扩展字段3
     */
    private String extCommon3;
    /**
     * [通用扩展字段4] 通用扩展字段4
     */
    private String extCommon4;

}
