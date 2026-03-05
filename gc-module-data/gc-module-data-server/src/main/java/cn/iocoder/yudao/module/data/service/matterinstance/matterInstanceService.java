package cn.iocoder.yudao.module.data.service.matterinstance;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.data.controller.admin.matterinstance.vo.*;
import cn.iocoder.yudao.module.data.dal.dataobject.matterinstance.matterInstanceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 管理事项实例 Service 接口
 *
 * @author zhucongquan
 */
public interface matterInstanceService {

    /**
     * 创建管理事项实例
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long creatematterInstance(@Valid matterInstanceSaveReqVO createReqVO);

    /**
     * 更新管理事项实例
     *
     * @param updateReqVO 更新信息
     */
    void updatematterInstance(@Valid matterInstanceSaveReqVO updateReqVO);

    /**
     * 删除管理事项实例
     *
     * @param id 编号
     */
    void deletematterInstance(Long id);

    /**
     * 获得管理事项实例
     *
     * @param id 编号
     * @return 管理事项实例
     */
    matterInstanceDO getmatterInstance(Long id);

    /**
     * 获得管理事项实例分页
     *
     * @param pageReqVO 分页查询
     * @return 管理事项实例分页
     */
    PageResult<matterInstanceDO> getmatterInstancePage(matterInstancePageReqVO pageReqVO);

    /**
     * 获取指定分类节点下的所有子分类ID（用于树形查询过滤）
     *
     * @param parentCategoryId 父分类ID
     * @param includeSelf 是否包含父分类自身
     * @return 子分类ID列表
     */
    List<String> getSubCategoryIdsForTree(String parentCategoryId, boolean includeSelf);
}