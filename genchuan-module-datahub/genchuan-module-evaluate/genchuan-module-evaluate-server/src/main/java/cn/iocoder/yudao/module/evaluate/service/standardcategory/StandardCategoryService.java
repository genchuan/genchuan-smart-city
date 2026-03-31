package cn.iocoder.yudao.module.evaluate.service.standardcategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.standardcategory.vo.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.standardcategory.StandardCategoryDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 标准分类 Service 接口
 *
 * @author 亘川智城
 */
public interface StandardCategoryService {

    /**
     * 创建标准分类（含标准项批量创建）
     *
     * @param createReqVO 创建信息（含标准项列表）
     * @return 编号
     */
    Long createStandardCategoryWithItems(@Valid StandardCategorySaveReqVO createReqVO);

    /**
     * 删除标准分类
     *
     * @param id 编号
     */
    void deleteStandardCategory(Long id);

    /**
    * 批量删除标准分类
    *
    * @param ids 编号
    */
    void deleteStandardCategoryListByIds(List<Long> ids);

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

    /**
     * 获得标准分类分页（含关联表名称和用户姓名）
     *
     * @param pageReqVO 分页查询
     * @return 标准分类分页（含名称）
     */
    PageResult<StandardCategoryRespVO> getStandardCategoryPageWithJoin(StandardCategoryPageReqVO pageReqVO);

    /**
     * 获得标准分类（包含关联的标准项列表）
     *
     * @param id 编号
     * @return 标准分类（含标准项列表）
     */
    StandardCategoryRespVO getStandardCategoryWithItems(Long id);

    /**
     * 更新标准分类（包含标准项列表的批量更新）
     *
     * @param updateReqVO 更新信息（含标准项列表）
     */
    void updateStandardCategoryWithItems(@Valid StandardCategorySaveReqVO updateReqVO);

    /**
     * 获取标准分类统计数据
     *
     * @return 标准分类统计数据
     */
    StandardCategoryStatisticsVO getStandardCategoryStatistics();

    /**
     * 获得标准分类导出列表（含标准项展开）
     *
     * @param pageReqVO 分页查询（pageSize 为空时导出所有）
     * @return 标准分类导出列表（含标准项）
     */
    List<StandardCategoryExportVO> getStandardCategoryExportListWithItems(StandardCategoryPageReqVO pageReqVO);

}