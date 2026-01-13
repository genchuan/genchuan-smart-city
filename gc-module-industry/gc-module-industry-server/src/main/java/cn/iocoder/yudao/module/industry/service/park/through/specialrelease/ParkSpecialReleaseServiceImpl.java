package cn.iocoder.yudao.module.industry.service.park.through.specialrelease;

import cn.iocoder.yudao.module.industry.controller.admin.park.through.specialrelease.vo.ParkSpecialReleasePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.specialrelease.vo.ParkSpecialReleaseSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.industry.dal.dataobject.park.through.specialrelease.ParkSpecialReleaseDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.industry.dal.mysql.park.through.specialrelease.ParkSpecialReleaseMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 特殊放行 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class ParkSpecialReleaseServiceImpl implements ParkSpecialReleaseService {

    @Resource
    private ParkSpecialReleaseMapper parkSpecialReleaseMapper;

    @Override
    public Long createParkSpecialRelease(ParkSpecialReleaseSaveReqVO createReqVO) {
        // 插入
        ParkSpecialReleaseDO parkSpecialRelease = BeanUtils.toBean(createReqVO, ParkSpecialReleaseDO.class);
        parkSpecialReleaseMapper.insert(parkSpecialRelease);
        // 返回
        return parkSpecialRelease.getId();
    }

    @Override
    public void updateParkSpecialRelease(ParkSpecialReleaseSaveReqVO updateReqVO) {
        // 校验存在
        validateParkSpecialReleaseExists(updateReqVO.getId());
        // 更新
        ParkSpecialReleaseDO updateObj = BeanUtils.toBean(updateReqVO, ParkSpecialReleaseDO.class);
        parkSpecialReleaseMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkSpecialRelease(Long id) {
        // 校验存在
        validateParkSpecialReleaseExists(id);
        // 删除
        parkSpecialReleaseMapper.deleteById(id);
    }

    private void validateParkSpecialReleaseExists(Long id) {
        if (parkSpecialReleaseMapper.selectById(id) == null) {
            throw exception(PARK_SPECIAL_RELEASE_NOT_EXISTS);
        }
    }

    @Override
    public ParkSpecialReleaseDO getParkSpecialRelease(Long id) {
        return parkSpecialReleaseMapper.selectById(id);
    }

    @Override
    public PageResult<ParkSpecialReleaseDO> getParkSpecialReleasePage(ParkSpecialReleasePageReqVO pageReqVO) {
        return parkSpecialReleaseMapper.selectPage(pageReqVO);
    }

}