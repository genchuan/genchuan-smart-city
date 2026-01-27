package cn.iocoder.yudao.module.park.service.park.user.merchantpermission;

import cn.iocoder.yudao.module.park.controller.admin.park.user.merchantpermission.vo.MerchantPermissionPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.merchantpermission.vo.MerchantPermissionSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.merchantpermission.MerchantPermissionDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.user.merchantpermission.MerchantPermissionMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.*;

/**
 * 商户权限 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MerchantPermissionServiceImpl implements MerchantPermissionService {

    @Resource
    private MerchantPermissionMapper merchantPermissionMapper;

    @Override
    public Long createMerchantPermission(MerchantPermissionSaveReqVO createReqVO) {
        // 插入
        MerchantPermissionDO merchantPermission = BeanUtils.toBean(createReqVO, MerchantPermissionDO.class);
        merchantPermissionMapper.insert(merchantPermission);
        // 返回
        return merchantPermission.getId();
    }

    @Override
    public void updateMerchantPermission(MerchantPermissionSaveReqVO updateReqVO) {
        // 校验存在
        validateMerchantPermissionExists(updateReqVO.getId());
        // 更新
        MerchantPermissionDO updateObj = BeanUtils.toBean(updateReqVO, MerchantPermissionDO.class);
        merchantPermissionMapper.updateById(updateObj);
    }

    @Override
    public void deleteMerchantPermission(Long id) {
        // 校验存在
        validateMerchantPermissionExists(id);
        // 删除
        merchantPermissionMapper.deleteById(id);
    }

    private void validateMerchantPermissionExists(Long id) {
        if (merchantPermissionMapper.selectById(id) == null) {
            throw exception(MERCHANT_PERMISSION_NOT_EXISTS);
        }
    }

    @Override
    public MerchantPermissionDO getMerchantPermission(Long id) {
        return merchantPermissionMapper.selectById(id);
    }

    @Override
    public PageResult<MerchantPermissionDO> getMerchantPermissionPage(MerchantPermissionPageReqVO pageReqVO) {
        return merchantPermissionMapper.selectPage(pageReqVO);
    }

}
