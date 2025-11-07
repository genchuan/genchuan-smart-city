package cn.iocoder.yudao.module.datacenter.dal.mysql.resschedmanage.emermatinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emermatinfo.vo.EmerMatInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emermatinfo.EmerMatInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 应急物资信息 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface EmerMatInfoMapper extends BaseMapperX<EmerMatInfoDO> {

    default PageResult<EmerMatInfoDO> selectPage(EmerMatInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EmerMatInfoDO>()
                .eqIfPresent(EmerMatInfoDO::getMatId, reqVO.getMatId())
                .eqIfPresent(EmerMatInfoDO::getMatCode, reqVO.getMatCode())
                .likeIfPresent(EmerMatInfoDO::getMatName, reqVO.getMatName())
                .eqIfPresent(EmerMatInfoDO::getCatId, reqVO.getCatId())
                .likeIfPresent(EmerMatInfoDO::getCatName, reqVO.getCatName())
                .eqIfPresent(EmerMatInfoDO::getApplyRegionCode, reqVO.getApplyRegionCode())
                .likeIfPresent(EmerMatInfoDO::getApplyRegionName, reqVO.getApplyRegionName())
                .eqIfPresent(EmerMatInfoDO::getMatSpec, reqVO.getMatSpec())
                .eqIfPresent(EmerMatInfoDO::getMatUnit, reqVO.getMatUnit())
                .eqIfPresent(EmerMatInfoDO::getTotalQuantity, reqVO.getTotalQuantity())
                .eqIfPresent(EmerMatInfoDO::getAvailableQuantity, reqVO.getAvailableQuantity())
                .eqIfPresent(EmerMatInfoDO::getMatStatus, reqVO.getMatStatus())
                .eqIfPresent(EmerMatInfoDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(EmerMatInfoDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(EmerMatInfoDO::getUpdateUser, reqVO.getUpdateUser())
                .orderByDesc(EmerMatInfoDO::getId));
    }

}