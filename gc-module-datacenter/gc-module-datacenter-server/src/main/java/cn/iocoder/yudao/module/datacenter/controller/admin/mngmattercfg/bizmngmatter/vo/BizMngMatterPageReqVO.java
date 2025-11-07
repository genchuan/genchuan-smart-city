package cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatter.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 管理事项信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BizMngMatterPageReqVO extends PageParam {

    @Schema(description = "事项ID，唯一编码，UUID生成", example = "22275")
    private String mngMatterId;

    @Schema(description = "事项标识码，格式6位行政码+2位大类码+3位小类码+5位顺序码，唯一")
    private String matterCode;

    @Schema(description = "事项名称，关联小类名称+位置，如“XX路私搭乱建”，关联管理事项小类表(gc_biz_mng_matter_minor)", example = "张三")
    private String matterName;

    @Schema(description = "关联管理事项小类ID，关联管理事项小类表(gc_biz_mng_matter_minor)", example = "1064")
    private String minorId;

    @Schema(description = "关联管理事项小类名称，与小类ID同步，不可修改，关联管理事项小类表(gc_biz_mng_matter_minor)", example = "王五")
    private String minorName;

    @Schema(description = "关联单元网格ID，关联网格信息表(biz_grid_info)", example = "30383")
    private String gridId;

    @Schema(description = "关联单元网格名称，与网格ID同步，关联网格信息表(biz_grid_info)", example = "赵六")
    private String gridName;

    @Schema(description = "事项状态，如待处置/处置中/已办结/已驳回，关联问题状态字典表(sys_dict_problem_status)", example = "2")
    private String matterStatus;

    @Schema(description = "事项等级，如一级/二级/三级，按影响范围判定")
    private String matterLevel;

    @Schema(description = "主管部门代码，关联主管部门信用代码，关联部门信息表(sys_org)")
    private String deptCode;

    @Schema(description = "主管部门名称，与部门代码同步，关联部门信息表(sys_org)", example = "李四")
    private String deptName;

    @Schema(description = "事发位置，详细位置描述，如“XX路与XX路交叉口东北侧”")
    private String incidentLocation;

    @Schema(description = "录入人账号，关联用户信息表(sys_user)")
    private String createUser;

    @Schema(description = "创建时间，系统生成，格式yyyy - MM - dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "修改人账号，关联用户信息表(sys_user)")
    private String updateUser;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}