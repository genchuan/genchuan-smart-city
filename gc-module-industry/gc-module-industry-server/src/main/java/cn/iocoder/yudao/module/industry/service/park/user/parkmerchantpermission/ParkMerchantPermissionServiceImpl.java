package cn.iocoder.yudao.module.industry.service.park.user.parkmerchantpermission;

import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmerchantpermission.vo.ParkMerchantPermissionPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmerchantpermission.vo.ParkMerchantPermissionSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkmerchantpermission.ParkMerchantPermissionDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.user.parkmerchantpermission.ParkMerchantPermissionMapper;
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
 * 商户权限 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkMerchantPermissionServiceImpl implements ParkMerchantPermissionService {

    @Resource
    private ParkMerchantPermissionMapper parkMerchantPermissionMapper;

    @Override
    public Long createParkMerchantPermission(ParkMerchantPermissionSaveReqVO createReqVO) {
        // 插入
        ParkMerchantPermissionDO parkMerchantPermission = BeanUtils.toBean(createReqVO, ParkMerchantPermissionDO.class);
        parkMerchantPermissionMapper.insert(parkMerchantPermission);
        // 返回
        return parkMerchantPermission.getId();
    }

    @Override
    public void updateParkMerchantPermission(ParkMerchantPermissionSaveReqVO updateReqVO) {
        // 校验存在
        validateParkMerchantPermissionExists(updateReqVO.getId());
        // 更新
        ParkMerchantPermissionDO updateObj = BeanUtils.toBean(updateReqVO, ParkMerchantPermissionDO.class);
        parkMerchantPermissionMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkMerchantPermission(Long id) {
        // 校验存在
        validateParkMerchantPermissionExists(id);
        // 删除
        parkMerchantPermissionMapper.deleteById(id);
    }

    private void validateParkMerchantPermissionExists(Long id) {
        if (parkMerchantPermissionMapper.selectById(id) == null) {
            throw exception(PARK_MERCHANT_PERMISSION_NOT_EXISTS);
        }
    }

    @Override
    public ParkMerchantPermissionDO getParkMerchantPermission(Long id) {
        return parkMerchantPermissionMapper.selectById(id);
    }

    @Override
    public PageResult<ParkMerchantPermissionDO> getParkMerchantPermissionPage(ParkMerchantPermissionPageReqVO pageReqVO) {
        return parkMerchantPermissionMapper.selectPage(pageReqVO);
    }

}
