package cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.asset;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 资产-thingsboard DO
 *
 * @author zhucongquan
 */
@TableName("tb_asset")
@KeySequence("tb_asset_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 资产编码
     */
    private String assetCode;
    /**
     * 资产名称
     */
    private String assetName;
    /**
     * 资产类型
     */
    private String assetType;
    /**
     * 所属区域编码
     */
    private String regionCode;
    /**
     * 状态：正常/停用
     */
    private String assetStatus;
    /**
     * 入账时间
     */
    private LocalDateTime entryTime;
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
