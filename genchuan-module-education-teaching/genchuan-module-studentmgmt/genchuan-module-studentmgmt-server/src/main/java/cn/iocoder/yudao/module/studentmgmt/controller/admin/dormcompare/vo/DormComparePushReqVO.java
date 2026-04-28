
package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcompare.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 宿舍评比推送 Request VO")
@Data
public class DormComparePushReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1,2")
    private Long[] ids;

    @Schema(description = "推送时间，默认当前时间，格式时间戳。")
    private LocalDateTime pushTime;

}