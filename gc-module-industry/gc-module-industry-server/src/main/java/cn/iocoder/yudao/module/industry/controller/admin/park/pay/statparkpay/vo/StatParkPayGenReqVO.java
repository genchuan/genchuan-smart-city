// StatParkPayGenReqVO.java
package cn.iocoder.yudao.module.industry.controller.admin.park.pay.statparkpay.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 生成缴费统计 Request VO")
@Data
public class StatParkPayGenReqVO {

    //生成统计的纬度

    //一、通用-时间-区域
    @Schema(description = "数据统计周期（如月,用英文）", requiredMode = Schema.RequiredMode.REQUIRED, example = "month")
    @NotNull(message = "数据统计周期不能为空")
    private String statCycle;

    @Schema(hidden = true)
    private LocalDateTime startTime;

    @Schema(hidden = true)
    private LocalDateTime endTime;

    @Schema(description = "区域代码", example = "350000000000")
    @NotNull(message = "数据统计区域不能为空")
    private String regionFullCode;

    //筛选地区的下一级地区有效长度，比如350100000000，有效位数就是6
    @Schema(hidden = true)
    private String regionCodeChildEffectiveLength;

    //筛选地区的下一级地区等级
    @Schema(hidden = true)
    private String regionCodeChildLevel;

    //二、业务纬度


}
