package cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholeconfig;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 窨井盖监测配置 DO
 *
 * @author 亘川智城
 */
@TableName("manhole_config")
@KeySequence("manhole_config_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManholeConfigDO extends BaseDO {

    @TableId(type = IdType.AUTO)
    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "21008")
    private Long id;

    @Schema(description = "窨井盖ID", example = "1001")
    private Long coverId;

    @Schema(description = "采集频率(秒)", example = "60")
    private Integer collectFrequency;

    @Schema(description = "倾斜角度阈值", example = "15")
    private BigDecimal tiltAngleThreshold;

    @Schema(description = "开启时长阈值", example = "5")
    private BigDecimal openDurationThreshold;

    @Schema(description = "离线超时时间(分钟)", example = "10")
    private Integer offlineTimeout;

    @Schema(description = "数据上传模式 0-实时 1-缓存", example = "0")
    private Integer dataUploadMode;

    @Schema(description = "报警级别", example = "1")
    private Integer alarmLevel;

    @Schema(description = "报警延迟秒数", example = "30")
    private Integer alarmDelay;

    @Schema(description = "配置状态 0-禁用 1-启用", example = "1")
    private Integer configStatus;

    @Schema(description = "配置状态名称", example = "已生效")
    private String configStatusName;

    @Schema(description = "区块链存证哈希", example = "0x123456")
    private String chainHash;

    @Schema(description = "扩展字段1 位移阈值", example = "10")
    private String extCommon1;

    @Schema(description = "扩展字段2 水位阈值", example = "30")
    private String extCommon2;

    @Schema(description = "扩展字段3 报警方式", example = "0,1")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Integer> extCommon3;

    @Schema(description = "扩展字段4 报警接收人", example = "13800138000")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> extCommon4;

    @Schema(description = "租户ID", example = "1")
    private Long tenantId;

}