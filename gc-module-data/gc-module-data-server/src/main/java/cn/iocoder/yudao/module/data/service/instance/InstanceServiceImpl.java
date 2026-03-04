package cn.iocoder.yudao.module.data.service.instance;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.data.dal.dataobject.category.CategoryDO;
import cn.iocoder.yudao.module.data.service.category.CategoryService;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import cn.iocoder.yudao.module.data.controller.admin.instance.vo.*;
import cn.iocoder.yudao.module.data.dal.dataobject.instance.InstanceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.data.dal.mysql.instance.InstanceMapper;
import org.springframework.web.multipart.MultipartFile;

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
        // 2. 读取Excel数据
        List<InstanceImportVO> importDataList = ExcelUtils.read(file, InstanceImportVO.class);
        if (CollectionUtils.isEmpty(importDataList)) {
            throw exception(INSTANCE_IMPORT_DATA_EMPTY);
        }

        // 3. 遍历并处理每一条数据
        int successCount = 0;
        StringBuilder errorMsg = new StringBuilder();
        for (int i = 0; i < importDataList.size(); i++) {
            InstanceImportVO importVO = importDataList.get(i);
            int rowNumber = i + 2; // Excel行号，通常第1行是表头
            try {
                // 3.1 数据转换与校验
                InstanceSaveReqVO saveReqVO = BeanUtils.toBean(importVO, InstanceSaveReqVO.class);
                // 此处可以调用自定义校验，例如名称、唯一编码等非空校验
                if (saveReqVO.getPartName() == null || saveReqVO.getPartName().trim().isEmpty()) {
                    throw new RuntimeException("部件名称不能为空");
                }
                // 检查唯一标识码是否已存在（假设业务要求uniqueCode唯一）
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
                // 记录错误行信息
                errorMsg.append("第").append(rowNumber).append("行数据导入失败：").append(e.getMessage()).append("; ");
            }
        }

        // 4. 构造返回信息
        String message = String.format("成功导入 %d 条数据", successCount);
        if (errorMsg.length() > 0) {
            message += String.format("，失败 %d 条。失败详情：%s", importDataList.size() - successCount, errorMsg.toString());
        }
        return message;
    }

}