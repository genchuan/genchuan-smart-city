package cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 市政设施专题 Response VO")
@Data
public class MuniFacRespVO {

    @Schema(description = "字段1描述", requiredMode = Schema.RequiredMode.REQUIRED, example = "120")
    @ExcelProperty("字段1标题")
    private Integer fieldOne;

}
