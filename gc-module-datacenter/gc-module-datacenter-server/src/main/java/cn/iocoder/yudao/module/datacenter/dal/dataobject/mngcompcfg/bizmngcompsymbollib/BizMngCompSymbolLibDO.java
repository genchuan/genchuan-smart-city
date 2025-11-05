package cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.bizmngcompsymbollib;

import lombok.*;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 管理部件图示符号库 DO
 *
 * @author 亘川智城
 */
@TableName("gc_biz_mng_comp_symbol_lib")
@KeySequence("gc_biz_mng_comp_symbol_lib_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BizMngCompSymbolLibDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 符号库ID，唯一编码，UUID生成
     */
    private String symbolLibId;
    /**
     * 符号名称
     */
    private String symbolName;
    /**
     * 符号路径
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