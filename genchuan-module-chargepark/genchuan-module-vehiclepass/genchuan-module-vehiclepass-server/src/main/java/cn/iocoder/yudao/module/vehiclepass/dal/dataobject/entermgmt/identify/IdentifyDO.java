package cn.iocoder.yudao.module.vehiclepass.dal.dataobject.entermgmt.identify;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 车牌识别 DO
 *
 * @author 亘川智城
 */
@TableName("plate_identify")
@KeySequence("plate_identify_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentifyDO extends BaseDO {

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
     * 车牌颜色：蓝牌/黄牌/绿牌/其他 关联字典：plate_identify_plate_color
     */
    private String plateColor;
    /**
     * 置信度 识别置信度百分比
     */
    private BigDecimal confidence;
    /**
     * 抓拍图片地址
     */
    private String imageUrl;
    /**
     * 识别状态：识别成功/识别失败 关联字典：plate_identify_status
     */
    private String status;
    /**
     * 场站ID 关联场站表
     */
    private Long stationId;
    /**
     * 场站名称
     */
    @TableField(exist = false)
    private String stationName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 修正记录标记：0-未修正 1-已修正 2-已确认
     */
    private Integer isCorrected;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}