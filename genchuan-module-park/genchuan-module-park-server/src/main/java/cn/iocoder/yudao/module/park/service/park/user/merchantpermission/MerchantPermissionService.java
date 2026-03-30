package cn.iocoder.yudao.module.park.service.park.user.merchantpermission;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.user.merchantpermission.vo.MerchantPermissionPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.merchantpermission.vo.MerchantPermissionSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.merchantpermission.MerchantPermissionDO;
import jakarta.validation.Valid;

/**
 * 商户权限 Service 接口
 *
 * @author 亘川智城
 */
public interface MerchantPermissionService {

    /**
     * 创建商户权限
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMerchantPermission(@Valid MerchantPermissionSaveReqVO createReqVO);

    /**
     * 更新商户权限
     *
     * @param updateReqVO 更新信息
     */
    void updateMerchantPermission(@Valid MerchantPermissionSaveReqVO updateReqVO);

    /**
     * 删除商户权限
     *
     * @param id 编号
     */
    void deleteMerchantPermission(Long id);

    /**
     * 获得商户权限
     *
     * @param id 编号
     * @return 商户权限
     */
    MerchantPermissionDO getMerchantPermission(Long id);

    /**
     * 获得商户权限分页
     *
     * @param pageReqVO 分页查询
     * @return 商户权限分页
     */
    PageResult<MerchantPermissionDO> getMerchantPermissionPage(MerchantPermissionPageReqVO pageReqVO);

}
