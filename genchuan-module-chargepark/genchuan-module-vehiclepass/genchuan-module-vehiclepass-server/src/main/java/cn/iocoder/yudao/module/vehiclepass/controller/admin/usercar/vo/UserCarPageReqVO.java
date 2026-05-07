package cn.iocoder.yudao.module.vehiclepass.controller.admin.usercar.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 用户车辆分页 Request VO")
@Data
public class UserCarPageReqVO extends PageParam {

    @Schema(description = "用户ID", example = "1")
    private Long userId;

    @Schema(description = "车牌号码", example = "闽C12345")
    private String plateNo;

    @Schema(description = "车牌颜色：蓝牌/黄牌/绿牌/黑牌/白牌", example = "蓝牌")
    private String plateColor;

    @Schema(description = "车辆类型：小型车/大型车/新能源/其他", example = "小型车")
    private String carType;

    @Schema(description = "绑定状态：待审核/已绑定/已解绑", example = "已绑定")
    private String status;

    @Schema(description = "绑定时间范围")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bindTime;

    @Schema(description = "创建时间范围")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
