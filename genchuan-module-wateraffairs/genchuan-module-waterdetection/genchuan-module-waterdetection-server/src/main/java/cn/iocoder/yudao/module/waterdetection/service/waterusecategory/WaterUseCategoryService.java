package cn.iocoder.yudao.module.waterdetection.service.waterusecategory;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.waterusecategory.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.waterusecategory.WaterUseCategoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 用水性质分类管理 Service 接口
 *
 * @author zcq
 */
public interface WaterUseCategoryService {

    /**
     * 创建用水性质分类管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWaterUseCategory(@Valid WaterUseCategorySaveReqVO createReqVO);

    /**
     * 更新用水性质分类管理
     *
     * @param updateReqVO 更新信息
     */
    void updateWaterUseCategory(@Valid WaterUseCategorySaveReqVO updateReqVO);

    /**
     * 删除用水性质分类管理
     *
     * @param id 编号
     */
    void deleteWaterUseCategory(Long id);

    /**
     * 获得用水性质分类管理
     *
     * @param id 编号
     * @return 用水性质分类管理
     */
    WaterUseCategoryDO getWaterUseCategory(Long id);

    /**
     * 获得用水性质分类管理分页
     *
     * @param pageReqVO 分页查询
     * @return 用水性质分类管理分页
     */
    PageResult<WaterUseCategoryDO> getWaterUseCategoryPage(WaterUseCategoryPageReqVO pageReqVO);

}