package cn.iocoder.yudao.module.park.service.park.user.supplier;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.user.supplier.vo.SupplierPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.supplier.vo.SupplierSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.supplier.SupplierDO;
import jakarta.validation.Valid;

/**
 * 供应商 Service 接口
 *
 * @author 亘川智城
 */
public interface SupplierService {

    /**
     * 创建供应商
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSupplier(@Valid SupplierSaveReqVO createReqVO);

    /**
     * 更新供应商
     *
     * @param updateReqVO 更新信息
     */
    void updateSupplier(@Valid SupplierSaveReqVO updateReqVO);

    /**
     * 删除供应商
     *
     * @param id 编号
     */
    void deleteSupplier(Long id);

    /**
     * 获得供应商
     *
     * @param id 编号
     * @return 供应商
     */
    SupplierDO getSupplier(Long id);

    /**
     * 获得供应商分页
     *
     * @param pageReqVO 分页查询
     * @return 供应商分页
     */
    PageResult<SupplierDO> getSupplierPage(SupplierPageReqVO pageReqVO);

}
