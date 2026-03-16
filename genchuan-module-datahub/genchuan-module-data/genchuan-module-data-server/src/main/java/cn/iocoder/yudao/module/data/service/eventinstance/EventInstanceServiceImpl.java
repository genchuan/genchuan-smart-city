package cn.iocoder.yudao.module.data.service.eventinstance;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.data.controller.admin.eventinstance.vo.EventInstanceImportVO;
import cn.iocoder.yudao.module.data.controller.admin.eventinstance.vo.EventInstancePageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.eventinstance.vo.EventInstanceSaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.eventinstance.vo.EventInstanceUpdateStatusReqVO;
import cn.iocoder.yudao.module.data.dal.dataobject.eventinstance.EventInstanceDO;
import cn.iocoder.yudao.module.data.dal.mysql.eventinstance.EventInstanceMapper;
import cn.iocoder.yudao.module.data.service.eventcategory.EventCategoryService;
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
 * 监测事件实例 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class EventInstanceServiceImpl implements EventInstanceService {

    @Resource
    private EventInstanceMapper eventInstanceMapper;

    @Resource
    private EventCategoryService eventCategoryService;

    @Override
    public Long createEventInstance(EventInstanceSaveReqVO createReqVO) {
        // 插入
        EventInstanceDO eventInstance = BeanUtils.toBean(createReqVO, EventInstanceDO.class);
        eventInstanceMapper.insert(eventInstance);
        // 返回
        return eventInstance.getId();
    }

    @Override
    public void updateEventInstance(EventInstanceSaveReqVO updateReqVO) {
        // 校验存在
        validateEventInstanceExists(updateReqVO.getId());
        // 更新
        EventInstanceDO updateObj = BeanUtils.toBean(updateReqVO, EventInstanceDO.class);
        eventInstanceMapper.updateById(updateObj);
    }

    @Override
    public void deleteEventInstance(Long id) {
        // 校验存在
        validateEventInstanceExists(id);
        // 删除
        eventInstanceMapper.deleteById(id);
    }

    private void validateEventInstanceExists(Long id) {
        if (eventInstanceMapper.selectById(id) == null) {
            throw exception(EVENT_INSTANCE_NOT_EXISTS);
        }
    }

    @Override
    public EventInstanceDO getEventInstance(Long id) {
        return eventInstanceMapper.selectById(id);
    }

    @Override
    public PageResult<EventInstanceDO> getEventInstancePage(EventInstancePageReqVO pageReqVO) {
        // 处理树形查询参数
        String treeParentId = pageReqVO.getTreeParentId();
        if (treeParentId != null && !treeParentId.trim().isEmpty()) {
            // 获取该节点及其所有子节点的ID
            List<Long> subCategoryIds = eventCategoryService.getSubCategoryIds(
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

            return eventInstanceMapper.selectPageByCategoryIds(pageReqVO, categoryIdStrs);
        }

        return eventInstanceMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateEventInstanceStatusBatch(EventInstanceUpdateStatusReqVO updateReqVO) {
        // 1. 参数校验
        if (updateReqVO.getIds() == null || updateReqVO.getIds().isEmpty()) {
            throw exception(EVENT_INSTANCE_IDS_EMPTY);
        }

        // 2. 查询出所有存在的实例，确保操作的ID都是有效的
        List<EventInstanceDO> existInstanceList = eventInstanceMapper.selectBatchIds(updateReqVO.getIds());
        if (existInstanceList.size() != updateReqVO.getIds().size()) {
            // 如果查询出的记录数不等于传入的ID数，说明有ID不存在
            List<Long> existIds = existInstanceList.stream()
                    .map(EventInstanceDO::getId)
                    .collect(Collectors.toList());
            updateReqVO.getIds().removeAll(existIds);
            throw exception(EVENT_INSTANCE_NOT_EXISTS, "ID列表中存在不存在的实例ID: " + updateReqVO.getIds());
        }

        // 3. 批量更新状态
        int rows = 0;
        for (EventInstanceDO instance : existInstanceList) {
            EventInstanceDO updateObj = new EventInstanceDO();
            updateObj.setId(instance.getId());
            updateObj.setStatus(updateReqVO.getStatus());
            rows += eventInstanceMapper.updateById(updateObj);
        }

        // 4. 返回更新成功的行数
        return rows;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String importEventInstanceExcel(MultipartFile file) throws IOException {
        // 1. 基础校验
        if (file.isEmpty()) {
            throw exception(EVENT_INSTANCE_IMPORT_FILE_EMPTY);
        }

        // 手动解析Excel
        List<EventInstanceImportVO> importDataList = new ArrayList<>();
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
                    EventInstanceImportVO importVO = new EventInstanceImportVO();

                    // 手动映射单元格到对象属性
                    importVO.setName(getCellStringValue(row.getCell(0))); // 事件名称
                    importVO.setUniqueCode(getCellStringValue(row.getCell(1))); // 18位标识码
                    importVO.setCategoryName(getCellStringValue(row.getCell(2))); // 所属分类
                    importVO.setMonitorName(getCellStringValue(row.getCell(3))); // 关联监测部件
                    importVO.setCoordinate(getCellStringValue(row.getCell(4))); // 事发坐标
                    importVO.setEventLevel(getCellStringValue(row.getCell(5))); // 事件等级
                    importVO.setDescription(getCellStringValue(row.getCell(6))); // 描述信息
                    importVO.setStatus(getCellStringValue(row.getCell(7))); // 状态
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

                    importVO.setHandler(getCellStringValue(row.getCell(10))); // 处置人

                    // 处理处置时间
                    Cell dealTimeCell = row.getCell(11);
                    if (dealTimeCell != null) {
                        if (dealTimeCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(dealTimeCell)) {
                            importVO.setDealTime(dealTimeCell.getDateCellValue().toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDateTime());
                        } else if (dealTimeCell.getCellType() == CellType.STRING) {
                            String dateStr = dealTimeCell.getStringCellValue();
                            if (dateStr != null && !dateStr.trim().isEmpty()) {
                                try {
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                    importVO.setDealTime(LocalDateTime.parse(dateStr.trim(), formatter));
                                } catch (Exception e) {
                                    // 解析失败，保持null
                                }
                            }
                        }
                    }

                    importVO.setAreaName(getCellStringValue(row.getCell(12))); // 行政区划归属
                    importVO.setMatterName(getCellStringValue(row.getCell(13))); // 关联管理事项

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
            EventInstanceImportVO importVO = importDataList.get(i);
            int rowNumber = i + 2; // Excel行号，通常第1行是表头

            try {
                // 验证必填字段
                if (importVO.getName() == null || importVO.getName().trim().isEmpty()) {
                    throw new RuntimeException("事件名称不能为空");
                }

                if (importVO.getUniqueCode() == null || importVO.getUniqueCode().trim().isEmpty()) {
                    throw new RuntimeException("18位标识码不能为空");
                }

                // 转换为保存对象
                EventInstanceSaveReqVO saveReqVO = BeanUtils.toBean(importVO, EventInstanceSaveReqVO.class);

                // 设置其他字段
                saveReqVO.setCategoryId(null); // 需要根据分类名称查询分类ID
                saveReqVO.setMonitorId(null); // 需要根据监测部件名称查询监测部件ID
                saveReqVO.setMatterId(null); // 需要根据管理事项名称查询管理事项ID

                // 检查唯一标识码是否已存在
                EventInstanceDO existInstance = eventInstanceMapper.selectOne(new LambdaQueryWrapperX<EventInstanceDO>()
                        .eq(EventInstanceDO::getUniqueCode, saveReqVO.getUniqueCode()));
                if (existInstance != null) {
                    // 如果存在，则执行更新操作
                    saveReqVO.setId(existInstance.getId());
                    updateEventInstance(saveReqVO);
                } else {
                    // 如果不存在，则执行新增操作
                    createEventInstance(saveReqVO);
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