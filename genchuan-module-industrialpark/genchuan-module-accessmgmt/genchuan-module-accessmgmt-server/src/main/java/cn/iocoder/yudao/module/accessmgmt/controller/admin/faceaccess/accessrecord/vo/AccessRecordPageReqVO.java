package cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.accessrecord.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 通行记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AccessRecordPageReqVO extends PageParam {

    @Schema(description = "人员姓名，支持模糊查询")
    private String userName;

    @Schema(description = "通行区域")
    private String accessArea;

    @Schema(description = "验证方式（人脸/刷卡）")
    private String verifyType;

    @Schema(description = "通行状态（正常通行/异常通行）")
    private String accessStatus;

    @Schema(description = "通行开始时间，格式时间戳")
    private Long startTime;

    @Schema(description = "通行结束时间，格式时间戳")
    private Long endTime;

}
