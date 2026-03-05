package cn.iocoder.yudao.module.industry.service.park.user.parkmerchantpermission;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmerchantpermission.vo.ParkMerchantPermissionPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmerchantpermission.vo.ParkMerchantPermissionSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkmerchantpermission.ParkMerchantPermissionDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 商户权限 Service 接口
 *
 * @author lxs
 */
public interface ParkMerchantPermissionService {

    /**
     * 创建商户权限
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkMerchantPermission(@Valid ParkMerchantPermissionSaveReqVO createReqVO);

    /**
     * 更新商户权限
     *
     * @param updateReqVO 更新信息
     */
    void updateParkMerchantPermission(@Valid ParkMerchantPermissionSaveReqVO updateReqVO);

    /**
     * 删除商户权限
     *
     * @param id 编号
     */
    void deleteParkMerchantPermission(Long id);

    /**
     * 获得商户权限
     *
     * @param id 编号
     * @return 商户权限
     */
    ParkMerchantPermissionDO getParkMerchantPermission(Long id);

    /**
     * 获得商户权限分页
     *
     * @param pageReqVO 分页查询
     * @return 商户权限分页
     */
    PageResult<ParkMerchantPermissionDO> getParkMerchantPermissionPage(ParkMerchantPermissionPageReqVO pageReqVO);

}
