package cn.iocoder.yudao.module.datacenter.service.appscenecategory;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.appscenecategory.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.appscenecategory.AppSceneCategoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 应用场景分类配置 Service 接口
 *
 * @author zcq
 */
public interface AppSceneCategoryService {

    /**
     * 创建应用场景分类配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAppSceneCategory(@Valid AppSceneCategorySaveReqVO createReqVO);

    /**
     * 更新应用场景分类配置
     *
     * @param updateReqVO 更新信息
     */
    void updateAppSceneCategory(@Valid AppSceneCategorySaveReqVO updateReqVO);

    /**
     * 删除应用场景分类配置
     *
     * @param id 编号
     */
    void deleteAppSceneCategory(Long id);

    /**
     * 获得应用场景分类配置
     *
     * @param id 编号
     * @return 应用场景分类配置
     */
    AppSceneCategoryDO getAppSceneCategory(Long id);

    /**
     * 获得应用场景分类配置分页
     *
     * @param pageReqVO 分页查询
     * @return 应用场景分类配置分页
     */
    PageResult<AppSceneCategoryDO> getAppSceneCategoryPage(AppSceneCategoryPageReqVO pageReqVO);


    /**
     * 获得应用场景分类配置树形结构
     *
     * @return 应用场景分类配置树形结构
     */
    List<AppSceneCategoryDO> getAppSceneCategoryTree();
}