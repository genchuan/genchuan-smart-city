package cn.iocoder.yudao.module.industry.service.park.through.trafficinspection;

import cn.iocoder.yudao.module.industry.controller.admin.park.through.trafficinspection.vo.ParkTrafficInspectionPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.trafficinspection.vo.ParkTrafficInspectionSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.industry.dal.dataobject.park.through.trafficinspection.ParkTrafficInspectionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.industry.dal.mysql.park.through.trafficinspection.ParkTrafficInspectionMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 通行稽查 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class ParkTrafficInspectionServiceImpl implements ParkTrafficInspectionService {

    @Resource
    private ParkTrafficInspectionMapper parkTrafficInspectionMapper;

    @Override
    public Long createParkTrafficInspection(ParkTrafficInspectionSaveReqVO createReqVO) {
        // 插入
        ParkTrafficInspectionDO parkTrafficInspection = BeanUtils.toBean(createReqVO, ParkTrafficInspectionDO.class);
        parkTrafficInspectionMapper.insert(parkTrafficInspection);
        // 返回
        return parkTrafficInspection.getId();
    }

    @Override
    public void updateParkTrafficInspection(ParkTrafficInspectionSaveReqVO updateReqVO) {
        // 校验存在
        validateParkTrafficInspectionExists(updateReqVO.getId());
        // 更新
        ParkTrafficInspectionDO updateObj = BeanUtils.toBean(updateReqVO, ParkTrafficInspectionDO.class);
        parkTrafficInspectionMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkTrafficInspection(Long id) {
        // 校验存在
        validateParkTrafficInspectionExists(id);
        // 删除
        parkTrafficInspectionMapper.deleteById(id);
    }

    private void validateParkTrafficInspectionExists(Long id) {
        if (parkTrafficInspectionMapper.selectById(id) == null) {
            throw exception(PARK_TRAFFIC_INSPECTION_NOT_EXISTS);
        }
    }

    @Override
    public ParkTrafficInspectionDO getParkTrafficInspection(Long id) {
        return parkTrafficInspectionMapper.selectById(id);
    }

    @Override
    public PageResult<ParkTrafficInspectionDO> getParkTrafficInspectionPage(ParkTrafficInspectionPageReqVO pageReqVO) {
        return parkTrafficInspectionMapper.selectPage(pageReqVO);
    }

}