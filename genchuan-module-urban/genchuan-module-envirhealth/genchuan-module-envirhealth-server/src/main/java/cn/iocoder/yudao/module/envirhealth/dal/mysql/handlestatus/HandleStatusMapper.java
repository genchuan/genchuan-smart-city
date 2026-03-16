package cn.iocoder.yudao.module.envirhealth.dal.mysql.handlestatus;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.handlestatus.vo.HandleStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.handlestatus.HandleStatusDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 处置状态字典表【通用复用】 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface HandleStatusMapper extends BaseMapperX<HandleStatusDO> {

    default PageResult<HandleStatusDO> selectPage(HandleStatusPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<HandleStatusDO>()
                .eqIfPresent(HandleStatusDO::getSysHandleStatusId, reqVO.getSysHandleStatusId())
                .likeIfPresent(HandleStatusDO::getName, reqVO.getName())
                .eqIfPresent(HandleStatusDO::getCode, reqVO.getCode())
                .eqIfPresent(HandleStatusDO::getStatus, reqVO.getStatus())
                .eqIfPresent(HandleStatusDO::getSort, reqVO.getSort())
                .eqIfPresent(HandleStatusDO::getRemark, reqVO.getRemark())
                .eqIfPresent(HandleStatusDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(HandleStatusDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(HandleStatusDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(HandleStatusDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(HandleStatusDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(HandleStatusDO::getId));
    }

}