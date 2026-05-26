package cn.iocoder.yudao.module.usermerchant.controller.admin.creditmgmt.usercredit.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 用户信用分页 Request VO")
@Data
public class UserCreditPageReqVO extends PageParam {

    @Schema(description = "用户ID", example = "31349")
    private Long userId;

    @TableField(exist = false)
    @Schema(description = "用户姓名", example = "张三")
    private String nickname;

    @Schema(description = "信用分，默认100")
    private Integer creditScore;

    @Schema(description = "信用等级：优秀/良好/中等/较差/极差")
    private String creditLevel;

    @Schema(description = "评分规则编码")
    private String ruleCode;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间（系统字段）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

    @TableField(exist = false)
    @Schema(description = "用户ID列表（内部使用）")
    private List<Long> userIds;

}