package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo;

import cn.iocoder.yudao.framework.dict.validation.InDict;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 救援信息新增/修改 Request VO")
@Data
public class RescueInfoSaveReqVO {

    @Schema(description = "主键 ID", example = "1024")
    private Long id;

    @Schema(description = "用户 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotNull(message = "用户 ID 不能为空")
    private Long userId;

    @Schema(description = "救援位置坐标（格式：经度,纬度，前端地图 SDK 选点时填充）",
            requiredMode = Schema.RequiredMode.REQUIRED, example = "118.675324,24.896541")
    @NotBlank(message = "救援位置不能为空")
    private String location;

    @Schema(description = "救援位置汉字地址（前端地图 SDK 选点时回传，供列表页展示）",
            example = "福建省泉州市丰泽区津淮街 123 号")
    private String locationName;

    @Schema(description = "救援类型,关联字典 rescue_info_rescue_type",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "道路救援",
            allowableValues = {"道路救援", "充电故障救援", "停车故障救援"})
    @NotBlank(message = "救援类型不能为空")
    @InDict(type = "rescue_info_rescue_type")
    private String rescueType;

    @Schema(description = "派发时间")
    private LocalDateTime dispatchTime;

    @Schema(description = "救援状态,关联字典 rescue_info_status(创建时由后端默认为 待派发)",
            example = "待派发",
            allowableValues = {"待派发", "待认领", "处理中", "已完成"})
    @InDict(type = "rescue_info_status")
    private String status;

    @Schema(description = "救援人员 ID")
    private Long rescueUserId;

    @Schema(description = "完成时间")
    private LocalDateTime finishTime;

    @Schema(description = "处理时长（秒）")
    private Integer handleDuration;

    @Schema(description = "评价得分，1-5 分", example = "5")
    @Min(value = 1, message = "评分最小为 1")
    @Max(value = 5, message = "评分最大为 5")
    private Integer score;

    @Schema(description = "归档状态,关联字典 rescue_info_archive_status",
            example = "未归档",
            allowableValues = {"未归档", "已归档"})
    @InDict(type = "rescue_info_archive_status")
    private String archiveStatus;

    @Schema(description = "派发备注")
    private String dispatchRemark;

    @Schema(description = "转派理由")
    private String transferReason;

    @Schema(description = "救援进度")
    private String progress;

    @Schema(description = "现场照片 URL")
    private String photo;

    @Schema(description = "评价内容")
    private String evaluateContent;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}
