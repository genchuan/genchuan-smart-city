package cn.iocoder.yudao.module.smartcity.controller.admin.denselypopulatedareas.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 人员密集场所安全 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DenselyPopulatedAreasRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "14114")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "所名称", example = "芋艿")
    @ExcelProperty("所名称")
    private String theName;

    @Schema(description = "场所类型")
    @ExcelProperty("场所类型")
    private String typeOfVenue;

    @Schema(description = "地址")
    @ExcelProperty("地址")
    private String address;

    @Schema(description = "实时人数")
    @ExcelProperty("实时人数")
    private String realTimeNumberOfPeople;

    @Schema(description = "人员密度")
    @ExcelProperty("人员密度")
    private String occupantDensity;

    @Schema(description = "人员流动速度")
    @ExcelProperty("人员流动速度")
    private String personnelTurnoverSpeed;

    @Schema(description = "人员分布区域")
    @ExcelProperty("人员分布区域")
    private String personnelDistributionArea;

    @Schema(description = "预警级别")
    @ExcelProperty("预警级别")
    private String warningLevel;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}