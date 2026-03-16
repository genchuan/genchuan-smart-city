package cn.iocoder.yudao.module.data.service.partinstance;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.data.controller.admin.partinstance.vo.InstanceImportVO;
import cn.iocoder.yudao.module.data.controller.admin.partinstance.vo.InstancePageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.partinstance.vo.InstanceSaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.partinstance.vo.InstanceUpdateStatusReqVO;
import cn.iocoder.yudao.module.data.dal.dataobject.partcategory.CategoryDO;
import cn.iocoder.yudao.module.data.dal.dataobject.partinstance.InstanceDO;
import cn.iocoder.yudao.module.data.dal.mysql.partinstance.InstanceMapper;
import cn.iocoder.yudao.module.data.service.partcategory.CategoryService;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.data.enums.ErrorCodeConstants.*;

/**
 * 管理部件实例 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class InstanceServiceImpl implements InstanceService {

    @Resource
    private InstanceMapper instanceMapper;

    @Resource
    private CategoryService categoryService;

    @Override
    public Long createInstance(InstanceSaveReqVO createReqVO) {
        // 插入
        InstanceDO instance = BeanUtils.toBean(createReqVO, InstanceDO.class);
        instanceMapper.insert(instance);
        // 返回
        return instance.getId();
    }

    @Override
    public void updateInstance(InstanceSaveReqVO updateReqVO) {
        // 校验存在
        validateInstanceExists(updateReqVO.getId());
        // 更新
        InstanceDO updateObj = BeanUtils.toBean(updateReqVO, InstanceDO.class);
        instanceMapper.updateById(updateObj);
    }

    @Override
    public void deleteInstance(Long id) {
        // 校验存在
        validateInstanceExists(id);
        // 删除
        instanceMapper.deleteById(id);
    }

    private void validateInstanceExists(Long id) {
        if (instanceMapper.selectById(id) == null) {
            throw exception(INSTANCE_NOT_EXISTS);
        }
    }

    @Override
    public InstanceDO getInstance(Long id) {
        return instanceMapper.selectById(id);
    }

    @Override
    public PageResult<InstanceDO> getInstancePage(InstancePageReqVO pageReqVO) {
        return instanceMapper.selectPageWithCategory(pageReqVO, categoryService);
    }

    @Override
    public List<InstanceDO> getInstanceListByCategoryId(String categoryId) {
        // 1. 验证分类ID是否存在
        if (categoryId == null || categoryId.trim().isEmpty()) {
            throw exception(INSTANCE_CATEGORY_ID_EMPTY);
        }

        // 2. 验证分类是否存在（确保category_id与part_category表对应）
        try {
            Long categoryIdLong = Long.parseLong(categoryId);
            CategoryDO category = categoryService.getCategory(categoryIdLong);
            if (category == null) {
                throw exception(CATEGORY_NOT_EXISTS);
            }
        } catch (NumberFormatException e) {
            throw exception(INSTANCE_CATEGORY_ID_INVALID);
        }

        // 3. 查询该分类下的所有实例
        return instanceMapper.selectList(new LambdaQueryWrapperX<InstanceDO>()
                .eq(InstanceDO::getParentCategoryId, categoryId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String importInstanceExcel(MultipartFile file) throws IOException {
        // 1. 基础校验
        if (file.isEmpty()) {
            throw exception(INSTANCE_IMPORT_FILE_EMPTY);
        }

        // 手动解析Excel
        List<InstanceImportVO> importDataList = new ArrayList<>();
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

//            System.out.println("表头信息:");
//            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
//                Cell cell = headerRow.getCell(i);
//                String value = getCellStringValue(cell);
//                System.out.print((value != null ? value : "[空]") + " | ");
//            }
//            System.out.println();

            // 读取数据行
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                try {
                    InstanceImportVO importVO = new InstanceImportVO();

                    // 手动映射单元格到对象属性
                    importVO.setPartName(getCellStringValue(row.getCell(0))); // 第一列
                    importVO.setUniqueCode(getCellStringValue(row.getCell(1))); // 第二列
                    importVO.setCategoryName(getCellStringValue(row.getCell(2))); // 第三列
                    importVO.setGridName(getCellStringValue(row.getCell(3))); // 第四列
                    importVO.setCoordinate(getCellStringValue(row.getCell(4))); // 第五列
                    importVO.setRunStatus(getCellStringValue(row.getCell(5))); // 第六列
                    importVO.setDeptName(getCellStringValue(row.getCell(6))); // 第七列
                    importVO.setCreator(getCellStringValue(row.getCell(7))); // 第八列

                    // 处理数字类型字段
                    Cell monitorCountCell = row.getCell(8);
                    if (monitorCountCell != null) {
                        if (monitorCountCell.getCellType() == CellType.NUMERIC) {
                            importVO.setMonitorCount((int) monitorCountCell.getNumericCellValue());
                        } else if (monitorCountCell.getCellType() == CellType.STRING) {
                            try {
                                importVO.setMonitorCount(Integer.parseInt(monitorCountCell.getStringCellValue()));
                            } catch (NumberFormatException e) {
                                // 忽略转换错误
                            }
                        }
                    }

                    importVO.setAreaName(getCellStringValue(row.getCell(9))); // 第十列

                    importDataList.add(importVO);
                } catch (Exception e) {
                    errorMsg.append("第").append(i + 1).append("行解析失败: ").append(e.getMessage()).append("; ");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("手动解析Excel失败: " + e.getMessage());
        }

        // 处理导入的数据
        for (int i = 0; i < importDataList.size(); i++) {
            InstanceImportVO importVO = importDataList.get(i);
            int rowNumber = i + 2; // Excel行号，通常第1行是表头

            try {
                // 验证必填字段
                if (importVO.getPartName() == null || importVO.getPartName().trim().isEmpty()) {
                    throw new RuntimeException("部件名称不能为空");
                }

                // 转换为保存对象
                InstanceSaveReqVO saveReqVO = BeanUtils.toBean(importVO, InstanceSaveReqVO.class);

                // 处理坐标信息
                if (importVO.getCoordinate() != null && !importVO.getCoordinate().isEmpty()) {
                    String[] coords = importVO.getCoordinate().split(",");
                    if (coords.length == 2) {
                        try {
                            saveReqVO.setLongitude(new BigDecimal(coords[0].trim()));
                            saveReqVO.setLatitude(new BigDecimal(coords[1].trim()));
                        } catch (NumberFormatException e) {
                            // 坐标格式错误，忽略
                        }
                    }
                }

                // 检查唯一标识码是否已存在
                if (saveReqVO.getUniqueCode() != null) {
                    InstanceDO existInstance = instanceMapper.selectOne(new LambdaQueryWrapperX<InstanceDO>()
                            .eq(InstanceDO::getUniqueCode, saveReqVO.getUniqueCode()));
                    if (existInstance != null) {
                        // 如果存在，则执行更新操作
                        saveReqVO.setId(existInstance.getId());
                        updateInstance(saveReqVO);
                    } else {
                        // 如果不存在，则执行新增操作
                        createInstance(saveReqVO);
                    }
                } else {
                    // 没有唯一编码，直接创建
                    createInstance(saveReqVO);
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
                    return String.valueOf(cell.getNumericCellValue());
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


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateInstanceStatusBatch(InstanceUpdateStatusReqVO updateReqVO) {
        // 1. 参数校验（@Valid已做基础校验，此处可做更复杂的业务校验）
        if (updateReqVO.getIds() == null || updateReqVO.getIds().isEmpty()) {
            throw exception(INSTANCE_IDS_EMPTY); // 假设已在ErrorCodeConstants中定义了此错误码
        }
        // 可以在这里添加对runStatus的枚举值或有效性校验
        // if (!isValidRunStatus(updateReqVO.getRunStatus())) {...}

        // 2. 查询出所有存在的实例，确保操作的ID都是有效的
        List<InstanceDO> existInstanceList = instanceMapper.selectBatchIds(updateReqVO.getIds());
        if (existInstanceList.size() != updateReqVO.getIds().size()) {
            // 如果查询出的记录数不等于传入的ID数，说明有ID不存在
            // 这里可以选择抛出异常，或者记录日志后继续更新存在的记录。
            // 为了数据安全，我们选择抛出异常，确保所有ID都有效才执行更新。
            List<Long> existIds = existInstanceList.stream().map(InstanceDO::getId).collect(Collectors.toList());
            updateReqVO.getIds().removeAll(existIds);
            throw exception(INSTANCE_NOT_EXISTS, "ID列表中存在不存在的实例ID: " + updateReqVO.getIds());
        }

        // 3. 批量更新状态
        // 3.1 构造要更新的实体列表
        List<InstanceDO> updateList = new ArrayList<>(existInstanceList.size());
        for (InstanceDO instance : existInstanceList) {
            InstanceDO updateObj = new InstanceDO();
            updateObj.setId(instance.getId()); // 设置主键
            updateObj.setRunStatus(updateReqVO.getRunStatus()); // 设置新的状态
            // 注意：如果需要更新`update_time`等审计字段，BaseDO通常会自动处理，这里不需要显式设置
            updateList.add(updateObj);
        }

        // 3.2 执行批量更新
        int rows = 0;
        for (InstanceDO instanceDO : updateList) {
            // updateById 会按照实体对象中的主键ID和设置的字段进行更新
            rows += instanceMapper.updateById(instanceDO);
        }

        // 4. 返回更新成功的行数
        return rows;
    }

}