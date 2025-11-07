package cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmattermajor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 管理事项大类 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BizMngMatterMajorRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20053")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "管理事项大类ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20451")
    @ExcelProperty("管理事项大类ID")
    private String mngMatterMajorId;

    @Schema(description = "大类代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("大类代码")
    private String matterMajorCode;

    @Schema(description = "大类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("大类名称")
    private String matterMajorName;

    @Schema(description = "大类说明")
    @ExcelProperty("大类说明")
    private String matterMajorDesc;

    @Schema(description = "排序序号")
    @ExcelProperty("排序序号")
    private Integer sortNum;

    @Schema(description = "启用状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("启用状态")
    private String enableStatus;

    @Schema(description = "创建人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人")
    private String createUser;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人")
    @ExcelProperty("更新人")
    private String updateUser;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}