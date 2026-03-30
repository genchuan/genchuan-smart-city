package cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.data.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 上报数据新增/修改 Request VO")
@Data
public class DataSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10836")
    private Long id;

    @Schema(description = "上报数据ID（UUID）", example = "4559")
    private String reportId;

    @Schema(description = "关联评价任务ID（关联eval_task.task_id）", example = "13591")
    private String taskId;

    @Schema(description = "评价对象ID（关联eval_object.object_id）", example = "8848")
    private String objectId;

    @Schema(description = "指标项ID（关联eval_index_item.item_id）", example = "18521")
    private String indexId;

    @Schema(description = "数据值")
    private String dataValue;

    @Schema(description = "上报时间")
    private LocalDateTime reportTime;

    @Schema(description = "上报人ID（关联sys_user.user_id）")
    private String reportBy;

    @Schema(description = "数据状态ID（关联sys_data_status.status_id）", example = "14506")
    private String dataStatusId;

    @Schema(description = "校验结果ID（关联sys_verify_result.result_id）", example = "13482")
    private String verifyResultId;

    @Schema(description = "错误原因", example = "不好")
    private String errorReason;

    @Schema(description = "处理时间")
    private LocalDateTime processTime;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}