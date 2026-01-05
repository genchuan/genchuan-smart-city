package cn.iocoder.yudao.module.industry.framework.util.lxs.importer;

import com.alibaba.excel.EasyExcel;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//导入工具类
public class ImportUtils {
    /**
     * 解析 Excel 文件成对象列表
     * @param file Excel 文件流
     * @param clazz DTO/DO 类型
     * @param <T> 泛型类型
     * @return Excel 数据列表
     */
    public static <T> List<T> parseExcel(InputStream file, Class<T> clazz) {
        // 解析 Excel
        List<T> dataList = EasyExcel.read(file)
                .head(clazz)
                .sheet()
                .doReadSync();
        return dataList;
    }

}
