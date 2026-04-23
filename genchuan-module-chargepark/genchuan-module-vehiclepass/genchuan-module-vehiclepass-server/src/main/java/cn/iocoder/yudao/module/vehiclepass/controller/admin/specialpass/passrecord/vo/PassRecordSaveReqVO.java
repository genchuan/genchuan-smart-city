package cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.passrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 放行记录新增/修改 Request VO")
@Data
public class PassRecordSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13009")
    private Long id;

    @Schema(description = "车牌")
    private String plateNo;

    @Schema(description = "放行原因：人工开闸 / 特殊车辆 / 其他，关联字典：pass_record_pass_reason", requiredMode = Schema.RequiredMode.REQUIRED, example = "不好")
    @NotEmpty(message = "放行原因：人工开闸 / 特殊车辆 / 其他，关联字典：pass_record_pass_reason不能为空")
    private String passReason;

    @Schema(description = "放行时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "放行时间不能为空")
    private LocalDateTime passTime;

    @Schema(description = "抓拍图片地址", example = "https://www.iocoder.cn")
    private String imageUrl;

    @Schema(description = "状态：正常记录 / 异常记录，关联字典：pass_record_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "状态：正常记录 / 异常记录，关联字典：pass_record_status不能为空")
    private String status;

    @Schema(description = "场站ID，关联场站表", requiredMode = Schema.RequiredMode.REQUIRED, example = "10614")
    @NotNull(message = "场站ID，关联场站表不能为空")
    private Long stationId;

    @Schema(description = "操作人ID，关联芋道用户表 system_user", example = "48")
    private Long operatorId;

    @Schema(description = "操作时间")
    private LocalDateTime operatorTime;

    @Schema(description = "核查结果")
    private String checkResult;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}