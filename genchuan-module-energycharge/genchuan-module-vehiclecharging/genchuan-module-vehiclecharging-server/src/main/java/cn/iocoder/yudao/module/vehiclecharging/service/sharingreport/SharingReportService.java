package cn.iocoder.yudao.module.vehiclecharging.service.sharingreport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport.vo.SharingReportPageReqVO;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.sharingreport.SharingReportDO;
import jakarta.validation.Valid;

public interface SharingReportService {


    PageResult<SharingReportDO> getSharingReportPage(@Valid SharingReportPageReqVO pageReqVO);
}
