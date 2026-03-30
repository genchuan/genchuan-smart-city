package cn.iocoder.yudao.module.data.service.eventcategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.data.controller.admin.eventcategory.vo.EventCategoryPageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.eventcategory.vo.EventCategorySaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.eventcategory.vo.EventCategorySimpleTreeRespVO;
import cn.iocoder.yudao.module.data.dal.dataobject.eventcategory.EventCategoryDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 监测事件分类 Service 接口
 *
 * @author zhucongquan
 */
public interface EventCategoryService {

    /**
     * 创建监测事件分类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEventCategory(@Valid EventCategorySaveReqVO createReqVO);

    /**
     * 更新监测事件分类
     *
     * @param updateReqVO 更新信息
     */
    void updateEventCategory(@Valid EventCategorySaveReqVO updateReqVO);

    /**
     * 删除监测事件分类
     *
     * @param id 编号
     */
    void deleteEventCategory(Long id);

    /**
     * 获得监测事件分类
     *
     * @param id 编号
     * @return 监测事件分类
     */
    EventCategoryDO getEventCategory(Long id);

    /**
     * 获得监测事件分类分页
     *
     * @param pageReqVO 分页查询
     * @return 监测事件分类分页
     */
    PageResult<EventCategoryDO> getEventCategoryPage(EventCategoryPageReqVO pageReqVO);

    /**
     * 批量删除监测事件分类
     *
     * @param ids 编号列表
     */
    void deleteEventCategories(List<Long> ids);

    /**
     * 获得监测事件分类简化树（仅包含id、label、children）
     *
     * @return 监测事件分类简化树列表
     */
    List<EventCategorySimpleTreeRespVO> getEventCategorySimpleTree();

    /**
     * 获取指定父节点下的所有子节点ID（包括自身）
     *
     * @param parentId 父节点ID
     * @param includeSelf 是否包含父节点自身
     * @return 子节点ID列表
     */
    List<Long> getSubCategoryIds(String parentId, boolean includeSelf);
}