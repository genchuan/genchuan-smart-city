package cn.iocoder.yudao.module.datacenter.service.operationguide.faq;

import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.faq.vo.FaqPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.faq.vo.FaqSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.operationguide.faq.FaqDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 常见问题解答 Service 接口
 *
 * @author 亘川智城
 */
public interface FaqService {

    /**
     * 创建常见问题解答
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createFaq(@Valid FaqSaveReqVO createReqVO);

    /**
     * 更新常见问题解答
     *
     * @param updateReqVO 更新信息
     */
    void updateFaq(@Valid FaqSaveReqVO updateReqVO);

    /**
     * 删除常见问题解答
     *
     * @param id 编号
     */
    void deleteFaq(Long id);

    /**
     * 获得常见问题解答
     *
     * @param id 编号
     * @return 常见问题解答
     */
    FaqDO getFaq(Long id);

    /**
     * 获得常见问题解答分页
     *
     * @param pageReqVO 分页查询
     * @return 常见问题解答分页
     */
    PageResult<FaqDO> getFaqPage(FaqPageReqVO pageReqVO);

}