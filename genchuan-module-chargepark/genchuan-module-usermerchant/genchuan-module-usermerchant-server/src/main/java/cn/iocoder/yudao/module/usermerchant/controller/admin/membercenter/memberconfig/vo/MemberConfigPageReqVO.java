package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 会员配置分页 Request VO")
@Data
public class MemberConfigPageReqVO extends PageParam {

    @Schema(description = "配置类型", example = "1")
    private String configType;

    @Schema(description = "权益内容（JSON或文本）")
    private String content;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "状态：0-未生效，1-已生效", example = "已生效")
    private Integer status;

    @Schema(description = "生效时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] effectiveTime;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}