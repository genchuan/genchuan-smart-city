package cn.iocoder.yudao.module.kitchen.service.rectifyreview;

import cn.idev.excel.EasyExcel;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.kitchen.controller.admin.aialertmessage.vo.add.AddAiAlertMessageReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.illegalleveldict.vo.IllegalLevelDictPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.enterpriseinfo.vo.EnterpriseInfoPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.add.AddEntRectifyRecordReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifynotice.vo.RectifyNoticeSaveReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.*;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.add.AddRectifyReviewReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.add.AddRectifyReviewReqVO2;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.cancel.CancelReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.issue.IssueReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.upload.UploadEvidenceFileReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.upload.UploadEvidenceFileRespVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.aialertmessage.AiAlertMessageDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.dictionary.cancelreasondict.CancelReasonDictDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.dictionary.illegalleveldict.IllegalLevelDictDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.dictionary.illegaltypedict.IllegalTypeDictDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.enterpriseinfo.EnterpriseInfoDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.rectifynotice.RectifyNoticeDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.rectifyreview.RectifyReviewDO;
import cn.iocoder.yudao.module.kitchen.dal.mysql.aialertmessage.AiAlertMessageMapper;
import cn.iocoder.yudao.module.kitchen.dal.mysql.dictionary.illegaltypedict.IllegalTypeDictMapper;
import cn.iocoder.yudao.module.kitchen.dal.mysql.rectifynotice.RectifyNoticeMapper;
import cn.iocoder.yudao.module.kitchen.dal.mysql.rectifyreview.RectifyReviewMapper;
import cn.iocoder.yudao.module.kitchen.vrv.utils.common.file.VrvFileUploadService;
import cn.iocoder.yudao.module.kitchen.vrv.utils.common.name.VrvNameUtil;
import cn.iocoder.yudao.module.kitchen.vrv.utils.common.pdf.VrvPdfGenerator;
import cn.iocoder.yudao.module.kitchen.vrv.utils.common.verify.VrvVerifyUtil;
import cn.iocoder.yudao.module.kitchen.service.aialertmessage.AiAlertMessageService;
import cn.iocoder.yudao.module.kitchen.service.dictionary.cancelreasondict.CancelReasonDictService;
import cn.iocoder.yudao.module.kitchen.service.dictionary.illegalleveldict.IllegalLevelDictService;
import cn.iocoder.yudao.module.kitchen.service.enterpriseinfo.EnterpriseInfoService;
import cn.iocoder.yudao.module.kitchen.service.entrectifyrecord.EntRectifyRecordService;
import cn.iocoder.yudao.module.kitchen.service.rectifynotice.RectifyNoticeService;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.kitchen.enums.ErrorCodeConstants.RECTIFY_REVIEW_NOT_EXISTS;

import org.springframework.http.HttpHeaders;
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
    private VrvFileUploadService vrvFileUploadService;

    @Resource
    private IllegalTypeDictMapper illegalTypeDictMapper;

    @Resource
    private AiAlertMessageService aiAlertMessageService;

    @Resource
    private EntRectifyRecordService entRectifyRecordService;

    @Resource
    private EnterpriseInfoService enterpriseInfoService;

    @Resource
    private IllegalLevelDictService illegalLevelDictService;

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


        // 当前时间（只取一次，避免循环内多次调用）
        LocalDateTime now = LocalDateTime.now();

        // 2. 计算逾期标识
        for (RectifyReviewLedgerRespVO item : list) {

            // 默认未逾期
            item.setOverdueFlag(0);

            // 判空（非常关键，避免 NPE）
            if (item.getRectifyDeadlineTime() == null || item.getReviewStatus() == null) {
                continue;
            }

            // 判断是否“已下发”
            if ("已下发".equals(item.getReviewStatus())) {

                // 判断是否超过截止时间
                if (now.isAfter(item.getRectifyDeadlineTime())) {
                    item.setOverdueFlag(1);
                }
            }
        }

        pageResult.setList(list);
        //2.查询条目数量
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
    @Transactional(rollbackFor = Exception.class)
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

        // 5.(已经产生了） 调用整改通知书生成接口
