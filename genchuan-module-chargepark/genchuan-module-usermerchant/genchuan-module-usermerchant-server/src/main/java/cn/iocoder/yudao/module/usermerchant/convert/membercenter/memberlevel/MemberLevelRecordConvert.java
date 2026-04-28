package cn.iocoder.yudao.module.usermerchant.convert.membercenter.memberlevel;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberlevel.vo.record.MemberLevelRecordRespVO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberlevel.MemberLevelDO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberlevel.MemberLevelRecordDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 会员等级记录 Convert
 *
 * @author owen
 */
@Mapper
public interface MemberLevelRecordConvert {

    MemberLevelRecordConvert INSTANCE = Mappers.getMapper(MemberLevelRecordConvert.class);

    MemberLevelRecordRespVO convert(MemberLevelRecordDO bean);

    List<MemberLevelRecordRespVO> convertList(List<MemberLevelRecordDO> list);

    PageResult<MemberLevelRecordRespVO> convertPage(PageResult<MemberLevelRecordDO> page);

    default MemberLevelRecordDO copyTo(MemberLevelDO from, MemberLevelRecordDO to) {
        if (from != null) {
            to.setLevelId(from.getId());
            to.setLevel(from.getLevel());
            to.setDiscountPercent(from.getDiscountPercent());
            to.setExperience(from.getExperience());
        }
        return to;
    }
}
