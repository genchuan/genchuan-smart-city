package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.offtimerule.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 错时规则 Response VO")
@Data
@ExcelIgnoreUnannotated
public class OfftimeRuleRespVO {

    @Schema(description = "[主键ID] 主键，自增", requiredMode = Schema.RequiredMode.REQUIRED, example = "18794")
    @ExcelProperty("[主键ID] 主键，自增")
    private Long id;

    @Schema(description = "[所属场站] 关联场站信息表 station_info", requiredMode = Schema.RequiredMode.REQUIRED, example = "14476")
    @ExcelProperty("[所属场站] 关联场站信息表 station_info")
    private Long stationId;

    @Schema(description = "[空闲时段] 错时优惠时段描述", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[空闲时段] 错时优惠时段描述")
    private String offTime;

    @Schema(description = "[错时费率] 单位：元/小时或元/次", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[错时费率] 单位：元/小时或元/次")
    private BigDecimal offFee;

    @Schema(description = "[状态] 如：待生效/已生效/已禁用", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("[状态] 如：待生效/已生效/已禁用")
    private String status;

    @Schema(description = "[审核时间] 审核通过的时间")
    @ExcelProperty("[审核时间] 审核通过的时间")
    private LocalDateTime auditTime;

    @Schema(description = "[审核人] 关联芋道用户表 system_user", example = "30078")
    @ExcelProperty("[审核人] 关联芋道用户表 system_user")
    private Long auditUserId;

    @Schema(description = "[错时订单量] 使用该规则的订单数量", example = "17986")
    @ExcelProperty("[错时订单量] 使用该规则的订单数量")
    private Integer offOrderCount;

    @Schema(description = "[备注] 扩展说明", example = "你猜")
    @ExcelProperty("[备注] 扩展说明")
    private String remark;

    @Schema(description = "[备用字段1] 预留扩展")
    @ExcelProperty("[备用字段1] 预留扩展")
    private String reserve1;

    @Schema(description = "[备用字段2] 预留扩展")
    @ExcelProperty("[备用字段2] 预留扩展")
    private String reserve2;

    @Schema(description = "[创建者] 创建人账号/姓名")
    @ExcelProperty("[创建者] 创建人账号/姓名")
    private String creator;

    @Schema(description = "[更新者] 更新人账号/姓名")
    @ExcelProperty("[更新者] 更新人账号/姓名")
    private String updater;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[更新时间] 记录最后更新时间")
    @ExcelProperty("[更新时间] 记录最后更新时间")
    private LocalDateTime updateTime;

}
