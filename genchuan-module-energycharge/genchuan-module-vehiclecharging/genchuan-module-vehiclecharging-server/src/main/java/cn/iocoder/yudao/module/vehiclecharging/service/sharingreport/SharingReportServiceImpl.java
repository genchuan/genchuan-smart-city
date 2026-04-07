package cn.iocoder.yudao.module.vehiclecharging.service.sharingreport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport.vo.SharingReportPageReqVO;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.sharingreport.SharingReportDO;
import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.sharingreport.SharingReportMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 分账报表 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class SharingReportServiceImpl implements SharingReportService {

    @Resource
    private SharingReportMapper sharingReportMapper;

    @Override
    public PageResult<SharingReportDO> getSharingReportPage(SharingReportPageReqVO pageReqVO) {
        return null;
    }
}
