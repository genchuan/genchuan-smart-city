package cn.iocoder.yudao.module.datacenter.service.govcivicexchange.publiconlineinterview;

import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publiconlineinterview.vo.PublicOnlineInterviewPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publiconlineinterview.vo.PublicOnlineInterviewSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.govcivicexchange.publiconlineinterview.PublicOnlineInterviewDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 在线访谈 Service 接口
 *
 * @author 亘川智城
 */
public interface PublicOnlineInterviewService {

    /**
     * 创建在线访谈
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPublicOnlineInterview(@Valid PublicOnlineInterviewSaveReqVO createReqVO);

    /**
     * 更新在线访谈
     *
     * @param updateReqVO 更新信息
     */
    void updatePublicOnlineInterview(@Valid PublicOnlineInterviewSaveReqVO updateReqVO);

    /**
     * 删除在线访谈
     *
     * @param id 编号
     */
    void deletePublicOnlineInterview(Long id);

    /**
     * 获得在线访谈
     *
     * @param id 编号
     * @return 在线访谈
     */
    PublicOnlineInterviewDO getPublicOnlineInterview(Long id);

    /**
     * 获得在线访谈分页
     *
     * @param pageReqVO 分页查询
     * @return 在线访谈分页
     */
    PageResult<PublicOnlineInterviewDO> getPublicOnlineInterviewPage(PublicOnlineInterviewPageReqVO pageReqVO);

}