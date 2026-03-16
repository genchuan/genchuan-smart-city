package cn.iocoder.yudao.module.kitchen.service.rectifyreview;

import cn.idev.excel.EasyExcel;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifynotice.vo.RectifyNoticeSaveReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.*;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.cancel.CancelReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.issue.IssueReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.dictionary.cancelreasondict.CancelReasonDictDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.rectifyreview.RectifyReviewDO;
import cn.iocoder.yudao.module.kitchen.dal.mysql.rectifynotice.RectifyNoticeMapper;
import cn.iocoder.yudao.module.kitchen.dal.mysql.rectifyreview.RectifyReviewMapper;
import cn.iocoder.yudao.module.kitchen.framework.lxsutils.common.verify.VerifyUtil;
import cn.iocoder.yudao.module.kitchen.service.dictionary.cancelreasondict.CancelReasonDictService;
import cn.iocoder.yudao.module.kitchen.service.rectifynotice.RectifyNoticeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
public class RectifyReviewServiceImpl implements RectifyReviewService {

    @Resource
    private RectifyReviewMapper rectifyReviewMapper;

    @Resource
    private RectifyNoticeService rectifyNoticeService;

    @Resource
    private CancelReasonDictService cancelReasonDictService;

    @Resource
    private RectifyNoticeMapper rectifyNoticeMapper;

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
