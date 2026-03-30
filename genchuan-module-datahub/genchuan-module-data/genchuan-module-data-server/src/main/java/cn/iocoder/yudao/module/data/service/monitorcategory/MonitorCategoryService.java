package cn.iocoder.yudao.module.data.service.monitorcategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.data.controller.admin.monitorcategory.vo.MonitorCategoryPageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.monitorcategory.vo.MonitorCategorySaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.monitorcategory.vo.MonitorCategorySimpleTreeRespVO;
import cn.iocoder.yudao.module.data.dal.dataobject.monitorcategory.MonitorCategoryDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 监测部件分类 Service 接口
 *
 * @author zhucongquan
 */
public interface MonitorCategoryService {

    /**
     * 创建监测部件分类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMonitorCategory(@Valid MonitorCategorySaveReqVO createReqVO);

    /**
     * 更新监测部件分类
     *
     * @param updateReqVO 更新信息
     */
    void updateMonitorCategory(@Valid MonitorCategorySaveReqVO updateReqVO);

    /**
     * 删除监测部件分类
     *
     * @param id 编号
     */
    void deleteMonitorCategory(Long id);

    /**
     * 批量删除监测部件分类
     *
     * @param ids 编号列表
     */
    void deleteMonitorCategories(List<Long> ids);

    /**
     * 获得监测部件分类
     *
     * @param id 编号
     * @return 监测部件分类
     */
    MonitorCategoryDO getMonitorCategory(Long id);

    /**
     * 获得监测部件分类分页
     *
     * @param pageReqVO 分页查询
     * @return 监测部件分类分页
     */
    PageResult<MonitorCategoryDO> getMonitorCategoryPage(MonitorCategoryPageReqVO pageReqVO);

    /**
     * 获得监测部件分类简化树（仅包含id、label、children）
     *
     * @return 监测部件分类简化树列表
     */
    List<MonitorCategorySimpleTreeRespVO> getMonitorCategorySimpleTree();

    /**
     * 根据父分类ID获取所有子分类ID列表（包含自身）
     *
     * @param parentId 父分类ID
     * @return 分类ID列表
     */
    List<Long> getCategoryAndChildrenIds(Long parentId);


}