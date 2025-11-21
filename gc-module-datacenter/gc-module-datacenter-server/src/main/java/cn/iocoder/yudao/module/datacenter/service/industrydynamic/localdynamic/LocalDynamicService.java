package cn.iocoder.yudao.module.datacenter.service.industrydynamic.localdynamic;

import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.localdynamic.vo.LocalDynamicPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.localdynamic.vo.LocalDynamicSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.industrydynamic.localdynamic.LocalDynamicDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 本地行业动态 Service 接口
 *
 * @author 亘川智城
 */
public interface LocalDynamicService {

    /**
     * 创建本地行业动态
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createLocalDynamic(@Valid LocalDynamicSaveReqVO createReqVO);

    /**
     * 更新本地行业动态
     *
     * @param updateReqVO 更新信息
     */
    void updateLocalDynamic(@Valid LocalDynamicSaveReqVO updateReqVO);

    /**
     * 删除本地行业动态
     *
     * @param id 编号
     */
    void deleteLocalDynamic(Long id);

    /**
     * 获得本地行业动态
     *
     * @param id 编号
     * @return 本地行业动态
     */
    LocalDynamicDO getLocalDynamic(Long id);

    /**
     * 获得本地行业动态分页
     *
     * @param pageReqVO 分页查询
     * @return 本地行业动态分页
     */
    PageResult<LocalDynamicDO> getLocalDynamicPage(LocalDynamicPageReqVO pageReqVO);

}