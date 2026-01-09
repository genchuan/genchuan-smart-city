package cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.warn.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 应急安全近期预警 Response VO")
@Data
public class EmergWarnRespVO {


    @Schema(description = "类型：应急安全、风险、应急资源等", requiredMode = Schema.RequiredMode.REQUIRED, example = "应急安全")
    @ExcelProperty("类型")
    private String type;

    @Schema(description = "发现时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2025-01-03 09:12:30")
    @ExcelProperty("发现时间")
    private String discoveryTime;

    @Schema(description = "状态：未处理、处理中、已解决", requiredMode = Schema.RequiredMode.REQUIRED, example = "未处理")
    @ExcelProperty("状态")
    private String status;

    @Schema(description = "描述", example = "施工围挡出现松动")
    @ExcelProperty("描述")
    private String description;

    @Schema(description = "区域名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "北京市")
    @ExcelProperty("区域名称")
    private String regionName;


}
