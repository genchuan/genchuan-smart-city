package cn.iocoder.yudao.module.kitchen.service.punishreviewledger;

import java.io.IOException;
import java.util.*;

import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.PunishReviewLedgerPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.PunishReviewLedgerRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.PunishReviewLedgerSaveReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.add.AddPunishReviewLedgerReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.cancel.CancelReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.chart.PunishReviewBarResp;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.chart.PunishReviewCancelReasonResp;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.chart.PunishReviewChartResp;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.issue.IssueReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.upload.UploadFileReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.upload.UploadFileRespVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.punishreviewledger.PunishReviewLedgerDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * 处罚通知书复审台账 Service 接口
 *
 * @author 亘川智城
 */
public interface PunishReviewLedgerService {

    /**
     * 创建处罚通知书复审台账
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPunishReviewLedger(@Valid PunishReviewLedgerSaveReqVO createReqVO);

    /**
     * 更新处罚通知书复审台账
     *
     * @param updateReqVO 更新信息
     */
    void updatePunishReviewLedger(@Valid PunishReviewLedgerSaveReqVO updateReqVO);

    /**
     * 删除处罚通知书复审台账
     *
     * @param id 编号
     */
    void deletePunishReviewLedger(Long id);

    /**
     * 获得处罚通知书复审台账
     *
     * @param id 编号
     * @return 处罚通知书复审台账
     */
    PunishReviewLedgerDO getPunishReviewLedger(Long id);

    /**
     * 获得处罚通知书复审台账分页
     *
     * @param pageReqVO 分页查询
     * @return 处罚通知书复审台账分页
     */
    PageResult<PunishReviewLedgerRespVO> getPunishReviewLedgerPage(PunishReviewLedgerPageReqVO pageReqVO);

    Long addPunishReviewLedger(@Valid AddPunishReviewLedgerReq reqVO);

    Long reviewCancel(CancelReqVO reqVO);

    Long reviewIssue(IssueReqVO reqVO);

    UploadFileRespVO uploadEvidenceFile(UploadFileReqVO reqVO, MultipartFile file);

    ResponseEntity<byte[]> downloadRectifyNoticePdfBatch(List<Long> punishNoticeIds) throws IOException;

    PunishReviewChartResp getPunishReviewChartStatistics(PunishReviewLedgerPageReqVO reqVO);

    PunishReviewBarResp getMonthReviewCount();

    PunishReviewCancelReasonResp getCancelReasonStatistics(PunishReviewLedgerPageReqVO reqVO);
}
