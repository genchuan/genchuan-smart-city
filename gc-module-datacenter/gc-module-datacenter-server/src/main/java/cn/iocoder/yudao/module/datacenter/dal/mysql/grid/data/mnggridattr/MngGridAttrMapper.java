package cn.iocoder.yudao.module.datacenter.dal.mysql.grid.data.mnggridattr;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.mnggridattr.vo.MngGridAttrPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.data.mnggridattr.MngGridAttrDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理网格属性 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface MngGridAttrMapper extends BaseMapperX<MngGridAttrDO> {

    default PageResult<MngGridAttrDO> selectPage(MngGridAttrPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MngGridAttrDO>()
                .eqIfPresent(MngGridAttrDO::getMgAttrId, reqVO.getMgAttrId())
                .eqIfPresent(MngGridAttrDO::getMngGridId, reqVO.getMngGridId())
                .eqIfPresent(MngGridAttrDO::getMgGridCode, reqVO.getMgGridCode())
                .eqIfPresent(MngGridAttrDO::getArea, reqVO.getArea())
                .betweenIfPresent(MngGridAttrDO::getInitTime, reqVO.getInitTime())
                .betweenIfPresent(MngGridAttrDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(MngGridAttrDO::getUnitGridList, reqVO.getUnitGridList())
                .eqIfPresent(MngGridAttrDO::getGridUserId, reqVO.getGridUserId())
                .likeIfPresent(MngGridAttrDO::getGridUserName, reqVO.getGridUserName())
                .eqIfPresent(MngGridAttrDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MngGridAttrDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(MngGridAttrDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(MngGridAttrDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(MngGridAttrDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(MngGridAttrDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MngGridAttrDO::getId));
    }

}