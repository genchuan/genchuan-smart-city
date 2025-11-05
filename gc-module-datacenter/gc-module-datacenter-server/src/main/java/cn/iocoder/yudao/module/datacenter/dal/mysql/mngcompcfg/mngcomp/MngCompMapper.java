package cn.iocoder.yudao.module.datacenter.dal.mysql.mngcompcfg.mngcomp;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.mngcomp.vo.MngCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.mngcomp.MngCompDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理部件统计 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MngCompMapper extends BaseMapperX<MngCompDO> {

    default PageResult<MngCompDO> selectPage(MngCompPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MngCompDO>()
                .eqIfPresent(MngCompDO::getStatMngCompId, reqVO.getStatMngCompId())
                .eqIfPresent(MngCompDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(MngCompDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(MngCompDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(MngCompDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(MngCompDO::getCompMajorId, reqVO.getCompMajorId())
                .likeIfPresent(MngCompDO::getCompMajorName, reqVO.getCompMajorName())
                .eqIfPresent(MngCompDO::getCompMinorId, reqVO.getCompMinorId())
                .likeIfPresent(MngCompDO::getCompMinorName, reqVO.getCompMinorName())
                .eqIfPresent(MngCompDO::getTotalCompCount, reqVO.getTotalCompCount())
                .eqIfPresent(MngCompDO::getNormalCompCount, reqVO.getNormalCompCount())
                .eqIfPresent(MngCompDO::getDamagedCompCount, reqVO.getDamagedCompCount())
                .eqIfPresent(MngCompDO::getLostCompCount, reqVO.getLostCompCount())
                .eqIfPresent(MngCompDO::getDiscardedCompCount, reqVO.getDiscardedCompCount())
                .eqIfPresent(MngCompDO::getNewCompCount, reqVO.getNewCompCount())
                .eqIfPresent(MngCompDO::getUpdateCompCount, reqVO.getUpdateCompCount())
                .eqIfPresent(MngCompDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(MngCompDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(MngCompDO::getId));
    }

}