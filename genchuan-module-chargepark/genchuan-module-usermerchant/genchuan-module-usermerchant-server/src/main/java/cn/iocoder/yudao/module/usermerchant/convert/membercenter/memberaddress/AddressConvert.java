package cn.iocoder.yudao.module.usermerchant.convert.membercenter.memberaddress;

import cn.iocoder.yudao.framework.ip.core.utils.AreaUtils;
import cn.iocoder.yudao.module.usermerchant.api.membercenter.memberaddress.dto.MemberAddressRespDTO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberaddress.vo.AddressRespVO;
import cn.iocoder.yudao.module.usermerchant.controller.app.membercenter.memberaddress.vo.AppAddressCreateReqVO;
import cn.iocoder.yudao.module.usermerchant.controller.app.membercenter.memberaddress.vo.AppAddressRespVO;
import cn.iocoder.yudao.module.usermerchant.controller.app.membercenter.memberaddress.vo.AppAddressUpdateReqVO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberaddress.MemberAddressDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户收件地址 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface AddressConvert {

    AddressConvert INSTANCE = Mappers.getMapper(AddressConvert.class);

    MemberAddressDO convert(AppAddressCreateReqVO bean);

    MemberAddressDO convert(AppAddressUpdateReqVO bean);

    @Mapping(source = "areaId", target = "areaName",  qualifiedByName = "convertAreaIdToAreaName")
    AppAddressRespVO convert(MemberAddressDO bean);

    List<AppAddressRespVO> convertList(List<MemberAddressDO> list);

    MemberAddressRespDTO convert02(MemberAddressDO bean);

    @Named("convertAreaIdToAreaName")
    default String convertAreaIdToAreaName(Integer areaId) {
        return AreaUtils.format(areaId);
    }

    List<AddressRespVO> convertList2(List<MemberAddressDO> list);

}
