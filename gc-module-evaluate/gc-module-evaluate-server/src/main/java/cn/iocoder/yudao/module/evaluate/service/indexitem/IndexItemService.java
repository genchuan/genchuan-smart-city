package cn.iocoder.yudao.module.evaluate.service.indexitem;

import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexitem.vo.IndexItemPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexitem.vo.IndexItemSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexitem.IndexItemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 指标项 Service 接口
 *
 * @author 亘川智城
 */
public interface IndexItemService {

    /**
     * 创建指标项
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createIndexItem(@Valid IndexItemSaveReqVO createReqVO);

    /**
     * 更新指标项
     *
     * @param updateReqVO 更新信息
     */
    void updateIndexItem(@Valid IndexItemSaveReqVO updateReqVO);

    /**
     * 删除指标项
     *
     * @param id 编号
     */
    void deleteIndexItem(Long id);

    /**
     * 获得指标项
     *
     * @param id 编号
     * @return 指标项
     */
    IndexItemDO getIndexItem(Long id);

    /**
     * 获得指标项分页
     *
     * @param pageReqVO 分页查询
     * @return 指标项分页
     */
    PageResult<IndexItemDO> getIndexItemPage(IndexItemPageReqVO pageReqVO);

}