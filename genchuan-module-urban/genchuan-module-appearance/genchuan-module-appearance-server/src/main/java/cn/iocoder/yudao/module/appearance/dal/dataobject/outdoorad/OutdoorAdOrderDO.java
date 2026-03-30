package cn.iocoder.yudao.module.appearance.dal.dataobject.outdoorad;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 户外广告 DO
 *
 * @author 亘川智城
 */
@TableName("rectification_order")
@KeySequence("rectification_order_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutdoorAdOrderDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private String id;
    /**
     * 工单主键ID（UUID）
     */
    private String orderId;
    /**
     * 工单编码
     */
    private String orderCode;
    /**
     * 广告主键ID
     */
    private String adId;
    /**
     * 问题描述
     */
    private String problemDesc;
    /**
     * 问题图片ID
     */
    private String probImgId;
    /**
     * 问题时间
     */
    private String requireTime;
    /**
     * 订单状态ID
     */
    private String orderStatusId;
    /**
     * 处理人ID
     */
    private String handlerId;
    /**
     * 处理描述
     */
    private String handlingDesc;
    /**
     * 处理图片ID
     */
    private String handImgId;
    /**
     * 完成时间
     */
    private LocalDateTime completeTime;
    /**
     * 拒绝原因
     */
    private String rejectReason;
    /**
     * 关闭原因
     */
    private String closeReason;
    /**
     * 创建人ID
     */
    private String createId;
    /**
     * 更新人ID
     */
    private String updateId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 区块链存证哈希值
     */
    private String chainHash;
    // ==================== 以下为关联查询字段（非数据库字段） ====================
    /**
     * 广告主键ID
     */
    @TableField(exist = false)
    private String outdoorAdId;
    /**
     * 广告名称
     */
    @TableField(exist = false)
    private String adName;
    /**
     * 广告编码
     */
    @TableField(exist = false)
    private String adCode;
    /**
     * 处理人ID
     */
    @TableField(exist = false)
    private String handlerUserId;
    /**
     * 处理人真实姓名
     */
    @TableField(exist = false)
    private String handlerName;
    /**
     * 创建人真实姓名
     */
    @TableField(exist = false)
    private String createByName;
    /**
     * 工单状态
     */
    @TableField(exist = false)
    private String orderStatus;

    // 现场图片列表
    @TableField(exist = false)
    private List<OnsiteImageDO> problemImgList;

    @TableField(exist = false)
    private List<OnsiteImageDO> handleImgList;
   }
