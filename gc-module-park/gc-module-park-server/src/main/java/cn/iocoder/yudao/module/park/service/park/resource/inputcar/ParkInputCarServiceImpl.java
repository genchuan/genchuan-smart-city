package cn.iocoder.yudao.module.park.service.park.resource.inputcar;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.bpm.api.task.BpmProcessInstanceApi;
import cn.iocoder.yudao.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import cn.iocoder.yudao.module.park.controller.admin.park.resource.inputcar.vo.*;
import cn.iocoder.yudao.module.park.controller.admin.park.resource.roadsideberthmanage.vo.RoadsideBerthManageSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.resource.inputcar.ParkInputCarDO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.resource.roadsideberthmanage.RoadsideBerthManageDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.resource.inputcar.ParkInputCarMapper;
import cn.iocoder.yudao.module.park.service.park.resource.roadsideberthmanage.RoadsideBerthManageService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.INPUT_CAR_NOT_EXISTS;


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

    @Resource
    private BpmProcessInstanceApi processInstanceApi;

    @Resource
    private RoadsideBerthManageService roadsideBerthManageService;

    private static final Logger log = LoggerFactory.getLogger(ParkInputCarServiceImpl.class);

    @Override
    public Long createInputCar(ParkInputCarSaveReqVO createReqVO) {
        // 插入
        ParkInputCarDO inputCar = BeanUtils.toBean(createReqVO, ParkInputCarDO.class);
        inputCarMapper.insert(inputCar);
        // 更新路测泊位管理表
        updateRoadsideBerthOnEntry(createReqVO.getTargetBerthNo(), createReqVO.getCarNumber(),createReqVO.getParkId());
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
    public List<ParkInputCarDO> getInputCarHistoryByBerthNo(String targetBerthNo, String parkingStatus, String parkId) {
        // 使用MyBatis-Plus的查询条件构造器
        QueryWrapper<ParkInputCarDO> queryWrapper = new QueryWrapper<>();

        // 设置查询条件：目标泊位号必须匹配
        queryWrapper.eq("target_berth_no", targetBerthNo).eq("park_id", parkId);;

        // 新增条件：如果parkingStatus非空，则按停车状态筛选
        if (parkingStatus != null && !parkingStatus.trim().isEmpty()) {
            queryWrapper.eq("parking_status", parkingStatus);
        }

        // 按入场时间倒序排列，最新的记录在前面
        queryWrapper.orderByDesc("entry_time");

        // 执行查询并返回结果列表
        return inputCarMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long simulateMagneticDetection(ParkInputCarMagneticDetectionReqVO reqVO) {
        // 创建进场记录
        ParkInputCarDO car = new ParkInputCarDO();
        car.setParkId(reqVO.getParkId());
        car.setTargetBerthNo(reqVO.getTargetBerthNo());
        car.setEntryTime(reqVO.getEntryTime());
        car.setParkingStatus("待录入"); // 1-在场状态
        inputCarMapper.insert(car);
        // 创建流程实例
        try {
            BpmProcessInstanceCreateReqDTO createReqDTO = new BpmProcessInstanceCreateReqDTO();
            createReqDTO.setProcessDefinitionKey("park_01");
            createReqDTO.setBusinessKey(String.valueOf(car.getId()));

            // 将车辆记录信息作为流程变量传递
            Map<String, Object> variables = new HashMap<>();
            variables.put("inputCarId", car.getId());
            variables.put("parkId", car.getParkId());
            variables.put("targetBerthNo", car.getTargetBerthNo());
            variables.put("entryTime", car.getEntryTime());
            variables.put("parkingStatus", car.getParkingStatus());
            createReqDTO.setVariables(variables);

            CommonResult<String> commonResult = processInstanceApi.createProcessInstance(1L, createReqDTO);

            if (!commonResult.isSuccess()) {
                log.warn("创建流程实例失败: {}, 但车辆记录已保存，ID: {}", commonResult.getMsg());
                // 不抛出异常，返回成功创建的车辆记录ID
                return car.getId();
            }

            log.info("成功创建流程实例: {}", commonResult.getData(), car.getId(),createReqDTO.getBusinessKey());
        } catch (Exception e) {
            log.warn("调用流程服务异常，但车辆记录已保存，ID: {}", car.getId(), e);
            // 不抛出异常，返回成功创建的车辆记录ID
        }

        return car.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean simulateMagneticDetectionExit(ParkInputCarMagneticDetectionExitReqVO reqVO) {
        // 校验车辆记录是否存在
        ParkInputCarDO car = inputCarMapper.selectById(reqVO.getId());
        if (car == null) {
            throw exception(INPUT_CAR_NOT_EXISTS);
        }

        // 更新车辆离场信息
        ParkInputCarDO updateObj = new ParkInputCarDO();
        updateObj.setId(reqVO.getId());
        updateObj.setExitTime(reqVO.getExitTime());
//        updateObj.setParkingStatus("2"); // 2-已离场状态
        inputCarMapper.updateById(updateObj);

        // 创建离场流程实例
        try {
            BpmProcessInstanceCreateReqDTO createReqDTO = new BpmProcessInstanceCreateReqDTO();
            createReqDTO.setProcessDefinitionKey("park_02");
            createReqDTO.setBusinessKey(String.valueOf(reqVO.getId()));

            CommonResult<String> commonResult = processInstanceApi.createProcessInstance(1L, createReqDTO);

            if (!commonResult.isSuccess()) {
                log.warn("创建离场流程实例失败: {}, 但车辆离场记录已更新，ID: {}", commonResult.getMsg(), reqVO.getId());
                // 不抛出异常，返回成功更新的状态
                return true;
            }

            log.info("成功创建离场流程实例: {}", commonResult.getData());
        } catch (Exception e) {
            log.warn("调用离场流程服务异常，但车辆离场记录已更新，ID: {}", reqVO.getId(), e);
            // 不抛出异常，返回成功更新的状态
        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createEntry(ParkInputCarEntryReqVO reqVO) {
        // 更新进场记录
        ParkInputCarDO car = new ParkInputCarDO();
        BeanUtils.copyProperties(reqVO, car);
        car.setId(reqVO.getId());
        car.setParkingStatus("已停入"); // 1-在场状态
        car.setExtCommon1(reqVO.getExtCommon1());
        inputCarMapper.updateById(car);

        // 更新路测泊位管理表
        updateRoadsideBerthOnEntry(reqVO.getTargetBerthNo(), reqVO.getCarNumber(),reqVO.getParkId());


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
        updateObj.setParkingStatus(reqVO.getParkingStatus());
        updateObj.setExitTime(reqVO.getExitTime());
        inputCarMapper.updateById(updateObj);

        // 更新路测泊位管理表
        updateRoadsideBerthOnExit(car.getTargetBerthNo(),car.getParkId());

    }

    // ：车辆进场时更新泊位状态
    private void updateRoadsideBerthOnEntry(String targetBerthNo, String carNumber, String parkId) {
        try {
            // 根据泊位编号查找路测泊位管理记录
            RoadsideBerthManageDO berth = roadsideBerthManageService.getRoadsideBerthManageByBerthCode(targetBerthNo,parkId);
            if (berth == null) {
                log.warn("更新路测泊位管理失败：泊位编号不存在，berthCode={}", targetBerthNo);
                return; // 泊位不存在，不阻断主流程
            }

            // 随机生成车辆类型：普通车辆或畅停卡
            String vehicleType = new Random().nextBoolean() ? "普通车辆" : "畅停卡";

            // 更新泊位状态和当前车辆
            RoadsideBerthManageSaveReqVO updateReqVO = new RoadsideBerthManageSaveReqVO();
            updateReqVO.setId(berth.getId());
            updateReqVO.setCurrentCar(carNumber);
            updateReqVO.setBerthStatus("占用"); // 1-占用状态
            updateReqVO.setExtCommon1(vehicleType);
            roadsideBerthManageService.updateRoadsideBerthManage(updateReqVO);
        } catch (Exception e) {
            log.error("更新路测泊位管理异常：berthCode={}", targetBerthNo, e);
            // 不抛出异常，避免影响车辆进场主流程
        }
    }

    // ：车辆离场时更新泊位状态
    private void updateRoadsideBerthOnExit(String targetBerthNo, String parkId) {
        try {
            // 根据泊位编号查找路测泊位管理记录
            RoadsideBerthManageDO berth = roadsideBerthManageService.getRoadsideBerthManageByBerthCode(targetBerthNo,parkId);
            if (berth == null) {
                log.warn("更新路测泊位管理失败：泊位编号不存在，berthCode={}", targetBerthNo);
                return;
            }
            // 更新泊位状态和当前车辆（离场后清空）
            RoadsideBerthManageSaveReqVO updateReqVO = new RoadsideBerthManageSaveReqVO();
            updateReqVO.setId(berth.getId());
            updateReqVO.setCurrentCar(" "); // 清空车辆
            updateReqVO.setExtCommon1(" "); // 清空车辆类型
            updateReqVO.setBerthStatus("空闲"); // 2-空闲状态

            roadsideBerthManageService.updateRoadsideBerthManage(updateReqVO);

        } catch (Exception e) {
            log.error("更新路测泊位管理异常：berthCode={}", targetBerthNo, e);
            // 不抛出异常，避免影响车辆离场主流程
        }
    }

}
