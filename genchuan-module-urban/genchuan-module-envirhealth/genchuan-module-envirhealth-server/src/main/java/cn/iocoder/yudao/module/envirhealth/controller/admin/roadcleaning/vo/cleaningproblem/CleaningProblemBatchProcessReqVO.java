package cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.cleaningproblem;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/3/11 15:27
 */
@Schema(description = "环境卫生管理 - 道路清扫问题批量处理 Request VO")
@Data
public class CleaningProblemBatchProcessReqVO {

    @Schema(description = "问题ID列表", required = true)
    @NotEmpty(message = "问题ID列表不能为空")
    private List<Long> ids;

/*    @Schema(description = "处理类型", required = true, example = "assign/dispatch/status")
    @NotNull(message = "处理类型不能为空")
    private String processType;

    @Schema(description = "指派人员ID", example = "1024")
    private Long assignUserId;*/

    @Schema(description = "目标状态", example = "已办结")
    private String handleStatus;

}