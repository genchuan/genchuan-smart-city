package cn.iocoder.yudao.module.park.service.park.user.address;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.user.address.vo.AddressPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.address.vo.AddressSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.address.AddressDO;
import jakarta.validation.Valid;

/**
 * 地址 Service 接口
 *
 * @author 亘川智城
 */
public interface AddressService {

    /**
     * 创建地址
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAddress(@Valid AddressSaveReqVO createReqVO);

    /**
     * 更新地址
     *
     * @param updateReqVO 更新信息
     */
    void updateAddress(@Valid AddressSaveReqVO updateReqVO);

    /**
     * 删除地址
     *
     * @param id 编号
     */
    void deleteAddress(Long id);

    /**
     * 获得地址
     *
     * @param id 编号
     * @return 地址
     */
    AddressDO getAddress(Long id);

    /**
     * 获得地址分页
     *
     * @param pageReqVO 分页查询
     * @return 地址分页
     */
    PageResult<AddressDO> getAddressPage(AddressPageReqVO pageReqVO);

}
