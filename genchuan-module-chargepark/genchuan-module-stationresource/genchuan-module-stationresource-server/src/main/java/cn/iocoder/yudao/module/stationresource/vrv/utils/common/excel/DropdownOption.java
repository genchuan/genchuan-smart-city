package cn.iocoder.yudao.module.stationresource.vrv.utils.common.excel;

/**
 * Excel 下拉选项：展示值 + 真值
 * <p>下拉框显示 label，导入时自动转换为 value。
 *
 * <pre>
 * 使用示例：
 *   DropdownOption opt = new DropdownOption("张三", 10001L);
 *   // 下拉显示"张三"，导入时自动存 10001
 * </pre>
 *
 * @author vrvliang
 * @version V1 2026-05-26 18:00
 */
public class DropdownOption {

    /**
     * 展示值（下拉框显示的文字，如"张三"）
     */
    private final String label;

    /**
     * 真值（实际存储的值，如 10001L）
     */
    private final Object value;

    public DropdownOption(String label, Object value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public Object getValue() {
        return value;
    }

}
