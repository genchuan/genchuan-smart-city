package cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupcar.vo;

import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.FlexibleTimestampDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 集团车辆分页 Request VO")
@Data
public class GroupCarPageReqVO extends PageParam {

    @Schema(description = "所属集团ID，关联group_info.id", example = "14580")
    private Long groupId;

    @Schema(description = "车牌号码，唯一")
    private String plateNo;

    @Schema(description = "车牌颜色：蓝牌/黄牌/绿牌/黑牌/白牌")
    private String plateColor;

    @Schema(description = "车辆类型：小型车/大型车/新能源/其他", example = "2")
    private String carType;

    @Schema(description = "绑定时间")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    private LocalDateTime[] bindTime;

    @Schema(description = "绑定状态：待审核/已绑定/已解绑", example = "2")
    private String status;

    @Schema(description = "审核人ID，关联system_user.id", example = "21277")
    private Long auditorId;

    @Schema(description = "审核时间")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    private LocalDateTime[] auditTime;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}