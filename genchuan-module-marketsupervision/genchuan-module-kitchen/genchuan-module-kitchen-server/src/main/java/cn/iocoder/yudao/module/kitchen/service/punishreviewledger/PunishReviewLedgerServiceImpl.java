package cn.iocoder.yudao.module.kitchen.service.punishreviewledger;

import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishnotice.vo.add.AddPunishNoticeReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishnotice.vo.template.DraftPunishNoticeReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.PunishReviewLedgerPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.PunishReviewLedgerRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.PunishReviewLedgerSaveReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.add.AddPunishReviewLedgerReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.cancel.CancelReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.chart.PunishReviewBarItemResp;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.chart.PunishReviewBarResp;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.chart.PunishReviewCancelReasonResp;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.chart.PunishReviewChartResp;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.issue.IssueReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.upload.UploadFileReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.upload.UploadFileRespVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.dictionary.cancelreasondict.CancelReasonDictDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.entrectifyrecord.EntRectifyRecordDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.punishnotice.PunishNoticeDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.punishreviewledger.PunishReviewLedgerDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.rectifynotice.RectifyNoticeDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.rectifyreview.RectifyReviewDO;
import cn.iocoder.yudao.module.kitchen.dal.mysql.dictionary.cancelreasondict.CancelReasonDictMapper;
import cn.iocoder.yudao.module.kitchen.dal.mysql.entrectifyrecord.EntRectifyRecordMapper;
import cn.iocoder.yudao.module.kitchen.dal.mysql.punishnotice.PunishNoticeMapper;
import cn.iocoder.yudao.module.kitchen.dal.mysql.punishreviewledger.PunishReviewLedgerMapper;
import cn.iocoder.yudao.module.kitchen.dal.mysql.rectifynotice.RectifyNoticeMapper;
import cn.iocoder.yudao.module.kitchen.dal.mysql.rectifyreview.RectifyReviewMapper;
import cn.iocoder.yudao.module.kitchen.vrv.utils.common.file.VrvFileUploadService;
import cn.iocoder.yudao.module.kitchen.vrv.utils.common.name.VrvNameUtil;
import cn.iocoder.yudao.module.kitchen.vrv.utils.common.pdf.VrvPdfGenerator;
import cn.iocoder.yudao.module.kitchen.vrv.utils.common.verify.VrvVerifyUtil;
import cn.iocoder.yudao.module.kitchen.service.punishnotice.PunishNoticeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import org.springframework.web.multipart.MultipartFile;


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
@Slf4j
public class PunishReviewLedgerServiceImpl implements PunishReviewLedgerService {

    @Resource
    private PunishNoticeMapper punishNoticeMapper;
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

    @Resource
    private PunishNoticeService punishNoticeService;

    @Resource
    private VrvFileUploadService vrvFileUploadService;
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
    public PageResult<PunishReviewLedgerRespVO> getPunishReviewLedgerPage(PunishReviewLedgerPageReqVO pageReqVO) {
        PageResult<PunishReviewLedgerRespVO> punishReviewLedgerDOPageResult = new PageResult<>();
        List<PunishReviewLedgerRespVO> punishReviewLedgerRespVOList = punishReviewLedgerMapper.selectLedgerPage(pageReqVO);

        // 当前时间（只取一次，避免循环内多次调用）
        LocalDateTime now = LocalDateTime.now();

        // 2. 计算逾期标识
//        for (PunishReviewLedgerRespVO item : punishReviewLedgerRespVOList) {
//
//            // 默认未逾期
//            item.setOverdueFlag(0);
//
//            // 判空（非常关键，避免 NPE）
//            if (item.getPaymentDeadlineTime() == null || item.getReviewStatus() == null) {
//                continue;
//            }
//
//            // 判断是否“已下发”
//            if ("已下发".equals(item.getReviewStatus())) {
//
//                // 判断是否超过缴费截止时间
//                if (now.isAfter(item.getPaymentDeadlineTime())) {
//                    item.setOverdueFlag(1);
//                }
//            }
//        }


        //配置 分页参数
        punishReviewLedgerDOPageResult.setList(punishReviewLedgerRespVOList);
        punishReviewLedgerDOPageResult.setTotal(punishReviewLedgerMapper.selectLedgerPageCount(pageReqVO));

//        PageResult<PunishReviewLedgerRespVO> result =BeanUtils.toBean(punishReviewLedgerDOPageResult,PunishReviewLedgerRespVO.class);
        return punishReviewLedgerDOPageResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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

        punishLedger.setEntRectifyRecordId(reqVO.getEntRectifyRecordId());

        // ===== 后端自动字段 =====
        reqVO.setLegalBasis("《中华人民共和国食品安全法》第三十条");
        punishLedger.setLegalBasis(reqVO.getLegalBasis());

        punishLedger.setLedgerCode(VrvNameUtil.generateCode("PNRL")); // 台账编号
        punishLedger.setEntId(entId);
        punishLedger.setIllegalTypeId(ledger.getIllegalTypeId());
        punishLedger.setIllegalLevelId(ledger.getIllegalLevelId());
        punishLedger.setEvidenceUrl(ledger.getEvidenceUrl());
        punishLedger.setReviewStatus("待复审");

        // =========================
        // 5. 草拟时间
        // =========================
        punishLedger.setDraftTime(LocalDateTime.now());

        //6.缴费截止时间 TODO 下发才产生
        punishLedger.setPaymentDeadlineTime(LocalDateTime.now().plusDays(30));

        // =========================
        // 7. 入库
        // =========================
        punishReviewLedgerMapper.insert(punishLedger);

        //7.回绑处罚台账id到对应的企业整改记录
        record.setPunishReviewId(punishLedger.getId());
        entRectifyRecordMapper.updateById(record);

        //8.TODO 生成处罚决定书
        AddPunishNoticeReq addPunishNoticeReq =new AddPunishNoticeReq();
        addPunishNoticeReq.setPunishReviewId(punishLedger.getId());
        Long punishNoticeId = punishNoticeService.addPunishNotice(addPunishNoticeReq);

        //9.草拟处罚决定书
        DraftPunishNoticeReq draftPunishNoticeReq = new DraftPunishNoticeReq();
        draftPunishNoticeReq.setPunishReviewNoticeId(punishNoticeId);
        punishNoticeService.generatePunishNoticeDraft(draftPunishNoticeReq);

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
        VrvVerifyUtil.verifyNotNullWithMsg(cancelReason, "请选择正确的撤销原因");

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

        //5.TODO 更新缴费日期
//        ledgerDO.setPaymentDeadlineTime(LocalDateTime.now().plusDays(30));

        // =========================
        // 5. 更新数据库
        // =========================
        punishReviewLedgerMapper.updateById(ledgerDO);

        // 6. 返回台账ID
        // =========================
        return ledgerDO.getId();
    }

