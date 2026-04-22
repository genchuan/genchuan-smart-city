package cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inparkmgmt.oilcarhandle;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 油车占位处置 DO
 *
 * @author 亘川智城
 */
@TableName("oil_car_handle")
@KeySequence("oil_car_handle_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OilCarHandleDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 车牌
     */
    private String plateNo;
    /**
     * 车位ID，关联车位表
     */
    private Long spaceId;
    /**
     * 识别时间
     */
    private LocalDateTime identifyTime;
    /**
     * 占位类型：燃油车占位 / 其他，关联字典oil_car_handle_occupy_type
     */
    private String occupyType;
    /**
     * 处置状态：未处理 / 处理中 / 已关闭，关联字典oil_car_handle_status
     */
    private String status;
    /**
     * 场站ID，关联场站表
     */
    private Long stationId;
    /**
     * 处置人ID，关联system_user用户表
     */
    private Long handleUserId;
    /**
     * 处置时间
     */
    private LocalDateTime handleTime;
    /**
     * 处置方式
     */
    private String handleMethod;
    /**
     * 处理类型：处置 / 忽略
     */
    private String handleType;
    /**
     * 忽略理由
     */
    private String ignoreReason;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}