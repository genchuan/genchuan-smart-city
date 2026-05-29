package cn.iocoder.yudao.module.stationresource.vrv.utils.common.excel;

import java.lang.annotation.*;

/**
 * Excel 导入模板下拉选择框注解
 * <p>标注在实体类字段上，生成导入模板时该列会自动添加下拉选择约束，
 * 用户只能从预设选项中选择，避免手动输入错误。
 *
 * <pre>
 * 使用示例：
 *   @ExcelDropdown(options = {"未生效", "已生效", "已禁用"})
 *   @Schema(description = "[状态]", example = "未生效")
 *   private String status;
 * </pre>
 *
 * @author vrvliang
 * @version V1 2026-05-26 15:00
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExcelDropdown {

    /**
     * @return 下拉选项列表（必填，至少2个选项）
     */
    String[] options();

}
