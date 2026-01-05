package cn.iocoder.yudao.module.industry.controller.admin.importer;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import com.alibaba.excel.EasyExcel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.IMPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "工具 - 导入")
@RestController
@RequestMapping("/industry/util-import")
@Validated
public class importerController {

    @PostMapping(
            value = "/import-excel",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @Operation(summary = "导入测试 Excel")
    @PreAuthorize("@ss.hasPermission('park:order-escape:import')")
    @ApiAccessLog(operateType = IMPORT)
    public CommonResult<Object> importOrderEscapeExcel(
            @Parameter(description = "Excel 文件", required = true)
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        Map<String, Object> result = new HashMap<>();

        try {
            // 1. 将文件内容读入字节数组，以便多次读取
            byte[] fileBytes = file.getBytes();

            System.out.println("cs2026-01-05 16:30:46:fileBytes:"+fileBytes);
            // 2. 读取为importVO对象（主要逻辑）
            System.out.println("=== 读取为importVO对象 ===");
            List<importVO> importList = readExcelAsVO(fileBytes);

            result.put("importList", importList);
            result.put("success", true);
            result.put("message", "成功读取 " + importList.size() + " 条数据");

            // 3. 打印调试信息
            if (!importList.isEmpty()) {
                System.out.println("读取到的数据:");
                for (int i = 0; i < importList.size(); i++) {
                    importVO vo = importList.get(i);
                    System.out.println("第" + (i+1) + "行: id=" + vo.getId() + ", orderNo=" + vo.getOrderNo());
                }
            }

        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "读取失败: " + e.getMessage());
            result.put("error", e.toString());
            e.printStackTrace();
        }

        return success(result);
    }

    /**
     * 读取Excel为VO对象
     */
    private List<importVO> readExcelAsVO(byte[] fileBytes) throws IOException {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(fileBytes)) {
            // 方式1: 直接读取为VO
            return EasyExcel.read(inputStream)
                    .head(importVO.class)
                    .sheet()
                    .headRowNumber(1) // 第1行是表头（从0开始计数）
                    .doReadSync();
        }
    }

    /**
     * 读取Excel原始数据（用于调试）
     */
    private void debugExcelContent(byte[] fileBytes) throws IOException {
        System.out.println("=== 调试Excel内容 ===");

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(fileBytes)) {
            // 1. 读取表头（第0行）
            List<Map<Integer, String>> headerList = EasyExcel.read(inputStream)
                    .sheet()
                    .headRowNumber(0)
                    .doReadSync();

            if (!headerList.isEmpty()) {
                Map<Integer, String> headerRow = headerList.get(0);
                System.out.println("表头行: " + headerRow);
                System.out.println("表头大小: " + headerRow.size());

                // 打印表头每列
                for (Map.Entry<Integer, String> entry : headerRow.entrySet()) {
                    System.out.println("  列" + entry.getKey() + ": " + entry.getValue());
                }
            }

            // 2. 重置流，读取所有数据
            try (ByteArrayInputStream inputStream2 = new ByteArrayInputStream(fileBytes)) {
                List<Map<Integer, String>> allData = EasyExcel.read(inputStream2)
                        .sheet()
                        .headRowNumber(0)
                        .doReadSync();

                System.out.println("总行数: " + allData.size());

                // 打印前几行数据
                for (int i = 0; i < Math.min(5, allData.size()); i++) {
                    Map<Integer, String> row = allData.get(i);
                    System.out.println("第" + i + "行: " + row);

                    // 打印每列的类型和值
                    for (Map.Entry<Integer, String> entry : row.entrySet()) {
                        String value = entry.getValue();
                        System.out.println("  列" + entry.getKey() + " - 值: \"" + value +
                                "\" (长度: " + (value != null ? value.length() : 0) + ")");
                    }
                }
            }
        }
    }

    /**
     * 简单版本的导入方法
     */
    @PostMapping(
            value = "/import-simple",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @Operation(summary = "简单导入测试")
    @PreAuthorize("@ss.hasPermission('park:order-escape:import')")
    @ApiAccessLog(operateType = IMPORT)
    public CommonResult<List<importVO>> importSimple(
            @Parameter(description = "Excel 文件", required = true)
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        if (file.isEmpty()) {
            return success(Collections.emptyList());
        }

        // 最简单的方式：直接读取
        List<importVO> list = EasyExcel.read(file.getInputStream())
                .head(importVO.class)
                .sheet()
                .headRowNumber(1)
                .doReadSync();

        System.out.println("成功读取 " + list.size() + " 条数据");

        // 检查数据
        for (importVO vo : list) {
            System.out.println("id: " + vo.getId() + " (类型: " +
                    (vo.getId() != null ? vo.getId().getClass().getSimpleName() : "null") +
                    "), orderNo: " + vo.getOrderNo());
        }

        return success(list);
    }

    /**
     * 使用监听器读取（更可靠的方式）
     */
    @PostMapping(
            value = "/import-with-listener",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @Operation(summary = "使用监听器导入")
    @PreAuthorize("@ss.hasPermission('park:order-escape:import')")
    @ApiAccessLog(operateType = IMPORT)
    public CommonResult<Object> importWithListener(
            @Parameter(description = "Excel 文件", required = true)
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        Map<String, Object> result = new HashMap<>();
        List<importVO> dataList = new ArrayList<>();

        EasyExcel.read(file.getInputStream(), importVO.class, new com.alibaba.excel.read.listener.ReadListener<importVO>() {
            @Override
            public void invoke(importVO data, com.alibaba.excel.context.AnalysisContext context) {
                System.out.println("读取到一行: id=" + data.getId() + ", orderNo=" + data.getOrderNo());
                dataList.add(data);
            }

            @Override
            public void doAfterAllAnalysed(com.alibaba.excel.context.AnalysisContext context) {
                System.out.println("读取完成，共" + dataList.size() + "条数据");
            }

            @Override
            public void invokeHead(Map<Integer, com.alibaba.excel.metadata.data.ReadCellData<?>> headMap,
                                   com.alibaba.excel.context.AnalysisContext context) {
                System.out.println("表头信息:");
                headMap.forEach((index, cellData) -> {
                    System.out.println("  列" + index + ": " + cellData.getStringValue());
                });
            }
        }).sheet().doRead();

        result.put("data", dataList);
        result.put("count", dataList.size());
        result.put("success", true);

        return success(result);
    }
}
