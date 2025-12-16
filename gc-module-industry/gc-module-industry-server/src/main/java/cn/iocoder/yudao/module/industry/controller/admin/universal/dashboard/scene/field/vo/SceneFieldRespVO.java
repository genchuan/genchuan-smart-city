package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.field.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 场景字段 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SceneFieldRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "1458")
    @ExcelProperty("主键")
    private Long id;


    @Schema(description = "所属场景id", requiredMode = Schema.RequiredMode.REQUIRED, example = "5430")
    @ExcelProperty("所属场景id")
    private String sceneCode;

//    @Schema(description = "唯一字段标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "23799")
//    @ExcelProperty("唯一字段标识")
//    private String fieldCode;

    @Schema(description = "唯一字段标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "23799")
    @ExcelProperty("唯一字段标识")
    private String key;

    @Schema(description = "字段名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "设施名称")
    @ExcelProperty("字段名称")
    private String label;

    @Schema(description = "字段类型：normal/status/coord", requiredMode = Schema.RequiredMode.REQUIRED, example = "normal")
    @ExcelProperty("字段类型：normal/status/coord")
    private String type;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
