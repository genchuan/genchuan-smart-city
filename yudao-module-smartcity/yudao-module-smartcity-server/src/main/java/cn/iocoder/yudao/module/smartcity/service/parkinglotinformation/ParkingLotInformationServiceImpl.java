package cn.iocoder.yudao.module.smartcity.service.parkinglotinformation;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.parkinglotinformation.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.parkinglotinformation.ParkingLotInformationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.parkinglotinformation.ParkingLotInformationMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 停车场信息管理 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class ParkingLotInformationServiceImpl implements ParkingLotInformationService {

    @Resource
    private ParkingLotInformationMapper parkingLotInformationMapper;

    @Override
    public Long createParkingLotInformation(ParkingLotInformationSaveReqVO createReqVO) {
        // 插入
        ParkingLotInformationDO parkingLotInformation = BeanUtils.toBean(createReqVO, ParkingLotInformationDO.class);
        parkingLotInformationMapper.insert(parkingLotInformation);
        // 返回
        return parkingLotInformation.getId();
    }

    @Override
    public void updateParkingLotInformation(ParkingLotInformationSaveReqVO updateReqVO) {
        // 校验存在
        validateParkingLotInformationExists(updateReqVO.getId());
        // 更新
        ParkingLotInformationDO updateObj = BeanUtils.toBean(updateReqVO, ParkingLotInformationDO.class);
        parkingLotInformationMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkingLotInformation(Long id) {
        // 校验存在
        validateParkingLotInformationExists(id);
        // 删除
        parkingLotInformationMapper.deleteById(id);
    }

    private void validateParkingLotInformationExists(Long id) {
        if (parkingLotInformationMapper.selectById(id) == null) {
            throw exception(PARKING_LOT_INFORMATION_NOT_EXISTS);
        }
    }

    @Override
    public ParkingLotInformationDO getParkingLotInformation(Long id) {
        return parkingLotInformationMapper.selectById(id);
    }

    @Override
    public PageResult<ParkingLotInformationDO> getParkingLotInformationPage(ParkingLotInformationPageReqVO pageReqVO) {
        return parkingLotInformationMapper.selectPage(pageReqVO);
    }

}