package cn.iocoder.yudao.module.evaluate.dal.mysql.calcway;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.calcway.vo.CalcWayPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.calcway.CalcWayDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 计算方式字典 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface CalcWayMapper extends BaseMapperX<CalcWayDO> {

    default PageResult<CalcWayDO> selectPage(CalcWayPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CalcWayDO>()
                .eqIfPresent(CalcWayDO::getWayId, reqVO.getWayId())
                .likeIfPresent(CalcWayDO::getName, reqVO.getName())
                .eqIfPresent(CalcWayDO::getCode, reqVO.getCode())
                .eqIfPresent(CalcWayDO::getDesc, reqVO.getDesc())
                .betweenIfPresent(CalcWayDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(CalcWayDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(CalcWayDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(CalcWayDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(CalcWayDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(CalcWayDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(CalcWayDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CalcWayDO::getId));
    }

}