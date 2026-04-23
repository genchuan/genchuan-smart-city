package cn.iocoder.yudao.module.inspectop.dal.dataobject.assetinfo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 资产信息 DO
 *
 * @author zhucongquan
 */
@TableName("asset_info")
@KeySequence("asset_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetInfoDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 资产名称
     */
    private String name;
    /**
     * 资产类型
     */
    private String type;
    /**
     * 采购时间
     */
    private LocalDateTime purchaseTime;
    /**
     * 资产状态
     */
    private String status;
    /**
     * 所属场站ID
     */
    private Long stationId;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}