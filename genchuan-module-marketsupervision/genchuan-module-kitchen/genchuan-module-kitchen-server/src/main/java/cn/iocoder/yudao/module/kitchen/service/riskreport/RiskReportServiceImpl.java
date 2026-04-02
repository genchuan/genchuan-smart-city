package cn.iocoder.yudao.module.kitchen.service.riskreport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.kitchen.controller.admin.riskreport.vo.page.EntReportPageReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.riskreport.vo.page.EntReportPageResp;
import cn.iocoder.yudao.module.kitchen.dal.mysql.riskreport.RiskReportMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskReportServiceImpl implements RiskReportService{
    @Resource
    private RiskReportMapper riskReportMapper;


    @Override
    public PageResult<EntReportPageResp> getEntReportPage(EntReportPageReq pageReqVO) {

        //1.获取list
        List<EntReportPageResp> list = riskReportMapper.getEntReportPage(pageReqVO);

        //2.获取total


        //3.配置返回参数
        PageResult<EntReportPageResp> result =new PageResult<>();
        result.setList(list);
        result.setTotal(10L);

        return result;
    }
}
