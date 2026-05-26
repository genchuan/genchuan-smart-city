package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberpoint.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 会员积分分页 Request VO")
@Data
public class MemberPointPageReqVO extends PageParam {

    @Schema(description = "用户编号", example = "9042")
    private Long userId;

    @Schema(description = "用户昵称（模糊搜索）")
    @TableField(exist = false)
    private String nickname;

    @Schema(description = "用户编号组")
    @TableField(exist = false)
    private List<Long> userIds;

    @Schema(description = "变动积分")
    private Integer changeAmount;

    @Schema(description = "变动后的总积分")
    private Integer totalPoint;

    @Schema(description = "变动类型：1-获取，2-消耗", example = "1")
    private Integer changeType;

    @Schema(description = "变动原因", example = "不香")
    private String changeReason;

    @Schema(description = "记录状态：0-异常，1-正常", example = "1")
    private Integer status;

    @Schema(description = "核查结果")
    private String checkResult;

    @Schema(description = "核查时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] checkTime;

    @Schema(description = "核查人")
    private String checkBy;

    @Schema(description = "业务编码", example = "25120")
    private String bizId;

    @Schema(description = "业务类型", example = "1")
    private Integer bizType;

    @Schema(description = "积分标题")
    private String title;

    @Schema(description = "积分描述", example = "随便")
    private String description;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "变动时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}