package cn.iocoder.yudao.module.evaluate.controller.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectOptionRespVO {
    // value：下拉框选中后传给后端的值（存库的字段）
    // 注意：如果是区域，value是String类型的area_code；如果是类型/负责人，value是Long类型的id
    private Object value;
    // label：下拉框显示给用户看的文字
    private String label;
}