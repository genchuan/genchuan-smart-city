package cn.iocoder.yudao.module.kitchen.service.rectifyreview;

import cn.idev.excel.EasyExcel;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifynotice.vo.RectifyNoticeSaveReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.*;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.add.AddRectifyReviewReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.cancel.CancelReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.issue.IssueReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.upload.UploadEvidenceFileReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.upload.UploadEvidenceFileRespVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.aialertmessage.AiAlertMessageDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.dictionary.cancelreasondict.CancelReasonDictDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.rectifyreview.RectifyReviewDO;
import cn.iocoder.yudao.module.kitchen.dal.mysql.aialertmessage.AiAlertMessageMapper;
import cn.iocoder.yudao.module.kitchen.dal.mysql.rectifynotice.RectifyNoticeMapper;
import cn.iocoder.yudao.module.kitchen.dal.mysql.rectifyreview.RectifyReviewMapper;
import cn.iocoder.yudao.module.kitchen.framework.lxsutils.common.file.FileUploadService;
import cn.iocoder.yudao.module.kitchen.framework.lxsutils.common.name.NameUtil;
import cn.iocoder.yudao.module.kitchen.framework.lxsutils.common.verify.VerifyUtil;
import cn.iocoder.yudao.module.kitchen.service.dictionary.cancelreasondict.CancelReasonDictService;
import cn.iocoder.yudao.module.kitchen.service.rectifynotice.RectifyNoticeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.kitchen.enums.ErrorCodeConstants.RECTIFY_REVIEW_NOT_EXISTS;


