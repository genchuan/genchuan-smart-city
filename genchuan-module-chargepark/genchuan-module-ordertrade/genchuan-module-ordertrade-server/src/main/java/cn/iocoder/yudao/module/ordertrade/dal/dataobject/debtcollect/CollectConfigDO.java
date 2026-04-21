package cn.iocoder.yudao.module.ordertrade.dal.dataobject.debtcollect;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 追缴配置 DO
 * @author genchuan
 */
@TableName("collect_config")
@KeySequence("collect_config_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CollectConfigDO extends BaseDO {

    /** 主键ID */
    @TableId
    private Long id;

    /** 配置编号，唯一 */
    private String configNo;

    /** 追缴方式，字典：collect_config_collect_method */
    private String collectMethod;

    /** 推送模板ID */
    private Long templateId;

    /** 推送频次（小时） */
    private Integer pushFrequency;

    /** 状态，字典：collect_config_status */
    private String status;

    /** 配置说明 */
    private String remark;

    /** 操作人ID */
    private Long operatorId;

    /** 备用字段1 */
    private String reserve1;

    /** 备用字段2 */
    private String reserve2;
}
