package cn.iocoder.yudao.module.accessmgmt.service.parkingmgmt.parkingspace;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingspace.vo.*;
import cn.iocoder.yudao.module.accessmgmt.dal.dataobject.parkingmgmt.parkingspace.ParkingSpaceDO;
import cn.iocoder.yudao.module.accessmgmt.dal.mysql.parkingmgmt.parkingspace.ParkingSpaceMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.accessmgmt.enums.ErrorCodeConstants.*;

/**
 * 车位信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ParkingSpaceServiceImpl implements ParkingSpaceService {

    @Resource
    private ParkingSpaceMapper parkingSpaceMapper;

    @Override
    public PageResult<ParkingSpaceRespVO> getParkingSpacePage(ParkingSpacePageReqVO pageReqVO) {
        PageResult<ParkingSpaceDO> pageResult = parkingSpaceMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult, ParkingSpaceRespVO.class);
    }

    @Override
    public ParkingSpaceRespVO getParkingSpace(Long id) {
        ParkingSpaceDO entity = parkingSpaceMapper.selectById(id);
        if (entity == null) {
            throw exception(PARKING_SPACE_NOT_EXISTS);
        }
        return BeanUtils.toBean(entity, ParkingSpaceRespVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createParkingSpace(ParkingSpaceCreateReqVO createReqVO) {
        LambdaQueryWrapper<ParkingSpaceDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ParkingSpaceDO::getSpaceCode, createReqVO.getSpaceCode());
        ParkingSpaceDO existByCode = parkingSpaceMapper.selectOne(wrapper);
        if (existByCode != null) {
            throw exception(PARKING_SPACE_CODE_EXISTS);
        }

        ParkingSpaceDO entity = BeanUtils.toBean(createReqVO, ParkingSpaceDO.class);
        entity.setSpaceStatus("空闲");
        parkingSpaceMapper.insert(entity);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean allocateParkingSpace(ParkingSpaceAllocateReqVO reqVO) {
        ParkingSpaceDO exist = parkingSpaceMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(PARKING_SPACE_NOT_EXISTS);
        }
        ParkingSpaceDO updateObj = new ParkingSpaceDO();
        updateObj.setId(reqVO.getId());
        updateObj.setOrderUser(reqVO.getOrderUser());
        updateObj.setSpaceStatus("占用");
        parkingSpaceMapper.updateById(updateObj);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean reserveParkingSpace(ParkingSpaceReserveReqVO reqVO) {
        ParkingSpaceDO exist = parkingSpaceMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(PARKING_SPACE_NOT_EXISTS);
        }
        ParkingSpaceDO updateObj = new ParkingSpaceDO();
        updateObj.setId(reqVO.getId());
        updateObj.setOrderUser(reqVO.getOrderUser());
        updateObj.setSpaceStatus("预约中");
        parkingSpaceMapper.updateById(updateObj);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean releaseParkingSpace(ParkingSpaceReleaseReqVO reqVO) {
        ParkingSpaceDO exist = parkingSpaceMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(PARKING_SPACE_NOT_EXISTS);
        }
        ParkingSpaceDO updateObj = new ParkingSpaceDO();
        updateObj.setId(reqVO.getId());
        updateObj.setOrderUser(null);
        updateObj.setUseDuration(null);
        updateObj.setSpaceStatus("空闲");
        parkingSpaceMapper.updateById(updateObj);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean disableParkingSpace(ParkingSpaceDisableReqVO reqVO) {
        ParkingSpaceDO exist = parkingSpaceMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(PARKING_SPACE_NOT_EXISTS);
        }
        ParkingSpaceDO updateObj = new ParkingSpaceDO();
        updateObj.setId(reqVO.getId());
        updateObj.setSpaceStatus("占用");
        updateObj.setReserve1(reqVO.getDisableReason());
        parkingSpaceMapper.updateById(updateObj);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean cancelParkingSpace(ParkingSpaceCancelReqVO reqVO) {
        ParkingSpaceDO exist = parkingSpaceMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(PARKING_SPACE_NOT_EXISTS);
        }
        ParkingSpaceDO updateObj = new ParkingSpaceDO();
        updateObj.setId(reqVO.getId());
        updateObj.setOrderUser(null);
        updateObj.setSpaceStatus("空闲");
        parkingSpaceMapper.updateById(updateObj);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean confirmParkingSpace(ParkingSpaceConfirmReqVO reqVO) {
        ParkingSpaceDO exist = parkingSpaceMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(PARKING_SPACE_NOT_EXISTS);
        }
        ParkingSpaceDO updateObj = new ParkingSpaceDO();
        updateObj.setId(reqVO.getId());
        updateObj.setSpaceStatus("占用");
        parkingSpaceMapper.updateById(updateObj);
        return true;
    }

    @Override
    public List<ParkingSpaceRespVO> getParkingSpaceList(ParkingSpacePageReqVO pageReqVO) {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<ParkingSpaceDO> pageResult = parkingSpaceMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult.getList(), ParkingSpaceRespVO.class);
    }

    @Override
    public ParkingSpaceChartRespVO getParkingSpaceChart(String parkName) {
        ParkingSpaceChartRespVO chartVO = parkingSpaceMapper.selectChartStats(parkName);
        if (chartVO == null) {
            chartVO = new ParkingSpaceChartRespVO();
            chartVO.setTotalSpace(0);
            chartVO.setFreeSpace(0);
            chartVO.setOccupySpace(0);
            chartVO.setReserveSpace(0);
        }
        chartVO.setParkMapList(parkingSpaceMapper.selectParkMapList(parkName));
        chartVO.setSpaceMapList(parkingSpaceMapper.selectSpaceMapList(parkName));
        chartVO.setUseRateList(parkingSpaceMapper.selectUseRateList(parkName));
        chartVO.setTypeRateList(parkingSpaceMapper.selectTypeRateList(parkName));
        return chartVO;
    }

}
