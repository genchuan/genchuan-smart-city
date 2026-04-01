package cn.iocoder.yudao.module.kitchen.service.rectifynotice;

import cn.iocoder.yudao.module.kitchen.controller.admin.rectifynotice.vo.RectifyNoticePageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifynotice.vo.RectifyNoticeSaveReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifynotice.vo.RectifyNoticeUpdateReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifynotice.vo.template.RectifyNoticeTemplateReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.dictionary.illegaltypedict.IllegalTypeDictDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.enterpriseinfo.EnterpriseInfoDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.rectifynotice.RectifyNoticeDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.rectifyreview.RectifyReviewDO;
import cn.iocoder.yudao.module.kitchen.dal.mysql.dictionary.illegaltypedict.IllegalTypeDictMapper;
import cn.iocoder.yudao.module.kitchen.dal.mysql.enterpriseinfo.EnterpriseInfoMapper;
import cn.iocoder.yudao.module.kitchen.dal.mysql.rectifynotice.RectifyNoticeMapper;
import cn.iocoder.yudao.module.kitchen.dal.mysql.rectifyreview.RectifyReviewMapper;
import cn.iocoder.yudao.module.kitchen.framework.lxsutils.common.name.NameUtil;
import cn.iocoder.yudao.module.kitchen.framework.lxsutils.common.pdf.PdfGenerator;
import cn.iocoder.yudao.module.kitchen.framework.lxsutils.common.verify.VerifyUtil;
import cn.iocoder.yudao.module.kitchen.service.rectifyreview.RectifyReviewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.kitchen.enums.ErrorCodeConstants.*;

