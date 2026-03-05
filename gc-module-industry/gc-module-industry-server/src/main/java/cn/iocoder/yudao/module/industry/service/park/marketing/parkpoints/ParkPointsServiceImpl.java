package cn.iocoder.yudao.module.industry.service.park.marketing.parkpoints;

import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpoints.vo.ParkPointsPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpoints.vo.ParkPointsSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.marketing.parkpoints.ParkPointsDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.marketing.parkpoints.ParkPointsMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 用户积分 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkPointsServiceImpl implements ParkPointsService {

    @Resource
    private ParkPointsMapper parkPointsMapper;

    @Override
    public Long createParkPoints(ParkPointsSaveReqVO createReqVO) {
        // 插入
        ParkPointsDO parkPoints = BeanUtils.toBean(createReqVO, ParkPointsDO.class);
        parkPointsMapper.insert(parkPoints);
        // 返回
        return parkPoints.getId();
    }

    @Override
    public void updateParkPoints(ParkPointsSaveReqVO updateReqVO) {
        // 校验存在
        validateParkPointsExists(updateReqVO.getId());
        // 更新
        ParkPointsDO updateObj = BeanUtils.toBean(updateReqVO, ParkPointsDO.class);
        parkPointsMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkPoints(Long id) {
        // 校验存在
        validateParkPointsExists(id);
        // 删除
        parkPointsMapper.deleteById(id);
    }

    private void validateParkPointsExists(Long id) {
        if (parkPointsMapper.selectById(id) == null) {
            throw exception(PARK_POINTS_NOT_EXISTS);
        }
    }

    @Override
    public ParkPointsDO getParkPoints(Long id) {
        return parkPointsMapper.selectById(id);
    }

    @Override
    public PageResult<ParkPointsDO> getParkPointsPage(ParkPointsPageReqVO pageReqVO) {
        return parkPointsMapper.selectPage(pageReqVO);
    }

}
