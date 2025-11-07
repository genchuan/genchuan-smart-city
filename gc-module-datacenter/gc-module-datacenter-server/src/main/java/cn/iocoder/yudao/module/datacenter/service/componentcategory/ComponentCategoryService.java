package cn.iocoder.yudao.module.datacenter.service.componentcategory;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.componentcategory.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.componentcategory.ComponentCategoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 监测部件分类 Service 接口
 *
 * @author lll
 */
public interface ComponentCategoryService {

    /**
     * 创建监测部件分类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createComponentCategory(@Valid ComponentCategorySaveReqVO createReqVO);

    /**
     * 更新监测部件分类
     *
     * @param updateReqVO 更新信息
     */
    void updateComponentCategory(@Valid ComponentCategorySaveReqVO updateReqVO);

    /**
     * 删除监测部件分类
     *
     * @param id 编号
     */
    void deleteComponentCategory(Long id);

    /**
     * 获得监测部件分类
     *
     * @param id 编号
     * @return 监测部件分类
     */
    ComponentCategoryDO getComponentCategory(Long id);

    /**
     * 获得监测部件分类分页
     *
     * @param pageReqVO 分页查询
     * @return 监测部件分类分页
     */
    PageResult<ComponentCategoryDO> getComponentCategoryPage(ComponentCategoryPageReqVO pageReqVO);

}