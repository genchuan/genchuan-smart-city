package cn.iocoder.yudao.module.datacenter.service.govcivicexchange.publicopinioncollect;

import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicopinioncollect.vo.PublicOpinionCollectPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicopinioncollect.vo.PublicOpinionCollectSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.govcivicexchange.publicopinioncollect.PublicOpinionCollectDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 民意征集 Service 接口
 *
 * @author 亘川智城
 */
public interface PublicOpinionCollectService {

    /**
     * 创建民意征集
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPublicOpinionCollect(@Valid PublicOpinionCollectSaveReqVO createReqVO);

    /**
     * 更新民意征集
     *
     * @param updateReqVO 更新信息
     */
    void updatePublicOpinionCollect(@Valid PublicOpinionCollectSaveReqVO updateReqVO);

    /**
     * 删除民意征集
     *
     * @param id 编号
     */
    void deletePublicOpinionCollect(Long id);

    /**
     * 获得民意征集
     *
     * @param id 编号
     * @return 民意征集
     */
    PublicOpinionCollectDO getPublicOpinionCollect(Long id);

    /**
     * 获得民意征集分页
     *
     * @param pageReqVO 分页查询
     * @return 民意征集分页
     */
    PageResult<PublicOpinionCollectDO> getPublicOpinionCollectPage(PublicOpinionCollectPageReqVO pageReqVO);

}