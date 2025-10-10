package cn.iocoder.yudao.module.datacenter.service.componentcategory;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.componentcategory.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.componentcategory.ComponentCategoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.componentcategory.ComponentCategoryMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 监测部件分类 Service 实现类
 *
 * @author lll
 */
@Service
@Validated
public class ComponentCategoryServiceImpl implements ComponentCategoryService {

    @Resource
    private ComponentCategoryMapper componentCategoryMapper;

    @Override
    public Long createComponentCategory(ComponentCategorySaveReqVO createReqVO) {
        // 插入
        ComponentCategoryDO componentCategory = BeanUtils.toBean(createReqVO, ComponentCategoryDO.class);
        componentCategoryMapper.insert(componentCategory);
        // 返回
        return componentCategory.getId();
    }

    @Override
    public void updateComponentCategory(ComponentCategorySaveReqVO updateReqVO) {
        // 校验存在
        validateComponentCategoryExists(updateReqVO.getId());
        // 更新
        ComponentCategoryDO updateObj = BeanUtils.toBean(updateReqVO, ComponentCategoryDO.class);
        componentCategoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteComponentCategory(Long id) {
        // 校验存在
        validateComponentCategoryExists(id);
        // 删除
        componentCategoryMapper.deleteById(id);
    }

    private void validateComponentCategoryExists(Long id) {
        if (componentCategoryMapper.selectById(id) == null) {
            throw exception(COMPONENT_CATEGORY_NOT_EXISTS);
        }
    }

    @Override
    public ComponentCategoryDO getComponentCategory(Long id) {
        return componentCategoryMapper.selectById(id);
    }

    @Override
    public PageResult<ComponentCategoryDO> getComponentCategoryPage(ComponentCategoryPageReqVO pageReqVO) {
        return componentCategoryMapper.selectPage(pageReqVO);
    }

}