package cn.iocoder.yudao.module.datacenter.service.publicinfopublish.publicsvcinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicsvcinfo.vo.PublicSvcInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicsvcinfo.vo.PublicSvcInfoSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.publicinfopublish.publicsvcinfo.PublicSvcInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 服务信息发布 Service 接口
 *
 * @author 亘川智城
 */
public interface PublicSvcInfoService {

    /**
     * 创建服务信息发布
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPublicSvcInfo(@Valid PublicSvcInfoSaveReqVO createReqVO);

    /**
     * 更新服务信息发布
     *
     * @param updateReqVO 更新信息
     */
    void updatePublicSvcInfo(@Valid PublicSvcInfoSaveReqVO updateReqVO);

    /**
     * 删除服务信息发布
     *
     * @param id 编号
     */
    void deletePublicSvcInfo(Long id);

    /**
     * 获得服务信息发布
     *
     * @param id 编号
     * @return 服务信息发布
     */
    PublicSvcInfoDO getPublicSvcInfo(Long id);

    /**
     * 获得服务信息发布分页
     *
     * @param pageReqVO 分页查询
     * @return 服务信息发布分页
     */
    PageResult<PublicSvcInfoDO> getPublicSvcInfoPage(PublicSvcInfoPageReqVO pageReqVO);

}