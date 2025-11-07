package cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedcomponentminorconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 管理部件小类配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ManagedComponentMinorConfigRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7266")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "小类ID，唯一编码，采用UUID", example = "23206")
    @ExcelProperty("小类ID，唯一编码，采用UUID")
    private String minorId;

    @Schema(description = "关联的大类ID", example = "10001")
    @ExcelProperty("关联的大类ID")
    private String majorId;

    @Schema(description = "小类代码，001-999")
    @ExcelProperty("小类代码，001-999")
    private String minorCode;

    @Schema(description = "小类名称，如上水井盖/污水井盖等", example = "赵六")
    @ExcelProperty("小类名称，如上水井盖/污水井盖等")
    private String minorName;

    @Schema(description = "小类说明，描述具体定义及特征")
    @ExcelProperty("小类说明，描述具体定义及特征")
    private String minorDesc;

    @Schema(description = "主管部门统一社会信用代码")
    @ExcelProperty("主管部门统一社会信用代码")
    private String deptCode;

    @Schema(description = "主管部门全称", example = "李四")
    @ExcelProperty("主管部门全称")
    private String deptName;

    @Schema(description = "是否扩展类：0-标准类/1-扩展类")
    @ExcelProperty("是否扩展类：0-标准类/1-扩展类")
    private String isExtend;

    @Schema(description = "创建人ID")
    @ExcelProperty("创建人ID")
    private String createUser;

    @Schema(description = "创建时间，格式：yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("创建时间，格式：yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "更新人ID")
    @ExcelProperty("更新人ID")
    private String updateUser;

    @Schema(description = "扩展字段1")
    @ExcelProperty("扩展字段1")
    private String extField1;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}