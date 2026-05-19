package cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.paycheck.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 缴费核验分页 Request VO")
@Data
public class PayCheckPageReqVO extends PageParam {

    @Schema(description = "车牌")
    private String plateNo;

    @Schema(description = "停车费用")
    private BigDecimal parkFee;

    @Schema(description = "缴费状态：已缴清/欠费", example = "已缴清")
    private String status;

    @Schema(description = "核验时间，时间戳格式", example = "[\"1775011986\",\"1775098386\"]")
    private String[] checkTime;

    @Schema(description = "场站ID，关联场站表", example = "30722")
    private Long stationId;

    @Schema(description = "场站名称，支持模糊查询")
    private String stationName;

    @Schema(description = "核验人ID，关联system_user用户表", example = "7217")
    private Long checkUserId;

    @Schema(description = "核验结果")
    private String checkResult;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

}