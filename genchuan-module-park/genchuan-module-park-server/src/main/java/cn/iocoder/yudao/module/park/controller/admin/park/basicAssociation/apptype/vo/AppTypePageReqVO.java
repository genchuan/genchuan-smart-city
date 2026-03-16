package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.apptype.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 行业应用类别分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppTypePageReqVO extends PageParam {

    @Schema(description = "上级应用类别ID", example = "6589")
    private Long parentTypeId;

    @Schema(description = "唯一应用编码")
    private String appCode;

    @Schema(description = "应用名称", example = "张三")
    private String appName;

    @Schema(description = "所属业务域")
    private String bizDomain;

    @Schema(description = "核心功能描述")
    private String functionDesc;

    @Schema(description = "访问权限编码")
    private String accessPermCode;

    @Schema(description = "状态：启用/停用", example = "2")
    private String appStatus;

    @Schema(description = "业务备注", example = "你猜")
    private String appRemark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
