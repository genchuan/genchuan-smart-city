package cn.iocoder.yudao.module.evaluate.service.standardcategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.standardcategory.vo.StandardCategoryPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.standardcategory.vo.StandardCategoryRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.standardcategory.vo.StandardCategorySaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.standardcategory.StandardCategoryDO;
import jakarta.validation.Valid;

/**
 * 标准分类 Service 接口
 *
 * @author 亘川智城
 */
public interface StandardCategoryService {

    /**
     * 创建标准分类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStandardCategory(@Valid StandardCategorySaveReqVO createReqVO);

    /**
     * 更新标准分类
     *
     * @param updateReqVO 更新信息
     */
    void updateStandardCategory(@Valid StandardCategorySaveReqVO updateReqVO);

    /**
     * 删除标准分类
     *
     * @param id 编号
     */
    void deleteStandardCategory(Long id);

    /**
     * 获得标准分类
     *
     * @param id 编号
     * @return 标准分类
     */
    StandardCategoryDO getStandardCategory(Long id);

    /**
     * 获得标准分类分页
     *
     * @param pageReqVO 分页查询
     * @return 标准分类分页
     */
    PageResult<StandardCategoryDO> getStandardCategoryPage(StandardCategoryPageReqVO pageReqVO);

    PageResult<StandardCategoryRespVO> getStandardCategoryJoinPage(StandardCategoryPageReqVO reqVO);
}