    @Override
    public UploadFileRespVO uploadEvidenceFile(UploadFileReqVO reqVO, MultipartFile file) {
        // 1. 校验整改复审台账是否存在
//        EntRectifyRecordDO entRectifyRecordDO = entRectifyRecordMapper.selectById(reqVO.getEntRectifyRecordId());
        PunishReviewLedgerDO punishReviewLedgerDO = punishReviewLedgerMapper.selectById(reqVO.getPunishReviewLedgerId());
        VrvVerifyUtil.verifyNotNullWithMsg(punishReviewLedgerDO,"处罚台账记录不存在数据库");


        try {
            // 2. 上传文件到 MinIO
            String fileUrl = vrvFileUploadService.uploadAvatar(file);

            // 3. 构建文件信息对象
            Map<String, String> fileInfo = new HashMap<>();
            fileInfo.put("url", fileUrl);
            fileInfo.put("name", file.getOriginalFilename());

            // 根据后缀决定 type
            String lowerName = file.getOriginalFilename().toLowerCase();
            if (lowerName.endsWith(".png") || lowerName.endsWith(".jpg") || lowerName.endsWith(".jpeg") || lowerName.endsWith(".gif")) {
                fileInfo.put("type", "image");
            } else if (lowerName.endsWith(".xls") || lowerName.endsWith(".xlsx")) {
                fileInfo.put("type", "excel");
            } else if (lowerName.endsWith(".doc") || lowerName.endsWith(".docx")) {
                fileInfo.put("type", "word");
            } else {
                fileInfo.put("type", "file"); // 其他通用文件
            }

            // 4. 获取原有 JSON
            String oldJson = punishReviewLedgerDO.getEvidenceUrl();
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, String>> fileList;

            if (oldJson == null || oldJson.isEmpty()) {
                fileList = new ArrayList<>();
            } else {
                // 解析原有 JSON
                fileList = objectMapper.readValue(oldJson, new TypeReference<List<Map<String, String>>>() {});
            }

            // 5. 添加新文件
            fileList.add(fileInfo);

            // 6. 转成 JSON 字符串
            String newJson = objectMapper.writeValueAsString(fileList);

            // 7. 更新数据库
            punishReviewLedgerDO.setEvidenceUrl(newJson);

            punishReviewLedgerMapper.updateById(punishReviewLedgerDO);

//            //TODO 修改状态 为 整改中
//            entRectifyRecordDO.setRectifyStatus("整改中");
//            entRectifyRecordDO.setRectifyDesc(reqVO.getRectifyDesc()!=null?
//                    reqVO.getRectifyDesc():"已进行整改");

//            entRectifyRecordMapper.updateById(entRectifyRecordDO);

            // 8. 返回结果
            UploadFileRespVO respVO = new UploadFileRespVO();
            respVO.setBizDataId(punishReviewLedgerDO.getId());
            respVO.setFileUrl(fileUrl);
            respVO.setFileName(file.getOriginalFilename());

            return respVO;

        } catch (Exception e) {
            log.error("上传文件资料失败", e);

            if (e instanceof ServiceException) {
                throw (ServiceException) e;
            }

            throw new ServiceException(500, "上传文件失败");
        }
    }