/**
 * 整改通知书 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class RectifyNoticeServiceImpl implements RectifyNoticeService {

    @Resource
    private RectifyNoticeMapper rectifyNoticeMapper;

    @Resource
    private RectifyReviewMapper rectifyReviewMapper;

    @Resource
    private EnterpriseInfoMapper enterpriseInfoMapper;

    @Resource
    private IllegalTypeDictMapper illegalTypeDictMapper;

    /**
     * 创建整改通知书
     *
     * <p>功能：
     * 1. 校验关联复审台账是否存在；
     * 2. 防止重复下发；
     * 3. 自动生成整改通知书编号；
     * 4. 设置整改通知书属性（时间、状态、截止日期等）；
     * 5. 生成通知书HTML内容；
     * 6. 保存到数据库并返回通知书ID。
     *
     * @param createReqVO 前端请求对象，包含复审台账ID和整改截止日期
     * @return Long 新生成的整改通知书ID
     */
    @Override
    public Long createRectifyNotice(@Valid RectifyNoticeSaveReqVO createReqVO) {


        System.out.println("cs2026-03-19 10:53:12:"+createReqVO);
        // ----------- 1. 校验复审台账 -----------
        RectifyReviewDO rectifyReviewDO = validateRectifyReview(createReqVO.getRectifyReviewId());

        // ----------- 2. 创建通知书对象并设置基础信息 -----------
        RectifyNoticeDO notice = new RectifyNoticeDO();
        notice.setNoticeCode(NameUtil.generateCode("RNTC"));
        notice.setRectifyReviewId(createReqVO.getRectifyReviewId());
        notice.setIssueTime(LocalDateTime.now());
        notice.setRectifyDeadline(createReqVO.getRectifyDeadline());
        notice.setReceiveStatus("未送达");

        // ----------- 3. 生成通知书HTML内容 TODO独立出来接口-----------

        //先插入
        //生成模版req
//        RectifyNoticeTemplateReqVO rectifyNoticeTemplateReqVO =  getNoticeTemplateReq(createReqVO.getRectifyReviewId());
//        notice.setNoticeContent(buildNoticeContent(rectifyNoticeTemplateReqVO));

        // ----------- 4. 保存到数据库 -----------
        rectifyNoticeMapper.insert(notice);

        //TODO 回返绑定id到RectifyReview

        // ----------- 3. 生成通知书HTML内容 TODO独立出来接口-----------

        //先插入
        //生成模版req
        RectifyNoticeTemplateReqVO rectifyNoticeTemplateReqVO =  getNoticeTemplateReq(createReqVO.getRectifyReviewId());
        notice.setNoticeContent(buildNoticeContent(rectifyNoticeTemplateReqVO));
        rectifyNoticeMapper.updateById(notice);

        // 返回通知书ID
//        11111
        return notice.getId();
    }

    /**
     * 校验复审台账是否存在，并防止重复下发
     */
    private RectifyReviewDO validateRectifyReview(Long rectifyReviewId) {
        RectifyReviewDO reviewDO = rectifyReviewMapper.selectById(rectifyReviewId);
        VerifyUtil.verifyNotNullWithMsg(reviewDO, "台账数据不能为空");

        if (reviewDO.getRectifyNoticeCode() != null) {
            throw exception("已经下发整改书，请勿重复下发");
        }
        return reviewDO;
    }

    /**
     * 生成整改通知书HTML内容（与执法文书模板一致）
     */
    private String buildNoticeContent(RectifyNoticeTemplateReqVO reqVO) {

        LocalDateTime issueTime = reqVO.getIssueTime();
        LocalDate deadline = reqVO.getDeadline();

        int year = issueTime.getYear();

        String html = """
    <div style="font-family:SimSun;font-size:16px;line-height:30px">

    <div style="text-align:center;font-size:22px;font-weight:bold">
    %s
    </div>

    <div style="text-align:center;font-size:20px;font-weight:bold;margin-top:10px">
    责令改正通知书
    </div>

    <div style="text-align:center;margin-top:10px">
    %s市监责改〔%d〕%s号
    </div>

    <br/>

    <p>%s：</p>

    <p style="text-indent:2em">
    经查，你（单位）在校园餐饮后厨操作过程中，
    存在%s的行为，
    违反了《中华人民共和国食品安全法》第四十七条
    （食品生产经营者应当建立并执行从业人员健康管理制度）、
    《餐饮服务食品安全操作规范》第十六条
    （从业人员个人卫生要求）等相关规定。
    </p>

    <p style="text-indent:2em">
    依据《中华人民共和国行政处罚法》第二十八条、
    《中华人民共和国食品安全法》第一百二十六条的规定，
    现责令你（单位）在 %d年%d月%d日 前改正。
    </p>

    <p style="text-indent:2em">
    （改正内容及要求：立即组织后厨从业人员开展食品安全操作规范培训，
    严格落实穿戴工作帽、口罩等个人卫生要求。）
    </p>

    <p style="text-indent:2em">
    （逾期不改的，本局将依据《中华人民共和国食品安全法》第一百二十六条的规定，
    依法给予行政处罚；情节严重的，责令停产停业，直至吊销许可证。）
    </p>

    <p style="text-indent:2em">
    如对本责令改正决定不服，可以自收到本通知书之日起六十日内向
    %s申请行政复议；
    也可以在六个月内依法向%s提起行政诉讼。
    </p>

    <br/>

    <p>
    联系人：%s
    &nbsp;&nbsp;&nbsp;&nbsp;
    联系电话：%s
    </p>

    <p>
    联系地址：%s
    </p>

    <br/><br/>

    <div style="text-align:right">
    %s
    </div>

    <div style="text-align:right;margin-top:10px">
    %d 年 %d 月 %d 日
    </div>

    <br/><br/>

    <p>
    本文书一式三份，两份送达，一份归档，存档于市场监督管理局档案室。
    </p>

    </div>
    """.formatted(
                reqVO.getMarketSupervisionBureauName(),
                reqVO.getRegionAbbreviation(),
                year,
                reqVO.getNoticeCode(),
                reqVO.getEntName(),
                reqVO.getIllegalTypeName(),
                deadline.getYear(),
                deadline.getMonthValue(),
                deadline.getDayOfMonth(),
                reqVO.getReconsiderationAuthority(),
                reqVO.getLitigationCourt(),
                reqVO.getContactPerson(),
                reqVO.getContactPhone(),
                reqVO.getContactAddress(),
                reqVO.getMarketSupervisionBureauName(),
                issueTime.getYear(),
                issueTime.getMonthValue(),
                issueTime.getDayOfMonth()
        );

        return html;
    }

    /**
     * 获取整改通知书模板请求参数
     *
     * @param rectifyReviewId 整改复审台账ID
     * @return 模板参数
     */
    public RectifyNoticeTemplateReqVO getNoticeTemplateReq(Long rectifyReviewId) {
        System.out.println("cs2026-03-19 10:56:09:"+rectifyReviewId);

        // 1.查询复审台账
        RectifyReviewDO review = rectifyReviewMapper.selectById(rectifyReviewId);
        if (review == null) {
            throw exception("整改复审台账不存在");
        }

        // 2.查询整改通知书
        RectifyNoticeDO notice = rectifyNoticeMapper.selectByRectifyReviewId(rectifyReviewId);
        if (notice == null) {
            throw exception("整改通知书不存在");
        }

        // 3.查询企业信息
        EnterpriseInfoDO enterprise = enterpriseInfoMapper.selectById(review.getEntId());
        if (enterprise == null) {
            throw exception("企业信息不存在");
        }

        //4.查询违规原因
        IllegalTypeDictDO illegalTypeDictDO = illegalTypeDictMapper.selectById(review.getIllegalTypeId());
        if (illegalTypeDictDO==null){
            throw exception("违规类型不存在");
        }

        // 4.组装模板参数
        RectifyNoticeTemplateReqVO req = new RectifyNoticeTemplateReqVO();

        req.setNoticeCode(notice.getNoticeCode());
        req.setIssueTime(notice.getIssueTime());
        req.setDeadline(notice.getRectifyDeadline());

        req.setEntName(enterprise.getEntName());
        req.setContactPerson(enterprise.getContactPerson());
        req.setContactPhone(enterprise.getContactPhone());
        req.setContactAddress(enterprise.getAddress());

        // 违规原因
        req.setIllegalTypeName(illegalTypeDictDO.getIllegalBehaviorDescription()!=null?
                illegalTypeDictDO.getIllegalBehaviorDescription():illegalTypeDictDO.getTypeName());

        return req;
    }


    @Override
    public void updateRectifyNotice(@Valid RectifyNoticeUpdateReqVO updateReqVO) {
        // 校验存在
        validateRectifyNoticeExists(updateReqVO.getId());
        // 更新
        RectifyNoticeDO updateObj = BeanUtils.toBean(updateReqVO, RectifyNoticeDO.class);
        rectifyNoticeMapper.updateById(updateObj);
    }

    @Override
    public void deleteRectifyNotice(Long id) {
        // 校验存在
        validateRectifyNoticeExists(id);
        // 删除
        rectifyNoticeMapper.deleteById(id);
    }

    private void validateRectifyNoticeExists(Long id) {
        if (rectifyNoticeMapper.selectById(id) == null) {
            throw exception(RECTIFY_NOTICE_NOT_EXISTS);
        }
    }

    @Override
    public RectifyNoticeDO getRectifyNotice(Long id) {
        return rectifyNoticeMapper.selectById(id);
    }

    @Override
    public PageResult<RectifyNoticeDO> getRectifyNoticePage(RectifyNoticePageReqVO pageReqVO) {
        return rectifyNoticeMapper.selectPage(pageReqVO);
    }

    @Override
    public ResponseEntity<byte[]> downloadRectifyNoticePdf(Long rectifyNoticeId) throws IOException {
        // 1. 根据 ID 获取整改复审记录
        RectifyNoticeDO rectifyNoticeDO = rectifyNoticeMapper.selectById(rectifyNoticeId);
        VerifyUtil.verifyNotNullWithMsg(rectifyNoticeDO,"通知书不存在");

        // 2. 根据记录生成 HTML 内容（这里示例固定模板，可根据 review 动态替换）
        String htmlStr = rectifyNoticeDO.getNoticeContent();
        VerifyUtil.verifyNotNullWithMsg(htmlStr,"HTML内容为空，请进行检查");

        // 3. 调用 PdfGenerator 生成 PDF 响应
        PdfGenerator pdfGenerator = new PdfGenerator();
        return pdfGenerator.generatePdfResponse(htmlStr);
    }

}
