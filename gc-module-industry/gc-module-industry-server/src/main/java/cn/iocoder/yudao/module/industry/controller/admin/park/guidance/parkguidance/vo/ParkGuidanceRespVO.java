package cn.iocoder.yudao.module.industry.controller.admin.park.guidance.parkguidance.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 停车诱导服务 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkGuidanceRespVO {

    @Schema(description = "记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "2361")
    @ExcelProperty("记录唯一标识")
    private Long id;

    @Schema(description = "停车诱导业务唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "10181")
    @ExcelProperty("停车诱导业务唯一标识")
    private String guidanceId;

    @Schema(description = "用户唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "6353")
    @ExcelProperty("用户唯一标识")
    private Long userId;

    @Schema(description = "用户当前位置X坐标")
    @ExcelProperty("用户当前位置X坐标")
    private BigDecimal userPosX;

    @Schema(description = "用户当前位置Y坐标")
    @ExcelProperty("用户当前位置Y坐标")
    private BigDecimal userPosY;

    @Schema(description = "推荐的停车场唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "13522")
    @ExcelProperty("推荐的停车场唯一标识")
    private Long recommendParkId;

    @Schema(description = "推荐的停车场名称", example = "芋艿")
    @ExcelProperty("推荐的停车场名称")
    private String recommendParkName;

    @Schema(description = "推荐停车场剩余泊位数量")
    @ExcelProperty("推荐停车场剩余泊位数量")
    private Integer remainBerths;

    @Schema(description = "到达推荐停车场的预计行驶时间（秒）")
    @ExcelProperty("到达推荐停车场的预计行驶时间（秒）")
    private Integer estDriveTime;

    @Schema(description = "推荐停车场收费标准")
    @ExcelProperty("推荐停车场收费标准")
    private String chargeStd;

    @Schema(description = "停车诱导采用的方式")
    @ExcelProperty("停车诱导采用的方式")
    private String guidanceMethod;

    @Schema(description = "执行停车诱导的时间")
    @ExcelProperty("执行停车诱导的时间")
    private LocalDateTime guidanceTime;

    @Schema(description = "用户在诱导过程中的行为记录")
    @ExcelProperty("用户在诱导过程中的行为记录")
    private String userBehavior;

    @Schema(description = "业务记录创建时间")
    @ExcelProperty("业务记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "所属行政区划名称", example = "李四")
    @ExcelProperty("所属行政区划名称")
    private String regionName;

    @Schema(description = "所属网格名称", example = "李四")
    @ExcelProperty("所属网格名称")
    private String gridName;

    @Schema(description = "关联的管理事项名称", example = "赵六")
    @ExcelProperty("关联的管理事项名称")
    private String matterName;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

}
