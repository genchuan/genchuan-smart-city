package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 救援信息分页 Request VO")
@Data
public class RescueInfoPageReqVO extends PageParam {

    @Schema(description = "用户 ID", example = "1001")
    private Long userId;

    @Schema(description = "救援类型，关联字典 rescue_info_rescue_type", example = "道路救援")
    private String rescueType;

    @Schema(description = "救援状态，关联字典 rescue_info_status", example = "待派发")
    private String status;

    @Schema(description = "救援位置（支持模糊查询）", example = "福建省泉州市")
    private String location;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
