package cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatterminor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 管理事项小类 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BizMngMatterMinorRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14291")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "管理事项小类ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "9537")
    @ExcelProperty("管理事项小类ID，唯一编码，UUID生成")
    private String mngMatterMinorId;

    @Schema(description = "所属大类ID，关联管理事项大类ID，关联管理事项大类表(gc_biz_mng_matter_major)", requiredMode = Schema.RequiredMode.REQUIRED, example = "27704")
    @ExcelProperty("所属大类ID，关联管理事项大类ID，关联管理事项大类表(gc_biz_mng_matter_major)")
    private String parentMajorId;

    @Schema(description = "所属大类名称，与所属大类ID同步，不可改，关联管理事项大类表(gc_biz_mng_matter_major)", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("所属大类名称，与所属大类ID同步，不可改，关联管理事项大类表(gc_biz_mng_matter_major)")
    private String parentMajorName;

    @Schema(description = "小类代码，3位字符，001 - 999，标准类001 - 079、扩展类080 - 999倒排，同一大类下唯一", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("小类代码，3位字符，001 - 999，标准类001 - 079、扩展类080 - 999倒排，同一大类下唯一")
    private String matterMinorCode;

    @Schema(description = "小类名称，如私搭乱建/垃圾乱堆乱放等，国标名，扩展类加“（自定义）”", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("小类名称，如私搭乱建/垃圾乱堆乱放等，国标名，扩展类加“（自定义）”")
    private String matterMinorName;

    @Schema(description = "小类说明，描述小类定义，如“私搭乱建：未经审批搭建”")
    @ExcelProperty("小类说明，描述小类定义，如“私搭乱建：未经审批搭建”")
    private String matterMinorDesc;

    @Schema(description = "主管部门代码，主管部门信用代码，关联部门信息表(sys_org)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主管部门代码，主管部门信用代码，关联部门信息表(sys_org)")
    private String deptCode;

    @Schema(description = "主管部门名称，与主管部门代码同步，关联部门信息表(sys_org)", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("主管部门名称，与主管部门代码同步，关联部门信息表(sys_org)")
    private String deptName;

    @Schema(description = "是否扩展类，0（标准类）/1（扩展类），按代码自动判断", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否扩展类，0（标准类）/1（扩展类），按代码自动判断")
    private String isExt;

    @Schema(description = "启用状态，1（启用）/0（禁用），默认继承大类状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("启用状态，1（启用）/0（禁用），默认继承大类状态")
    private String enableStatus;

    @Schema(description = "创建人，配置人账号，关联用户信息表(sys_user)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人，配置人账号，关联用户信息表(sys_user)")
    private String createUser;

    @Schema(description = "创建时间，系统生成，格式yyyy - MM - dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间，系统生成，格式yyyy - MM - dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "更新人，修改人账号，关联用户信息表(sys_user)")
    @ExcelProperty("更新人，修改人账号，关联用户信息表(sys_user)")
    private String updateUser;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}