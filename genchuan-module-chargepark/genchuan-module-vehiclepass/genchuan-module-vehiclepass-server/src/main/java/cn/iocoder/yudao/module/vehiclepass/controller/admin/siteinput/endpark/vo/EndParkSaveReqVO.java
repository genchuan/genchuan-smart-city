package cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 结束停车新增/修改 Request VO")
@Data
public class EndParkSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19031")
    private Long id;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车牌不能为空")
    private String plateNo;

    @Schema(description = "车位ID，关联车位表", requiredMode = Schema.RequiredMode.REQUIRED, example = "9244")
    @NotNull(message = "车位ID，关联车位表不能为空")
    private Long spaceId;

    @Schema(description = "结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "结束时间不能为空")
    private LocalDateTime endTime;

    @Schema(description = "缴费状态：待支付/已支付/已取消，关联字典end_park_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "缴费状态：待支付/已支付/已取消，关联字典end_park_status不能为空")
    private String status;

    @Schema(description = "片区ID，关联片区表", requiredMode = Schema.RequiredMode.REQUIRED, example = "5559")
    @NotNull(message = "片区ID，关联片区表不能为空")
    private Long areaId;

    @Schema(description = "操作人ID，关联芋道用户表system_user", example = "8424")
    private Long operatorId;

    @Schema(description = "订单编号")
    private String orderNo;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}