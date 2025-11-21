package cn.iocoder.yudao.module.datacenter.service.bizservicedemand.publiccomplain;

import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publiccomplain.vo.PublicComplainPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publiccomplain.vo.PublicComplainSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.bizservicedemand.publiccomplain.PublicComplainDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 投诉举报 Service 接口
 *
 * @author 亘川智城
 */
public interface PublicComplainService {

    /**
     * 创建投诉举报
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPublicComplain(@Valid PublicComplainSaveReqVO createReqVO);

    /**
     * 更新投诉举报
     *
     * @param updateReqVO 更新信息
     */
    void updatePublicComplain(@Valid PublicComplainSaveReqVO updateReqVO);

    /**
     * 删除投诉举报
     *
     * @param id 编号
     */
    void deletePublicComplain(Long id);

    /**
     * 获得投诉举报
     *
     * @param id 编号
     * @return 投诉举报
     */
    PublicComplainDO getPublicComplain(Long id);

    /**
     * 获得投诉举报分页
     *
     * @param pageReqVO 分页查询
     * @return 投诉举报分页
     */
    PageResult<PublicComplainDO> getPublicComplainPage(PublicComplainPageReqVO pageReqVO);

}