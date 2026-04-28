package cn.iocoder.yudao.module.usermerchant.convert.membercenter.membergroup;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membergroup.vo.MemberGroupCreateReqVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membergroup.vo.MemberGroupRespVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membergroup.vo.MemberGroupSimpleRespVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membergroup.vo.MemberGroupUpdateReqVO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.membergroup.MemberGroupDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户分组 Convert
 *
 * @author owen
 */
@Mapper
public interface MemberGroupConvert {

    MemberGroupConvert INSTANCE = Mappers.getMapper(MemberGroupConvert.class);

    MemberGroupDO convert(MemberGroupCreateReqVO bean);

    MemberGroupDO convert(MemberGroupUpdateReqVO bean);

    MemberGroupRespVO convert(MemberGroupDO bean);

    List<MemberGroupRespVO> convertList(List<MemberGroupDO> list);

    PageResult<MemberGroupRespVO> convertPage(PageResult<MemberGroupDO> page);

    List<MemberGroupSimpleRespVO> convertSimpleList(List<MemberGroupDO> list);
}
