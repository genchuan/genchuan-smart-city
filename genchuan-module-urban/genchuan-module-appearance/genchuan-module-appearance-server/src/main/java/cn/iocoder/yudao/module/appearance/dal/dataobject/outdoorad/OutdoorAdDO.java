package cn.iocoder.yudao.module.appearance.dal.dataobject.outdoorad;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
     * 主键ID（UUID）
     */
    @TableId
    private String id;
    /**
     * 广告编码，唯一
     */
    private String adCode;
    /**
     * 广告名称
     */
    private String adName;
    /**
     * 广告类型（立柱/墙面/灯箱/电子屏）
     */
    private String adType;
    /**
     * 广告尺寸（长*宽，单位：米）
     */
    private String adSize;
    /**
     * 实际尺寸（长*宽，单位：米）
     */
    private String actualSize;
    /**
     * 广告具体位置
     */
    private String adLocation;
    /**
     * 广告点位经度
     */
    private BigDecimal lng;
    /**
     * 广告点位纬度
     */
    private BigDecimal lat;
    /**
     * 所属区域编码
     */
    private String areaCode;
    /**
     * 所属网格编码
     */
    private String gridCode;
    /**
     * 审批状态（待审批/已审批/已驳回）
     */
    private String approvalStatus;
    /**
     * 审批意见
     */
    private String approvalOpinion;
    /**
     * 审批人真实姓名
     */
    private String approverName;
    /**
     * 审批时间
     */
    private LocalDateTime approvalTime;
    /**
     * 数据状态：0-未启用，1-已启用，2-已归档
     */
    private Integer dataStatus;
    /**
     * 归档版本号
     */
    private String archiveVersion;
    /**
     * 区块链存证哈希值
     */
    private String chainHash;
    /**
     * 全生命周期ID
     */
    private String lifeCycleId;
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

    // ==================== 以下为关联查询字段（非数据库字段） ====================
    /**
     * 所属区域名称
     */
    @TableField(exist = false)
    private String areaName;
    /**
     * 所属网格名称
     */
    @TableField(exist = false)
    private String gridName;
    /**
     * 审批人名称（对应 approverName）
     */
    @TableField(exist = false)
    private String approvalName;
    /**
     * 创建人名称（对应 creator，BaseDO 中有 creator 字段但存储的是用户ID，此处为昵称）
     */
    @TableField(exist = false)
    private String createByName;
    /**
     * 更新人名称（对应 updater）
     */
    @TableField(exist = false)
    private String updateByName;
    /**
     * 附件文件列表
     */
    @TableField(exist = false)
    private List<SysAttachFileDO> attachFileList;
}