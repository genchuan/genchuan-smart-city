package cn.iocoder.yudao.module.industry.service.park.cardriveoutrecord;

import cn.iocoder.yudao.module.industry.controller.admin.park.cardriveoutrecord.vo.CarDriveoutRecordPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.cardriveoutrecord.vo.CarDriveoutRecordSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.industry.dal.dataobject.park.cardriveoutrecord.CarDriveoutRecordDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.industry.dal.mysql.park.cardriveoutrecord.CarDriveoutRecordMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 车辆出场记录 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class CarDriveoutRecordServiceImpl implements CarDriveoutRecordService {

    @Resource
    private CarDriveoutRecordMapper carDriveoutRecordMapper;

    @Override
    public Long createCarDriveoutRecord(CarDriveoutRecordSaveReqVO createReqVO) {
        // 插入
        CarDriveoutRecordDO carDriveoutRecord = BeanUtils.toBean(createReqVO, CarDriveoutRecordDO.class);
        carDriveoutRecordMapper.insert(carDriveoutRecord);
        // 返回
        return carDriveoutRecord.getId();
    }

    @Override
    public void updateCarDriveoutRecord(CarDriveoutRecordSaveReqVO updateReqVO) {
        // 校验存在
        validateCarDriveoutRecordExists(updateReqVO.getId());
        // 更新
        CarDriveoutRecordDO updateObj = BeanUtils.toBean(updateReqVO, CarDriveoutRecordDO.class);
        carDriveoutRecordMapper.updateById(updateObj);
    }

    @Override
    public void deleteCarDriveoutRecord(Long id) {
        // 校验存在
        validateCarDriveoutRecordExists(id);
        // 删除
        carDriveoutRecordMapper.deleteById(id);
    }

    private void validateCarDriveoutRecordExists(Long id) {
        if (carDriveoutRecordMapper.selectById(id) == null) {
            throw exception(CAR_DRIVEOUT_RECORD_NOT_EXISTS);
        }
    }

    @Override
    public CarDriveoutRecordDO getCarDriveoutRecord(Long id) {
        return carDriveoutRecordMapper.selectById(id);
    }

    @Override
    public PageResult<CarDriveoutRecordDO> getCarDriveoutRecordPage(CarDriveoutRecordPageReqVO pageReqVO) {
        return carDriveoutRecordMapper.selectPage(pageReqVO);
    }

}