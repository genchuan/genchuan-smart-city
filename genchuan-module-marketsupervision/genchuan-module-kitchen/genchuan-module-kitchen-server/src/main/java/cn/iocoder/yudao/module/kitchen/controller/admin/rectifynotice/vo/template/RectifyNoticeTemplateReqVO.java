package cn.iocoder.yudao.module.kitchen.controller.admin.rectifynotice.vo.template;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RectifyNoticeTemplateReqVO {
    @Schema(description = "[下发时间]", requiredMode = Schema.RequiredMode.REQUIRED, example = "1773628162000")
    @NotNull(message = "[下发时间] 不能为空")
    private LocalDateTime issueTime;

    @Schema(description = "[截止时间]", requiredMode = Schema.RequiredMode.REQUIRED, example = "2026-05-16")
    @NotNull(message = "[截止时间] 不能为空")
    private LocalDate deadline;

    @Schema(description = "[企业名称]", requiredMode = Schema.RequiredMode.REQUIRED, example = "福头企业")
    @NotEmpty(message = "[企业名称] 不能为空")
    private String entName;

    @Schema(description = "[违规原因]", requiredMode = Schema.RequiredMode.REQUIRED, example = "福头企业")
    @NotEmpty(message = "[违规原因] 不能为空")
    private String illegalTypeName;

    @Schema(description = "[联系人]", requiredMode = Schema.RequiredMode.REQUIRED, example = "福头企业")
    @NotEmpty(message = "[联系人] 不能为空")
    private String contactPerson;

    @Schema(description = "[联系电话]", requiredMode = Schema.RequiredMode.REQUIRED, example = "福头企业")
    @NotEmpty(message = "[联系电话] 不能为空")
    private String contactPhone;

    @Schema(description = "[联系地址]", requiredMode = Schema.RequiredMode.REQUIRED, example = "福头企业")
    @NotEmpty(message = "[联系地址] 不能为空")
    private String contactAddress;

    @Schema(description = "[市场监督管理局名称]", requiredMode = Schema.RequiredMode.REQUIRED, hidden = true)
    private String marketSupervisionBureauName = "泉州市市场监督管理局";

    @Schema(description = "[监督的市名]", requiredMode = Schema.RequiredMode.REQUIRED, hidden = true)
    private String regionAbbreviation = "泉州";

    @Schema(description = "[行政复议机关]", requiredMode = Schema.RequiredMode.REQUIRED, hidden = true)
    private String reconsiderationAuthority = "泉州市人民政府";

    @Schema(description = "[行政诉讼法院]", requiredMode = Schema.RequiredMode.REQUIRED, hidden = true)
    private String litigationCourt = "泉州市鲤城区人民法院";
}
