package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.module.ordertrade.enums.ArrearRecordStatusEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - ArrearRecord 分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ArrearRecordPageReqVO extends PageParam {

    @Schema(description = "车牌，模糊查询")
    private String plateNo;
    @Schema(description = "结清状态")
    @ExcelProperty(value = "结清状态", converter = EnumExcelConverter.class)
    @EnumFormat(ArrearRecordStatusEnum.class)
    private String status;
    @Schema(description = "场站ID")
    private Long stationId;
}
