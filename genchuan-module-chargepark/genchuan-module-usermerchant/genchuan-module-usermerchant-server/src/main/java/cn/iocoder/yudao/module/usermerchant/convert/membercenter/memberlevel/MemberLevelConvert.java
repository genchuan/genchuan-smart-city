package cn.iocoder.yudao.module.usermerchant.convert.membercenter.memberlevel;

import cn.iocoder.yudao.module.usermerchant.api.membercenter.memberlevel.dto.MemberLevelRespDTO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberlevel.vo.level.MemberLevelCreateReqVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberlevel.vo.level.MemberLevelRespVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberlevel.vo.level.MemberLevelSimpleRespVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberlevel.vo.level.MemberLevelUpdateReqVO;
import cn.iocoder.yudao.module.usermerchant.controller.app.membercenter.memberlevel.vo.level.AppMemberLevelRespVO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberlevel.MemberLevelDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 会员等级 Convert
 *
 * @author owen
 */
@Mapper
public interface MemberLevelConvert {

    MemberLevelConvert INSTANCE = Mappers.getMapper(MemberLevelConvert.class);

    MemberLevelDO convert(MemberLevelCreateReqVO bean);

    MemberLevelDO convert(MemberLevelUpdateReqVO bean);

    MemberLevelRespVO convert(MemberLevelDO bean);

    List<MemberLevelRespVO> convertList(List<MemberLevelDO> list);

    List<MemberLevelSimpleRespVO> convertSimpleList(List<MemberLevelDO> list);

    List<AppMemberLevelRespVO> convertList02(List<MemberLevelDO> list);

    MemberLevelRespDTO convert02(MemberLevelDO bean);

}
