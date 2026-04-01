package cn.iocoder.yudao.module.evaluate.service.standarditem;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.standarditem.vo.StandardItemPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.standarditem.vo.StandardItemSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.standarditem.StandardItemDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 标准项 Service 接口
 *
 * @author 亘川智城
 */
public interface StandardItemService {

    /**
     * 创建标准项
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStandardItem(@Valid StandardItemSaveReqVO createReqVO);

    /**
     * 更新标准项
     *
     * @param updateReqVO 更新信息
     */
    void updateStandardItem(@Valid StandardItemSaveReqVO updateReqVO);

    /**
     * 删除标准项
     *
     * @param id 编号
     */
    void deleteStandardItem(Long id);

    /**
    * 批量删除标准项
    *
    * @param ids 编号
    */
    void deleteStandardItemListByIds(List<Long> ids);

    /**
     * 获得标准项
     *
     * @param id 编号
     * @return 标准项
     */
    StandardItemDO getStandardItem(Long id);

    /**
     * 获得标准项分页
     *
     * @param pageReqVO 分页查询
     * @return 标准项分页
     */
    PageResult<StandardItemDO> getStandardItemPage(StandardItemPageReqVO pageReqVO);

    /**
     * 根据标准分类ID列表删除标准项
     *
     * @param standardCategoryIds 标准分类ID列表
     */
    void deleteStandardItemListByStandardCategoryIds(List<Long> standardCategoryIds);

}