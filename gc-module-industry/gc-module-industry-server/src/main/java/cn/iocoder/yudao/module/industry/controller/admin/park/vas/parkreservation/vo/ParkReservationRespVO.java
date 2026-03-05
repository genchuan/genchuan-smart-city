package cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkreservation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 停车预约 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkReservationRespVO {

    @Schema(description = "[主键ID] 停车预约记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "8420")
    @ExcelProperty("[主键ID] 停车预约记录唯一标识")
    private Long id;

    @Schema(description = "[预约编号] 停车预约唯一编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[预约编号] 停车预约唯一编号")
    private String reservationNo;

    @Schema(description = "[用户ID] 预约用户唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "9540")
    @ExcelProperty("[用户ID] 预约用户唯一标识")
    private Long userId;

    @Schema(description = "[车牌] 预约车辆车牌号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[车牌] 预约车辆车牌号")
    private String carNumber;

    @Schema(description = "[车场ID] 所属车场唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "12417")
    @ExcelProperty("[车场ID] 所属车场唯一标识")
    private Long lotId;

    @Schema(description = "[车位ID] 所属车位唯一标识", example = "18104")
    @ExcelProperty("[车位ID] 所属车位唯一标识")
    private Long spaceId;

    @Schema(description = "[预约日期] 用户预约的日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[预约日期] 用户预约的日期")
    private LocalDate reserveDate;

    @Schema(description = "[预约开始时间] 预约开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[预约开始时间] 预约开始时间")
    private LocalDateTime startTime;

    @Schema(description = "[预约结束时间] 预约结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[预约结束时间] 预约结束时间")
    private LocalDateTime endTime;

    @Schema(description = "[预约状态] 如：待核验/已确认/已使用/已取消/已过期", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("[预约状态] 如：待核验/已确认/已使用/已取消/已过期")
    private String status;

    @Schema(description = "[核验时间] 预约核验时间")
    @ExcelProperty("[核验时间] 预约核验时间")
    private LocalDateTime verifyTime;

    @Schema(description = "[核验人] 核验人员唯一标识")
    @ExcelProperty("[核验人] 核验人员唯一标识")
    private Long verifyBy;

    @Schema(description = "[取消时间] 预约取消时间")
    @ExcelProperty("[取消时间] 预约取消时间")
    private LocalDateTime cancelTime;

    @Schema(description = "[取消原因] 预约取消原因", example = "不好")
    @ExcelProperty("[取消原因] 预约取消原因")
    private String cancelReason;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注] 停车预约相关备注说明", example = "你说的对")
    @ExcelProperty("[备注] 停车预约相关备注说明")
    private String remark;

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

}
