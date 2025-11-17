package cn.iocoder.yudao.module.datacenter.service.govcivicexchange.publicaisvc;

import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicaisvc.vo.PublicAiSvcPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicaisvc.vo.PublicAiSvcSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.govcivicexchange.publicaisvc.PublicAiSvcDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 智能客服知识库 Service 接口
 *
 * @author 亘川智城
 */
public interface PublicAiSvcService {

    /**
     * 创建智能客服知识库
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPublicAiSvc(@Valid PublicAiSvcSaveReqVO createReqVO);

    /**
     * 更新智能客服知识库
     *
     * @param updateReqVO 更新信息
     */
    void updatePublicAiSvc(@Valid PublicAiSvcSaveReqVO updateReqVO);

    /**
     * 删除智能客服知识库
     *
     * @param id 编号
     */
    void deletePublicAiSvc(Long id);

    /**
     * 获得智能客服知识库
     *
     * @param id 编号
     * @return 智能客服知识库
     */
    PublicAiSvcDO getPublicAiSvc(Long id);

    /**
     * 获得智能客服知识库分页
     *
     * @param pageReqVO 分页查询
     * @return 智能客服知识库分页
     */
    PageResult<PublicAiSvcDO> getPublicAiSvcPage(PublicAiSvcPageReqVO pageReqVO);

}