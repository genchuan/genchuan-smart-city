package cn.iocoder.yudao.module.industry.service.park.through.carexit;

import cn.iocoder.yudao.module.industry.controller.admin.park.through.carexit.vo.ParkCarExitPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.carexit.vo.ParkCarExitSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.industry.dal.dataobject.park.through.carexit.ParkCarExitDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.industry.dal.mysql.park.through.carexit.ParkCarExitMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 离场记录 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class ParkCarExitServiceImpl implements ParkCarExitService {

    @Resource
    private ParkCarExitMapper parkCarExitMapper;

    @Override
    public Long createParkCarExit(ParkCarExitSaveReqVO createReqVO) {
        // 插入
        ParkCarExitDO parkCarExit = BeanUtils.toBean(createReqVO, ParkCarExitDO.class);
        parkCarExitMapper.insert(parkCarExit);
        // 返回
        return parkCarExit.getId();
    }

    @Override
    public void updateParkCarExit(ParkCarExitSaveReqVO updateReqVO) {
        // 校验存在
        validateParkCarExitExists(updateReqVO.getId());
        // 更新
        ParkCarExitDO updateObj = BeanUtils.toBean(updateReqVO, ParkCarExitDO.class);
        parkCarExitMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkCarExit(Long id) {
        // 校验存在
        validateParkCarExitExists(id);
        // 删除
        parkCarExitMapper.deleteById(id);
    }

    private void validateParkCarExitExists(Long id) {
        if (parkCarExitMapper.selectById(id) == null) {
            throw exception(PARK_CAR_EXIT_NOT_EXISTS);
        }
    }

    @Override
    public ParkCarExitDO getParkCarExit(Long id) {
        return parkCarExitMapper.selectById(id);
    }

    @Override
    public PageResult<ParkCarExitDO> getParkCarExitPage(ParkCarExitPageReqVO pageReqVO) {
        return parkCarExitMapper.selectPage(pageReqVO);
    }

}