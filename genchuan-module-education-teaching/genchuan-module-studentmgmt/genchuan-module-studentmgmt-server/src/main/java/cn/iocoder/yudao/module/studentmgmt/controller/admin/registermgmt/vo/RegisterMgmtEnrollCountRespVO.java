package cn.iocoder.yudao.module.studentmgmt.controller.admin.registermgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 报名管理确认 response VO")
@Data
public class RegisterMgmtEnrollCountRespVO {
    @Schema(description = "近一周报名趋势数据")
    private List<EnrollCountVO> majorEnrollData;
}