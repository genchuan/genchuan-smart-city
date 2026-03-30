package cn.iocoder.yudao.module.kitchen.service.lawreviewledger;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.kitchen.controller.admin.lawreviewledger.vo.LawReviewLedgerPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.lawreviewledger.vo.LawReviewLedgerSaveReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.lawreviewledger.LawReviewLedgerDO;
import jakarta.validation.Valid;

/**
 * 执法复审总台账 Service 接口
 *
 * @author 亘川智城
 */
public interface LawReviewLedgerService {

    /**
     * 创建执法复审总台账
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createLawReviewLedger(@Valid LawReviewLedgerSaveReqVO createReqVO);

    /**
     * 更新执法复审总台账
     *
     * @param updateReqVO 更新信息
     */
    void updateLawReviewLedger(@Valid LawReviewLedgerSaveReqVO updateReqVO);

    /**
     * 删除执法复审总台账
     *
     * @param id 编号
     */
    void deleteLawReviewLedger(Long id);

    /**
     * 获得执法复审总台账
     *
     * @param id 编号
     * @return 执法复审总台账
     */
    LawReviewLedgerDO getLawReviewLedger(Long id);

    /**
     * 获得执法复审总台账分页
     *
     * @param pageReqVO 分页查询
     * @return 执法复审总台账分页
     */
    PageResult<LawReviewLedgerDO> getLawReviewLedgerPage(LawReviewLedgerPageReqVO pageReqVO);

}
