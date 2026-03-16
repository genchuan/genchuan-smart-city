package cn.iocoder.yudao.module.envirhealth.dal.mysql.market;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.market.vo.checkresult.CheckResultPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.market.CheckResultDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 核查结果字典表 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CheckResultMapper extends BaseMapperX<CheckResultDO> {

    default PageResult<CheckResultDO> selectPage(CheckResultPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CheckResultDO>()
                .eqIfPresent(CheckResultDO::getCheckResultId, reqVO.getCheckResultId())
                .likeIfPresent(CheckResultDO::getCheckResultName, reqVO.getCheckResultName())
                .eqIfPresent(CheckResultDO::getDescription, reqVO.getDescription())
                .eqIfPresent(CheckResultDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CheckResultDO::getSort, reqVO.getSort())
                .eqIfPresent(CheckResultDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(CheckResultDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(CheckResultDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(CheckResultDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(CheckResultDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CheckResultDO::getId));
    }

}