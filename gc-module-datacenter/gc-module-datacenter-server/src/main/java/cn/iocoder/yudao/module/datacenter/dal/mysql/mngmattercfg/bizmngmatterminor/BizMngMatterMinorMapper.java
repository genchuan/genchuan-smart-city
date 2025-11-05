package cn.iocoder.yudao.module.datacenter.dal.mysql.mngmattercfg.bizmngmatterminor;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatterminor.vo.BizMngMatterMinorPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.bizmngmatterminor.BizMngMatterMinorDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理事项小类 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface BizMngMatterMinorMapper extends BaseMapperX<BizMngMatterMinorDO> {

    default PageResult<BizMngMatterMinorDO> selectPage(BizMngMatterMinorPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BizMngMatterMinorDO>()
                .eqIfPresent(BizMngMatterMinorDO::getMngMatterMinorId, reqVO.getMngMatterMinorId())
                .eqIfPresent(BizMngMatterMinorDO::getParentMajorId, reqVO.getParentMajorId())
                .likeIfPresent(BizMngMatterMinorDO::getParentMajorName, reqVO.getParentMajorName())
                .eqIfPresent(BizMngMatterMinorDO::getMatterMinorCode, reqVO.getMatterMinorCode())
                .likeIfPresent(BizMngMatterMinorDO::getMatterMinorName, reqVO.getMatterMinorName())
                .eqIfPresent(BizMngMatterMinorDO::getMatterMinorDesc, reqVO.getMatterMinorDesc())
                .eqIfPresent(BizMngMatterMinorDO::getDeptCode, reqVO.getDeptCode())
                .likeIfPresent(BizMngMatterMinorDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(BizMngMatterMinorDO::getIsExt, reqVO.getIsExt())
                .eqIfPresent(BizMngMatterMinorDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(BizMngMatterMinorDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(BizMngMatterMinorDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(BizMngMatterMinorDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(BizMngMatterMinorDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(BizMngMatterMinorDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(BizMngMatterMinorDO::getId));
    }

}