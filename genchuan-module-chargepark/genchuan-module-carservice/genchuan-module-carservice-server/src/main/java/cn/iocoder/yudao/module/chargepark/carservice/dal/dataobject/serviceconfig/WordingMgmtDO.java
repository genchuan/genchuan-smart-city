package cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.serviceconfig;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 话术管理 DO
 *
 * 数据库表：wording_mgmt
 *
 * @author carservice
 */
@TableName("wording_mgmt")
@KeySequence("wording_mgmt_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WordingMgmtDO extends BaseDO {

    /**
     * 主键 ID
     */
    @TableId
    private Long id;
    /**
     * 话术名称
     */
    private String name;
    /**
     * 话术内容
     */
    private String content;
    /**
     * 话术类型：快捷回复/自动回复/投诉回复
     * 关联字典 wording_mgmt_type
     */
    private String type;
    /**
     * 状态：未生效/已生效
     * 关联字典 wording_mgmt_status
     */
    private String status;
    /**
     * 备用字段 1
     */
    private String reserve1;
    /**
     * 备用字段 2
     */
    private String reserve2;

}
