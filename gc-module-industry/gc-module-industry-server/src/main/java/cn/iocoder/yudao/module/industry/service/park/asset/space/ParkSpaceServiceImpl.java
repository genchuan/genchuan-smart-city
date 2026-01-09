package cn.iocoder.yudao.module.industry.service.park.asset.space;

import cn.iocoder.yudao.module.industry.controller.admin.park.asset.space.vo.ParkSpacePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.space.vo.ParkSpaceSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.space.ParkSpaceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.industry.dal.mysql.park.asset.space.ParkSpaceMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 车位信息 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class ParkSpaceServiceImpl implements ParkSpaceService {

    @Resource
    private ParkSpaceMapper parkSpaceMapper;

    @Override
    public Long createParkSpace(ParkSpaceSaveReqVO createReqVO) {
        // 插入
        ParkSpaceDO parkSpace = BeanUtils.toBean(createReqVO, ParkSpaceDO.class);
        parkSpaceMapper.insert(parkSpace);
        // 返回
        return parkSpace.getId();
    }

    @Override
    public void updateParkSpace(ParkSpaceSaveReqVO updateReqVO) {
        // 校验存在
        validateParkSpaceExists(updateReqVO.getId());
        // 更新
        ParkSpaceDO updateObj = BeanUtils.toBean(updateReqVO, ParkSpaceDO.class);
        parkSpaceMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkSpace(Long id) {
        // 校验存在
        validateParkSpaceExists(id);
        // 删除
        parkSpaceMapper.deleteById(id);
    }

    private void validateParkSpaceExists(Long id) {
        if (parkSpaceMapper.selectById(id) == null) {
            throw exception(PARK_SPACE_NOT_EXISTS);
        }
    }

    @Override
    public ParkSpaceDO getParkSpace(Long id) {
        return parkSpaceMapper.selectById(id);
    }

    @Override
    public PageResult<ParkSpaceDO> getParkSpacePage(ParkSpacePageReqVO pageReqVO) {
        return parkSpaceMapper.selectPage(pageReqVO);
    }

}