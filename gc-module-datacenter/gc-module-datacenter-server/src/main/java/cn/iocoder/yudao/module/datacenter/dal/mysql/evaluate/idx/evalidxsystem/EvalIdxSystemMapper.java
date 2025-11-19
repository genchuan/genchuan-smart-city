package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.idx.evalidxsystem;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxsystem.vo.EvalIdxSystemPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.idx.evalidxsystem.EvalIdxSystemDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 指标体系管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalIdxSystemMapper extends BaseMapperX<EvalIdxSystemDO> {

    default PageResult<EvalIdxSystemDO> selectPage(EvalIdxSystemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalIdxSystemDO>()
                .eqIfPresent(EvalIdxSystemDO::getIdxSystemId, reqVO.getIdxSystemId())
                .likeIfPresent(EvalIdxSystemDO::getIdxSystemName, reqVO.getIdxSystemName())
                .eqIfPresent(EvalIdxSystemDO::getSystemCode, reqVO.getSystemCode())
                .eqIfPresent(EvalIdxSystemDO::getApplyObjectType, reqVO.getApplyObjectType())
                .eqIfPresent(EvalIdxSystemDO::getSystemVersion, reqVO.getSystemVersion())
                .eqIfPresent(EvalIdxSystemDO::getSystemDesc, reqVO.getSystemDesc())
                .eqIfPresent(EvalIdxSystemDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(EvalIdxSystemDO::getCreateUserBiz, reqVO.getCreateUserBiz())
                .eqIfPresent(EvalIdxSystemDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalIdxSystemDO::getUpdateUserBiz, reqVO.getUpdateUserBiz())
                .eqIfPresent(EvalIdxSystemDO::getUpdateTimeBiz, reqVO.getUpdateTimeBiz())
                .eqIfPresent(EvalIdxSystemDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalIdxSystemDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalIdxSystemDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalIdxSystemDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalIdxSystemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalIdxSystemDO::getId));
    }

}