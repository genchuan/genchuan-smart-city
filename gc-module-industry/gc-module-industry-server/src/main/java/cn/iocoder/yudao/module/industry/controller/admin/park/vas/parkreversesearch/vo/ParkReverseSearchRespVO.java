package cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkreversesearch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 反向寻车记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkReverseSearchRespVO {

    @Schema(description = "[主键ID] 反向寻车记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "28009")
    @ExcelProperty("[主键ID] 反向寻车记录唯一标识")
    private Long id;

    @Schema(description = "[用户ID] 用户唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "19518")
    @ExcelProperty("[用户ID] 用户唯一标识")
    private Long userId;

    @Schema(description = "[车牌号码] 用户车辆车牌号码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[车牌号码] 用户车辆车牌号码")
    private String carNumber;

    @Schema(description = "[车场ID] 所属车场ID", example = "32126")
    @ExcelProperty("[车场ID] 所属车场ID")
    private Long lotId;

    @Schema(description = "[车位ID] 所属车位ID", example = "5983")
    @ExcelProperty("[车位ID] 所属车位ID")
    private Long spaceId;

    @Schema(description = "[寻车时间] 用户发起反向寻车的时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[寻车时间] 用户发起反向寻车的时间")
    private LocalDateTime searchTime;

    @Schema(description = "[坐标X] 车位或定位点X坐标")
    @ExcelProperty("[坐标X] 车位或定位点X坐标")
    private BigDecimal locationX;

    @Schema(description = "[坐标Y] 车位或定位点Y坐标")
    @ExcelProperty("[坐标Y] 车位或定位点Y坐标")
    private BigDecimal locationY;

    @Schema(description = "[所在区域] 如：楼层/分区")
    @ExcelProperty("[所在区域] 如：楼层/分区")
    private String locationArea;

    @Schema(description = "[行政区域全码] 12位行政区域编码")
    @ExcelProperty("[行政区域全码] 12位行政区域编码")
    private String regionFullCode;

    @Schema(description = "[导航路径信息] JSON格式导航路径数据")
    @ExcelProperty("[导航路径信息] JSON格式导航路径数据")
    private String routeInfo;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    @ExcelProperty("[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    @ExcelProperty("[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    @ExcelProperty("[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    @ExcelProperty("[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

    @Schema(description = "[备注] 反向寻车相关备注说明", example = "你猜")
    @ExcelProperty("[备注] 反向寻车相关备注说明")
    private String remark;

}
