package cn.iocoder.yudao.module.datacenter.service.bizmngcompspatial;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.bizmngcompspatial.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.bizmngcompspatial.BizMngCompSpatialDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 管理部件空间数据 Service 接口
 *
 * @author 亘川智城
 */
public interface BizMngCompSpatialService {

    /**
     * 创建管理部件空间数据
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBizMngCompSpatial(@Valid BizMngCompSpatialSaveReqVO createReqVO);

    /**
     * 更新管理部件空间数据
     *
     * @param updateReqVO 更新信息
     */
    void updateBizMngCompSpatial(@Valid BizMngCompSpatialSaveReqVO updateReqVO);

    /**
     * 删除管理部件空间数据
     *
     * @param id 编号
     */
    void deleteBizMngCompSpatial(Long id);

    /**
     * 获得管理部件空间数据
     *
     * @param id 编号
     * @return 管理部件空间数据
     */
    BizMngCompSpatialDO getBizMngCompSpatial(Long id);

    /**
     * 获得管理部件空间数据分页
     *
     * @param pageReqVO 分页查询
     * @return 管理部件空间数据分页
     */
    PageResult<BizMngCompSpatialDO> getBizMngCompSpatialPage(BizMngCompSpatialPageReqVO pageReqVO);

}