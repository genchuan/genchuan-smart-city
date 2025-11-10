package cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopflowcfg.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 联动流程配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CoopFlowCfgPageReqVO extends PageParam {

    @Schema(description = "配置ID，唯一编码，UUID生成", example = "4783")
    private String flowCfgId;

    @Schema(description = "配置名称，如“火灾救援联动流程配置”", example = "李四")
    private String flowCfgName;

    @Schema(description = "流程步骤，详细描述联动流程的各个步骤")
    private String flowSteps;

    @Schema(description = "创建人账号，关联用户信息表")
    private String createUser;

    @Schema(description = "创建时间，格式yyyy - MM - dd HH:mm:ss，系统自动生成")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人账号，关联用户信息表")
    private String updateUser;

}