package cn.iocoder.yudao.module.datacenter.dal.mysql.mngmattercfg.relmngmattermajmin;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.relmngmattermajmin.vo.RelMngMatterMajMinPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.relmngmattermajmin.RelMngMatterMajMinDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理事项大小类关联 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface RelMngMatterMajMinMapper extends BaseMapperX<RelMngMatterMajMinDO> {

    default PageResult<RelMngMatterMajMinDO> selectPage(RelMngMatterMajMinPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RelMngMatterMajMinDO>()
                .eqIfPresent(RelMngMatterMajMinDO::getMngMatterMajorMinorId, reqVO.getMngMatterMajorMinorId())
                .eqIfPresent(RelMngMatterMajMinDO::getMajorId, reqVO.getMajorId())
                .likeIfPresent(RelMngMatterMajMinDO::getMajorName, reqVO.getMajorName())
                .eqIfPresent(RelMngMatterMajMinDO::getMinorId, reqVO.getMinorId())
                .likeIfPresent(RelMngMatterMajMinDO::getMinorName, reqVO.getMinorName())
                .eqIfPresent(RelMngMatterMajMinDO::getRelStatus, reqVO.getRelStatus())
                .eqIfPresent(RelMngMatterMajMinDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(RelMngMatterMajMinDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(RelMngMatterMajMinDO::getId));
    }

}