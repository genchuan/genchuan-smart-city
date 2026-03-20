package cn.iocoder.yudao.module.kitchen.service.punishreviewledger;

import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.PunishReviewLedgerPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.PunishReviewLedgerSaveReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.add.AddPunishReviewLedgerReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.cancel.CancelReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.issue.IssueReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.dictionary.cancelreasondict.CancelReasonDictDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.entrectifyrecord.EntRectifyRecordDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.punishreviewledger.PunishReviewLedgerDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.rectifynotice.RectifyNoticeDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.rectifyreview.RectifyReviewDO;
import cn.iocoder.yudao.module.kitchen.dal.mysql.dictionary.cancelreasondict.CancelReasonDictMapper;
import cn.iocoder.yudao.module.kitchen.dal.mysql.entrectifyrecord.EntRectifyRecordMapper;
import cn.iocoder.yudao.module.kitchen.dal.mysql.punishreviewledger.PunishReviewLedgerMapper;
import cn.iocoder.yudao.module.kitchen.dal.mysql.rectifynotice.RectifyNoticeMapper;
import cn.iocoder.yudao.module.kitchen.dal.mysql.rectifyreview.RectifyReviewMapper;
import cn.iocoder.yudao.module.kitchen.framework.lxsutils.common.name.NameUtil;
import cn.iocoder.yudao.module.kitchen.framework.lxsutils.common.verify.VerifyUtil;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.kitchen.enums.ErrorCodeConstants.*;

