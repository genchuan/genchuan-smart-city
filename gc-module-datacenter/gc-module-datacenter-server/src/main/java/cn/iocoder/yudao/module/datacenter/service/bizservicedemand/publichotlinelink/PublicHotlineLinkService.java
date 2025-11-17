package cn.iocoder.yudao.module.datacenter.service.bizservicedemand.publichotlinelink;

import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publichotlinelink.vo.PublicHotlineLinkPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publichotlinelink.vo.PublicHotlineLinkSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.bizservicedemand.publichotlinelink.PublicHotlineLinkDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 热线对接 Service 接口
 *
 * @author 亘川智城
 */
public interface PublicHotlineLinkService {

    /**
     * 创建热线对接
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPublicHotlineLink(@Valid PublicHotlineLinkSaveReqVO createReqVO);

    /**
     * 更新热线对接
     *
     * @param updateReqVO 更新信息
     */
    void updatePublicHotlineLink(@Valid PublicHotlineLinkSaveReqVO updateReqVO);

    /**
     * 删除热线对接
     *
     * @param id 编号
     */
    void deletePublicHotlineLink(Long id);

    /**
     * 获得热线对接
     *
     * @param id 编号
     * @return 热线对接
     */
    PublicHotlineLinkDO getPublicHotlineLink(Long id);

    /**
     * 获得热线对接分页
     *
     * @param pageReqVO 分页查询
     * @return 热线对接分页
     */
    PageResult<PublicHotlineLinkDO> getPublicHotlineLinkPage(PublicHotlineLinkPageReqVO pageReqVO);

}