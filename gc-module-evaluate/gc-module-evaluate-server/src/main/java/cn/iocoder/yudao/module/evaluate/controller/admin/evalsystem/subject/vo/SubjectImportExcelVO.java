package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subject.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class SubjectImportExcelVO {

    @ExcelProperty("主体名称")
    @NotBlank(message = "主体名称不能为空")
    private String name;

    @ExcelProperty("主体编码")
    @NotBlank(message = "主体编码不能为空")
    private String code;

    @ExcelProperty("主体类型")
    @NotBlank(message = "主体类型不能为空")
    private String subjectTypeName; // 注意：这里存的是字典里的“名称”，不是ID

    @ExcelProperty("联系人")
    @NotBlank(message = "联系人不能为空")
    private String contactUserName; // 存的是用户的“用户名”

    @ExcelProperty("联系电话")
    private String contactPhone; // 这个可以通过联系人自动带出，也可以Excel里填

    @ExcelProperty("成员列表")
    private String memberUserNames; // 多个用户用逗号分隔，如 "张三,李四"

    @ExcelProperty("状态")
    @NotBlank(message = "状态不能为空")
    private String statusName; // 存的是状态字典的“名称”

    @ExcelProperty("创建人")
    private String createUserName; // 存的是创建人的“用户名”

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

    @ExcelProperty("变更日志")
    private String changeLog;
}