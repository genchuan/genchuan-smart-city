package cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.relmngcompsymbol;

import lombok.*;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 管理部件图示关联 DO
 *
 * @author 亘川智城
 */
@TableName("gc_rel_mng_comp_symbol")
@KeySequence("gc_rel_mng_comp_symbol_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelMngCompSymbolDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 关联ID，唯一编码，UUID生成
     */
    private String mngCompSymbolId;
    /**
     * 所属小类ID，关联管理部件小类ID，一个小类关联一个图示，关联管理部件小类表(gc_biz_mng_comp_minor)
     */
    private String minorId;
    /**
     * 所属小类名称，与小类ID同步，不可改，关联管理部件小类表(gc_biz_mng_comp_minor)
     */
    private String minorName;
    /**
     * 图示ID，关联图示符号库ID，关联图示符号库表(gc_biz_mng_comp_symbol_lib)
     */
    private String symbolId;
    /**
     * 图示名称，如“上水井盖图示”，关联图示符号库表(gc_biz_mng_comp_symbol_lib)
     */
    private String symbolName;
    /**
     * 图示路径，如“/static/symbols/water_cover.png”，支持PNG/SVG，关联图示符号库表(gc_biz_mng_comp_symbol_lib)
     */
    private String symbolPath;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}