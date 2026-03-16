package cn.iocoder.yudao.module.data.service.monitorinstance;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.data.controller.admin.monitorinstance.vo.MonitorInstanceImportVO;
import cn.iocoder.yudao.module.data.controller.admin.monitorinstance.vo.MonitorInstancePageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.monitorinstance.vo.MonitorInstanceSaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.monitorinstance.vo.MonitorInstanceUpdateStatusReqVO;
import cn.iocoder.yudao.module.data.dal.dataobject.monitorinstance.MonitorInstanceDO;
import cn.iocoder.yudao.module.data.dal.mysql.monitorinstance.MonitorInstanceMapper;
import cn.iocoder.yudao.module.data.service.monitorcategory.MonitorCategoryService;
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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.data.enums.ErrorCodeConstants.*;

/**
 * 监测部件实例 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class MonitorInstanceServiceImpl implements MonitorInstanceService {

    @Resource
    private MonitorInstanceMapper monitorInstanceMapper;

    @Resource
    private MonitorCategoryService monitorCategoryService;

    @Override
    public Long createMonitorInstance(MonitorInstanceSaveReqVO createReqVO) {
        // 插入
        MonitorInstanceDO monitorInstance = BeanUtils.toBean(createReqVO, MonitorInstanceDO.class);
        monitorInstanceMapper.insert(monitorInstance);
        // 返回
        return monitorInstance.getId();
    }

    @Override
    public void updateMonitorInstance(MonitorInstanceSaveReqVO updateReqVO) {
        // 校验存在
        validateMonitorInstanceExists(updateReqVO.getId());
        // 更新
        MonitorInstanceDO updateObj = BeanUtils.toBean(updateReqVO, MonitorInstanceDO.class);
        monitorInstanceMapper.updateById(updateObj);
    }

    @Override
    public void deleteMonitorInstance(Long id) {
        // 校验存在
        validateMonitorInstanceExists(id);
        // 删除
        monitorInstanceMapper.deleteById(id);
    }

    private void validateMonitorInstanceExists(Long id) {
        if (monitorInstanceMapper.selectById(id) == null) {
            throw exception(MONITOR_INSTANCE_NOT_EXISTS);
        }
    }

    @Override
    public MonitorInstanceDO getMonitorInstance(Long id) {
        return monitorInstanceMapper.selectById(id);
    }

    @Override
    public PageResult<MonitorInstanceDO> getMonitorInstancePage(MonitorInstancePageReqVO pageReqVO) {

        return monitorInstanceMapper.selectPage(pageReqVO, monitorCategoryService);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateMonitorInstanceStatusBatch(MonitorInstanceUpdateStatusReqVO updateReqVO) {
        // 1. 参数校验
        if (updateReqVO.getIds() == null || updateReqVO.getIds().isEmpty()) {
            throw exception(MONITOR_INSTANCE_IDS_EMPTY);
        }

        // 2. 查询出所有存在的监测部件实例，确保操作的ID都是有效的
        List<MonitorInstanceDO> existMonitorInstanceList = monitorInstanceMapper.selectBatchIds(updateReqVO.getIds());
        if (existMonitorInstanceList.size() != updateReqVO.getIds().size()) {
            // 如果查询出的记录数不等于传入的ID数，说明有ID不存在
            List<Long> existIds = existMonitorInstanceList.stream()
                    .map(MonitorInstanceDO::getId)
                    .collect(Collectors.toList());

            // 找出不存在的ID
            List<Long> notExistIds = new ArrayList<>(updateReqVO.getIds());
            notExistIds.removeAll(existIds);

            throw exception(MONITOR_INSTANCE_NOT_EXISTS, "ID列表中存在不存在的监测部件实例ID: " + notExistIds);
        }

        // 3. 批量更新状态
        // 3.1 构造要更新的实体列表
        List<MonitorInstanceDO> updateList = new ArrayList<>(existMonitorInstanceList.size());
        for (MonitorInstanceDO monitorInstance : existMonitorInstanceList) {
            MonitorInstanceDO updateObj = new MonitorInstanceDO();
            updateObj.setId(monitorInstance.getId()); // 设置主键
            updateObj.setRunStatus(updateReqVO.getRunStatus()); // 设置新的运行状态
            updateList.add(updateObj);
        }

        // 3.2 执行批量更新
        int rows = 0;
        for (MonitorInstanceDO monitorInstanceDO : updateList) {
            rows += monitorInstanceMapper.updateById(monitorInstanceDO);
        }

        // 4. 返回更新成功的行数
        return rows;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String importMonitorInstanceExcel(MultipartFile file) throws IOException {
        // 1. 基础校验
        if (file.isEmpty()) {
            throw exception(MONITOR_INSTANCE_IMPORT_FILE_EMPTY);
        }

        // 手动解析Excel
        List<MonitorInstanceImportVO> importDataList = new ArrayList<>();
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

            // 读取数据行
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                try {
                    MonitorInstanceImportVO importVO = new MonitorInstanceImportVO();

                    // 手动映射单元格到对象属性
                    importVO.setName(getCellStringValue(row.getCell(0))); // 部件名称
                    importVO.setUniqueCode(getCellStringValue(row.getCell(1))); // 18位标识码
                    importVO.setCategoryName(getCellStringValue(row.getCell(2))); // 所属分类
                    importVO.setGridName(getCellStringValue(row.getCell(3))); // 所在网格
                    importVO.setCoordinate(getCellStringValue(row.getCell(4))); // 坐标信息
                    importVO.setRunStatus(getCellStringValue(row.getCell(5))); // 运行状态

                    // 处理安装时间
                    Cell installTimeCell = row.getCell(6);
                    if (installTimeCell != null) {
                        if (installTimeCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(installTimeCell)) {
                            importVO.setInstallTime(installTimeCell.getDateCellValue().toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDateTime());
                        } else if (installTimeCell.getCellType() == CellType.STRING) {
                            String dateStr = installTimeCell.getStringCellValue();
                            if (dateStr != null && !dateStr.trim().isEmpty()) {
                                try {
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                    importVO.setInstallTime(LocalDateTime.parse(dateStr.trim(), formatter));
                                } catch (Exception e) {
                                    // 解析失败，保持null
                                }
                            }
                        }
                    }

                    // 处理数字字段：校准周期
                    Cell calibrateCycleCell = row.getCell(7);
                    if (calibrateCycleCell != null) {
                        if (calibrateCycleCell.getCellType() == CellType.NUMERIC) {
                            importVO.setCalibrateCycle((int) calibrateCycleCell.getNumericCellValue());
                        } else if (calibrateCycleCell.getCellType() == CellType.STRING) {
                            try {
                                importVO.setCalibrateCycle(Integer.parseInt(calibrateCycleCell.getStringCellValue().trim()));
                            } catch (NumberFormatException e) {
                                // 忽略转换错误
                            }
                        }
                    }

                    importVO.setCreator(getCellStringValue(row.getCell(8))); // 创建人

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
                                    // 解析失败，保持null
                                }
                            }
                        }
                    }

                    importVO.setAreaName(getCellStringValue(row.getCell(10))); // 行政区划归属
                    importVO.setRelatedPartName(getCellStringValue(row.getCell(11))); // 关联管理部件

                    // 处理下次校准时间
                    Cell nextCalibrateTimeCell = row.getCell(12);
                    if (nextCalibrateTimeCell != null) {
                        if (nextCalibrateTimeCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(nextCalibrateTimeCell)) {
                            importVO.setNextCalibrateTime(nextCalibrateTimeCell.getDateCellValue().toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDateTime());
                        } else if (nextCalibrateTimeCell.getCellType() == CellType.STRING) {
                            String dateStr = nextCalibrateTimeCell.getStringCellValue();
                            if (dateStr != null && !dateStr.trim().isEmpty()) {
                                try {
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                    importVO.setNextCalibrateTime(LocalDateTime.parse(dateStr.trim(), formatter));
                                } catch (Exception e) {
                                    // 解析失败，保持null
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

        // 处理导入的数据
        for (int i = 0; i < importDataList.size(); i++) {
            MonitorInstanceImportVO importVO = importDataList.get(i);
            int rowNumber = i + 2; // Excel行号，通常第1行是表头

            try {
                // 验证必填字段
                if (importVO.getName() == null || importVO.getName().trim().isEmpty()) {
                    throw new RuntimeException("部件名称不能为空");
                }

                if (importVO.getUniqueCode() == null || importVO.getUniqueCode().trim().isEmpty()) {
                    throw new RuntimeException("18位标识码不能为空");
                }

                // 转换为保存对象
                MonitorInstanceSaveReqVO saveReqVO = BeanUtils.toBean(importVO, MonitorInstanceSaveReqVO.class);

                // 设置其他字段
                saveReqVO.setCategoryId(null); // 需要根据分类名称查询分类ID
                saveReqVO.setGridId(null); // 需要根据网格名称查询网格ID
                saveReqVO.setRelatedPartId(null); // 需要根据管理部件名称查询部件ID

                // 如果Excel中没有安装时间，可以使用创建时间
                if (saveReqVO.getInstallTime() == null) {
                    saveReqVO.setInstallTime(saveReqVO.getInstallTime());
                }

                // 如果下次校准时间为空，根据校准周期计算
                if (saveReqVO.getNextCalibrateTime() == null && saveReqVO.getCalibrateCycle() != null) {
                    if (saveReqVO.getInstallTime() != null) {
                        saveReqVO.setNextCalibrateTime(saveReqVO.getInstallTime().plusDays(saveReqVO.getCalibrateCycle()));
                    } else if (saveReqVO.getNextCalibrateTime() != null) {
                        saveReqVO.setNextCalibrateTime(saveReqVO.getNextCalibrateTime().plusDays(saveReqVO.getCalibrateCycle()));
                    }
                }

                // 检查唯一标识码是否已存在
                MonitorInstanceDO existInstance = monitorInstanceMapper.selectOne(new LambdaQueryWrapperX<MonitorInstanceDO>()
                        .eq(MonitorInstanceDO::getUniqueCode, saveReqVO.getUniqueCode()));
                if (existInstance != null) {
                    // 如果存在，则执行更新操作
                    saveReqVO.setId(existInstance.getId());
                    updateMonitorInstance(saveReqVO);
                } else {
                    // 如果不存在，则执行新增操作
                    createMonitorInstance(saveReqVO);
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

}