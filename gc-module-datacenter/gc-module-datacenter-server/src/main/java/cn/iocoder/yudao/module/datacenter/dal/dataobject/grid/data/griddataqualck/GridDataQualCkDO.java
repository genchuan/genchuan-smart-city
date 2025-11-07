package cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.data.griddataqualck;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 网格数据质量检查 DO
 *
 * @author zcq
 */
@TableName("biz_grid_data_qual_ck")
@KeySequence("biz_grid_data_qual_ck_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridDataQualCkDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 质量检查ID
     */
    private String qualCkId;
    /**
     * 网格ID
     */
    private String gridId;
    /**
     * 网格类型
     */
    private String gridType;
    /**
     * 网格编码
     */
    private String gridCode;
    /**
     * 检查项
     */
    private String ckItem;
    /**
     * 检查结果
     */
    private String ckResult;
    /**
     * 错误描述
     */
    private String errorDesc;
    /**
     * 整改建议
     */
    private String rectifySugg;
    /**
     * 检查时间
     */
    private LocalDateTime ckTime;
    /**
     * 检查人员
     */
    private String ckUserId;
    /**
     * 整改状态
     */
    private String rectifyStatus;
    /**
     * 整改时间
     */
    private LocalDateTime rectifyTime;
    /**
     * 整改人员
     */
    private String rectifyUserId;
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