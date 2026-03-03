package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subject.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectImportRespVO {

    /**
     * 行号 (从 1 开始，方便用户定位 Excel)
     */
    private Integer lineNo;

    /**
     * 主体名称 (用于展示)
     */
    private String name;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 失败原因
     */
    private String errorMsg;
}