package cn.iocoder.yudao.module.park.controller.admin.park.parklot.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalTime;
import java.util.Date;

@Schema(description = "管理后台 - 停车场分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkLotPageReqVO  extends PageParam {
    // ========== 字符串字段：模糊查询参数 ==========
    private String lotId; // 车场UUID
    private String regionFullCode; // 12位地区码
    private String parkType; // 车场类型
    private String openTime; // 开放时间（字符串）
    private String closeTime; // 关闭时间（字符串）
    private String managementMerchantId; // 运营商户ID
    private String feeStrategyId; // 费率策略ID
    private String lotRemark; // 车场备注/名称
    private String extCommon1; // 扩展字段1
    private String extCommon2; // 扩展字段2
    private String extCommon3; // 扩展字段3
    private String extCommon4; // 扩展字段4
    private String creator; // 创建人
    private String updater; // 更新人

    // ========== 数字字段：范围查询参数（Start=≥，End=≤） ==========
    private Long assetExtendIdStart; // 关联资产扩展ID 最小值
    private Long assetExtendIdEnd; // 关联资产扩展ID 最大值
    private Integer totalSpaceStart; // 总车位数 最小值
    private Integer totalSpaceEnd; // 总车位数 最大值
    private Integer availableSpaceStart; // 可用车位数 最小值
    private Integer availableSpaceEnd; // 可用车位数 最大值
    private Long tenantIdStart; // 租户ID 最小值
    private Long tenantIdEnd; // 租户ID 最大值

    // ========== 时间字段：范围查询参数 ==========
    private Date lotCreateTimeStart; // 业务创建时间 开始
    private Date lotCreateTimeEnd; // 业务创建时间 结束
    private Date lotUpdateTimeStart; // 业务更新时间 开始
    private Date lotUpdateTimeEnd; // 业务更新时间 结束
    private Date createTimeStart; // 数据创建时间 开始
    private Date createTimeEnd; // 数据创建时间 结束
    private Date updateTimeStart; // 数据更新时间 开始
    private Date updateTimeEnd; // 数据更新时间 结束

    // ========== 布尔字段：精准查询参数 ==========
    private Boolean deleted; // 逻辑删除标识（true=已删，false=未删）

}