/**
 * 整改通知书复审台账 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
@Slf4j
public class RectifyReviewServiceImpl implements RectifyReviewService {

    @Resource
    private RectifyReviewMapper rectifyReviewMapper;

    @Resource
    private RectifyNoticeService rectifyNoticeService;

    @Resource
    private CancelReasonDictService cancelReasonDictService;

    @Resource
    private RectifyNoticeMapper rectifyNoticeMapper;

    @Resource
    private AiAlertMessageMapper aiAlertMessageMapper;

    @Resource
    private FileUploadService fileUploadService;

    // 在类里定义 ObjectMapper 实例
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Long createRectifyReview(RectifyReviewSaveReqVO createReqVO) {
        // 插入
        RectifyReviewDO rectifyReview = BeanUtils.toBean(createReqVO, RectifyReviewDO.class);
        rectifyReviewMapper.insert(rectifyReview);
        // 返回
        return rectifyReview.getId();
    }

    @Override
    public void updateRectifyReview(RectifyReviewSaveReqVO updateReqVO) {
        // 校验存在
        validateRectifyReviewExists(updateReqVO.getId());
        // 更新
        RectifyReviewDO updateObj = BeanUtils.toBean(updateReqVO, RectifyReviewDO.class);
        rectifyReviewMapper.updateById(updateObj);
    }

    @Override
    public void deleteRectifyReview(Long id) {
        // 校验存在
        validateRectifyReviewExists(id);
        // 删除
        rectifyReviewMapper.deleteById(id);
    }

    private void validateRectifyReviewExists(Long id) {
        if (rectifyReviewMapper.selectById(id) == null) {
            throw exception(RECTIFY_REVIEW_NOT_EXISTS);
        }
    }

    @Override
    public RectifyReviewDO getRectifyReview(Long id) {
        return rectifyReviewMapper.selectById(id);
    }

    @Override
    public PageResult<RectifyReviewDO> getRectifyReviewPage(RectifyReviewPageReqVO pageReqVO) {
        return rectifyReviewMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<RectifyReviewLedgerRespVO> getRectifyReviewLedgerPage(RectifyReviewLedgerPageReqVO reqVO) {
        List<RectifyReviewLedgerRespVO> list = rectifyReviewMapper.selectLedgerPage(reqVO);
        PageResult<RectifyReviewLedgerRespVO> pageResult= new PageResult<>();
        pageResult.setList(list);

//        TODO 总条数
        Long count = rectifyReviewMapper.selectLedgerPageCount(reqVO);
        pageResult.setTotal(count);
        return pageResult;
    }

    /**
     * 导出整改复审台账 Excel
     * @param list 待导出的数据
     * @return 下载 URL
     */
    public String exportExcel(List<RectifyReviewLedgerRespVO> list) {

        // 生成文件名
        String fileName = "整改复审台账_" + System.currentTimeMillis() + ".xlsx";

        // 临时目录，每台服务独立
        String dirPath = System.getProperty("java.io.tmpdir") + "/rectify-review-export/";

        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String filePath = dirPath + fileName;

        // 使用 EasyExcel 写入 Excel
        EasyExcel.write(filePath, RectifyReviewLedgerRespVO.class)
                .sheet("台账数据")
                .doWrite(list);

        // 返回前端下载 URL
        return fileName;
    }

    @Override
    public List<RectifyReviewLedgerRespVO> getByIds(List<Long> ledgerIds) {
        RectifyReviewLedgerPageReqVO req = new RectifyReviewLedgerPageReqVO();
        req.setPageNo(1).setPageSize(10000);
        req.setIdList(ledgerIds);
        // 调用 方法 查询台账数据
        return getRectifyReviewLedgerPage(req).getList();
    }

    @Override
    public PageResult<RectifyEvidenceVO> getBatchEvidence(BatchEvidenceRequestVO reqVO) {
        // 计算分页的起始偏移量：当前页码减1乘以每页大小
        int offset = (int) ((reqVO.getPageNo() - 1) * reqVO.getPageSize());

        // 从数据库中批量查询整改证据信息
        // 参数：ledgerId列表、分页偏移量、分页大小
        List<RectifyReviewDO> reviews = rectifyReviewMapper.selectBatchEvidence(
                reqVO.getLedgerIdList(), offset, reqVO.getPageSize()
        );

        // 将查询到的DO对象列表转换为VO对象列表
        List<RectifyEvidenceVO> records = reviews.stream().map(r -> {
            RectifyEvidenceVO vo = new RectifyEvidenceVO();
            // 设置台账编号
            vo.setLedgerCode(r.getLedgerCode());
            // 解析证据URL字符串为列表，并设置到VO中
            vo.setEvidenceList(parseEvidence(r.getEvidenceUrl()));
            return vo;
        }).collect(Collectors.toList());

        // 构建分页结果对象
        PageResult<RectifyEvidenceVO> pageResult = new PageResult<>();
        // 设置当前页的数据列表
        pageResult.setList(records);
        // 设置总记录数，这里简单使用ledgerId列表的数量作为总数
        // 如果需要精准分页，应改为实际总数查询
        pageResult.setTotal((long) reqVO.getLedgerIdList().size());

        // 如果需要，可以解注释设置当前页码和每页大小
//    pageResult.setPageNo(reqVO.getPageNo());
//    pageResult.setPageSize(reqVO.getPageSize());

        // 返回分页结果
        return pageResult;
    }


    //下发操作
    @Override
    public Long reviewIssue(IssueReqVO reqVO) {
        // 1. 查询台账
        RectifyReviewDO reviewDO = rectifyReviewMapper.selectById(reqVO.getId());
        if (reviewDO == null) {
            throw exception("台账不存在");
        }

        // 2. 校验当前状态
        if (!"待复审".equals(reviewDO.getReviewStatus())) {
            throw exception("当前台账状态不是待复审，无法操作");
        }

        // 3. 校验 evidence_url 不为空
//        if (reviewDO.getEvidenceUrl() == null || reviewDO.getEvidenceUrl().trim().isEmpty()) {
//            throw exception("违规证据不能为空，无法审核通过");
//        }

        // 4. 更新复审状态、复审人、复审时间
        reviewDO.setReviewStatus("已下发");               // 状态更新
        reviewDO.setReviewBy(getLoginUserId());        // 当前登录用户ID
        reviewDO.setReviewTime(LocalDateTime.now());     // 当前时间

        rectifyReviewMapper.updateById(reviewDO);        // 保存复审信息

        // 5. 调用整改通知书生成接口 TODO
        RectifyNoticeSaveReqVO rectifyNoticeSaveReqVO =new RectifyNoticeSaveReqVO();
        //构造 通知书 的 插入VO
        rectifyNoticeSaveReqVO.setRectifyReviewId(reqVO.getId());
        //通知书 整改截止时间为当前时间30天后
        rectifyNoticeSaveReqVO.setRectifyDeadline(LocalDate.from(LocalDateTime.now().plusDays(30)));
        Long noticeId = rectifyNoticeService.createRectifyNotice(rectifyNoticeSaveReqVO);

        //6.返回 通知书id
        return noticeId;
    }


    //撤销操作
    @Override
    public Long reviewCancel(CancelReqVO reqVO) {
        // 1. 查询台账
        RectifyReviewDO reviewDO = rectifyReviewMapper.selectById(reqVO.getId());
        if (reviewDO == null) {
            throw exception("台账不存在");
        }

        // 2. 校验当前状态
        if (!"待复审".equals(reviewDO.getReviewStatus())) {
            throw exception("当前台账状态不是待复审，无法操作");
        }

        // 3. 校验 evidence_url 不为空
//        if (reviewDO.getEvidenceUrl() == null || reviewDO.getEvidenceUrl().trim().isEmpty()) {
//            throw exception("违规证据不能为空，无法审核通过");
//        }

        // 4. 更新复审状态、复审人、复审时间
        reviewDO.setReviewStatus("已撤销");               // 状态更新
        reviewDO.setReviewBy(getLoginUserId());        // 当前登录用户ID
        reviewDO.setReviewTime(LocalDateTime.now());     // 当前时间
        reviewDO.setCancelTime(LocalDateTime.now());    //  撤销时间

        //5.撤销原因
        if (reqVO.getCancelReasonId()==null){
            throw exception("撤销原因Id不能为空");
        }

        CancelReasonDictDO cancelReasonDictDO = cancelReasonDictService.getCancelReasonDict(reqVO.getCancelReasonId());

        VerifyUtil.verifyNotNullWithMsg(cancelReasonDictDO,"请选择正确的撤销原因");
        reviewDO.setCancelReasonId(reqVO.getCancelReasonId());

        //6.更新复审
        rectifyReviewMapper.updateById(reviewDO);        // 保存复审信息

        //7.返回 通知书id
        return reviewDO.getId();
    }

    @Override
    @Transactional
    public Long reviewAdd(AddRectifyReviewReqVO reqVO) {

        // ========= 0.基础校验 =========
        if (reqVO == null || reqVO.getEntId() == null) {
            throw new IllegalArgumentException("企业ID不能为空");
        }

        // ========= 1.创建DO对象 =========
        RectifyReviewDO insertDO = new RectifyReviewDO();

        // ========= 2.台账编号（自动生成） =========
        insertDO.setLedgerCode(NameUtil.generateCode("RECTIFY"));

        // ========= 3.企业ID（前端传入） =========
        insertDO.setEntId(reqVO.getEntId());

        // ========= 4.违规类型（暂时写死，后续根据告警映射） =========
        insertDO.setIllegalTypeId(1L);

        // ========= 5.违规等级（暂时写死，后续根据告警映射） =========
        insertDO.setIllegalLevelId(1L);

        // ========= 6.违规证据（TODO：后续根据告警或上传） =========
        // 统一用 JSON 数组格式字符串
        insertDO.setEvidenceUrl("[{\"name\":\"Snipaste_2026-03-09_14-23-50.png\",\"type\":\"image\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/7dfda1f0-49eb-4ba6-bfb9-30560bdc4a21.png\"},{\"name\":\"Snipaste_2026-03-09_14-22-05.png\",\"type\":\"image\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/2eede5e1-6b48-41bc-8f03-b4b145a68681.png\"}]");

        // ========= 7.草拟时间（当前时间） =========
        insertDO.setDraftTime(LocalDateTime.now());

        // ========= 8.复审状态（初始化） =========
        insertDO.setReviewStatus("待复审");

        // ========= 9.复审人（当前登录用户） =========
        Long currentUserId = getLoginUserId();
        insertDO.setReviewBy(currentUserId);

        // ========= 10.执法复审台账编号（临时写死） =========
        insertDO.setLawLedgerCode(NameUtil.generateCode("LAW"));

        // ========= 11.入库 =========
        rectifyReviewMapper.insert(insertDO);


        //12.修改预警的绑定 整改复审id
        AiAlertMessageDO updateAiAlertMessageDO = aiAlertMessageMapper.selectById(reqVO.getAiAlertMessageId());
        VerifyUtil.verifyNotNullWithMsg(updateAiAlertMessageDO,"预警不存在数据库");

        updateAiAlertMessageDO.setRectifyReviewId(insertDO.getId());
        aiAlertMessageMapper.updateById(updateAiAlertMessageDO);

        // ========= 13.返回主键 =========
        return insertDO.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UploadEvidenceFileRespVO uploadEvidenceFile(UploadEvidenceFileReqVO reqVO, MultipartFile file) {
        // 1. 校验整改复审台账是否存在
        RectifyReviewDO rectifyReviewDO = rectifyReviewMapper.selectById(reqVO.getRectifyReviewId());
        VerifyUtil.verifyNotNullWithMsg(rectifyReviewDO,"整改复审台账记录不存在数据库");


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
            String oldJson = rectifyReviewDO.getEvidenceUrl();
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
            RectifyReviewDO updateRectifyReviewDO = new RectifyReviewDO();
            updateRectifyReviewDO.setId(rectifyReviewDO.getId());
            updateRectifyReviewDO.setEvidenceUrl(newJson);
            rectifyReviewMapper.updateById(updateRectifyReviewDO);

            // 8. 返回结果
            UploadEvidenceFileRespVO respVO = new UploadEvidenceFileRespVO();
            respVO.setRectifyReviewId(rectifyReviewDO.getId());
            respVO.setFileUrl(fileUrl);
            respVO.setFileName(file.getOriginalFilename());

            return respVO;

        } catch (Exception e) {
            log.error("上传资料失败", e);

            if (e instanceof ServiceException) {
                throw (ServiceException) e;
            }

            throw new ServiceException(500, "上传文件失败");
        }
    }


    //    private List<EvidenceItem> parseEvidence(String evidenceUrl) {
