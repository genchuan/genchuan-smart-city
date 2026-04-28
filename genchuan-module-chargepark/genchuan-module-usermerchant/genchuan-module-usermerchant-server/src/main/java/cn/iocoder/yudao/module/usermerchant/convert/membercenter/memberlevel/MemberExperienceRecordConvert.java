package cn.iocoder.yudao.module.usermerchant.convert.membercenter.memberlevel;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberlevel.vo.experience.MemberExperienceRecordRespVO;
import cn.iocoder.yudao.module.usermerchant.controller.app.membercenter.memberlevel.vo.experience.AppMemberExperienceRecordRespVO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberlevel.MemberExperienceRecordDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 会员经验记录 Convert
 *
 * @author owen
 */
@Mapper
public interface MemberExperienceRecordConvert {

    MemberExperienceRecordConvert INSTANCE = Mappers.getMapper(MemberExperienceRecordConvert.class);

    MemberExperienceRecordRespVO convert(MemberExperienceRecordDO bean);

    List<MemberExperienceRecordRespVO> convertList(List<MemberExperienceRecordDO> list);

    PageResult<MemberExperienceRecordRespVO> convertPage(PageResult<MemberExperienceRecordDO> page);

    MemberExperienceRecordDO convert(Long userId, Integer experience, Integer totalExperience,
                                     String bizId, Integer bizType,
                                     String title, String description);

    PageResult<AppMemberExperienceRecordRespVO> convertPage02(PageResult<MemberExperienceRecordDO> page);

}
