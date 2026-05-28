package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberlevel.vo;

import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.FlexibleTimestampDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 会员等级分页 Request VO")
@Data
public class MemberLevelPageReqVO extends PageParam {

    @Schema(description = "等级名称", example = "赵六")
    private String name;

    @Schema(description = "等级数值（排序用）")
    private Integer levelValue;

    @Schema(description = "升级条件（JSON）")
    private String upgradeCondition;

    @Schema(description = "权益内容（JSON）")
    private String benefits;

    @Schema(description = "状态：0-未生效，1-已生效", example = "1")
    private Integer status;

    @Schema(description = "生效时间")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    private LocalDateTime[] effectiveTime;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "创建时间")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    private LocalDateTime[] createTime;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "更新时间")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    private LocalDateTime[] updateTime;

}