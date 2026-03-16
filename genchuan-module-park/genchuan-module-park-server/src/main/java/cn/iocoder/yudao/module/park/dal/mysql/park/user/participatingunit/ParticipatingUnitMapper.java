package cn.iocoder.yudao.module.park.dal.mysql.park.user.participatingunit;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.user.participatingunit.vo.ParticipatingUnitPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.participatingunit.ParticipatingUnitDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 参与单位 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ParticipatingUnitMapper extends BaseMapperX<ParticipatingUnitDO> {

    default PageResult<ParticipatingUnitDO> selectPage(ParticipatingUnitPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParticipatingUnitDO>()
                .likeIfPresent(ParticipatingUnitDO::getUnitName, reqVO.getUnitName())
                .eqIfPresent(ParticipatingUnitDO::getUnitType, reqVO.getUnitType())
                .eqIfPresent(ParticipatingUnitDO::getContactPerson, reqVO.getContactPerson())
                .eqIfPresent(ParticipatingUnitDO::getContactPhone, reqVO.getContactPhone())
                .eqIfPresent(ParticipatingUnitDO::getCooperationContent, reqVO.getCooperationContent())
                .betweenIfPresent(ParticipatingUnitDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(ParticipatingUnitDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(ParticipatingUnitDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ParticipatingUnitDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParticipatingUnitDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ParticipatingUnitDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParticipatingUnitDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParticipatingUnitDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParticipatingUnitDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ParticipatingUnitDO::getId));
    }

}
