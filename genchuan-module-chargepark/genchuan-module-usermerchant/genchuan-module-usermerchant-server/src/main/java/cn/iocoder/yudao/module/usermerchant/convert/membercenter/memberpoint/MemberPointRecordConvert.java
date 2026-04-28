package cn.iocoder.yudao.module.usermerchant.convert.membercenter.memberpoint;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.MapUtils;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberpoint.vo.recrod.MemberPointRecordRespVO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberpoint.MemberPointRecordDO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberuser.MemberUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertMap;

/**
 * 用户积分记录 Convert
 *
 * @author QingX
 */
@Mapper
public interface MemberPointRecordConvert {

    MemberPointRecordConvert INSTANCE = Mappers.getMapper(MemberPointRecordConvert.class);

    default PageResult<MemberPointRecordRespVO> convertPage(PageResult<MemberPointRecordDO> pageResult, List<MemberUserDO> users) {
        PageResult<MemberPointRecordRespVO> voPageResult = convertPage(pageResult);
        // user 拼接
        Map<Long, MemberUserDO> userMap = convertMap(users, MemberUserDO::getId);
        voPageResult.getList().forEach(record -> MapUtils.findAndThen(userMap, record.getUserId(),
                memberUserRespDTO -> record.setNickname(memberUserRespDTO.getNickname())));
        return voPageResult;
    }
    PageResult<MemberPointRecordRespVO> convertPage(PageResult<MemberPointRecordDO> pageResult);

}
