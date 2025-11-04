package cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.icons.gridcodeannotate;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 网格标识码注记配置 DO
 *
 * @author zcq
 */
@TableName("biz_grid_code_annotate")
@KeySequence("biz_grid_code_annotate_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridCodeAnnotateDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 注记ID
     */
    private String annotateId;
    /**
     * 比例尺
     */
    private String scale;
    /**
     * 网格类型
     */
    private String gridType;
    /**
     * 字高(mm)
     */
    private BigDecimal fontHeight;
    /**
     * 字型
     */
    private String fontType;
    /**
     * 颜色C值(%)
     */
    private BigDecimal colorC;
    /**
     * 颜色M值(%)
     */
    private BigDecimal colorM;
    /**
     * 颜色Y值(%)
     */
    private BigDecimal colorY;
    /**
     * 颜色K值(%)
     */
    private BigDecimal colorK;
    /**
     * 创建人
     */
    private String createUserId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 分类扩展字段1
     */
    private String extCat1;
    /**
     * 分类扩展字段2
     */
    private String extCat2;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;

}