package cn.iocoder.yudao.module.datacenter.dal.mysql.mngmattercfg.bizmngmatter;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatter.vo.BizMngMatterPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.bizmngmatter.BizMngMatterDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理事项信息 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface BizMngMatterMapper extends BaseMapperX<BizMngMatterDO> {

    default PageResult<BizMngMatterDO> selectPage(BizMngMatterPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BizMngMatterDO>()
                .eqIfPresent(BizMngMatterDO::getMngMatterId, reqVO.getMngMatterId())
                .eqIfPresent(BizMngMatterDO::getMatterCode, reqVO.getMatterCode())
                .likeIfPresent(BizMngMatterDO::getMatterName, reqVO.getMatterName())
                .eqIfPresent(BizMngMatterDO::getMinorId, reqVO.getMinorId())
                .likeIfPresent(BizMngMatterDO::getMinorName, reqVO.getMinorName())
                .eqIfPresent(BizMngMatterDO::getGridId, reqVO.getGridId())
                .likeIfPresent(BizMngMatterDO::getGridName, reqVO.getGridName())
                .eqIfPresent(BizMngMatterDO::getMatterStatus, reqVO.getMatterStatus())
                .eqIfPresent(BizMngMatterDO::getMatterLevel, reqVO.getMatterLevel())
                .eqIfPresent(BizMngMatterDO::getDeptCode, reqVO.getDeptCode())
                .likeIfPresent(BizMngMatterDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(BizMngMatterDO::getIncidentLocation, reqVO.getIncidentLocation())
                .eqIfPresent(BizMngMatterDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(BizMngMatterDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(BizMngMatterDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(BizMngMatterDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(BizMngMatterDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(BizMngMatterDO::getId));
    }

}