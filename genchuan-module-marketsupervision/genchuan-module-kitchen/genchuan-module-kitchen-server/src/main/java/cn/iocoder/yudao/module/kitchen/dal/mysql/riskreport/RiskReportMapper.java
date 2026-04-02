package cn.iocoder.yudao.module.kitchen.dal.mysql.riskreport;

import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.RectifyReviewLedgerPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.RectifyReviewLedgerRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.riskreport.vo.page.EntReportPageReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.riskreport.vo.page.EntReportPageResp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RiskReportMapper {



    List<EntReportPageResp> getEntReportPage(EntReportPageReq pageReqVO);
}
