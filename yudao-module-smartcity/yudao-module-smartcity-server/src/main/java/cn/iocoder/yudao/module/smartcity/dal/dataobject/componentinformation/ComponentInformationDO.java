package cn.iocoder.yudao.module.smartcity.dal.dataobject.componentinformation;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 部件信息 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_component_information")
@KeySequence("smartcity_component_information_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComponentInformationDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 部件编号
     */
    private String partNumber;
    /**
     * 部件名称
     */
    private String componentName;
    /**
     * 部件类型
     */
    private String partType;
    /**
     * 所属区域
     */
    private String belongingRegion;
    /**
     * 安装位置
     */
    private String installationPosition;
    /**
     * 经纬度坐标
     */
    private String latitudeLongitude;
    /**
     * 建设日期
     */
    private String constructionDate;
    /**
     * 投入使用日期
     */
    private LocalDateTime dateOfPutIntoUse;
    /**
     * 管理部门
     */
    private String administrativeDepartment;
    /**
     * 维护单位
     */
    private String maintenanceUnit;
    /**
     * 联系电话
     */
    private String contactNumber;
    /**
     * 部件状态
     */
    private String componentStatus;
    /**
     * 规格型号
     */
    private String specificationAndModel;
    /**
     * 技术参数
     */
    private String technicalParameter;
    /**
     * 使用寿命
     */
    private String serviceLife;
    /**
     * 关联事件记录
     */
    private String relatedEventRecords;
    /**
     * 巡检周期
     */
    private String inspectionCycle;
    /**
     * 上次巡检日期
     */
    private LocalDateTime lastInspectionDate;
    /**
     * 下次巡检计划日期
     */
    private LocalDateTime nextScheduledDate;
    /**
     * 备注信息 
     */
    private String memo;

}