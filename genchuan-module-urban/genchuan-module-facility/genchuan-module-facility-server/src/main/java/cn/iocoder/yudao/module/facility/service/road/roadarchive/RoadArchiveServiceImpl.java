package cn.iocoder.yudao.module.facility.service.road.roadarchive;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadarchive.vo.RoadArchivePageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadarchive.vo.RoadArchivePageRespVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.sysarchive.SysArchiveDO;
import cn.iocoder.yudao.module.facility.service.sysarchive.SysArchiveService;
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
