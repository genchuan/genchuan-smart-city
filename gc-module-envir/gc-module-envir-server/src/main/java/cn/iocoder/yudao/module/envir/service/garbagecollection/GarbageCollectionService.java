package cn.iocoder.yudao.module.envir.service.garbagecollection;

import java.util.*;

import cn.iocoder.yudao.module.envir.dal.dataobject.garbagecollection.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envir.controller.admin.garbagecollection.vo.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 收运计划 Service 接口
 *
 * @author 亘川智城
 */
public interface GarbageCollectionService {

    /**
     * 创建收运计划
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGarbageCollection(@Valid GarbageCollectionSaveReqVO createReqVO);

    /**
     * 更新收运计划
     *
     * @param updateReqVO 更新信息
     */
    void updateGarbageCollection(@Valid GarbageCollectionSaveReqVO updateReqVO);

    /**
     * 删除收运计划
     *
     * @param id 编号
     */
    void deleteGarbageCollection(Long id);

    /**
     * 获得收运计划
     *
     * @param id 编号
     * @return 收运计划
     */
    GarbageCollectionDO getGarbageCollection(Long id);

    /**
     * 获得收运计划分页
     *
     * @param pageReqVO 分页查询
     * @return 收运计划分页
     */
    PageResult<GarbageCollectionDO> getGarbageCollectionPage(GarbageCollectionPageReqVO pageReqVO);

    /**
     * 获得收运计划列表(详情)
     *
     */
    List<GarbageCollectionDetailDO> getGarbageCollectionListDetail();

    /**
     * 获得收运计划详情分页
     *
     * @param pageReqVO 分页查询
     * @return 收运计划详情分页
     */
    PageResult<GarbageCollectionDetailDO> getGarbageCollectionDetailPage(GarbageCollectionPageReqVO pageReqVO);
}