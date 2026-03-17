package cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.add;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 企业整改记录新增/修改 Request VO")
@Data
public class AddEntRectifyRecordReqVO {
    @Schema(description = "[整改通知书ID] 关联park_rectify_notice.id，唯一", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "[整改通知书ID] 关联park_rectify_notice.id，唯一不能为空")
    private Long rectifyNoticeId;



    //==================================================================



    @Schema(description = "[唯一标识code]", example = "ENT1",hidden = true)
    private String uniCode;

    //由整改通知书获取
    @Schema(description = "[整改复审台账id]", hidden = true)
    private Long rectifyReviewId;

    //通过整改通知书获取
    @Schema(description = "[企业ID] 关联park_enterprise_info.id", hidden = true)
//    @NotNull(message = "[企业ID] 关联park_enterprise_info.id不能为空")
    private Long entId;

    //赋值未整改
    @Schema(description = "[整改状态] 如：未整改/整改中/已完成/整改不合格", hidden = true)
//    @NotEmpty(message = "[整改状态] 如：未整改/整改中/已完成/整改不合格不能为空")
    private String rectifyStatus;

}
