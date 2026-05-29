package cn.iocoder.yudao.module.stationresource.vrv.utils.common.excel;

import cn.hutool.poi.excel.ExcelUtil;
import cn.idev.excel.write.handler.SheetWriteHandler;
import cn.idev.excel.write.metadata.holder.WriteSheetHolder;
import cn.idev.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.util.Map;

/**
 * Excel 导入模板下拉框写入处理器
 * <p>在生成导入模板时，为指定列添加下拉选择约束（DataValidation），
 * 通过创建隐藏的"字典sheet"存储下拉选项，再用公式引用实现。
 *
 * <pre>
 * 使用方式：
 *   Map<Integer, String[]> dropdownMap = new LinkedHashMap<>();
 *   dropdownMap.put(4, new String[]{"未生效", "已生效", "已禁用"});
 *   .registerWriteHandler(new ExcelDropdownWriteHandler(dropdownMap))
 * </pre>
 *
 * @author vrvliang
 * @version V1 2026-05-26 15:00
 */
public class ExcelDropdownWriteHandler implements SheetWriteHandler {

    /**
     * 下拉框生效的起始行（第1行=表头，第2行起=数据行，0-indexed 即 row 1 起效）
     */
    private static final int FIRST_ROW = 1;
    /**
     * 下拉框生效的结束行（默认覆盖前2000行）
     */
    private static final int LAST_ROW = 2000;
    /**
     * 隐藏的字典sheet名称
     */
    private static final String DICT_SHEET_NAME = "下拉选项";

    /**
     * key: 列索引（0-based），value: 该列的下拉选项数组
     */
    private final Map<Integer, String[]> dropdownMap;

    /**
     * @param dropdownMap 列索引 → 下拉选项数组 的映射（key 为 0-based 列号）
     */
    public ExcelDropdownWriteHandler(Map<Integer, String[]> dropdownMap) {
        this.dropdownMap = dropdownMap;
    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        if (dropdownMap == null || dropdownMap.isEmpty()) {
            return;
        }

        // 1. 获取 POI 操作对象
        DataValidationHelper helper = writeSheetHolder.getSheet().getDataValidationHelper();//helper：用来创建 “下拉验证规则”
        Workbook workbook = writeWorkbookHolder.getWorkbook();//workbook：整个 Excel 文件

        // 2. 创建隐藏的"下拉选项"sheet，每列存一个下拉数据源
        Sheet dictSheet = workbook.createSheet(DICT_SHEET_NAME);

        for (Map.Entry<Integer, String[]> entry : dropdownMap.entrySet()) {
            int colIndex = entry.getKey();
            String[] options = entry.getValue();

            // 2.1 将下拉选项逐行写入字典sheet的对应列
            for (int i = 0; i < options.length; i++) {
                Row row = dictSheet.getRow(i);
                if (row == null) {
                    row = dictSheet.createRow(i);
                }
                row.createCell(colIndex).setCellValue(options[i]);
            }

            // 2.2 为该列设置下拉验证
            setColumnValidation(writeSheetHolder, workbook, helper, colIndex, options.length);
        }

        // 3. 隐藏字典sheet（用户不可见）
        workbook.setSheetHidden(workbook.getSheetIndex(DICT_SHEET_NAME), true);
    }

    /**
     * 为指定列设置下拉选择验证
     *
     * @param writeSheetHolder sheet 持有者
     * @param workbook         工作簿
     * @param helper           验证助手
     * @param colIndex         列索引（0-based）
     * @param optionCount      选项数量
     */
    private void setColumnValidation(WriteSheetHolder writeSheetHolder, Workbook workbook,
                                      DataValidationHelper helper, int colIndex, int optionCount) {
        // 1. 创建名称引用指向字典sheet的该列数据
        Name name = workbook.createName();
        String colLetter = ExcelUtil.indexToColName(colIndex); // 列索引转字母，如 0→A, 1→B
        String refers = DICT_SHEET_NAME + "!$" + colLetter + "$1:$" + colLetter + "$" + optionCount;
        name.setNameName("dropdown" + colIndex);
        name.setRefersToFormula(refers);

        // 2. 创建公式约束（引用上面定义的名称）
        DataValidationConstraint constraint = helper.createFormulaListConstraint("dropdown" + colIndex);

        // 3. 设置约束生效范围（该列的第2行到第2001行）
        CellRangeAddressList rangeAddressList = new CellRangeAddressList(FIRST_ROW, LAST_ROW, colIndex, colIndex);
        DataValidation validation = helper.createValidation(constraint, rangeAddressList);

        // 4. 配置下拉框样式
        if (validation instanceof HSSFDataValidation) {
            validation.setSuppressDropDownArrow(false); // xls 格式显示下拉箭头
        } else {
            validation.setSuppressDropDownArrow(true);  // xlsx 格式显示下拉箭头
            validation.setShowErrorBox(true);
        }

        // 5. 阻止输入非下拉选项的值
        validation.setErrorStyle(DataValidation.ErrorStyle.STOP);
        validation.createErrorBox("输入错误", "请从下拉列表中选择，不支持手动输入！");

        // 6. 添加验证到 sheet
        writeSheetHolder.getSheet().addValidationData(validation);
    }

}
