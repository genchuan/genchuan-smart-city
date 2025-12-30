package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkberthlock.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 泊位锁定记录新增/修改 Request VO")
@Data
public class ParkBerthLockSaveReqVO {

    @Schema(description = "主键，唯一标识", example = "29444")
    private Long id;

    @Schema(description = "关联预约ID", example = "18569")
    private Long reservationId;

    @Schema(description = "锁定的泊位唯一标识", example = "10041")
    private Long berthId;

    @Schema(description = "锁定开始时间")
    private LocalDateTime lockStartTime;

    @Schema(description = "锁定结束时间")
    private LocalDateTime lockEndTime;

    @Schema(description = "泊位锁定状态", example = "2")
    private String lockStatus;

    @Schema(description = "泊位解锁原因", example = "不香")
    private String unlockReason;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}