//        if (evidenceUrl == null || evidenceUrl.isEmpty()) return Collections.emptyList();
//        return Arrays.stream(evidenceUrl.split(","))
//                .map(url -> {
//                    EvidenceItem item = new EvidenceItem();
//                    item.setUrl(url);
//                    item.setName(url.substring(url.lastIndexOf("/") + 1));
//                    item.setType(getFileType(url));
//                    return item;
//                }).collect(Collectors.toList());
//    }
    private List<EvidenceItem> parseEvidence(String evidenceJson) {
        if (evidenceJson == null || evidenceJson.isEmpty()) return Collections.emptyList();
        try {
            // 将 JSON 字符串直接转成 List<EvidenceItem>
            return objectMapper.readValue(evidenceJson, new TypeReference<List<EvidenceItem>>() {});
        } catch (Exception e) {
            // 如果解析失败，可以退回到老的逗号分隔解析方式
            return Arrays.stream(evidenceJson.split(","))
                    .map(url -> {
                        EvidenceItem item = new EvidenceItem();
                        item.setUrl(url);
                        item.setName(url.substring(url.lastIndexOf("/") + 1));
                        item.setType(getFileType(url));
                        return item;
                    }).collect(Collectors.toList());
        }
    }

    private String getFileType(String url) {
        if (url.endsWith(".jpg") || url.endsWith(".jpeg") || url.endsWith(".png")) return "image";
        if (url.endsWith(".pdf")) return "pdf";
        return "file";
    }

}
