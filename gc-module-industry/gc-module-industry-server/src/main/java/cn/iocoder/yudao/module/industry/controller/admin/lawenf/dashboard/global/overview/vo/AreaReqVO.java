package cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.overview.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 执法全域数据概览 查询行政区req")
@Data
public class AreaReqVO {
    @Schema(description = "行政区shortCode码，6位",example = "110000")
    private String regionShortCode;
}
