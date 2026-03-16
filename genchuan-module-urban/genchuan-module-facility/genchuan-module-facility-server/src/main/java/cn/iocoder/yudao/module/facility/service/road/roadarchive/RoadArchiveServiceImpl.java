package cn.iocoder.yudao.module.facility.service.road.roadarchive;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadarchive.vo.RoadArchivePageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadarchive.vo.RoadArchivePageRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadworkorder.vo.RoadWorkOrderPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadworkorder.vo.RoadWorkOrderPageRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.sysarchive.vo.SysArchiveRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.workorder.vo.WorkOrderRespVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.sysarchive.SysArchiveDO;
import cn.iocoder.yudao.module.facility.dal.mysql.road.roadworkorder.RoadWorkOrderMapper;
import cn.iocoder.yudao.module.facility.service.road.roadworkorder.RoadWorkOrderService;
import cn.iocoder.yudao.module.facility.service.sysarchive.SysArchiveService;
import cn.iocoder.yudao.module.facility.service.workorder.WorkOrderService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 工单 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class RoadArchiveServiceImpl implements RoadArchiveService {

    @Resource
    private SysArchiveService sysArchiveService;

    @Override
    public PageResult<RoadArchivePageRespVO> getArchivePage(RoadArchivePageReqVO pageReqVO) {
        PageResult<SysArchiveDO> orginPage = sysArchiveService.getSysArchivePage(pageReqVO);
        PageResult<RoadArchivePageRespVO> resultPage = BeanUtils.toBean(orginPage, RoadArchivePageRespVO.class);
        return resultPage;
    }



}
