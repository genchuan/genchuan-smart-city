package cn.iocoder.yudao.module.datacenter.dal.mysql.mnggriddiv;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mnggriddiv.MngGridDivDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.mnggriddiv.vo.*;

/**
 * 管理网格划分 Mapper
 *
 * @author zcq
 */
@Mapper
public interface MngGridDivMapper extends BaseMapperX<MngGridDivDO> {

    default PageResult<MngGridDivDO> selectPage(MngGridDivPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MngGridDivDO>()
                .eqIfPresent(MngGridDivDO::getMngGridId, reqVO.getMngGridId())
                .likeIfPresent(MngGridDivDO::getMngGridName, reqVO.getMngGridName())
                .eqIfPresent(MngGridDivDO::getTownStreetId, reqVO.getTownStreetId())
                .eqIfPresent(MngGridDivDO::getIncludedUnitIds, reqVO.getIncludedUnitIds())
                .eqIfPresent(MngGridDivDO::getArea, reqVO.getArea())
                .betweenIfPresent(MngGridDivDO::getDivTime, reqVO.getDivTime())
                .betweenIfPresent(MngGridDivDO::getUpdateTime, reqVO.getUpdateTime())
                .eqIfPresent(MngGridDivDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MngGridDivDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(MngGridDivDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(MngGridDivDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(MngGridDivDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(MngGridDivDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MngGridDivDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(MngGridDivDO::getId));
    }

}