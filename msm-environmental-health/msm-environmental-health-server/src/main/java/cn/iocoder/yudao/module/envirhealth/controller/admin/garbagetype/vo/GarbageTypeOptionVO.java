package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetype.vo;

import lombok.Data;

/**
 * 垃圾品类字典 下拉框VO
 * 适配前端label/value下拉框格式
 */
@Data
public class GarbageTypeOptionVO {
    /**
     * 下拉框显示标签（品类名称）
     */
    private String label;

    /**
     * 下拉框值（业务主键sysGarbageTypeId）
     */
    private String value;

}