    @Override
    public ResponseEntity<byte[]> downloadRectifyNoticePdfBatch(List<Long> punishNoticeIds) throws IOException {
        System.out.println("cs2026-03-24 10:23:09:6586");
        VrvVerifyUtil.verifyNotNullWithMsg(punishNoticeIds, "ID不能为空");

        // 1. 查询通知书数据
        List<PunishNoticeDO> list = punishNoticeMapper.selectBatchIds(punishNoticeIds);
        log.info("批量通知书id："+punishNoticeIds);
        VrvVerifyUtil.verifyNotNullWithMsg(list, "通知书不存在");

        // 2. 创建ZIP流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);

        for (PunishNoticeDO item : list) {
            String html = item.getDecisionContent();
            if (html == null) {
                continue;
            }

            // 3. HTML → PDF
            VrvPdfGenerator pdfGenerator = new VrvPdfGenerator();
            byte[] pdfBytes = pdfGenerator.generatePdfResponse(html).getBody();
            if (pdfBytes == null || pdfBytes.length == 0) {
                log.warn("PDF生成失败，处罚决定书ID：{}", item.getId());
                continue;
            }

            // 4. 写入ZIP（文件名使用UTF-8，避免中文乱码）
            String fileName = "处罚决定书_" + item.getPunishReviewId() + ".pdf";
            // ZIP内部文件名使用UTF-8编码
            ZipEntry entry = new ZipEntry(fileName);
//            ZipEntry entry = new ZipEntry(new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            zos.putNextEntry(entry);
            zos.write(pdfBytes);
            zos.closeEntry();
        }

        zos.close();

        // 5. 返回ZIP（修复中文文件名编码问题）
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        // 使用RFC 5987标准编码中文文件名
        String fileName = "处罚决定书.zip";
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString())
                .replace("+", "%20"); // 替换空格编码
        headers.add("Content-Disposition",
                String.format("attachment; filename=\"%s\"; filename*=%s",
                        new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1),
                        encodedFileName));

        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
    }

    @Override
    public PunishReviewChartResp getPunishReviewChartStatistics(PunishReviewLedgerPageReqVO reqVO) {
        PunishReviewChartResp resp = punishReviewLedgerMapper.getPunishReviewChartResp(reqVO);

        if (resp == null) {
            resp = new PunishReviewChartResp();
            resp.setPendingReviewCount(0);
            resp.setIssuedCount(0);
            resp.setCanceledCount(0);
            resp.setTotalCount(0);
            resp.setPendingReviewRatio(BigDecimal.ZERO);
            resp.setIssuedRatio(BigDecimal.ZERO);
            resp.setCanceledRatio(BigDecimal.ZERO);
            return resp;
        }

        int total = resp.getTotalCount();
        if (total > 0) {
            resp.setPendingReviewRatio(calcRatio(resp.getPendingReviewCount(), total));
            resp.setIssuedRatio(calcRatio(resp.getIssuedCount(), total));
            resp.setCanceledRatio(calcRatio(resp.getCanceledCount(), total));
        }

        return resp;
    }

    @Override
    public PunishReviewBarResp getMonthReviewCount() {
        List<PunishReviewBarItemResp> list = punishReviewLedgerMapper.selectMonthReviewCount();

        PunishReviewBarResp resp = new PunishReviewBarResp();
        resp.setList(list);
        return resp;
    }

    @Override
    public PunishReviewCancelReasonResp getCancelReasonStatistics(PunishReviewLedgerPageReqVO reqVO) {
        List<PunishReviewCancelReasonResp.CancelReasonItem> list = punishReviewLedgerMapper.selectCancelReasonCount(reqVO);

        int total = 0;
        if (list != null) {
            for (PunishReviewCancelReasonResp.CancelReasonItem item : list) {
                if (item.getCount() != null) {
                    total += item.getCount();
                }
            }
        }

        if (list != null && total > 0) {
            for (PunishReviewCancelReasonResp.CancelReasonItem item : list) {
                item.setRatio(calcRatio(item.getCount(), total));
            }
        }

        PunishReviewCancelReasonResp resp = new PunishReviewCancelReasonResp();
        resp.setList(list);
        resp.setTotalCount(total);
        return resp;
    }

    private BigDecimal calcRatio(Integer count, int total) {
        if (count == null || count == 0) return BigDecimal.ZERO;
        return BigDecimal.valueOf(count * 100.0 / total).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

}
