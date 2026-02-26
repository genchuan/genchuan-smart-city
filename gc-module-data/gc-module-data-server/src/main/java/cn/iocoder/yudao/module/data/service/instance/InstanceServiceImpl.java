package cn.iocoder.yudao.module.data.service.instance;

import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.data.dal.dataobject.category.CategoryDO;
import cn.iocoder.yudao.module.data.service.category.CategoryService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.data.controller.admin.instance.vo.*;
import cn.iocoder.yudao.module.data.dal.dataobject.instance.InstanceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.data.dal.mysql.instance.InstanceMapper;

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
        return instanceMapper.selectPage(pageReqVO);
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
                .eq(InstanceDO::getCategoryId, categoryId));
    }

}