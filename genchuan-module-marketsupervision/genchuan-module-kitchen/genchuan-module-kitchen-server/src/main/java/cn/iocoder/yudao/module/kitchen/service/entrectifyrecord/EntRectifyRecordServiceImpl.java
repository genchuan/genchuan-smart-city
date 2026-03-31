package cn.iocoder.yudao.module.kitchen.service.entrectifyrecord;

import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.EntRectifyRecordPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.EntRectifyRecordSaveReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.add.AddEntRectifyRecordReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.review.ReviewApproveReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.review.ReviewRejectReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.upload.UploadFileReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.upload.UploadFileRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.add.AddPunishReviewLedgerReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.upload.UploadEvidenceFileRespVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.entrectifyrecord.EntRectifyRecordDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.rectifynotice.RectifyNoticeDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.rectifyreview.RectifyReviewDO;
import cn.iocoder.yudao.module.kitchen.dal.mysql.entrectifyrecord.EntRectifyRecordMapper;
import cn.iocoder.yudao.module.kitchen.dal.mysql.rectifynotice.RectifyNoticeMapper;
import cn.iocoder.yudao.module.kitchen.dal.mysql.rectifyreview.RectifyReviewMapper;
import cn.iocoder.yudao.module.kitchen.framework.lxsutils.common.file.FileUploadService;
import cn.iocoder.yudao.module.kitchen.framework.lxsutils.common.name.NameUtil;
import cn.iocoder.yudao.module.kitchen.framework.lxsutils.common.verify.VerifyUtil;
import cn.iocoder.yudao.module.kitchen.service.punishreviewledger.PunishReviewLedgerService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import org.springframework.web.multipart.MultipartFile;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.kitchen.enums.ErrorCodeConstants.*;

