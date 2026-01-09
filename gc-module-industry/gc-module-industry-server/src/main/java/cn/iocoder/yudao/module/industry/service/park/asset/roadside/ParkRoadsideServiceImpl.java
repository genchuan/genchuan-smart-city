package cn.iocoder.yudao.module.industry.service.park.asset.roadside;

import cn.iocoder.yudao.module.industry.controller.admin.park.asset.roadside.vo.ParkRoadsidePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.roadside.vo.ParkRoadsideSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.roadside.ParkRoadsideDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.industry.dal.mysql.park.asset.roadside.ParkRoadsideMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 路侧泊位 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class ParkRoadsideServiceImpl implements ParkRoadsideService {

    @Resource
    private ParkRoadsideMapper parkRoadsideMapper;

    @Override
    public Long createParkRoadside(ParkRoadsideSaveReqVO createReqVO) {
        // 插入
        ParkRoadsideDO parkRoadside = BeanUtils.toBean(createReqVO, ParkRoadsideDO.class);
        parkRoadsideMapper.insert(parkRoadside);
        // 返回
        return parkRoadside.getId();
    }

    @Override
    public void updateParkRoadside(ParkRoadsideSaveReqVO updateReqVO) {
        // 校验存在
        validateParkRoadsideExists(updateReqVO.getId());
        // 更新
        ParkRoadsideDO updateObj = BeanUtils.toBean(updateReqVO, ParkRoadsideDO.class);
        parkRoadsideMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkRoadside(Long id) {
        // 校验存在
        validateParkRoadsideExists(id);
        // 删除
        parkRoadsideMapper.deleteById(id);
    }

    private void validateParkRoadsideExists(Long id) {
        if (parkRoadsideMapper.selectById(id) == null) {
            throw exception(PARK_ROADSIDE_NOT_EXISTS);
        }
    }

    @Override
    public ParkRoadsideDO getParkRoadside(Long id) {
        return parkRoadsideMapper.selectById(id);
    }

    @Override
    public PageResult<ParkRoadsideDO> getParkRoadsidePage(ParkRoadsidePageReqVO pageReqVO) {
        return parkRoadsideMapper.selectPage(pageReqVO);
    }

}