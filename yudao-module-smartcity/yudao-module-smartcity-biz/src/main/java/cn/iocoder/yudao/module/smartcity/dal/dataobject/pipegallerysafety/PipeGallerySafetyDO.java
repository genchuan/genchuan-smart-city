package cn.iocoder.yudao.module.smartcity.dal.dataobject.pipegallerysafety;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 管廊安全 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_pipe_gallery_safety")
@KeySequence("smartcity_pipe_gallery_safety_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PipeGallerySafetyDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 管廊名称
     */
    private String pipeGalleryName;
    /**
     * 管廊位置
     */
    private String locationGallery;
    /**
     * 管廊编号
     */
    private String pipeGalleryNumber;
    /**
     * 入廊管线类型
     */
    private String typePipeline;
    /**
     * 入廊管线数量
     */
    private String numberOfEntrancePipelines;
    /**
     * 管廊结构安全状况
     */
    private String safetyStatusStructure;
    /**
     * 管廊渗漏水情况
     */
    private String leakagePipeGallery;
    /**
     * 温湿度
     */
    private String temperatureAndHumidity;
    /**
     * 有害气体浓度
     */
    private String harmfulGasConcentration;
    /**
     * 氧气含量
     */
    private String oxygenContent;
    /**
     * 通风设备运行状态
     */
    private String operationVentilationEquipment;
    /**
     * 预警级别
     */
    private String warningLevel;
    /**
     * 维修记录
     */
    private String maintenanceRecord;
    /**
     * 维修时间
     */
    private LocalDateTime maintenanceTime;
    /**
     * 应急物资储备情况
     */
    private String emergencyMaterialSituation;

}