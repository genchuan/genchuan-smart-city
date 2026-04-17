package cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.module.ordertrade.enums.AmountCheckStatusEnum;
import cn.iocoder.yudao.module.ordertrade.enums.ParkOrderStatusEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - AmountCheck 分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AmountCheckPageReqVO extends PageParam {

    @Schema(description = "关联订单ID")
    private Long orderId;
    @Schema(description = "状态")
    @ExcelProperty(value = "状态", converter = EnumExcelConverter.class)
    @EnumFormat(AmountCheckStatusEnum.class)   // ← 指向枚举类
    private String status;
    @Schema(description = "核算结果")
    private String checkResult;
}
