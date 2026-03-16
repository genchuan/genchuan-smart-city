package cn.iocoder.yudao.module.data.service.sceneinstance;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.data.controller.admin.sceneinstance.vo.SceneInstanceImportVO;
import cn.iocoder.yudao.module.data.controller.admin.sceneinstance.vo.SceneInstancePageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.sceneinstance.vo.SceneInstanceSaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.sceneinstance.vo.SceneInstanceUpdateStatusReqVO;
import cn.iocoder.yudao.module.data.dal.dataobject.sceneinstance.SceneInstanceDO;
import cn.iocoder.yudao.module.data.dal.mysql.sceneinstance.SceneInstanceMapper;
import cn.iocoder.yudao.module.data.service.scenecategory.SceneCategoryService;
import com.alibaba.nacos.common.utils.CollectionUtils;
import jakarta.annotation.Resource;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.data.enums.ErrorCodeConstants.*;

/**
 * 应用场景实例 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class SceneInstanceServiceImpl implements SceneInstanceService {

    @Resource
    private SceneCategoryService sceneCategoryService; // 假设有应用场景分类的服务

    @Resource
    private SceneInstanceMapper sceneInstanceMapper;

    @Override
    public Long createSceneInstance(SceneInstanceSaveReqVO createReqVO) {
        // 插入
        SceneInstanceDO sceneInstance = BeanUtils.toBean(createReqVO, SceneInstanceDO.class);
        sceneInstanceMapper.insert(sceneInstance);
        // 返回
        return sceneInstance.getId();
    }

    @Override
    public void updateSceneInstance(SceneInstanceSaveReqVO updateReqVO) {
        // 校验存在
        validateSceneInstanceExists(updateReqVO.getId());
        // 更新
        SceneInstanceDO updateObj = BeanUtils.toBean(updateReqVO, SceneInstanceDO.class);
        sceneInstanceMapper.updateById(updateObj);
    }

    @Override
    public void deleteSceneInstance(Long id) {
        // 校验存在
        validateSceneInstanceExists(id);
        // 删除
        sceneInstanceMapper.deleteById(id);
    }

    private void validateSceneInstanceExists(Long id) {
        if (sceneInstanceMapper.selectById(id) == null) {
            throw exception(SCENE_INSTANCE_NOT_EXISTS);
        }
    }

    @Override
    public SceneInstanceDO getSceneInstance(Long id) {
        return sceneInstanceMapper.selectById(id);
    }

    @Override
    public PageResult<SceneInstanceDO> getSceneInstancePage(SceneInstancePageReqVO pageReqVO) {
        // 处理树形查询参数
        String treeParentId = pageReqVO.getTreeParentId();
        if (treeParentId != null && !treeParentId.trim().isEmpty()) {
            // 获取该节点及其所有子节点的ID
            // 注意：这里假设sceneCategoryService有getSubCategoryIds方法
            // 如果不存在，需要先实现，可以参考EventCategoryService的实现
            List<Long> subCategoryIds = sceneCategoryService.getSubCategoryIds(
                    treeParentId,
                    pageReqVO.getIncludeSelf() != null ? pageReqVO.getIncludeSelf() : true
            );

            if (CollectionUtils.isEmpty(subCategoryIds)) {
                // 如果没有找到任何节点，返回空结果
                return new PageResult<>(Collections.emptyList(), 0L);
            }

            // 将Long类型的ID转换为String类型（因为categoryId是String类型）
            List<String> categoryIdStrs = subCategoryIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());

            return sceneInstanceMapper.selectPageByCategoryIds(pageReqVO, categoryIdStrs);
        }

        return sceneInstanceMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateSceneInstanceStatusBatch(SceneInstanceUpdateStatusReqVO updateReqVO) {
        // 1. 参数校验
        if (updateReqVO.getIds() == null || updateReqVO.getIds().isEmpty()) {
            // 这里需要使用对应的错误码，如果不存在需要定义
            throw exception(SCENE_INSTANCE_IDS_EMPTY);
        }

        // 2. 处理状态时间
        LocalDateTime statusTime = null;
        if (updateReqVO.getStatusTime() != null) {
            // 将时间戳转换为LocalDateTime
            statusTime = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(updateReqVO.getStatusTime()),
                    ZoneId.systemDefault()
            );
        } else {
            // 如果没有传入状态时间，使用当前时间
            statusTime = LocalDateTime.now();
        }

        // 3. 查询出所有存在的实例，确保操作的ID都是有效的
        List<SceneInstanceDO> existInstanceList = sceneInstanceMapper.selectBatchIds(updateReqVO.getIds());
        if (existInstanceList.size() != updateReqVO.getIds().size()) {
            // 如果查询出的记录数不等于传入的ID数，说明有ID不存在
            List<Long> existIds = existInstanceList.stream()
                    .map(SceneInstanceDO::getId)
                    .collect(Collectors.toList());
            updateReqVO.getIds().removeAll(existIds);
            // 这里需要使用对应的错误码
            throw exception(SCENE_INSTANCE_NOT_EXISTS, "ID列表中存在不存在的实例ID: " + updateReqVO.getIds());
        }

        // 4. 批量更新状态
        int rows = 0;
        for (SceneInstanceDO instance : existInstanceList) {
            SceneInstanceDO updateObj = new SceneInstanceDO();
            updateObj.setId(instance.getId());
            updateObj.setStatus(updateReqVO.getStatus());
            // 设置状态时间
            updateObj.setStatusTime(statusTime);
            rows += sceneInstanceMapper.updateById(updateObj);
        }

        // 5. 返回更新成功的行数
        return rows;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String importSceneInstanceExcel(MultipartFile file) throws IOException {
        // 1. 基础校验
        if (file.isEmpty()) {
            throw exception(SCENE_INSTANCE_IMPORT_FILE_EMPTY);
        }

        // 手动解析Excel
        List<SceneInstanceImportVO> importDataList = new ArrayList<>();
        int successCount = 0;
        StringBuilder errorMsg = new StringBuilder();

        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook;
            try {
                // 尝试创建XSSFWorkbook（.xlsx格式）
                workbook = new XSSFWorkbook(inputStream);
            } catch (Exception e) {
                // 如果失败，尝试创建HSSFWorkbook（.xls格式）
                try {
                    inputStream.reset();
                    workbook = new HSSFWorkbook(inputStream);
                } catch (Exception ex) {
                    throw new RuntimeException("无法解析Excel文件，可能是格式不支持");
                }
            }

            Sheet sheet = workbook.getSheetAt(0); // 获取第一个工作表
            // 读取表头
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new RuntimeException("Excel文件没有表头行");
            }

            // 验证表头格式（可选）
            if (!validateExcelHeader(headerRow)) {
                throw new RuntimeException("Excel表头格式不正确，请使用标准模板");
            }

            // 读取数据行
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                try {
                    SceneInstanceImportVO importVO = new SceneInstanceImportVO();

                    // 手动映射单元格到对象属性
                    importVO.setSceneName(getCellStringValue(row.getCell(0)));      // 场景名称
                    importVO.setSceneCode(getCellStringValue(row.getCell(1)));      // 场景编码
                    importVO.setCategoryName(getCellStringValue(row.getCell(2)));    // 关联分类
                    importVO.setGridName(getCellStringValue(row.getCell(3)));        // 所在网格
                    importVO.setFacilities(getCellStringValue(row.getCell(4)));      // 涉及设施
                    importVO.setManager(getCellStringValue(row.getCell(5)));         // 负责人
                    importVO.setProcess(getCellStringValue(row.getCell(6)));        // 处置流程
                    importVO.setStatus(getCellStringValue(row.getCell(7)));          // 状态
                    importVO.setCreator(getCellStringValue(row.getCell(8)));         // 创建人

                    // 处理创建时间
                    Cell createTimeCell = row.getCell(9);
                    if (createTimeCell != null) {
                        if (createTimeCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(createTimeCell)) {
                            importVO.setCreateTime(createTimeCell.getDateCellValue().toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDateTime());
                        } else if (createTimeCell.getCellType() == CellType.STRING) {
                            String dateStr = createTimeCell.getStringCellValue();
                            if (dateStr != null && !dateStr.trim().isEmpty()) {
                                try {
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                    importVO.setCreateTime(LocalDateTime.parse(dateStr.trim(), formatter));
                                } catch (Exception e) {
                                    // 解析失败，设置为当前时间
                                    importVO.setCreateTime(LocalDateTime.now());
                                    errorMsg.append("第").append(i + 1).append("行创建时间格式错误，已设置为当前时间; ");
                                }
                            }
                        }
                    }

                    // 处理关联部件数
                    Cell partCountCell = row.getCell(10);
                    if (partCountCell != null) {
                        if (partCountCell.getCellType() == CellType.NUMERIC) {
                            importVO.setPartCount((int) partCountCell.getNumericCellValue());
                        } else if (partCountCell.getCellType() == CellType.STRING) {
                            try {
                                importVO.setPartCount(Integer.parseInt(partCountCell.getStringCellValue().trim()));
                            } catch (NumberFormatException e) {
                                importVO.setPartCount(0);
                            }
                        }
                    }

                    // 处理关联事件数
                    Cell eventCountCell = row.getCell(11);
                    if (eventCountCell != null) {
                        if (eventCountCell.getCellType() == CellType.NUMERIC) {
                            importVO.setEventCount((int) eventCountCell.getNumericCellValue());
                        } else if (eventCountCell.getCellType() == CellType.STRING) {
                            try {
                                importVO.setEventCount(Integer.parseInt(eventCountCell.getStringCellValue().trim()));
                            } catch (NumberFormatException e) {
                                importVO.setEventCount(0);
                            }
                        }
                    }

                    // 处理启用/停用时间
                    Cell statusTimeCell = row.getCell(12);
                    if (statusTimeCell != null) {
                        if (statusTimeCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(statusTimeCell)) {
                            importVO.setStatusTime(statusTimeCell.getDateCellValue().toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDateTime());
                        } else if (statusTimeCell.getCellType() == CellType.STRING) {
                            String dateStr = statusTimeCell.getStringCellValue();
                            if (dateStr != null && !dateStr.trim().isEmpty()) {
                                try {
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                    importVO.setStatusTime(LocalDateTime.parse(dateStr.trim(), formatter));
                                } catch (Exception e) {
                                    // 解析失败，保持null
                                    importVO.setStatusTime(null);
                                }
                            }
                        }
                    }

                    importDataList.add(importVO);
                } catch (Exception e) {
                    errorMsg.append("第").append(i + 1).append("行解析失败: ").append(e.getMessage()).append("; ");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("手动解析Excel失败: " + e.getMessage());
        }

        if (CollectionUtils.isEmpty(importDataList)) {
            throw exception(SCENE_INSTANCE_IMPORT_LIST_EMPTY);
        }

        // 处理导入的数据
        for (int i = 0; i < importDataList.size(); i++) {
            SceneInstanceImportVO importVO = importDataList.get(i);
            int rowNumber = i + 2; // Excel行号，通常第1行是表头

            try {
                // 验证必填字段
                if (importVO.getSceneName() == null || importVO.getSceneName().trim().isEmpty()) {
                    throw new RuntimeException("场景名称不能为空");
                }

                if (importVO.getSceneCode() == null || importVO.getSceneCode().trim().isEmpty()) {
                    throw new RuntimeException("场景编码不能为空");
                }

                if (importVO.getCategoryName() == null || importVO.getCategoryName().trim().isEmpty()) {
                    throw new RuntimeException("关联分类不能为空");
                }

                // 转换为保存对象
                SceneInstanceSaveReqVO saveReqVO = new SceneInstanceSaveReqVO();

                // 设置字段
                saveReqVO.setSceneName(importVO.getSceneName());
                saveReqVO.setSceneCode(importVO.getSceneCode());
                saveReqVO.setCategoryName(importVO.getCategoryName());
                saveReqVO.setGridName(importVO.getGridName());
                saveReqVO.setFacilities(importVO.getFacilities());
                saveReqVO.setManager(importVO.getManager());
                saveReqVO.setProcess(importVO.getProcess());
                saveReqVO.setStatus(importVO.getStatus());
                saveReqVO.setCreator(importVO.getCreator());
//                saveReqVO.setCreateTime(importVO.getCreateTime());
                saveReqVO.setPartCount(importVO.getPartCount());
                saveReqVO.setEventCount(importVO.getEventCount());
                saveReqVO.setStatusTime(importVO.getStatusTime());

                // 这里可以添加其他字段的逻辑，比如根据名称查询ID
                // saveReqVO.setCategoryId(getCategoryIdByName(importVO.getCategoryName()));
                // saveReqVO.setGridIds(getGridIdsByNames(importVO.getGridName()));

                // 检查场景编码是否已存在
                SceneInstanceDO existInstance = sceneInstanceMapper.selectOne(new LambdaQueryWrapperX<SceneInstanceDO>()
                        .eq(SceneInstanceDO::getSceneCode, saveReqVO.getSceneCode()));
                if (existInstance != null) {
                    // 如果存在，则执行更新操作
                    saveReqVO.setId(existInstance.getId());
                    updateSceneInstance(saveReqVO);
                } else {
                    // 如果不存在，则执行新增操作
                    createSceneInstance(saveReqVO);
                }
                successCount++;
            } catch (Exception e) {
                errorMsg.append("第").append(rowNumber).append("行导入失败: ").append(e.getMessage()).append("; ");
            }
        }

        // 构造返回信息
        String message = String.format("成功导入 %d 条数据", successCount);
        if (errorMsg.length() > 0) {
            message += String.format("，失败 %d 条。失败详情：%s",
                    importDataList.size() - successCount, errorMsg.toString());
        }
        return message;
    }

    // 辅助方法：获取单元格字符串值
    private String getCellStringValue(Cell cell) {
        if (cell == null) return null;

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    // 如果是整数，去掉小数部分
                    double value = cell.getNumericCellValue();
                    if (value == (int) value) {
                        return String.valueOf((int) value);
                    } else {
                        return String.valueOf(value);
                    }
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                try {
                    return cell.getStringCellValue();
                } catch (Exception e) {
                    try {
                        return String.valueOf(cell.getNumericCellValue());
                    } catch (Exception ex) {
                        return null;
                    }
                }
            default:
                return null;
        }
    }

    // 辅助方法：验证Excel表头（可选）
    private boolean validateExcelHeader(Row headerRow) {
        String[] expectedHeaders = {
                "场景名称", "场景编码", "关联分类", "所在网格", "涉及设施", "负责人",
                "处置流程", "状态", "创建人", "创建时间", "关联部件数", "关联事件数", "启用/停用时间"
        };

        if (headerRow.getPhysicalNumberOfCells() != expectedHeaders.length) {
            return false;
        }

        for (int i = 0; i < expectedHeaders.length; i++) {
            Cell cell = headerRow.getCell(i);
            if (cell == null || !expectedHeaders[i].equals(cell.getStringCellValue().trim())) {
                return false;
            }
        }

        return true;
    }


}