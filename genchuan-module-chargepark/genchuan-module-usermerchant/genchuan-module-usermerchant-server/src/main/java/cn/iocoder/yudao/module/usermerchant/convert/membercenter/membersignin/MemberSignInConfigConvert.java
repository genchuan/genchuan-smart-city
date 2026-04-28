package cn.iocoder.yudao.module.usermerchant.convert.membercenter.membersignin;

import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membersignin.vo.config.MemberSignInConfigCreateReqVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membersignin.vo.config.MemberSignInConfigRespVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membersignin.vo.config.MemberSignInConfigUpdateReqVO;
import cn.iocoder.yudao.module.usermerchant.controller.app.membercenter.membersignin.vo.config.AppMemberSignInConfigRespVO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.membersignin.MemberSignInConfigDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 签到规则 Convert
 *
 * @author QingX
 */
@Mapper
public interface MemberSignInConfigConvert {

    MemberSignInConfigConvert INSTANCE = Mappers.getMapper(MemberSignInConfigConvert.class);

    MemberSignInConfigDO convert(MemberSignInConfigCreateReqVO bean);

    MemberSignInConfigDO convert(MemberSignInConfigUpdateReqVO bean);

    MemberSignInConfigRespVO convert(MemberSignInConfigDO bean);

    List<MemberSignInConfigRespVO> convertList(List<MemberSignInConfigDO> list);

    List<AppMemberSignInConfigRespVO> convertList02(List<MemberSignInConfigDO> list);

}