/**
 * 企业整改记录 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
@Slf4j
public class EntRectifyRecordServiceImpl implements EntRectifyRecordService {

    @Resource
    private EntRectifyRecordMapper entRectifyRecordMapper;

    @Resource
    private RectifyNoticeMapper rectifyNoticeMapper;

    @Resource
    private RectifyReviewMapper rectifyReviewMapper;

    @Resource
    private FileUploadService fileUploadService;

    @Resource
    private PunishReviewLedgerService punishReviewLedgerService;

    @Override
    public Long createEntRectifyRecord(EntRectifyRecordSaveReqVO createReqVO) {
        // 插入
        EntRectifyRecordDO entRectifyRecord = BeanUtils.toBean(createReqVO, EntRectifyRecordDO.class);
        entRectifyRecordMapper.insert(entRectifyRecord);
        // 返回
        return entRectifyRecord.getId();
    }

    @Override
    public void updateEntRectifyRecord(EntRectifyRecordSaveReqVO updateReqVO) {
        // 校验存在
        validateEntRectifyRecordExists(updateReqVO.getId());
        // 更新
        EntRectifyRecordDO updateObj = BeanUtils.toBean(updateReqVO, EntRectifyRecordDO.class);
        entRectifyRecordMapper.updateById(updateObj);
    }

    @Override
    public void deleteEntRectifyRecord(Long id) {
        // 校验存在
        validateEntRectifyRecordExists(id);
        // 删除
        entRectifyRecordMapper.deleteById(id);
    }

    private void validateEntRectifyRecordExists(Long id) {
        if (entRectifyRecordMapper.selectById(id) == null) {
            throw exception(ENT_RECTIFY_RECORD_NOT_EXISTS);
        }
    }

    @Override
    public EntRectifyRecordDO getEntRectifyRecord(Long id) {
        return entRectifyRecordMapper.selectById(id);
    }

    @Override
    public PageResult<EntRectifyRecordDO> getEntRectifyRecordPage(EntRectifyRecordPageReqVO pageReqVO) {
        return entRectifyRecordMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long addEntRectifyRecord(AddEntRectifyRecordReqVO createReqVO) {

        //0. 插入实体
        EntRectifyRecordDO insertDO = new EntRectifyRecordDO();

        //1.获取整改通知书
        RectifyNoticeDO rectifyNoticeDO = rectifyNoticeMapper.selectById(createReqVO.getRectifyNoticeId());
        VerifyUtil.verifyNotNullWithMsg(rectifyNoticeDO,"整改通知书不存在数据库");
        if (!"未送达".equals(rectifyNoticeDO.getReceiveStatus())){
            throw exception("只有处于待送达的整改通知书才能产生企业记录");
        }

        insertDO.setRectifyNoticeId(createReqVO.getRectifyNoticeId());

        //2.通过整改通知书获取 整改复审台账id
        VerifyUtil.verifyNotNullSimple(rectifyNoticeDO.getRectifyReviewId());
        insertDO.setRectifyReviewId(rectifyNoticeDO.getRectifyReviewId());

        //3.获取企业ID
        RectifyReviewDO rectifyReviewDO = rectifyReviewMapper.selectById(insertDO.getRectifyReviewId());
        VerifyUtil.verifyNotNullSimple(rectifyReviewDO);

        Long entId = rectifyReviewDO.getEntId();
        insertDO.setEntId(entId);

        //4.自动生成编号
        insertDO.setUniCode(NameUtil.generateCode("ERRD"));

        //5.整改状态
        insertDO.setRectifyStatus("未整改");

        //6.插入
        Long id = (long) entRectifyRecordMapper.insert(insertDO);

        //7.更新 整改通知 的 送达时间和 送达状态
        rectifyNoticeDO.setReceiveTime(LocalDateTime.now());
        rectifyNoticeDO.setReceiveStatus("已送达");

        rectifyNoticeMapper.updateById(rectifyNoticeDO);

        //7.返回id
        return insertDO.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UploadFileRespVO uploadEvidenceFile(UploadFileReqVO reqVO, MultipartFile file) {
        // 1. 校验整改复审台账是否存在
        EntRectifyRecordDO entRectifyRecordDO = entRectifyRecordMapper.selectById(reqVO.getEntRectifyRecordId());
        VerifyUtil.verifyNotNullWithMsg(entRectifyRecordDO,"企业整改记录不存在数据库");


        try {
            // 2. 上传文件到 MinIO
            String fileUrl = fileUploadService.uploadAvatar(file);

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
            String oldJson = entRectifyRecordDO.getRectifyEvidenceUrl();
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
            entRectifyRecordDO.setRectifyEvidenceUrl(newJson);

            //修改状态 为 整改中
            entRectifyRecordDO.setRectifyStatus("整改中");
            entRectifyRecordDO.setRectifyDesc(reqVO.getRectifyDesc()!=null?
                    reqVO.getRectifyDesc():"已进行整改");

            entRectifyRecordMapper.updateById(entRectifyRecordDO);

            // 8. 返回结果
            UploadFileRespVO respVO = new UploadFileRespVO();
            respVO.setBizDataId(entRectifyRecordDO.getId());
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
    @Transactional(rollbackFor = Exception.class)
    public Boolean reviewApprove(ReviewApproveReq reqVO) {

        // ================= 1. 参数校验 =================
        if (reqVO == null || reqVO.getEntRectifyRecordId() == null) {
            throw exception("整改记录ID不能为空");
        }

        // ================= 2. 查询记录 =================
        EntRectifyRecordDO record = entRectifyRecordMapper.selectById(reqVO.getEntRectifyRecordId());

        if (record == null) {
            throw exception("整改记录不存在");
        }

        // ================= 3. 状态校验 =================
//        VerifyUtil.verifyNotNullWithMsg(record.getRectifyEvidenceUrl(),"请先上传整改资料");
        //TODO 只能“整改中”才能审核
        if (!"未整改".equals(record.getRectifyStatus())) {
            throw exception("当前状态不允许审核，必须为【未整改】");
        }

        // ================= 4. 执行更新 =================
        EntRectifyRecordDO updateObj = new EntRectifyRecordDO();
        updateObj.setId(record.getId());

        // 审核结果
        updateObj.setAuditResult("合格");

        // 审核人
        updateObj.setAuditBy(getLoginUserId());

        // 审核时间
        updateObj.setAuditTime(LocalDateTime.now());
        updateObj.setRectifyCompleteTime(LocalDateTime.now());

        // 状态流转（核心）
        updateObj.setRectifyStatus("已完成");

        // 更新数据库
        entRectifyRecordMapper.updateById(updateObj);

        //整改台账状态修改为已完成
        RectifyReviewDO rectifyReviewDO =rectifyReviewMapper.selectById(record.getRectifyReviewId());
        rectifyReviewDO.setReviewStatus("已完成");
        rectifyReviewMapper.updateById(rectifyReviewDO);

        return true;
    }

    //审核-拒绝
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean reviewReject(ReviewRejectReq reqVO) {
        // ================= 1. 参数校验 =================
        if (reqVO == null || reqVO.getEntRectifyRecordId() == null) {
            throw exception("整改记录ID不能为空");
        }

        if (reqVO.getRejectReason() == null || reqVO.getRejectReason().trim().isEmpty()) {
            throw exception("驳回原因不能为空");
        }

        // ================= 2. 查询记录 =================
        EntRectifyRecordDO record = entRectifyRecordMapper.selectById(reqVO.getEntRectifyRecordId());

        if (record == null) {
            throw exception("整改记录不存在");
        }

        // ================= 3. 状态校验 =================
        //TODO  只能“整改中”才能审核驳回
        if (!"未整改".equals(record.getRectifyStatus())) {
            throw exception("当前状态不允许驳回，必须为【未整改】");
        }

        // ================= 4. 执行更新 =================
        EntRectifyRecordDO updateObj = new EntRectifyRecordDO();
        updateObj.setId(record.getId());

        // 审核结果
        updateObj.setAuditResult("不合格");

        // 驳回原因
        updateObj.setRejectReason(reqVO.getRejectReason());

        // 审核人
        updateObj.setAuditBy(getLoginUserId());

        // 审核时间
        updateObj.setAuditTime(LocalDateTime.now());
        updateObj.setRectifyCompleteTime(LocalDateTime.now());

        // 状态流转（核心）
        updateObj.setRectifyStatus("整改不合格");

        // 更新数据库
        entRectifyRecordMapper.updateById(updateObj);

        //TODO 2.产生处罚台账
        AddPunishReviewLedgerReq addPunishReviewLedgerReq = new AddPunishReviewLedgerReq();

        // 生成 100~1000 的整数（包含100，不包含1001）
        BigDecimal draftPunishAmt = BigDecimal.valueOf(
                ThreadLocalRandom.current().nextInt(100, 1001)
        );
        addPunishReviewLedgerReq.setDraftPunishAmt(draftPunishAmt);
        addPunishReviewLedgerReq.setEntRectifyRecordId(record.getId());
        Long punishLedgerId = punishReviewLedgerService.addPunishReviewLedger(addPunishReviewLedgerReq);

        return true;
    }

}
