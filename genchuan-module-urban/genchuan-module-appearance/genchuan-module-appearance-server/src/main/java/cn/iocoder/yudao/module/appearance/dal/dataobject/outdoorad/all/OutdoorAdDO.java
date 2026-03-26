package cn.iocoder.yudao.module.appearance.dal.dataobject.outdoorad.all;

import cn.idev.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 户外广告 DO
 *
 * @author 亘川智城
 */
@TableName("outdoor_ad")
@KeySequence("outdoor_ad_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutdoorAdDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 广告ID（UUID）
     */
    private String outdoorAdId;
    /**
     * 广告编码
     */
    private String adCode;
    /**
     * 广告名称
     */
    private String name;
    /**
     * 广告类型
     */
    private String type;
    /**
     * 广告位置
     */
    private String location;
    /**
     * 广告点位经度
     */
    private String lng;
    /**
     * 广告点位纬度
     */
    private String lat;
    /**
     * 网格ID
     */
    private Long gridId;
    /**
     * 审批尺寸
     */
    private String approvedSize;
    /**
     * 审批状态
     */
    private String approvalStatus;
    /**
     * 审批意见
     */
    private String approvedDesc;
    /**
     * 审批人ID
     */
    private Long approvalBy;
    /**
     * 审批开始时间
     */
    private LocalDateTime startApprovalTime;
    /**
     * 审批结束时间
     */
    private LocalDateTime endApprovalTime;
    /**
     * 数据状态
     */
    private Long dataStatus;
    /**
     * 归档版本号
     */
    private String archiveVersion;
    /**
     * 附件文件ID
     */
    private Long attachFileId;
    /**
     * 实际尺寸
     */
    private String actualSize;
    /**
     * 区块链存证哈希值
     */
    private String chainHash;
    /**
     * 全生命周期ID
     */
    private String lifeCycleId;
    /**
     * 倾斜角度
     */
    private BigDecimal tiltAngle;
    /**
     * 关联 sys_damage_status.id，破损状态ID
     */
    private Long damageStatusId;
    /**
     * 关联 sys_ad_status.id，广告状态ID
     */
    private Long adStatusId;
    /**
     * 区域ID
     */
    private Long areaId;
    /**
     * 关联 sys_user.id，监管员ID
     */
    private Long supervisorId;
    /**
     * 关联 sys_warning_type.id，预警类型ID
     */
    private Long warningTypeId;
    /**
     * 预警时间
     */
    private LocalDateTime warningTime;
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
    // ==================== 以下为关联查询字段（非数据库字段） ====================
    /**
     * 区域名称
     */
    @TableField(exist = false)
    private String areaName;
    /**
     * 关联 sys_area.id，区域编码
     */
    @TableField(exist = false)
    private String areaCode;
    /**
     * 网格名称
     */
    @TableField(exist = false)
    private String gridName;
    /**
     * 文件名称
     */
    @TableField(exist = false)
    private String fileName;
    /**
     * 文件地址
     */
    @TableField(exist = false)
    private String fileUrl;
    /**
     * 文件类型
     */
    @TableField(exist = false)
    private String fileType;
    /**
     * 审批人名称（对应 approvalBy）
     */
    @TableField(exist = false)
    private String approvalName;
    /**
     * 创建人名称（对应 creator）
     */
    @TableField(exist = false)
    private String createName;
    /**
     * 更新人名称（对应 updater）
     */
    @TableField(exist = false)
    private String updateName;
    /**
     * 文件ID
     */
    @TableField(exist = false)
    private Long fileId;
    /**
     * 关联 sys_grid.id，网格编码
     */
    @TableField(exist = false)
    private String gridCode;
}