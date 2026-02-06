package cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.assetextend;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 资产扩展 DO
 *
 * @author zhucongquan
 */
@TableName("tb_asset_extend")
@KeySequence("tb_asset_extend_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetExtendDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 资产类型：车场/车库/路侧泊位/车位/出入口
     */
    private String assetType;
    /**
     * 资产名称
     */
    private String assetName;
    /**
     * 唯一资产编码
     */
    private String assetCode;
    /**
     * 状态：正常/故障/停用
     */
    private String assetStatus;
    /**
     * 所属区域编码
     */
    private String regionCode;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 经度
     */
    private BigDecimal longitude;
    /**
     * 纬度
     */
    private BigDecimal latitude;
    /**
     * 业务创建时间
     */
    private LocalDateTime assetCreateTime;
    /**
     * 业务更新时间
     */
    private LocalDateTime assetUpdateTime;
    /**
     * 业务备注
     */
    private String assetRemark;

}
