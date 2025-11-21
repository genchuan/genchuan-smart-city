package cn.iocoder.yudao.module.datacenter.dal.mysql.publicinfopublish.publicpolinterpret;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicpolinterpret.vo.PublicPolInterpretPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.publicinfopublish.publicpolinterpret.PublicPolInterpretDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 政策解读发布 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PublicPolInterpretMapper extends BaseMapperX<PublicPolInterpretDO> {

    default PageResult<PublicPolInterpretDO> selectPage(PublicPolInterpretPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PublicPolInterpretDO>()
                .eqIfPresent(PublicPolInterpretDO::getInterpretId, reqVO.getInterpretId())
                .eqIfPresent(PublicPolInterpretDO::getInterpretTitle, reqVO.getInterpretTitle())
                .eqIfPresent(PublicPolInterpretDO::getPolNo, reqVO.getPolNo())
                .likeIfPresent(PublicPolInterpretDO::getPolName, reqVO.getPolName())
                .eqIfPresent(PublicPolInterpretDO::getInterpretContent, reqVO.getInterpretContent())
                .eqIfPresent(PublicPolInterpretDO::getPublishDepartmentCode, reqVO.getPublishDepartmentCode())
                .likeIfPresent(PublicPolInterpretDO::getPublishDepartmentName, reqVO.getPublishDepartmentName())
                .eqIfPresent(PublicPolInterpretDO::getPublishUserId, reqVO.getPublishUserId())
                .betweenIfPresent(PublicPolInterpretDO::getPublishTime, reqVO.getPublishTime())
                .eqIfPresent(PublicPolInterpretDO::getInterpretStatus, reqVO.getInterpretStatus())
                .betweenIfPresent(PublicPolInterpretDO::getEffectiveTime, reqVO.getEffectiveTime())
                .eqIfPresent(PublicPolInterpretDO::getAttachPath, reqVO.getAttachPath())
                .eqIfPresent(PublicPolInterpretDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PublicPolInterpretDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(PublicPolInterpretDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(PublicPolInterpretDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PublicPolInterpretDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PublicPolInterpretDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(PublicPolInterpretDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(PublicPolInterpretDO::getId));
    }

}