package cn.iocoder.yudao.module.envirhealth.util.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/27 17:12
 */
@Data
@Schema(description = "名称-数值键值对VO")
public class NameValueVO {

    @Schema(description = "名称", example = "深圳市")
    private String name;

    @Schema(description = "数值", example = "1")
    private Long value;

    // 快捷构造方法
    public NameValueVO(String name, Long value) {
        this.name = name;
        this.value = value;
    }
}