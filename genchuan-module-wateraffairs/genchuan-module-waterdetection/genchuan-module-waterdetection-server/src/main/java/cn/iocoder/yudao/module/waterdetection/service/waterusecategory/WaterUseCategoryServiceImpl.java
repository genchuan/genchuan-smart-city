package cn.iocoder.yudao.module.waterdetection.service.waterusecategory;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.waterusecategory.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.waterusecategory.WaterUseCategoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.waterusecategory.WaterUseCategoryMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 用水性质分类管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class WaterUseCategoryServiceImpl implements WaterUseCategoryService {

    @Resource
    private WaterUseCategoryMapper waterUseCategoryMapper;

    @Override
    public Long createWaterUseCategory(WaterUseCategorySaveReqVO createReqVO) {
        // 插入
        WaterUseCategoryDO waterUseCategory = BeanUtils.toBean(createReqVO, WaterUseCategoryDO.class);
        waterUseCategoryMapper.insert(waterUseCategory);
        // 返回
        return waterUseCategory.getId();
    }

    @Override
    public void updateWaterUseCategory(WaterUseCategorySaveReqVO updateReqVO) {
        // 校验存在
        validateWaterUseCategoryExists(updateReqVO.getId());
        // 更新
        WaterUseCategoryDO updateObj = BeanUtils.toBean(updateReqVO, WaterUseCategoryDO.class);
        waterUseCategoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteWaterUseCategory(Long id) {
        // 校验存在
        validateWaterUseCategoryExists(id);
        // 删除
        waterUseCategoryMapper.deleteById(id);
    }

    private void validateWaterUseCategoryExists(Long id) {
        if (waterUseCategoryMapper.selectById(id) == null) {
            throw exception(WATER_USE_CATEGORY_NOT_EXISTS);
        }
    }

    @Override
    public WaterUseCategoryDO getWaterUseCategory(Long id) {
        return waterUseCategoryMapper.selectById(id);
    }

    @Override
    public PageResult<WaterUseCategoryDO> getWaterUseCategoryPage(WaterUseCategoryPageReqVO pageReqVO) {
        return waterUseCategoryMapper.selectPage(pageReqVO);
    }

}