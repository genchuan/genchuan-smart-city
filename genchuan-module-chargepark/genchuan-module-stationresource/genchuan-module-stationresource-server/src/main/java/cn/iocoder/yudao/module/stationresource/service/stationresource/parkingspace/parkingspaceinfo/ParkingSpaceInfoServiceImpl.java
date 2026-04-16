package cn.iocoder.yudao.module.stationresource.service.stationresource.parkingspace.parkingspaceinfo;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ParkingSpaceInfoPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ParkingSpaceInfoSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ops.AddParkingSpaceInfoReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.parkingspace.parkingspaceinfo.ParkingSpaceInfoDO;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.parkingspace.parkingspaceinfo.ParkingSpaceInfoMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.stationresource.enums.ErrorCodeConstants.*;

/**
 * 车位信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ParkingSpaceInfoServiceImpl implements ParkingSpaceInfoService {

    @Resource
    private ParkingSpaceInfoMapper parkingSpaceInfoMapper;

    @Override
    public Long addParkingSpaceInfo(AddParkingSpaceInfoReqVO createReqVO) {
        // 1. 校验车位编号唯一
        ParkingSpaceInfoDO exist = parkingSpaceInfoMapper.selectOne(ParkingSpaceInfoDO::getSpaceNo, createReqVO.getSpaceNo());
        if (exist != null) {
            throw exception("车位编号已存在，请勿重复创建");
        }

        // 2. 转换 DO
        ParkingSpaceInfoDO entity = BeanUtils.toBean(createReqVO, ParkingSpaceInfoDO.class);

        // 3. 未创建时默认状态：未绑定
        entity.setStatus("未绑定");

        // 4. 插入
        parkingSpaceInfoMapper.insert(entity);
        return entity.getId();
    }
    @Override
    public Long createParkingSpaceInfo(ParkingSpaceInfoSaveReqVO createReqVO) {
        // 插入
        ParkingSpaceInfoDO parkingSpaceInfo = BeanUtils.toBean(createReqVO, ParkingSpaceInfoDO.class);
        parkingSpaceInfoMapper.insert(parkingSpaceInfo);

        // 返回
        return parkingSpaceInfo.getId();
    }

    @Override
    public void updateParkingSpaceInfo(ParkingSpaceInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateParkingSpaceInfoExists(updateReqVO.getId());
        // 更新
        ParkingSpaceInfoDO updateObj = BeanUtils.toBean(updateReqVO, ParkingSpaceInfoDO.class);
        parkingSpaceInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkingSpaceInfo(Long id) {
        // 校验存在
        validateParkingSpaceInfoExists(id);
        // 删除
        parkingSpaceInfoMapper.deleteById(id);
    }

    @Override
        public void deleteParkingSpaceInfoListByIds(List<Long> ids) {
        // 删除
        parkingSpaceInfoMapper.deleteByIds(ids);
        }


    private void validateParkingSpaceInfoExists(Long id) {
        if (parkingSpaceInfoMapper.selectById(id) == null) {
            throw exception(PARKING_SPACE_INFO_NOT_EXISTS);
        }
    }

    @Override
    public ParkingSpaceInfoDO getParkingSpaceInfo(Long id) {
        return parkingSpaceInfoMapper.selectById(id);
    }

    @Override
    public PageResult<ParkingSpaceInfoDO> getParkingSpaceInfoPage(ParkingSpaceInfoPageReqVO pageReqVO) {
        return parkingSpaceInfoMapper.selectPage(pageReqVO);
    }

}
