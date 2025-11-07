package cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatterext.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 管理事项扩展管理事项配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BizMngMatterExtRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19002")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "扩展ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "29157")
    @ExcelProperty("扩展ID，唯一编码，UUID生成")
    private String mngMatterExtId;

    @Schema(description = "归属大类ID，无则归“其他管理事项”，关联管理事项大类ID，关联管理事项大类表(gc_biz_mng_matter_major)", requiredMode = Schema.RequiredMode.REQUIRED, example = "17714")
    @ExcelProperty("归属大类ID，无则归“其他管理事项”，关联管理事项大类ID，关联管理事项大类表(gc_biz_mng_matter_major)")
    private String matterMajorId;

    @Schema(description = "归属大类名称，与大类ID同步，关联管理事项大类表(gc_biz_mng_matter_major)", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("归属大类名称，与大类ID同步，关联管理事项大类表(gc_biz_mng_matter_major)")
    private String matterMajorName;

    @Schema(description = "扩展小类代码，080 - 999，倒排编码，避免与标准类冲突", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("扩展小类代码，080 - 999，倒排编码，避免与标准类冲突")
    private String extMinorCode;

    @Schema(description = "扩展小类名称，名称加“(自定义)”，如“共享单车乱停放(自定义)”", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("扩展小类名称，名称加“(自定义)”，如“共享单车乱停放(自定义)”")
    private String extMinorName;

    @Schema(description = "扩展小类说明，描述用途，如“共享单车未停指定区域，影响市容”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("扩展小类说明，描述用途，如“共享单车未停指定区域，影响市容”")
    private String extMinorDesc;

    @Schema(description = "建议主管部门代码，建议主管部门信用代码，关联部门信息表(sys_org)")
    @ExcelProperty("建议主管部门代码，建议主管部门信用代码，关联部门信息表(sys_org)")
    private String suggestDeptCode;

    @Schema(description = "建议主管部门名称，与部门代码同步，关联部门信息表(sys_org)", example = "李四")
    @ExcelProperty("建议主管部门名称，与部门代码同步，关联部门信息表(sys_org)")
    private String suggestDeptName;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}