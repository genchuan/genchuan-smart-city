package cn.iocoder.yudao.module.park.service.park.user.address;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.user.address.vo.AddressPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.address.vo.AddressSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.address.AddressDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.user.address.AddressMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.ADDRESS_NOT_EXISTS;

/**
 * 地址 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressMapper addressMapper;

    @Override
    public Long createAddress(AddressSaveReqVO createReqVO) {
        // 插入
        AddressDO address = BeanUtils.toBean(createReqVO, AddressDO.class);
        addressMapper.insert(address);
        // 返回
        return address.getId();
    }

    @Override
    public void updateAddress(AddressSaveReqVO updateReqVO) {
        // 校验存在
        validateAddressExists(updateReqVO.getId());
        // 更新
        AddressDO updateObj = BeanUtils.toBean(updateReqVO, AddressDO.class);
        addressMapper.updateById(updateObj);
    }

    @Override
    public void deleteAddress(Long id) {
        // 校验存在
        validateAddressExists(id);
        // 删除
        addressMapper.deleteById(id);
    }

    private void validateAddressExists(Long id) {
        if (addressMapper.selectById(id) == null) {
            throw exception(ADDRESS_NOT_EXISTS);
        }
    }

    @Override
    public AddressDO getAddress(Long id) {
        return addressMapper.selectById(id);
    }

    @Override
    public PageResult<AddressDO> getAddressPage(AddressPageReqVO pageReqVO) {
        return addressMapper.selectPage(pageReqVO);
    }

}
