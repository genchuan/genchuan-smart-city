package cn.iocoder.yudao.module.datacenter.dal.dataobject.coopcfg.coopunitinfo;

import lombok.*;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 联动单位信息 DO
 *
 * @author 亘川智城
 */
@TableName("biz_coop_unit_info")
@KeySequence("biz_coop_unit_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoopUnitInfoDO extends BaseDO {

    /**
     * 自增主键ID
     */
    @TableId
    private Long id;
    /**
     * 单位ID，唯一编码，UUID生成
     */
    private String unitId;
    /**
     * 单位编码，系统自动生成，格式“UNIT+年份（4位）+6位流水号”
     */
    private String unitCode;
    /**
     * 单位名称，如“XX市消防救援支队XX中队”
     */
    private String unitName;
    /**
     * 单位类型，政府部门/企业/社会组织
     */
    private String unitType;
    /**
     * 行政区划代码，关联行政区划表
     */
    private String regionCode;
    /**
     * 行政区划名称，与区域代码同步
     */
    private String regionName;
    /**
     * 负责人姓名
     */
    private String leaderName;
    /**
     * 负责人联系方式，加密存储
     */
    private String leaderContact;
    /**
     * 单位详细地址
     */
    private String contactAddress;
    /**
     * 联动范围，如“火灾救援、建筑物坍塌救援”
     */
    private String coopScope;
    /**
     * 单位状态，启用/禁用，默认启用
     */
    private String unitStatus;
    /**
     * 创建人账号，关联用户信息表
     */
    private String createUser;
    /**
     * 更新人账号，关联用户信息表
     */
    private String updateUser;

}