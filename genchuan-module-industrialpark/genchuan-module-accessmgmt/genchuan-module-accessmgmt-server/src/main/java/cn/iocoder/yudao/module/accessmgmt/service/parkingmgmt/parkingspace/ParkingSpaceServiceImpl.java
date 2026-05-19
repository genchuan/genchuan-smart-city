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
 * <p>
 * 提供车位管理的全流程业务：分页查询、创建、分配、预约、释放、禁用、取消、确认、数据导出及态势统计。
 * 车位状态流转：空闲 → 占用/预约中 → 空闲（释放/取消）。
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ParkingSpaceServiceImpl implements ParkingSpaceService {

    @Resource
    private ParkingSpaceMapper parkingSpaceMapper;

    // ==================== 车位查询 ====================

    @Override
    public PageResult<ParkingSpaceRespVO> getParkingSpacePage(ParkingSpacePageReqVO pageReqVO) {
        // 分页查询，Mapper 层按 spaceCode(模糊)/parkName(模糊)/spaceType(精确)/spaceStatus(精确) 动态条件筛选，按主键倒序
        PageResult<ParkingSpaceDO> pageResult = parkingSpaceMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult, ParkingSpaceRespVO.class);
    }

    @Override
    public ParkingSpaceRespVO getParkingSpace(Long id) {
        // 按主键查单条，不存在抛 PARKING_SPACE_NOT_EXISTS 业务异常
        ParkingSpaceDO entity = parkingSpaceMapper.selectById(id);
        if (entity == null) {
            throw exception(PARKING_SPACE_NOT_EXISTS);
        }
        return BeanUtils.toBean(entity, ParkingSpaceRespVO.class);
    }

    // ==================== 车位创建 ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createParkingSpace(ParkingSpaceCreateReqVO createReqVO) {
        // 1. 校验车位编号唯一性：按 spaceCode 精确查询，已存在则抛 PARKING_SPACE_CODE_EXISTS
        LambdaQueryWrapper<ParkingSpaceDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ParkingSpaceDO::getSpaceCode, createReqVO.getSpaceCode());
        ParkingSpaceDO existByCode = parkingSpaceMapper.selectOne(wrapper);
        if (existByCode != null) {
            throw exception(PARKING_SPACE_CODE_EXISTS);
        }

        // 2. VO → DO，初始状态设为"空闲"，写入数据库
        ParkingSpaceDO entity = BeanUtils.toBean(createReqVO, ParkingSpaceDO.class);
        entity.setSpaceStatus("空闲");
        parkingSpaceMapper.insert(entity);
        return true;
    }

    // ==================== 车位状态管控（分配 / 预约 / 释放 / 禁用 / 取消 / 确认） ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean allocateParkingSpace(ParkingSpaceAllocateReqVO reqVO) {
        // 分配车位：校验记录存在 → spaceStatus → "占用"，同时写入 orderUser
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
        // 预约车位：校验记录存在 → spaceStatus → "预约中"，同时写入 orderUser
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
        // 释放车位：校验记录存在 → spaceStatus → "空闲"，清空 orderUser 和 useDuration
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
        // 禁用车位：校验记录存在 → spaceStatus → "占用"，停用原因写入 reserve1
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
        // 取消车位：校验记录存在 → spaceStatus → "空闲"，清空 orderUser
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
        // 确认车位：校验记录存在 → spaceStatus → "占用"
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

    // ==================== 数据导出 ====================

    @Override
    public List<ParkingSpaceRespVO> getParkingSpaceList(ParkingSpacePageReqVO pageReqVO) {
        // 设置 PAGE_SIZE_NONE 绕过 MyBatis-Plus 分页限制，查询全量数据
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<ParkingSpaceDO> pageResult = parkingSpaceMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult.getList(), ParkingSpaceRespVO.class);
    }

    // ==================== 统计态势 ====================

    @Override
    public ParkingSpaceChartRespVO getParkingSpaceChart(String parkName) {
        // 1. 统计各状态车位总数（总/空闲/占用/预约），若查询为空则默认全 0 防止 NPE
        ParkingSpaceChartRespVO chartVO = parkingSpaceMapper.selectChartStats(parkName);
        if (chartVO == null) {
            chartVO = new ParkingSpaceChartRespVO();
            chartVO.setTotalSpace(0);
            chartVO.setFreeSpace(0);
            chartVO.setOccupySpace(0);
            chartVO.setReserveSpace(0);
        }
        // 2. 补充四个维度列表：停车场地图分布、车位地图分布、时段使用率趋势、类型占比
        chartVO.setParkMapList(parkingSpaceMapper.selectParkMapList(parkName));
        chartVO.setSpaceMapList(parkingSpaceMapper.selectSpaceMapList(parkName));
        chartVO.setUseRateList(parkingSpaceMapper.selectUseRateList(parkName));
        chartVO.setTypeRateList(parkingSpaceMapper.selectTypeRateList(parkName));
        return chartVO;
    }

}
