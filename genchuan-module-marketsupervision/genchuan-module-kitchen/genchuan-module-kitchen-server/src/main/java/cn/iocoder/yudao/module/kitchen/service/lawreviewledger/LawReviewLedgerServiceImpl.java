package cn.iocoder.yudao.module.kitchen.service.lawreviewledger;

import cn.iocoder.yudao.module.kitchen.controller.admin.lawreviewledger.vo.LawReviewLedgerPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.lawreviewledger.vo.LawReviewLedgerSaveReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.lawreviewledger.LawReviewLedgerDO;
import cn.iocoder.yudao.module.kitchen.dal.mysql.lawreviewledger.LawReviewLedgerMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.kitchen.enums.ErrorCodeConstants.*;

/**
 * 执法复审总台账 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class LawReviewLedgerServiceImpl implements LawReviewLedgerService {

    @Resource
    private LawReviewLedgerMapper lawReviewLedgerMapper;

    @Override
    public Long createLawReviewLedger(LawReviewLedgerSaveReqVO createReqVO) {
        // 插入
        LawReviewLedgerDO lawReviewLedger = BeanUtils.toBean(createReqVO, LawReviewLedgerDO.class);
        lawReviewLedgerMapper.insert(lawReviewLedger);
        // 返回
        return lawReviewLedger.getId();
    }

    @Override
    public void updateLawReviewLedger(LawReviewLedgerSaveReqVO updateReqVO) {
        // 校验存在
        validateLawReviewLedgerExists(updateReqVO.getId());
        // 更新
        LawReviewLedgerDO updateObj = BeanUtils.toBean(updateReqVO, LawReviewLedgerDO.class);
        lawReviewLedgerMapper.updateById(updateObj);
    }

    @Override
    public void deleteLawReviewLedger(Long id) {
        // 校验存在
        validateLawReviewLedgerExists(id);
        // 删除
        lawReviewLedgerMapper.deleteById(id);
    }

    private void validateLawReviewLedgerExists(Long id) {
        if (lawReviewLedgerMapper.selectById(id) == null) {
            throw exception(LAW_REVIEW_LEDGER_NOT_EXISTS);
        }
    }

    @Override
    public LawReviewLedgerDO getLawReviewLedger(Long id) {
        return lawReviewLedgerMapper.selectById(id);
    }

    @Override
    public PageResult<LawReviewLedgerDO> getLawReviewLedgerPage(LawReviewLedgerPageReqVO pageReqVO) {
        return lawReviewLedgerMapper.selectPage(pageReqVO);
    }

}
