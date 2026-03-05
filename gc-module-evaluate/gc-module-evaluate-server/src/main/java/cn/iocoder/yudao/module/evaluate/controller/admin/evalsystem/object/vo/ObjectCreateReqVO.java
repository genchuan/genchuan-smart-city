package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 评价对象创建 Request VO")
@Data
public class ObjectCreateReqVO {

    // ========== 严格匹配Excel表头，字段名备注映射目标 ==========
    @ExcelProperty("对象名称") // Excel表头：对象名称
    private String objectName; // 对应VO的name

    @ExcelProperty("对象编码") // Excel表头：对象编码
    private String objectCode; // 对应VO的code

    @ExcelProperty("所属区域") // Excel表头：所属区域（名称，如“北京市”）
    private String areaName; // 需映射到sys_area.area_code

    @ExcelProperty("对象类型") // Excel表头：对象类型（名称，如“国有企业”）
    private String objectTypeName; // 需映射到sys_object_type.type_id

    @ExcelProperty("负责人") // Excel表头：负责人（名称，如“张三”）
    private String managerName; // 需映射到sys_user.user_id

    @ExcelProperty("联系电话") // Excel表头：联系电话
    private String managerPhone; // 对应VO的managerPhone

    @ExcelProperty("关联网格/状态ID") // Excel表头：关联网格/状态ID（名称，如“XX网格”）
    private String relatedName; // 需映射到related_id
}
