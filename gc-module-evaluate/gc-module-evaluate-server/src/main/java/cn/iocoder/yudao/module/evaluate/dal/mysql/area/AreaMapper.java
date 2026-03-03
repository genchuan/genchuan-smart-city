package cn.iocoder.yudao.module.evaluate.dal.mysql.area;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.baseinfo.area.vo.AreaPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.area.AreaDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 区域编码 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AreaMapper extends BaseMapperX<AreaDO> {

    default PageResult<AreaDO> selectPage(AreaPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AreaDO>()
                .eqIfPresent(AreaDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(AreaDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(AreaDO::getParentCode, reqVO.getParentCode())
                .eqIfPresent(AreaDO::getLevel, reqVO.getLevel())
                .eqIfPresent(AreaDO::getStatusId, reqVO.getStatusId())
                .betweenIfPresent(AreaDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(AreaDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(AreaDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AreaDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(AreaDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(AreaDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(AreaDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AreaDO::getId));
    }

}