package cn.iocoder.yudao.module.kitchen.service.riskreport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.kitchen.controller.admin.riskreport.vo.page.EntReportPageReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.riskreport.vo.page.EntReportPageResp;
import cn.iocoder.yudao.module.kitchen.dal.mysql.riskreport.RiskReportMapper;
import cn.iocoder.yudao.module.kitchen.vrv.utils.common.pdf.VrvPdfGenerator;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskReportServiceImpl implements RiskReportService{
    @Resource
    private RiskReportMapper riskReportMapper;


    @Override
    public PageResult<EntReportPageResp> getEntReportPage(EntReportPageReq pageReqVO) {

//        // 1. 构建分页
//        Page<EntReportPageResp> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
//
//        // 2. 查询分页数据
//        IPage<EntReportPageResp> resultPage = riskReportMapper.getEntReportPage(page, pageReqVO);
//        List<EntReportPageResp> list = resultPage.getRecords();

        //1.获取list
        List<EntReportPageResp> list = riskReportMapper.getEntReportPage(pageReqVO);



        //2.计算风险等级
        for (EntReportPageResp entReport:list){
            if (entReport.getViolationCount()>3){
                entReport.setRiskLevel("高风险");
            } else if (entReport.getViolationCount()>1) {
                entReport.setRiskLevel("中风险");
            }else {
                entReport.setRiskLevel("低风险");
            }
        }

        //2.获取total
        long count = riskReportMapper.getEntReportPageCount(pageReqVO);

        //3.配置返回参数
        PageResult<EntReportPageResp> result =new PageResult<>();
        result.setList(list);
        result.setTotal(count);

        return result;
    }

    // ===================== 【导出PDF - 核心方法】 =====================
    @Override
    public ResponseEntity<byte[]> exportRiskReportPdf(EntReportPageReq pageReqVO) {
        // 1. 查询全部数据
//        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EntReportPageResp> list = this.getEntReportPage(pageReqVO).getList();

        // 2. 拼接 HTML
        String html = buildPdfHtml(list);

        // 3. 生成PDF并返回
        VrvPdfGenerator pdfGenerator = new VrvPdfGenerator();
        return pdfGenerator.generatePdfResponse(html);
    }

    // ===================== 【拼接HTML表格】 =====================
    private String buildPdfHtml(List<EntReportPageResp> list) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<meta charset=\"UTF-8\">");
        html.append("<style>");
        html.append("body { font-family: 'SimSun'; font-size: 14px; }");
        html.append("table { width: 100%; border-collapse: collapse; margin-top:10px; }");
        html.append("th, td { border:1px solid #333; padding:8px; text-align:center; }");
        html.append("th { background:#f5f5f5; font-weight:bold; }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");

        html.append("<h2 style='text-align:center'>企业风险评估报告</h2>");

        // 表头
        html.append("<table>");
        html.append("<tr>");
        html.append("<th>报表类型</th>");
        html.append("<th>统计周期</th>");
        html.append("<th>报告编号</th>");
        html.append("<th>企业ID</th>");
        html.append("<th>企业名称</th>");
        html.append("<th>风险等级</th>");
        html.append("<th>区域</th>");
        html.append("<th>企业类型</th>");
        html.append("<th>统计开始时间</th>");
        html.append("<th>统计结束时间</th>");
        html.append("<th>违规次数</th>");
        html.append("</tr>");

        // 数据
        for (EntReportPageResp resp : list) {
            html.append("<tr>");
            html.append("<td>").append(resp.getReportType() == 1 ? "月报" : "自定义报表").append("</td>");
            html.append("<td>").append(resp.getStatisticPeriod() == null ? "" : resp.getStatisticPeriod()).append("</td>");
            html.append("<td>").append(resp.getReportNo() == null ? "" : resp.getReportNo()).append("</td>");
            html.append("<td>").append(resp.getEntId() == null ? "" : resp.getEntId()).append("</td>");
            html.append("<td>").append(resp.getEntName() == null ? "" : resp.getEntName()).append("</td>");
            html.append("<td>").append(resp.getRiskLevel() == null ? "" : resp.getRiskLevel()).append("</td>");
            html.append("<td>").append(resp.getArea() == null ? "" : resp.getArea()).append("</td>");
            html.append("<td>").append(resp.getEntType() == null ? "" : resp.getEntType()).append("</td>");
            html.append("<td>").append(resp.getBeginTime() == null ? "" : resp.getBeginTime()).append("</td>");
            html.append("<td>").append(resp.getEndTime() == null ? "" : resp.getEndTime()).append("</td>");
            html.append("<td>").append(resp.getViolationCount() == null ? 0 : resp.getViolationCount()).append("</td>");
            html.append("</tr>");
        }

        html.append("</table>");
        html.append("</body>");
        html.append("</html>");
        return html.toString();
    }
}
