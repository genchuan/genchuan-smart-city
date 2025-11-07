package cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emermatinfo;

import lombok.*;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 应急物资信息 DO
 *
 * @author 亘川智城
 */
@TableName("biz_emer_mat_info")
@KeySequence("biz_emer_mat_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmerMatInfoDO extends BaseDO {

    /**
     * 自增主键ID
     */
    @TableId
    private Long id;
    /**
     * 物资ID，唯一编码，UUID
     */
    private String matId;
    /**
     * 物资编码，系统自动生成
     */
    private String matCode;
    /**
     * 物资名称，标准名称
     */
    private String matName;
    /**
     * 分类ID，关联应急物资分类配置表
     */
    private String catId;
    /**
     * 分类名称，与分类ID同步
     */
    private String catName;
    /**
     * 适用区域代码，关联行政区划表
     */
    private String applyRegionCode;
    /**
     * 适用区域名称，与区域代码同步
     */
    private String applyRegionName;
    /**
     * 物资规格，如“5kg装”
     */
    private String matSpec;
    /**
     * 物资单位，如“个”“箱”
     */
    private String matUnit;
    /**
     * 总数量，物资的总库存数量
     */
    private Integer totalQuantity;
    /**
     * 可用数量，正常状态可使用的物资数量
     */
    private Integer availableQuantity;
    /**
     * 物资状态，1（正常）/0（异常），默认1
     */
    private String matStatus;
    /**
     * 创建人，创建人账号，关联用户信息表(sys_user)
     */
    private String createUser;
    /**
     * 更新人，更新人账号，关联用户信息表(sys_user)
     */
    private String updateUser;

}