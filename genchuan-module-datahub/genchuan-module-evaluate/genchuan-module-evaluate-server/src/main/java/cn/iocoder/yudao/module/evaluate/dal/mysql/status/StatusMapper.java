package cn.iocoder.yudao.module.evaluate.dal.mysql.status;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.status.vo.StatusPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.status.StatusDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 状态字典 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface StatusMapper extends BaseMapperX<StatusDO> {

    default PageResult<StatusDO> selectPage(StatusPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StatusDO>()
                .eqIfPresent(StatusDO::getStatusId, reqVO.getStatusId())
                .likeIfPresent(StatusDO::getName, reqVO.getName())
                .eqIfPresent(StatusDO::getCode, reqVO.getCode())
                .eqIfPresent(StatusDO::getDesc, reqVO.getDesc())
                .betweenIfPresent(StatusDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(StatusDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(StatusDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(StatusDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(StatusDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(StatusDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(StatusDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(StatusDO::getId));
    }

}