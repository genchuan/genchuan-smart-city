package cn.iocoder.yudao.module.industry.service.park.cardriveinrecord;

import cn.iocoder.yudao.module.industry.controller.admin.park.cardriveinrecord.vo.CarDriveinRecordPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.cardriveinrecord.vo.CarDriveinRecordSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.industry.dal.dataobject.park.cardriveinrecord.CarDriveinRecordDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.industry.dal.mysql.park.cardriveinrecord.CarDriveinRecordMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 车辆入场记录 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class CarDriveinRecordServiceImpl implements CarDriveinRecordService {

    @Resource
    private CarDriveinRecordMapper carDriveinRecordMapper;

    @Override
    public Long createCarDriveinRecord(CarDriveinRecordSaveReqVO createReqVO) {
        // 插入
        CarDriveinRecordDO carDriveinRecord = BeanUtils.toBean(createReqVO, CarDriveinRecordDO.class);
        carDriveinRecordMapper.insert(carDriveinRecord);
        // 返回
        return carDriveinRecord.getId();
    }

    @Override
    public void updateCarDriveinRecord(CarDriveinRecordSaveReqVO updateReqVO) {
        // 校验存在
        validateCarDriveinRecordExists(updateReqVO.getId());
        // 更新
        CarDriveinRecordDO updateObj = BeanUtils.toBean(updateReqVO, CarDriveinRecordDO.class);
        carDriveinRecordMapper.updateById(updateObj);
    }

    @Override
    public void deleteCarDriveinRecord(Long id) {
        // 校验存在
        validateCarDriveinRecordExists(id);
        // 删除
        carDriveinRecordMapper.deleteById(id);
    }

    private void validateCarDriveinRecordExists(Long id) {
        if (carDriveinRecordMapper.selectById(id) == null) {
            throw exception(CAR_DRIVEIN_RECORD_NOT_EXISTS);
        }
    }

    @Override
    public CarDriveinRecordDO getCarDriveinRecord(Long id) {
        return carDriveinRecordMapper.selectById(id);
    }

    @Override
    public PageResult<CarDriveinRecordDO> getCarDriveinRecordPage(CarDriveinRecordPageReqVO pageReqVO) {
        return carDriveinRecordMapper.selectPage(pageReqVO);
    }

}