package cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatter.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 管理事项信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BizMngMatterRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2169")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "事项ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "22275")
    @ExcelProperty("事项ID，唯一编码，UUID生成")
    private String mngMatterId;

    @Schema(description = "事项标识码，格式6位行政码+2位大类码+3位小类码+5位顺序码，唯一", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事项标识码，格式6位行政码+2位大类码+3位小类码+5位顺序码，唯一")
    private String matterCode;

    @Schema(description = "事项名称，关联小类名称+位置，如“XX路私搭乱建”，关联管理事项小类表(gc_biz_mng_matter_minor)", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("事项名称，关联小类名称+位置，如“XX路私搭乱建”，关联管理事项小类表(gc_biz_mng_matter_minor)")
    private String matterName;

    @Schema(description = "关联管理事项小类ID，关联管理事项小类表(gc_biz_mng_matter_minor)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1064")
    @ExcelProperty("关联管理事项小类ID，关联管理事项小类表(gc_biz_mng_matter_minor)")
    private String minorId;

    @Schema(description = "关联管理事项小类名称，与小类ID同步，不可修改，关联管理事项小类表(gc_biz_mng_matter_minor)", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("关联管理事项小类名称，与小类ID同步，不可修改，关联管理事项小类表(gc_biz_mng_matter_minor)")
    private String minorName;

    @Schema(description = "关联单元网格ID，关联网格信息表(biz_grid_info)", requiredMode = Schema.RequiredMode.REQUIRED, example = "30383")
    @ExcelProperty("关联单元网格ID，关联网格信息表(biz_grid_info)")
    private String gridId;

    @Schema(description = "关联单元网格名称，与网格ID同步，关联网格信息表(biz_grid_info)", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("关联单元网格名称，与网格ID同步，关联网格信息表(biz_grid_info)")
    private String gridName;

    @Schema(description = "事项状态，如待处置/处置中/已办结/已驳回，关联问题状态字典表(sys_dict_problem_status)", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("事项状态，如待处置/处置中/已办结/已驳回，关联问题状态字典表(sys_dict_problem_status)")
    private String matterStatus;

    @Schema(description = "事项等级，如一级/二级/三级，按影响范围判定")
    @ExcelProperty("事项等级，如一级/二级/三级，按影响范围判定")
    private String matterLevel;

    @Schema(description = "主管部门代码，关联主管部门信用代码，关联部门信息表(sys_org)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主管部门代码，关联主管部门信用代码，关联部门信息表(sys_org)")
    private String deptCode;

    @Schema(description = "主管部门名称，与部门代码同步，关联部门信息表(sys_org)", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("主管部门名称，与部门代码同步，关联部门信息表(sys_org)")
    private String deptName;

    @Schema(description = "事发位置，详细位置描述，如“XX路与XX路交叉口东北侧”")
    @ExcelProperty("事发位置，详细位置描述，如“XX路与XX路交叉口东北侧”")
    private String incidentLocation;

    @Schema(description = "录入人账号，关联用户信息表(sys_user)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("录入人账号，关联用户信息表(sys_user)")
    private String createUser;

    @Schema(description = "创建时间，系统生成，格式yyyy - MM - dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间，系统生成，格式yyyy - MM - dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "修改人账号，关联用户信息表(sys_user)")
    @ExcelProperty("修改人账号，关联用户信息表(sys_user)")
    private String updateUser;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}