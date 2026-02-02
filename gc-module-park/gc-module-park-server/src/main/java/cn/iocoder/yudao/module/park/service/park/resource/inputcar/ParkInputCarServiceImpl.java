package cn.iocoder.yudao.module.park.service.park.resource.inputcar;

import cn.iocoder.yudao.module.park.controller.admin.park.resource.inputcar.vo.ParkInputCarEntryReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.resource.inputcar.vo.ParkInputCarExitReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.resource.inputcar.vo.ParkInputCarPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.resource.inputcar.vo.ParkInputCarSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.park.dal.dataobject.park.resource.inputcar.ParkInputCarDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.park.dal.mysql.park.resource.inputcar.ParkInputCarMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.*;

/**
 * 泊位录入车辆 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class ParkInputCarServiceImpl implements ParkInputCarService {

    @Resource
    private ParkInputCarMapper inputCarMapper;

    @Override
    public Long createInputCar(ParkInputCarSaveReqVO createReqVO) {
        // 插入
        ParkInputCarDO inputCar = BeanUtils.toBean(createReqVO, ParkInputCarDO.class);
        inputCarMapper.insert(inputCar);
        // 返回
        return inputCar.getId();
    }

    @Override
    public void updateInputCar(ParkInputCarSaveReqVO updateReqVO) {
        // 校验存在
        validateInputCarExists(updateReqVO.getId());
        // 更新
        ParkInputCarDO updateObj = BeanUtils.toBean(updateReqVO, ParkInputCarDO.class);
        inputCarMapper.updateById(updateObj);
    }

    @Override
    public void deleteInputCar(Long id) {
        // 校验存在
        validateInputCarExists(id);
        // 删除
        inputCarMapper.deleteById(id);
    }

    private void validateInputCarExists(Long id) {
        if (inputCarMapper.selectById(id) == null) {
            throw exception(INPUT_CAR_NOT_EXISTS);
        }
    }

    @Override
    public ParkInputCarDO getInputCar(Long id) {
        return inputCarMapper.selectById(id);
    }

    @Override
    public PageResult<ParkInputCarDO> getInputCarPage(ParkInputCarPageReqVO pageReqVO) {
        return inputCarMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createEntry(ParkInputCarEntryReqVO reqVO) {
        // 创建进场记录
        ParkInputCarDO car = new ParkInputCarDO();
        BeanUtils.copyProperties(reqVO, car);
        car.setParkingStatus("1"); // 1-在场状态
        inputCarMapper.insert(car);
        return car.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateExit(ParkInputCarExitReqVO reqVO) {
        // 校验记录是否存在
        ParkInputCarDO car = inputCarMapper.selectById(reqVO.getId());
        if (car == null) {
            throw exception(INPUT_CAR_NOT_EXISTS);
        }

        // 更新离场信息
        ParkInputCarDO updateObj = new ParkInputCarDO();
        updateObj.setId(reqVO.getId());
        updateObj.setExitTime(reqVO.getExitTime());
        updateObj.setParkingStatus("2"); // 2-已离场状态
        inputCarMapper.updateById(updateObj);
    }

}