package cn.iocoder.yudao.module.data.service.matterinstance;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.data.controller.admin.matterinstance.vo.MatterInstanceImportVO;
import cn.iocoder.yudao.module.data.controller.admin.matterinstance.vo.MatterInstancePageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.matterinstance.vo.MatterInstanceSaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.matterinstance.vo.MatterInstanceUpdateStatusNameReqVO;
import cn.iocoder.yudao.module.data.dal.dataobject.mattercategory.MatterCategoryDO;
import cn.iocoder.yudao.module.data.dal.dataobject.matterinstance.MatterInstanceDO;
import cn.iocoder.yudao.module.data.dal.mysql.mattercategory.MatterCategoryMapper;
import cn.iocoder.yudao.module.data.dal.mysql.matterinstance.MatterInstanceMapper;
import cn.iocoder.yudao.module.data.service.mattercategory.MatterCategoryService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
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
 * 管理事项实例 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class MatterInstanceServiceImpl implements MatterInstanceService {

    @Resource
    private MatterInstanceMapper matterInstanceMapper;

    @Resource
    private MatterCategoryService matterCategoryService1;

    @Resource
    private MatterCategoryMapper matterCategoryMapper;

    @Override
    public Long creatematterInstance(MatterInstanceSaveReqVO createReqVO) {
        // 插入
        MatterInstanceDO matterInstance = BeanUtils.toBean(createReqVO, MatterInstanceDO.class);
        matterInstanceMapper.insert(matterInstance);
        // 返回
        return matterInstance.getId();
    }

    @Override
    public void updatematterInstance(MatterInstanceSaveReqVO updateReqVO) {
        // 校验存在
        validatematterInstanceExists(updateReqVO.getId());
        // 更新
        MatterInstanceDO updateObj = BeanUtils.toBean(updateReqVO, MatterInstanceDO.class);
        matterInstanceMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateInstanceStatusNameBatch(MatterInstanceUpdateStatusNameReqVO updateReqVO) {
        // 1. 参数基础校验（@Valid已做，此处可做补充业务校验）
        List<Long> ids = updateReqVO.getIds();
        if (ids == null || ids.isEmpty()) {
            throw exception(MATTER_INSTANCE_IDS_EMPTY);
        }

        // 2. 校验传入的ID是否在数据库中都存在
        // 查询出所有存在的记录
        List<MatterInstanceDO> existList = matterInstanceMapper.selectBatchIds(ids);
        if (existList.size() != ids.size()) {
            // 如果数量不一致，说明有ID不存在
            List<Long> existIds = existList.stream().map(MatterInstanceDO::getId).collect(Collectors.toList());
            ids.removeAll(existIds); // 得到不存在的ID列表
            throw exception(MATTER_INSTANCE_NOT_EXISTS, "ID列表中存在不存在的实例ID: " + ids);
        }

        // 3. 执行批量更新
        // 使用 MyBatis-Plus 的 UpdateWrapper 构造更新条件和内容
        LambdaUpdateWrapper<MatterInstanceDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(MatterInstanceDO::getStatus, updateReqVO.getStatus()) // 设置要更新的字段
                .in(MatterInstanceDO::getId, updateReqVO.getIds()); // 设置更新条件：id在给定的列表中

        int updateRows = matterInstanceMapper.update(null, updateWrapper); // 执行更新

        // 4. 返回更新的记录数
        return updateRows;
    }

    @Override
    public void deletematterInstance(Long id) {
        // 校验存在
        validatematterInstanceExists(id);
        // 删除
        matterInstanceMapper.deleteById(id);
    }

    private void validatematterInstanceExists(Long id) {
        if (matterInstanceMapper.selectById(id) == null) {
            throw exception(MATTER_INSTANCE_NOT_EXISTS);
        }
    }

    @Override
    public MatterInstanceDO getmatterInstance(Long id) {
        return matterInstanceMapper.selectById(id);
    }

    @Override
    public PageResult<MatterInstanceDO> getmatterInstancePage(MatterInstancePageReqVO pageReqVO) {
        // 处理树形查询参数
        String treeParentCategoryId = pageReqVO.getTreeParentId();
        if (treeParentCategoryId != null && !treeParentCategoryId.trim().isEmpty()) {
            // 获取该分类节点及其所有子分类的ID列表
            List<String> subCategoryIds = getSubCategoryIdsForTree(treeParentCategoryId, pageReqVO.getIncludeSelf());

            if (subCategoryIds.isEmpty()) {
                // 如果没有找到任何关联的分类，则返回空结果
                return new PageResult<>(Collections.emptyList(), 0L);
            }
            // 调用 Mapper 中新增的方法，根据分类ID列表进行分页查询
            return matterInstanceMapper.selectPageByCategoryIds(pageReqVO, subCategoryIds);
        }
        // 普通分页查询
        return matterInstanceMapper.selectPage(pageReqVO);
    }

    @Override
    public List<String> getSubCategoryIdsForTree(String parentCategoryId, boolean includeSelf) {
        // 1. 获取子分类的自增主键id列表
        List<Long> subCategoryIdList = matterCategoryService1.getSubMatterCategoryIds(parentCategoryId, includeSelf);

        if (subCategoryIdList.isEmpty()) {
            return Collections.emptyList();
        }

        // 2. 由于matterCategoryService1.getSubMatterCategoryIds返回的是自增主键id(Long)
        // 但实例表中的category_id存储的是业务ID(String)，如'CAT001'
        // 所以我们需要从分类表中查询对应的业务ID

        // 3. 使用LambdaQueryWrapperX查询分类表，获取这些id对应的业务ID
        List<MatterCategoryDO> categories =
                matterCategoryMapper.selectList(new LambdaQueryWrapperX<MatterCategoryDO>()
                        .in(MatterCategoryDO::getId, subCategoryIdList));

        // 4. 提取业务ID列表
        return categories.stream()
                .map(MatterCategoryDO::getMatterCategoryId)
                .collect(Collectors.toList());
    }

    // 在 MatterInstanceServiceImpl.java 中添加以下方法
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String importMatterInstanceExcel(MultipartFile file) throws IOException {
        // 1. 基础校验
        if (file.isEmpty()) {
            throw exception(MATTER_INSTANCE_IMPORT_FILE_EMPTY);
        }

        List<MatterInstanceImportVO> importDataList = new ArrayList<>();
        int successCount = 0;
        StringBuilder errorMsg = new StringBuilder();

        // 2. 手动解析Excel
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook;
            try {
                workbook = new XSSFWorkbook(inputStream); // .xlsx
            } catch (Exception e) {
                try {
                    inputStream.reset();
                    workbook = new HSSFWorkbook(inputStream); // .xls
                } catch (Exception ex) {
                    throw new RuntimeException("无法解析Excel文件，请检查格式");
                }
            }

            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new RuntimeException("Excel文件没有表头行");
            }

            // 从第1行开始读取数据（索引0是表头）
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                try {
                    MatterInstanceImportVO importVO = new MatterInstanceImportVO();

                    // 手动映射单元格，顺序与模板列顺序一致
                    importVO.setName(getCellStringValue(row.getCell(0)));
                    importVO.setUniqueCode(getCellStringValue(row.getCell(1)));
                    importVO.setCategoryName(getCellStringValue(row.getCell(2)));
                    importVO.setParentCategoryId(getCellStringValue(row.getCell(3)));
                    importVO.setLocation(getCellStringValue(row.getCell(4)));
                    importVO.setGridName(getCellStringValue(row.getCell(5)));
                    importVO.setDescription(getCellStringValue(row.getCell(6)));
                    importVO.setStatus(getCellStringValue(row.getCell(7)));
                    importVO.setDeptName(getCellStringValue(row.getCell(8)));
                    importVO.setCreator(getCellStringValue(row.getCell(9)));
                    // 处理时间字段
                    importVO.setCreateTime(getCellLocalDateTimeValue(row.getCell(10)));
                    importVO.setHandler(getCellStringValue(row.getCell(11)));
                    importVO.setDealTime(getCellLocalDateTimeValue(row.getCell(12)));

                    // 处理数字字段
                    Cell partCountCell = row.getCell(13);
                    if (partCountCell != null) {
                        if (partCountCell.getCellType() == CellType.NUMERIC) {
                            importVO.setPartCount((int) partCountCell.getNumericCellValue());
                        } else if (partCountCell.getCellType() == CellType.STRING) {
                            try {
                                importVO.setPartCount(Integer.parseInt(partCountCell.getStringCellValue().trim()));
                            } catch (NumberFormatException e) {
                                // 忽略转换错误，或记录日志
                            }
                        }
                    }
                    importDataList.add(importVO);
                } catch (Exception e) {
                    errorMsg.append("第").append(i + 1).append("行解析失败: ").append(e.getMessage()).append("; ");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("解析Excel文件失败: " + e.getMessage());
        }

        // 3. 处理导入的数据
        for (int i = 0; i < importDataList.size(); i++) {
            MatterInstanceImportVO importVO = importDataList.get(i);
            int rowNumber = i + 2; // Excel中的实际行号

            try {
                // 基础业务校验
                if (StringUtils.isBlank(importVO.getName())) {
                    throw new RuntimeException("事项名称不能为空");
                }
                if (StringUtils.isBlank(importVO.getUniqueCode())) {
                    throw new RuntimeException("16位标识码不能为空");
                }

                // 转换为保存对象
                MatterInstanceSaveReqVO saveReqVO = BeanUtils.toBean(importVO, MatterInstanceSaveReqVO.class);
                // BeanUtils可能无法自动映射所有字段，特别是名称相同的字段（如categoryName -> categoryName）应该可以。
                // 如果还有其他需要特殊处理的字段，在此处补充。

                // 根据唯一标识码判断是新增还是更新
                MatterInstanceDO existInstance = matterInstanceMapper.selectOne(new LambdaQueryWrapperX<MatterInstanceDO>()
                        .eq(MatterInstanceDO::getUniqueCode, saveReqVO.getUniqueCode()));
                if (existInstance != null) {
                    saveReqVO.setId(existInstance.getId());
                    updatematterInstance(saveReqVO);
                } else {
                    creatematterInstance(saveReqVO);
                }
                successCount++;
            } catch (Exception e) {
                errorMsg.append("第").append(rowNumber).append("行处理失败: ").append(e.getMessage()).append("; ");
            }
        }

        // 4. 构造返回结果
        String message = String.format("成功导入 %d 条数据", successCount);
        if (errorMsg.length() > 0) {
            message += String.format("，失败 %d 条。失败详情：%s",
                    importDataList.size() - successCount, errorMsg.toString());
        }
        return message;
    }

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

    // 辅助方法：从Cell获取LocalDateTime (从 InstanceServiceImpl 的 getCellStringValue 扩展而来)
    private LocalDateTime getCellLocalDateTimeValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        try {
            switch (cell.getCellType()) {
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        // 将java.util.Date转换为java.time.LocalDateTime
                        return cell.getDateCellValue().toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime();
                    }
                    // 如果不是日期格式，可以尝试按数字解析，但这里通常返回null或抛异常
                    return null;
                case STRING:
                    // 尝试解析字符串格式的日期，这里需要根据你的模板格式调整
                    String dateString = cell.getStringCellValue().trim();
                    if (!dateString.isEmpty()) {
                        // 使用一个简单的解析，例如 "yyyy-MM-dd HH:mm:ss"
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        return LocalDateTime.parse(dateString, formatter);
                    }
                    return null;
                default:
                    return null;
            }
        } catch (Exception e) {
            // 解析失败，返回null，外部调用者可能会因此抛出业务异常
            return null;
        }
    }

}