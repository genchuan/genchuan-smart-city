package cn.iocoder.yudao.module.kitchen.service.punishnotice;

import cn.hutool.core.convert.NumberChineseFormatter;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishnotice.vo.PunishNoticePageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishnotice.vo.PunishNoticeSaveReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishnotice.vo.add.AddPunishNoticeReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishnotice.vo.template.DraftPunishNoticeReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishnotice.vo.template.PunishNoticeTemplateReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.dictionary.illegaltypedict.IllegalTypeDictDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.enterpriseinfo.EnterpriseInfoDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.punishnotice.PunishNoticeDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.punishreviewledger.PunishReviewLedgerDO;
import cn.iocoder.yudao.module.kitchen.dal.mysql.dictionary.illegaltypedict.IllegalTypeDictMapper;
import cn.iocoder.yudao.module.kitchen.dal.mysql.enterpriseinfo.EnterpriseInfoMapper;
import cn.iocoder.yudao.module.kitchen.dal.mysql.punishnotice.PunishNoticeMapper;
import cn.iocoder.yudao.module.kitchen.dal.mysql.punishreviewledger.PunishReviewLedgerMapper;
import cn.iocoder.yudao.module.kitchen.vrv.utils.common.name.VrvNameUtil;
import cn.iocoder.yudao.module.kitchen.vrv.utils.common.pdf.VrvPdfGenerator;
import cn.iocoder.yudao.module.kitchen.vrv.utils.common.verify.VrvVerifyUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.kitchen.enums.ErrorCodeConstants.*;

