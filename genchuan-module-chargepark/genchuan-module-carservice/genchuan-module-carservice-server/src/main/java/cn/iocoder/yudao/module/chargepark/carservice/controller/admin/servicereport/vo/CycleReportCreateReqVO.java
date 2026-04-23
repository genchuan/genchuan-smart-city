package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo;

import cn.iocoder.yudao.module.chargepark.carservice.framework.jackson.StringLocalDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周期报表 生成 Request VO")
@Data
public class CycleReportCreateReqVO {

    @Schema(description = "统计类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "自定义",
            allowableValues = {"日报", "周报", "月报", "季报", "半年报", "年报", "自定义"})
    @NotBlank(message = "统计类型不能为空")
    private String statType;

    @Schema(description = "统计开始时间", requiredMode = Schema.RequiredMode.REQUIRED,
            example = "2026-04-01 00:00:00")
    @NotNull(message = "统计开始时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = StringLocalDateTimeDeserializer.class)
    private LocalDateTime statStartTime;

    @Schema(description = "统计结束时间", requiredMode = Schema.RequiredMode.REQUIRED,
            example = "2026-04-20 23:59:59")
    @NotNull(message = "统计结束时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = StringLocalDateTimeDeserializer.class)
    private LocalDateTime statEndTime;

}
