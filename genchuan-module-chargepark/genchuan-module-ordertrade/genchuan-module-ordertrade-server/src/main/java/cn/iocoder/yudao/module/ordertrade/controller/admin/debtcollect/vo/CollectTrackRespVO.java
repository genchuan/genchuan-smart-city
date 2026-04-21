package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.ordertrade.enums.CollectMethodEnum;
import cn.iocoder.yudao.module.ordertrade.enums.CollectTrackStatusEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 追缴跟踪 Response VO")
@Data
public class CollectTrackRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "追缴编号")
    @ExcelProperty("追缴编号")
    private String trackNo;

    @Schema(description = "车牌")
    @ExcelProperty("车牌")
    private String plateNo;

    @Schema(description = "追缴方式")
    @ExcelProperty(value = "追缴方式", converter = EnumExcelConverter.class)
    @EnumFormat(CollectMethodEnum.class)
    private String collectMethod;

    @Schema(description = "追缴时间")
    @ExcelProperty("追缴时间")
    private LocalDateTime collectTime;

    @Schema(description = "状态")
    @ExcelProperty(value = "状态", converter = EnumExcelConverter.class)
    @EnumFormat(CollectTrackStatusEnum.class)
    private String status;

    @Schema(description = "片区ID")
    @ExcelProperty("片区ID")
    private Long areaId;

    @Schema(description = "转派用户ID")
    @ExcelProperty("转派用户ID")
    private Long transferUserId;

    @Schema(description = "追缴进度")
    @ExcelProperty("追缴进度")
    private String collectProgress;

    @Schema(description = "操作人ID")
    @ExcelProperty("操作人ID")
    private Long operatorId;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
