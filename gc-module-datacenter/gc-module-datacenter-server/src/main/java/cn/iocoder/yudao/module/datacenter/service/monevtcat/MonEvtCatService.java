package cn.iocoder.yudao.module.datacenter.service.monevtcat;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcat.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcat.MonEvtCatDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 监测事件分类配置 Service 接口
 *
 * @author 亘川智城
 */
public interface MonEvtCatService {

    /**
     * 创建监测事件分类配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMonEvtCat(@Valid MonEvtCatSaveReqVO createReqVO);

    /**
     * 更新监测事件分类配置
     *
     * @param updateReqVO 更新信息
     */
    void updateMonEvtCat(@Valid MonEvtCatSaveReqVO updateReqVO);

    /**
     * 删除监测事件分类配置
     *
     * @param id 编号
     */
    void deleteMonEvtCat(Long id);

    /**
     * 获得监测事件分类配置
     *
     * @param id 编号
     * @return 监测事件分类配置
     */
    MonEvtCatDO getMonEvtCat(Long id);

    /**
     * 获得监测事件分类配置分页
     *
     * @param pageReqVO 分页查询
     * @return 监测事件分类配置分页
     */
    PageResult<MonEvtCatDO> getMonEvtCatPage(MonEvtCatPageReqVO pageReqVO);

}