/**
 * 处罚通知书复审台账 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PunishReviewLedgerServiceImpl implements PunishReviewLedgerService {

    @Resource
    private PunishReviewLedgerMapper punishReviewLedgerMapper;

    @Resource
    private RectifyNoticeMapper rectifyNoticeMapper;

    @Resource
    private RectifyReviewMapper rectifyReviewMapper;
    @Resource
    private EntRectifyRecordMapper entRectifyRecordMapper;

    @Resource
    private CancelReasonDictMapper cancelReasonDictMapper;
    @Override
    public Long createPunishReviewLedger(PunishReviewLedgerSaveReqVO createReqVO) {
        // 插入
        PunishReviewLedgerDO punishReviewLedger = BeanUtils.toBean(createReqVO, PunishReviewLedgerDO.class);
        punishReviewLedgerMapper.insert(punishReviewLedger);
        // 返回
        return punishReviewLedger.getId();
    }

    @Override
    public void updatePunishReviewLedger(PunishReviewLedgerSaveReqVO updateReqVO) {
        // 校验存在
        validatePunishReviewLedgerExists(updateReqVO.getId());
        // 更新
        PunishReviewLedgerDO updateObj = BeanUtils.toBean(updateReqVO, PunishReviewLedgerDO.class);
        punishReviewLedgerMapper.updateById(updateObj);
    }

    @Override
    public void deletePunishReviewLedger(Long id) {
        // 校验存在
        validatePunishReviewLedgerExists(id);
        // 删除
        punishReviewLedgerMapper.deleteById(id);
    }

    private void validatePunishReviewLedgerExists(Long id) {
        if (punishReviewLedgerMapper.selectById(id) == null) {
            throw exception(PUNISH_REVIEW_LEDGER_NOT_EXISTS);
        }
    }

    @Override
    public PunishReviewLedgerDO getPunishReviewLedger(Long id) {
        return punishReviewLedgerMapper.selectById(id);
    }

    @Override
    public PageResult<PunishReviewLedgerDO> getPunishReviewLedgerPage(PunishReviewLedgerPageReqVO pageReqVO) {
        return punishReviewLedgerMapper.selectPage(pageReqVO);
    }

    @Override
    public Long addPunishReviewLedger(AddPunishReviewLedgerReq reqVO) {

        // =========================
        // 1. 校验 & 查询整改记录
        // =========================
        EntRectifyRecordDO record = entRectifyRecordMapper.selectById(reqVO.getEntRectifyRecordId());
        if (record == null) {
            throw exception("整改记录不存在");
        }
        if (!"整改不合格".equals(record.getRectifyStatus())){
            throw exception("只有企业整改记录为\"整改不合格\"才能发起处罚审核");
        }

        // 企业ID（直接拿）
        Long entId = record.getEntId();

        // =========================
        // 2. 查询整改通知书
        // =========================
        RectifyNoticeDO notice = rectifyNoticeMapper.selectById(record.getRectifyNoticeId());
        if (notice == null) {
            throw exception("整改通知书不存在");
        }

        // =========================
        // 3. 查询整改台账
        // =========================
        RectifyReviewDO ledger = rectifyReviewMapper.selectById(notice.getRectifyReviewId());
        if (ledger == null) {
            throw exception("整改台账不存在");
        }

        // =========================
        // 4. 组装处罚复审台账数据
        // =========================
        PunishReviewLedgerDO punishLedger = new PunishReviewLedgerDO();

        // ===== 前端字段 =====
        punishLedger.setDraftPunishAmt(reqVO.getDraftPunishAmt());
        punishLedger.setLegalBasis(reqVO.getLegalBasis());
        punishLedger.setEntRectifyRecordId(reqVO.getEntRectifyRecordId());

        // ===== 后端自动字段 =====
        punishLedger.setLedgerCode(NameUtil.generateCode("PNRL")); // 台账编号
        punishLedger.setEntId(entId);
        punishLedger.setIllegalTypeId(ledger.getIllegalTypeId());
        punishLedger.setIllegalLevelId(ledger.getIllegalLevelId());
        punishLedger.setEvidenceUrl(ledger.getEvidenceUrl());
        punishLedger.setReviewStatus("待复审");

        // =========================
        // 5. 草拟时间
        // =========================
        punishLedger.setDraftTime(LocalDateTime.now());

        // =========================
        // 6. 入库
        // =========================
        punishReviewLedgerMapper.insert(punishLedger);

        //7.回绑处罚台账id到对应的企业整改记录
        record.setPunishReviewId(punishLedger.getId());
        entRectifyRecordMapper.updateById(record);

        return punishLedger.getId();
    }

    @Override
    public Long reviewCancel(CancelReqVO reqVO) {

        // =========================
        // 1. 查询处罚复审台账
        // =========================
        PunishReviewLedgerDO ledgerDO = punishReviewLedgerMapper.selectById(reqVO.getId());
        if (ledgerDO == null) {
            throw exception("台账不存在");
        }

        // =========================
        // 2. 校验当前状态（必须是待复审）
        // =========================
        if (!"待复审".equals(ledgerDO.getReviewStatus())) {
            throw exception("当前台账状态不是待复审，无法撤销");
        }

        // =========================
        // 3. 校验撤销原因
        // =========================
        if (reqVO.getCancelReasonId() == null) {
            throw exception("撤销原因Id不能为空");
        }

        CancelReasonDictDO cancelReason = cancelReasonDictMapper.selectById(reqVO.getCancelReasonId());
        VerifyUtil.verifyNotNullWithMsg(cancelReason, "请选择正确的撤销原因");

        // =========================
        // 4. 更新台账状态
        // =========================
        ledgerDO.setReviewStatus("已撤销");              // 状态：已撤销
        ledgerDO.setReviewBy(getLoginUserId());        // 当前登录人
        ledgerDO.setReviewTime(LocalDateTime.now());  // 复审时间
        ledgerDO.setCancelTime(LocalDateTime.now());  // 撤销时间
        ledgerDO.setCancelReasonId(reqVO.getCancelReasonId()); // 撤销原因

        // =========================
        // 5. 更新数据库
        // =========================
        punishReviewLedgerMapper.updateById(ledgerDO);

        // =========================
        // 6. 返回ID
        // =========================
        return ledgerDO.getId();
    }

    @Override
    @Transactional
    public Long reviewIssue(IssueReqVO reqVO) {

        // =========================
        // 1. 查询处罚复审台账
        // =========================
        PunishReviewLedgerDO ledgerDO = punishReviewLedgerMapper.selectById(reqVO.getId());
        if (ledgerDO == null) {
            throw exception("台账不存在");
        }

        // =========================
        // 2. 校验当前状态（必须是待复审）
        // =========================
        if (!"待复审".equals(ledgerDO.getReviewStatus())) {
            throw exception("当前台账状态不是待复审，无法下发");
        }

        // =========================
        // 3. （可选）校验必要字段
        // =========================
        // 比如处罚金额、依据等是否为空（按你业务决定）
        if (ledgerDO.getDraftPunishAmt() == null) {
            throw exception("处罚金额不能为空，无法下发");
        }

        if (ledgerDO.getLegalBasis() == null || ledgerDO.getLegalBasis().trim().isEmpty()) {
            throw exception("处罚依据不能为空，无法下发");
        }

        // =========================
        // 4. 更新复审信息
        // =========================
        ledgerDO.setReviewStatus("已下发");          // 状态：已下发
        ledgerDO.setReviewBy(getLoginUserId());    // 当前登录用户
        ledgerDO.setReviewTime(LocalDateTime.now()); // 当前时间

        // =========================
        // 5. 更新数据库
        // =========================
        punishReviewLedgerMapper.updateById(ledgerDO);

        // 6. 返回台账ID
        // =========================
        return ledgerDO.getId();
    }

}
