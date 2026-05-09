package cn.iocoder.yudao.module.kitchen.dal.mysql.riskreport;

import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.RectifyReviewLedgerPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.RectifyReviewLedgerRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.riskreport.vo.page.EntReportPageReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.riskreport.vo.page.EntReportPageResp;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RiskReportMapper {

//    IPage<EntReportPageResp> getEntReportPage(Page page, @Param("req") EntReportPageReq req);

    List<EntReportPageResp> getEntReportPage(EntReportPageReq pageReqVO);

    long getEntReportPageCount(EntReportPageReq pageReqVO);

    /**
     * 按企业维度汇总违规次数（去重，不按月份分组）
     */
    List<EntViolationStatDO> getEntViolationStatList(EntReportPageReq req);
}