//        RectifyNoticeSaveReqVO rectifyNoticeSaveReqVO =new RectifyNoticeSaveReqVO();
//        //构造 通知书 的 插入VO
//        rectifyNoticeSaveReqVO.setRectifyReviewId(reviewDO.getId());
//        //通知书 整改截止时间为当前时间30天后
//        rectifyNoticeSaveReqVO.setRectifyDeadline(LocalDate.from(LocalDateTime.now().plusDays(30)));
//        Long noticeId = rectifyNoticeService.createRectifyNotice(rectifyNoticeSaveReqVO);

        //5.更新整改通知书接口


        //6.返回 整改台账id
        return reviewDO.getId();
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

        VrvVerifyUtil.verifyNotNullWithMsg(cancelReasonDictDO,"请选择正确的撤销原因");
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
            throw exception("企业ID不能为空");
        }
        //校验预警
        VrvVerifyUtil.verifyNotNullWithMsg(reqVO.getAiAlertMessageId(),"预警id不能为空");
        AiAlertMessageDO aiAlertMessageDO = aiAlertMessageMapper.selectById(reqVO.getAiAlertMessageId());
        VrvVerifyUtil.verifyNotNullWithMsg(aiAlertMessageDO,"Ai预警不存在");
        if (aiAlertMessageDO.getRectifyReviewId()!=null){
            throw exception("该预警已产生整改台账记录，请勿重复发送");
        }
        //校验违规类型字典
        VrvVerifyUtil.verifyNotNullWithMsg(aiAlertMessageDO.getAiAbilityCode(),"违规类型不存在");
        IllegalTypeDictDO illegalTypeDictDO = illegalTypeDictMapper.selectByTypeCode(aiAlertMessageDO.getAiAbilityCode());
        VrvVerifyUtil.verifyNotNullWithMsg(illegalTypeDictDO,"违规类型字典不存在");

        // ========= 1.创建DO对象 =========
        RectifyReviewDO insertDO = new RectifyReviewDO();

        // ========= 2.台账编号（自动生成） =========
        insertDO.setLedgerCode(VrvNameUtil.generateCode("RECTIFY"));

        // ========= 3.企业ID（前端传入） =========
        insertDO.setEntId(reqVO.getEntId());

        // ========= 4.违规类型（暂时写死，后续根据告警映射） =========
        insertDO.setIllegalTypeId(illegalTypeDictDO.getId());

        // ========= 5.违规等级（暂时写死，后续根据告警映射）TODO =========
// ========= 5.违规等级（随机选取） =========

// 查询违规等级列表（建议你有一个 dictService 或 mapper）
        List<IllegalLevelDictDO> illegalLevelList
                = illegalLevelDictService.getIllegalLevelDictPage(new IllegalLevelDictPageReqVO()).getList();
// 如果你没有 list()，就用你现有的查询方法（比如 selectList / page）

// 判空
        if (illegalLevelList == null || illegalLevelList.isEmpty()) {
            throw new RuntimeException("违规等级字典为空，无法随机选取");
        }

// 随机
        Random random = new Random();
        int index = random.nextInt(illegalLevelList.size());

// 获取随机等级
        IllegalLevelDictDO level = illegalLevelList.get(index);

