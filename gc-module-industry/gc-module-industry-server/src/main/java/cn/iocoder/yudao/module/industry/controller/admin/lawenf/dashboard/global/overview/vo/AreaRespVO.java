package cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.overview.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "管理后台 - 执法全域数据概览 行政区返回对象")
@Data
public class AreaRespVO {
    @Schema(description = "（省/市/县）行政区名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "丰台区")
    @ExcelProperty("行政区名称")
    private String regionName;

    @Schema(description = "行政区shortCode码，6位",example = "110000")
    private String regionShortCode;
}
