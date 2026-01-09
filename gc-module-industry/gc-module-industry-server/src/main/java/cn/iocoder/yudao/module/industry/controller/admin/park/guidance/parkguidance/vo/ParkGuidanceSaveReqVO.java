package cn.iocoder.yudao.module.industry.controller.admin.park.guidance.parkguidance.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 停车诱导服务新增/修改 Request VO")
@Data
public class ParkGuidanceSaveReqVO {

    @Schema(description = "记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "2361")
    private Long id;

    @Schema(description = "停车诱导业务唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "10181")
    @NotEmpty(message = "停车诱导业务唯一标识不能为空")
    private String guidanceId;

    @Schema(description = "用户唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "6353")
    @NotNull(message = "用户唯一标识不能为空")
    private Long userId;

    @Schema(description = "用户当前位置X坐标")
    private BigDecimal userPosX;

    @Schema(description = "用户当前位置Y坐标")
    private BigDecimal userPosY;

    @Schema(description = "推荐的停车场唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "13522")
    @NotNull(message = "推荐的停车场唯一标识不能为空")
    private Long recommendParkId;

    @Schema(description = "推荐的停车场名称", example = "芋艿")
    private String recommendParkName;

    @Schema(description = "推荐停车场剩余泊位数量")
    private Integer remainBerths;

    @Schema(description = "到达推荐停车场的预计行驶时间（秒）")
    private Integer estDriveTime;

    @Schema(description = "推荐停车场收费标准")
    private String chargeStd;

    @Schema(description = "停车诱导采用的方式")
    private String guidanceMethod;

    @Schema(description = "执行停车诱导的时间")
    private LocalDateTime guidanceTime;

    @Schema(description = "用户在诱导过程中的行为记录")
    private String userBehavior;

    @Schema(description = "所属行政区划名称", example = "李四")
    private String regionName;

    @Schema(description = "所属网格名称", example = "李四")
    private String gridName;

    @Schema(description = "关联的管理事项名称", example = "赵六")
    private String matterName;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}