// 设置
        insertDO.setIllegalLevelId(level.getId());

        // ========= 6.违规证据（根据AI预警的 违规图片或 保底图片） =========
        // 统一用 JSON 数组格式字符串
        if (aiAlertMessageDO.getSrcUrl() != null && !aiAlertMessageDO.getSrcUrl().isEmpty()) {
            // 1. 按逗号分割 URL 字符串，得到 URL 数组
            String[] urlArray = aiAlertMessageDO.getSrcUrl().split(",");

            // 2. 构建包含每个 URL 信息的 Map 列表
            List<Map<String, String>> evidenceList = new ArrayList<>();
            for (String url : urlArray) {
                url = url.trim(); // 去除可能存在的空格
                if (url.isEmpty()) {
                    continue; // 跳过空字符串
                }

                Map<String, String> item = new LinkedHashMap<>(); // 保持顺序
                // 从 URL 中提取文件名（例如 http://xxx.com/abc.png -> abc.png）
                String fileName = url.substring(url.lastIndexOf('/') + 1);
                // 如果文件名后带查询参数，则去除（如 abc.png?t=123 -> abc.png）
                if (fileName.contains("?")) {
                    fileName = fileName.substring(0, fileName.indexOf('?'));
                }

                item.put("name", fileName);
                item.put("type", "image");    // 默认类型为图片
                item.put("url", url);
                evidenceList.add(item);
            }

            // 3. 将 List 转换为 JSON 字符串（使用 Fastjson 或 Jackson）
            String evidenceUrlJson = JSON.toJSONString(evidenceList); // Fastjson
            // 如果使用 Jackson，则为：new ObjectMapper().writeValueAsString(evidenceList);

            //4. 整改截止时间为 10天
            insertDO.setRectifyDeadlineTime(LocalDateTime.now().plusDays(10));
            // 4. 设置到插入对象
            insertDO.setEvidenceUrl(evidenceUrlJson);
        } else {
            // 没有 srcUrl 时使用默认的 JSON 字符串
            insertDO.setEvidenceUrl("[{\"name\":\"违规图片1.png\",\"type\":\"image\",\"url\":\"http://112.47.127.21:9000/shunchang/avatar/676ab23c-47c0-4d20-860a-c0d2021861e1.png\"},{\"name\":\"违规图片2.png\",\"type\":\"image\",\"url\":\"http://112.47.127.21:9000/shunchang/avatar/29904d39-8a4f-4c15-ac28-5b34c3781f11.png\"}]");
        }

        // ========= 7.草拟时间（当前时间） =========
        insertDO.setDraftTime(LocalDateTime.now());

        // ========= 8.复审状态（初始化） =========
        insertDO.setReviewStatus("待复审");

        // ========= 9.复审人（当前登录用户） =========
        Long currentUserId = getLoginUserId();
        insertDO.setReviewBy(currentUserId);

        // ========= 10.执法复审台账编号（临时写死） =========
        insertDO.setLawLedgerCode(VrvNameUtil.generateCode("LAW"));

        // ========= 11.入库 =========
        rectifyReviewMapper.insert(insertDO);


        //12.修改预警的绑定 整改复审id
        AiAlertMessageDO updateAiAlertMessageDO = aiAlertMessageMapper.selectById(reqVO.getAiAlertMessageId());
        VrvVerifyUtil.verifyNotNullWithMsg(updateAiAlertMessageDO,"预警不存在数据库");

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
        VrvVerifyUtil.verifyNotNullWithMsg(rectifyReviewDO,"整改复审台账记录不存在数据库");


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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long reviewAdd2(AddRectifyReviewReqVO2 reqVO) {

        //1.自动产生预警
        //配置新增预警参数
        AddAiAlertMessageReq addAiAlertMessageReq =new AddAiAlertMessageReq();
//        addAiAlertMessageReq.setSceneId("scene_088930");
//        addAiAlertMessageReq.setAiAbilityCode("100600");
//        addAiAlertMessageReq.setAlertType(13);
//        addAiAlertMessageReq.setSrcUrl("http://112.47.127.21:9000/shunchang/avatar/29904d39-8a4f-4c15-ac28-5b34c3781f11.png");
        Long aiAlertMessageId =  aiAlertMessageService.addAiAlertMessage(addAiAlertMessageReq);

        log.info("[整改流程] 预警生成成功 aiAlertMessageId={}", aiAlertMessageId);

        //2.利用预警产生整改台账
        reqVO.setAiAlertMessageId(aiAlertMessageId);

        //3.从当前企业列表随机选取一个
        List<EnterpriseInfoDO> enterpriseInfoRespVOList
                = enterpriseInfoService.getEnterpriseInfoPage(new EnterpriseInfoPageReqVO()).getList();
        // 判空（非常重要，避免空指针）
        if (enterpriseInfoRespVOList == null || enterpriseInfoRespVOList.isEmpty()) {
            throw exception("企业列表为空，无法随机选取企业");
        }

        // 使用 Random 随机
        Random random = new Random();
        int index = random.nextInt(enterpriseInfoRespVOList.size());

        // 获取随机企业
        EnterpriseInfoDO randomEnterprise = enterpriseInfoRespVOList.get(index);

        // 设置企业ID
        reqVO.setEntId(randomEnterprise.getId());

        //4.生成整改台账
        AddRectifyReviewReqVO addRectifyReviewReqVO = BeanUtils.toBean(reqVO,AddRectifyReviewReqVO.class);
        Long rectifyReviewId =  this.reviewAdd(addRectifyReviewReqVO);

        log.info("[整改流程] 台账生成成功 rectifyReviewId={}", rectifyReviewId);

        //获取整改台账
        RectifyReviewDO rectifyReviewDO = rectifyReviewMapper.selectById(rectifyReviewId);
        VrvVerifyUtil.verifyNotNullWithMsg(rectifyReviewDO,"整改台账不存在");



        //3. TODO 产生整改通知书
        RectifyNoticeSaveReqVO rectifyNoticeSaveReqVO =new RectifyNoticeSaveReqVO();
        rectifyNoticeSaveReqVO.setRectifyReviewId(rectifyReviewDO.getId());
        rectifyNoticeSaveReqVO.setRectifyDeadline(rectifyReviewDO.getRectifyDeadlineTime().toLocalDate());
        Long rectifyNoticeId = rectifyNoticeService.createRectifyNotice(rectifyNoticeSaveReqVO);

        log.info("[整改流程] 通知书生成成功 rectifyNoticeId={}", rectifyNoticeId);

        //4. 整改台账设置整改通知书id
        rectifyReviewDO.setRectifyNoticeId(rectifyNoticeId);
        rectifyReviewMapper.updateById(rectifyReviewDO);

        // ================== 4. 汇总日志（重点） ==================
        log.info("[整改流程完成] aiAlertMessageId={}, rectifyReviewId={}, rectifyNoticeId={}",
                aiAlertMessageId, rectifyReviewId, rectifyNoticeId);
        // ========= 13.返回主键 =========
        return rectifyReviewDO.getId();
    }

    @Override
    public ResponseEntity<byte[]> downloadRectifyNoticePdfBatch(List<Long> rectifyNoticeIds) throws IOException {
        VrvVerifyUtil.verifyNotNullWithMsg(rectifyNoticeIds, "ID不能为空");

        // 1. 查询通知书数据
        List<RectifyNoticeDO> list = rectifyNoticeMapper.selectBatchIds(rectifyNoticeIds);
        log.info("批量通知书id："+rectifyNoticeIds);
        VrvVerifyUtil.verifyNotNullWithMsg(list, "通知书不存在");

        // 2. 创建ZIP流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);

        for (RectifyNoticeDO item : list) {
            String html = item.getNoticeContent();
            if (html == null) {
                continue;
            }

            // 3. HTML → PDF
            VrvPdfGenerator pdfGenerator = new VrvPdfGenerator();
            byte[] pdfBytes = pdfGenerator.generatePdfResponse(html).getBody();
            if (pdfBytes == null || pdfBytes.length == 0) {
                log.warn("PDF生成失败，通知书ID：{}", item.getId());
                continue;
            }

            // 4. 写入ZIP（文件名使用UTF-8，避免中文乱码）
            String fileName = "整改通知书_" + item.getRectifyReviewId() + ".pdf";
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
        String fileName = "整改通知书.zip";
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString())
                .replace("+", "%20"); // 替换空格编码
        headers.add("Content-Disposition",
                String.format("attachment; filename=\"%s\"; filename*=%s",
                        new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1),
                        encodedFileName));

        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long reviewIssue2(IssueReqVO reqVO) {
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

        // 5.(已经产生了） 调用整改通知书生成接口
//        RectifyNoticeSaveReqVO rectifyNoticeSaveReqVO =new RectifyNoticeSaveReqVO();
//        //构造 通知书 的 插入VO
//        rectifyNoticeSaveReqVO.setRectifyReviewId(reviewDO.getId());
//        //通知书 整改截止时间为当前时间30天后
//        rectifyNoticeSaveReqVO.setRectifyDeadline(LocalDate.from(LocalDateTime.now().plusDays(30)));
//        Long noticeId = rectifyNoticeService.createRectifyNotice(rectifyNoticeSaveReqVO);

        //5.创建企业整改记录
        VrvVerifyUtil.verifyNotNullWithMsg(reviewDO.getRectifyNoticeId(),"整改通知书不存在");
        AddEntRectifyRecordReqVO addEntRectifyRecordReqVO = new AddEntRectifyRecordReqVO();
        addEntRectifyRecordReqVO.setRectifyNoticeId(reviewDO.getRectifyNoticeId());

        entRectifyRecordService.addEntRectifyRecord(addEntRectifyRecordReqVO);


        //6.返回 整改台账id
        return reviewDO.getId();
    }

//    private byte[] generatePdfBytes(String html) {
//    }


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
