package cn.iocoder.yudao.module.park.dal.mysql.park.user.address;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.user.address.vo.AddressPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.address.AddressDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 地址 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AddressMapper extends BaseMapperX<AddressDO> {

    default PageResult<AddressDO> selectPage(AddressPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AddressDO>()
                .eqIfPresent(AddressDO::getUserId, reqVO.getUserId())
                .eqIfPresent(AddressDO::getEnterpriseId, reqVO.getEnterpriseId())
                .likeIfPresent(AddressDO::getReceiverName, reqVO.getReceiverName())
                .eqIfPresent(AddressDO::getPhone, reqVO.getPhone())
                .eqIfPresent(AddressDO::getProvince, reqVO.getProvince())
                .eqIfPresent(AddressDO::getCity, reqVO.getCity())
                .eqIfPresent(AddressDO::getDistrict, reqVO.getDistrict())
                .eqIfPresent(AddressDO::getDetailAddress, reqVO.getDetailAddress())
                .eqIfPresent(AddressDO::getIsDefault, reqVO.getIsDefault())
                .betweenIfPresent(AddressDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(AddressDO::getRemark, reqVO.getRemark())
                .eqIfPresent(AddressDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AddressDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(AddressDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(AddressDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(AddressDO::getId));
    }

}
