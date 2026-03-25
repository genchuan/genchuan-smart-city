package cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.issue;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class IssueReqVO {
    @Schema(description = "台账ID", required = true)
    private Long id;
}