/**
 * 处罚通知书 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PunishNoticeServiceImpl implements PunishNoticeService {

    @Resource
    private PunishNoticeMapper punishNoticeMapper;

    @Resource
    private PunishReviewLedgerMapper punishReviewLedgerMapper;

    @Resource
    private EnterpriseInfoMapper enterpriseInfoMapper;

    @Resource
    private IllegalTypeDictMapper illegalTypeDictMapper;
    @Override
    public Long createPunishNotice(PunishNoticeSaveReqVO createReqVO) {
        // 插入
        PunishNoticeDO punishNotice = BeanUtils.toBean(createReqVO, PunishNoticeDO.class);
        punishNoticeMapper.insert(punishNotice);
        // 返回
        return punishNotice.getId();
    }

    @Override
    public void updatePunishNotice(PunishNoticeSaveReqVO updateReqVO) {
        // 校验存在
        validatePunishNoticeExists(updateReqVO.getId());
        // 更新
        PunishNoticeDO updateObj = BeanUtils.toBean(updateReqVO, PunishNoticeDO.class);
        punishNoticeMapper.updateById(updateObj);
    }

    @Override
    public void deletePunishNotice(Long id) {
        // 校验存在
        validatePunishNoticeExists(id);
        // 删除
        punishNoticeMapper.deleteById(id);
    }

    private void validatePunishNoticeExists(Long id) {
        if (punishNoticeMapper.selectById(id) == null) {
            throw exception(PUNISH_NOTICE_NOT_EXISTS);
        }
    }

    @Override
    public PunishNoticeDO getPunishNotice(Long id) {
        return punishNoticeMapper.selectById(id);
    }

    @Override
    public PageResult<PunishNoticeDO> getPunishNoticePage(PunishNoticePageReqVO pageReqVO) {
        return punishNoticeMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long addPunishNotice(AddPunishNoticeReq reqVO) {

        // 1. 校验处罚复审台账是否存在
        PunishReviewLedgerDO punishReviewDO = punishReviewLedgerMapper.selectById(reqVO.getPunishReviewId());
        VrvVerifyUtil.verifyNotNullWithMsg(punishReviewDO, "处罚复审台账不存在");

        if (punishReviewDO.getPunishNoticeId() != null) {
            throw exception("已经下发处罚通知书，请勿重复下发");
        }


        // 2. 创建处罚通知书对象
        PunishNoticeDO notice = new PunishNoticeDO();
        notice.setNoticeCode(VrvNameUtil.generateCode("PNTC")); // 编号自动生成
        notice.setPunishReviewId(reqVO.getPunishReviewId());
        notice.setIssueTime(LocalDateTime.now());

        //处罚期限
        reqVO.setPayDeadline(punishReviewDO.getPaymentDeadlineTime());
        notice.setPayDeadline(reqVO.getPayDeadline());
        notice.setReceiveStatus("未送达");

        //处罚金额
        reqVO.setActualPunishAmt(punishReviewDO.getDraftPunishAmt());
        notice.setActualPunishAmt(reqVO.getActualPunishAmt());

//        // 富文本内容不在这里生成，可在前端调用单独接口生成
//        notice.setDecisionContent(""); // 或保留空，后续由前端/服务生成HTML

        // 3. 保存到数据库
        punishNoticeMapper.insert(notice);

        // 4. 回填：更新复审台账绑定 punish_notice_id
        punishReviewDO.setPunishNoticeId(notice.getId());
        punishReviewLedgerMapper.updateById(punishReviewDO);


        // 5. 返回新增通知书ID
        return notice.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String generatePunishNoticeDraft(DraftPunishNoticeReq reqVO) {
        System.out.println("cs2026-03-24 10:21:12:1556");
        VrvVerifyUtil.verifyNotNullSimple(reqVO.getPunishReviewNoticeId());
        //1.获取处罚通知书
        PunishNoticeDO punishNoticeDO = punishNoticeMapper.selectById(reqVO.getPunishReviewNoticeId());
        VrvVerifyUtil.verifyNotNullWithMsg(punishNoticeDO,"处罚通知书不能为空");

        //2.获取处罚通知台账
        PunishReviewLedgerDO punishReviewLedgerDO =
                punishReviewLedgerMapper.selectById(punishNoticeDO.getPunishReviewId());
        VrvVerifyUtil.verifyNotNullWithMsg(punishReviewLedgerDO,"处罚台账记录不存在");

        //3.获取企业信息
        EnterpriseInfoDO enterpriseInfoDO = enterpriseInfoMapper.selectById(punishReviewLedgerDO.getEntId());
        VrvVerifyUtil.verifyNotNullWithMsg(enterpriseInfoDO,"企业信息不存在");

        //4.违规类型字典
        IllegalTypeDictDO illegalTypeDictDO =
                illegalTypeDictMapper.selectById(punishReviewLedgerDO.getIllegalTypeId());
        VrvVerifyUtil.verifyNotNullWithMsg(illegalTypeDictDO,"违规类型不存在");

        // ================== 关键：组装模板 VO ==================
        PunishNoticeTemplateReqVO template = new PunishNoticeTemplateReqVO();

        // 1. 通知书编号
        template.setNoticeCode(punishNoticeDO.getNoticeCode());

        // 2. 企业信息（enterprise_info）
        template.setEntName(enterpriseInfoDO.getEntName());
        template.setEntCode(enterpriseInfoDO.getEntCode());
        template.setEntAddress(enterpriseInfoDO.getAddress());
        template.setLegalPerson(enterpriseInfoDO.getContactPerson());
        template.setContactPhone(enterpriseInfoDO.getContactPhone());

        // 3. 违法行为（字典 + 台账）
        template.setIllegalBehavior(illegalTypeDictDO.getIllegalBehaviorDescription());
        // 如果台账里有补充描述，可以拼接：
        // template.setIllegalBehavior(illegalTypeDictDO.getName() + "，" + punishReviewLedgerDO.getIllegalDesc());

        // 证据（默认写死“整改通知书”，也可以扩展）
        template.setEvidence(template.getEvidence());

        // 4. 处罚金额
        template.setPunishAmount(punishNoticeDO.getActualPunishAmt());

        // 大写金额（建议统一工具类）
        template.setPunishAmountChinese(
                NumberChineseFormatter.format(punishNoticeDO.getActualPunishAmt().doubleValue(), true)
        );

        // 5. 时间
        template.setIssueTime(punishNoticeDO.getIssueTime());

        //其他字段（银行、联系人、监管局）用默认值即可（ VO 里已经写死了）,TODO 后续可能有具体的

        // ================== 生成HTML ==================
        String noticeHtml = buildPunishNoticeContent(template);

        //草拟通知书保存到通知书的数据库
        punishNoticeDO.setDecisionContent(noticeHtml);
        punishNoticeMapper.updateById(punishNoticeDO);

        //草拟时间保存到台账数据库
        punishReviewLedgerDO.setDraftTime(LocalDateTime.now());
        punishReviewLedgerMapper.updateById(punishReviewLedgerDO);
        return noticeHtml;
    }

    @Override
    public ResponseEntity<byte[]> downloadRectifyNoticePdf(Long punishNoticeId) {
        // 1. 根据 ID 获取整改复审记录
        PunishNoticeDO punishNoticeDO = punishNoticeMapper.selectById(punishNoticeId);
        VrvVerifyUtil.verifyNotNullWithMsg(punishNoticeDO,"通知书不存在");

        // 2. 根据记录生成 HTML 内容（这里示例固定模板，可根据 review 动态替换）
        String htmlStr = punishNoticeDO.getDecisionContent();
        VrvVerifyUtil.verifyNotNullWithMsg(htmlStr,"HTML内容为空，请进行检查");

        // 3. 调用 VrvPdfGenerator 生成 PDF 响应
        VrvPdfGenerator pdfGenerator = new VrvPdfGenerator();
        return pdfGenerator.generatePdfResponse(htmlStr);
    }

    private String buildPunishNoticeContent(PunishNoticeTemplateReqVO reqVO) {
        LocalDateTime issueTime = reqVO.getIssueTime();
        int year = issueTime.getYear();
        int month = issueTime.getMonthValue();
        int day = issueTime.getDayOfMonth();

        String html = """
    <div style="font-family:SimSun;font-size:16px;line-height:28px">

    <div style="text-align:center;font-size:22px;font-weight:bold">
    %s
    </div>

    <div style="text-align:center;font-size:20px;font-weight:bold;margin-top:10px">
    行政处罚决定书
    </div>

    <div style="text-align:center;margin-top:10px">
    %s市监罚〔%s〕%s号
    </div>

    <br/>

    <p>当事人：%s</p>
    <p>统一社会信用代码/注册号：%s</p>
    <p>地址：%s</p>
    <p>法定代表人/负责人：%s 联系电话：%s</p>

    <p style="text-indent:2em">
    经查，你（单位）在校园餐饮后厨经营过程中，存在%s。上述事实有%s为证。
    </p>

    <p style="text-indent:2em">
    你（单位）的上述行为违反了《中华人民共和国食品安全法》第三十三条、
    《餐饮服务食品安全操作规范》第十六条等相关规定，已构成违法。
    </p>

    <p style="text-indent:2em">
    依据《中华人民共和国行政处罚法》第五十七条、《中华人民共和国食品安全法》第一百二十六条第一款第（三）项的规定，决定对你（单位）作出如下行政处罚：
    <br/>1. 警告；
    <br/>2. 罚款人民币%s元（大写：%s）。
    </p>

    <p style="text-indent:2em">
    限你（单位）自收到本决定书之日起十五日内，将罚款缴至%s（账户：%s）。
    逾期不缴纳罚款的，依据《中华人民共和国行政处罚法》第七十二条第一款第（一）项的规定，每日按罚款数额的百分之三加处罚款。
    </p>

    <p style="text-indent:2em">
    如对本处罚决定不服，可以自收到本决定书之日起六十日内向%s申请行政复议；
    也可以在六个月内依法向%s提起行政诉讼。复议、诉讼期间，本行政处罚决定不停止执行。
    </p>

    <p>联系人：%s 联系电话：%s</p>
    <p>联系地址：%s</p>

    <br/>
    <div style="text-align:right">%s</div>
    <div style="text-align:right">(印 章)</div>
    <div style="text-align:right;margin-top:10px">%d 年 %d 月 %d 日</div>

    <br/><br/>
    <p>本文书一式三份，两份送达，一份归档，存档于市场监督管理局档案室。</p>

    </div>
    """.formatted(
                reqVO.getMarketSupervisionBureauName(),
                reqVO.getRegionAbbreviation(),
                year,
                reqVO.getNoticeCode(),
                reqVO.getEntName(),
                reqVO.getEntCode(),
                reqVO.getEntAddress(),
                reqVO.getLegalPerson(),
                reqVO.getContactPhone(),
                reqVO.getIllegalBehavior(),
                reqVO.getEvidence(),
                reqVO.getPunishAmount(),
                reqVO.getPunishAmountChinese(),
                reqVO.getBankName(),
                reqVO.getBankAccount(),
                reqVO.getReconsiderationAuthority(),
                reqVO.getLitigationCourt(),
                reqVO.getPunishSupervisionContactPerson(),
                reqVO.getPunishSupervisionContactPhone(),
                reqVO.getPunishSupervisionContactAddress(),
                reqVO.getMarketSupervisionBureauName(),
                year, month, day
        );

        return html;
    }

}
