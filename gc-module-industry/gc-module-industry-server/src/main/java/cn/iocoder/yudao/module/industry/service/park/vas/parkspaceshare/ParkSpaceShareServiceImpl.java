package cn.iocoder.yudao.module.industry.service.park.vas.parkspaceshare;

import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkspaceshare.vo.ParkSpaceSharePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkspaceshare.vo.ParkSpaceShareSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkspaceshare.ParkSpaceShareDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.vas.parkspaceshare.ParkSpaceShareMapper;
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
 * 车位共享配置 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkSpaceShareServiceImpl implements ParkSpaceShareService {

    @Resource
    private ParkSpaceShareMapper parkSpaceShareMapper;

    @Override
    public Long createParkSpaceShare(ParkSpaceShareSaveReqVO createReqVO) {
        // 插入
        ParkSpaceShareDO parkSpaceShare = BeanUtils.toBean(createReqVO, ParkSpaceShareDO.class);
        parkSpaceShareMapper.insert(parkSpaceShare);
        // 返回
        return parkSpaceShare.getId();
    }

    @Override
    public void updateParkSpaceShare(ParkSpaceShareSaveReqVO updateReqVO) {
        // 校验存在
        validateParkSpaceShareExists(updateReqVO.getId());
        // 更新
        ParkSpaceShareDO updateObj = BeanUtils.toBean(updateReqVO, ParkSpaceShareDO.class);
        parkSpaceShareMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkSpaceShare(Long id) {
        // 校验存在
        validateParkSpaceShareExists(id);
        // 删除
        parkSpaceShareMapper.deleteById(id);
    }

    private void validateParkSpaceShareExists(Long id) {
        if (parkSpaceShareMapper.selectById(id) == null) {
            throw exception(PARK_SPACE_SHARE_NOT_EXISTS);
        }
    }

    @Override
    public ParkSpaceShareDO getParkSpaceShare(Long id) {
        return parkSpaceShareMapper.selectById(id);
    }

    @Override
    public PageResult<ParkSpaceShareDO> getParkSpaceSharePage(ParkSpaceSharePageReqVO pageReqVO) {
        return parkSpaceShareMapper.selectPage(pageReqVO);
    }

}
