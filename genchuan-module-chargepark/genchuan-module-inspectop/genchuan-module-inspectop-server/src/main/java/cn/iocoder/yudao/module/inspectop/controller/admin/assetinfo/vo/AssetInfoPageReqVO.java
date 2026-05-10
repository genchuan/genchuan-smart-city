package cn.iocoder.yudao.module.inspectop.controller.admin.assetinfo.vo;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "巡查巡检 - 资产信息分页 Request VO")
@Data
public class AssetInfoPageReqVO extends PageParam {

    @Schema(description = "资产名称")
    private String name;

    @Schema(description = "资产类型")
    private String type;

    @Schema(description = "采购时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] purchaseTime;

    @Schema(description = "资产状态")
    private String status;

    @Schema(description = "所属场站ID")
    private Long stationId;

    @Schema(description = "场站名称")
    private String stationName;

    @Schema(description = "报废理由")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}