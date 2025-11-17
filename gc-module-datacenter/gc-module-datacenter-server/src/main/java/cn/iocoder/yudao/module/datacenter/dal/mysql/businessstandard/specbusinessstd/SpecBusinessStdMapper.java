package cn.iocoder.yudao.module.datacenter.dal.mysql.businessstandard.specbusinessstd;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.businessstandard.specbusinessstd.vo.SpecBusinessStdPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.businessstandard.specbusinessstd.SpecBusinessStdDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 专项业务标准 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface SpecBusinessStdMapper extends BaseMapperX<SpecBusinessStdDO> {

    default PageResult<SpecBusinessStdDO> selectPage(SpecBusinessStdPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SpecBusinessStdDO>()
                .eqIfPresent(SpecBusinessStdDO::getSpecBusinessStdId, reqVO.getSpecBusinessStdId())
                .likeIfPresent(SpecBusinessStdDO::getStdName, reqVO.getStdName())
                .eqIfPresent(SpecBusinessStdDO::getBusinessFieldCode, reqVO.getBusinessFieldCode())
                .likeIfPresent(SpecBusinessStdDO::getBusinessFieldName, reqVO.getBusinessFieldName())
                .eqIfPresent(SpecBusinessStdDO::getStdVersion, reqVO.getStdVersion())
                .betweenIfPresent(SpecBusinessStdDO::getEffectiveTime, reqVO.getEffectiveTime())
                .eqIfPresent(SpecBusinessStdDO::getStdClause, reqVO.getStdClause())
                .eqIfPresent(SpecBusinessStdDO::getExecRequirement, reqVO.getExecRequirement())
                .eqIfPresent(SpecBusinessStdDO::getAcceptIdx, reqVO.getAcceptIdx())
                .eqIfPresent(SpecBusinessStdDO::getStdFileId, reqVO.getStdFileId())
                .eqIfPresent(SpecBusinessStdDO::getStdFilePath, reqVO.getStdFilePath())
                .eqIfPresent(SpecBusinessStdDO::getStdStatus, reqVO.getStdStatus())
                .eqIfPresent(SpecBusinessStdDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(SpecBusinessStdDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(SpecBusinessStdDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(SpecBusinessStdDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(SpecBusinessStdDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(SpecBusinessStdDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(SpecBusinessStdDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(SpecBusinessStdDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(SpecBusinessStdDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(SpecBusinessStdDO::getId));
    }

}