package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.serviceconfig.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 话术管理分页 Request VO")
@Data
public class WordingMgmtPageReqVO extends PageParam {

    @Schema(description = "话术名称（支持模糊查询）")
    private String name;

    @Schema(description = "话术类型,关联字典 wording_mgmt_type", example = "快捷回复",
            allowableValues = {"快捷回复", "自动回复", "投诉回复"})
    private String type;

    @Schema(description = "状态,关联字典 wording_mgmt_status", example = "已生效",
            allowableValues = {"未生效", "已生效"})
    private String status;

}
