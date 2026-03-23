package cn.iocoder.yudao.module.facility.service.manhole.manholecover;

import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholecover.vo.ManholeCoverDetailRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholecover.vo.ManholeCoverPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholecover.vo.ManholeCoverSaveReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo.ManholeMonitorStatsRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo.ManholeCoverRealTimePageRespVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.disposalorder.DisposalOrderDO;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholecover.ManholeCoverDO;
import cn.iocoder.yudao.module.facility.dal.mysql.manhole.disposalorder.DisposalOrderMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.manhole.manholecover.ManholeCoverMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.manhole.manholemonitor.ManholeMonitorMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.facility.enums.ErrorCodeConstants.COVER_NOT_EXISTS;

/**
 * 窨井盖设施 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ManholeCoverServiceImpl implements ManholeCoverService {

    @Resource
    private ManholeCoverMapper coverMapper;

    @Resource
    private ManholeMonitorMapper monitorMapper;

    @Resource
    private DisposalOrderMapper disposalOrderMapper;

    @Override
    public Long createCover(ManholeCoverSaveReqVO createReqVO) {
        // 插入
        ManholeCoverDO cover = BeanUtils.toBean(createReqVO, ManholeCoverDO.class);
        coverMapper.insert(cover);
        // 返回
        return cover.getId();
    }

    @Override
    public void updateCover(ManholeCoverSaveReqVO updateReqVO) {
        // 校验存在
        validateCoverExists(updateReqVO.getId());
        // 更新
        ManholeCoverDO updateObj = BeanUtils.toBean(updateReqVO, ManholeCoverDO.class);
        coverMapper.updateById(updateObj);

    }

    @Override
    public void deleteCover(Long id) {
        // 校验存在
        validateCoverExists(id);
        // 删除
        coverMapper.deleteById(id);
    }

    private void validateCoverExists(Long id) {
        if (coverMapper.selectById(id) == null) {
            throw exception(COVER_NOT_EXISTS);
        }
    }

    @Override
    public ManholeCoverDO getCover(Long id) {
        return coverMapper.selectById(id);
    }

    @Override
    public PageResult<ManholeCoverDO> getCoverPage(ManholeCoverPageReqVO pageReqVO) {
        return coverMapper.selectPage(pageReqVO);
    }

//    @Override
//    public ManholeCoverDetailRespVO getCoverDetail(Long id) {
//        ManholeCoverRealTimePageRespVO cover = monitorMapper.selectManholeDetailByCoverId(id);
//        if (ObjectUtil.isNull(cover)) {
//            throw exception(COVER_NOT_EXISTS);
//        }

//        ManholeCoverDetailRespVO detail = new ManholeCoverDetailRespVO();
//        BeanUtils.copyProperties(cover, detail);

//        ManholeMonitorVO latestMonitor = monitorMapper.selectManholeDetailByCoverId(id);
//        if (latestMonitor != null) {
//            BeanUtils.toBean(latestMonitor, detail);
//        }

//        ManholeConfigDO config = configMapper.selectByCoverId(id);
//        if (config != null) {
//            detail.setTiltAngleThreshold(config.getTiltAngleThreshold());
//            detail.setCollectFrequency(config.getCollectFrequency());
//        }

//        ManholeMonitorStatsRespVO stats = monitorMapper.select24HourStats(id);
//        if (stats == null) {
//            stats = new ManholeMonitorStatsRespVO();
//            stats.setAvgTiltAngle(BigDecimal.ZERO);
//            stats.setMaxTiltAngle(BigDecimal.ZERO);
//            stats.setMinTiltAngle(BigDecimal.ZERO);
//            stats.setAvgVibration(BigDecimal.ZERO);
//            stats.setMaxVibration(BigDecimal.ZERO);
//            stats.setMinVibration(BigDecimal.ZERO);
//        }
//        detail.setManholeMonitorStatsRespVO(stats);
//
//        List<DisposalOrderDO> faultRecords = disposalOrderMapper.selectFaultRecordsByCoverId(id);
//
//        System.out.println(faultRecords);
//
//        if (faultRecords == null) {
//            faultRecords = new ArrayList<>();
//        }
//        detail.setDisposalOrderDOList(faultRecords);
//
//        return detail;
//    }
}