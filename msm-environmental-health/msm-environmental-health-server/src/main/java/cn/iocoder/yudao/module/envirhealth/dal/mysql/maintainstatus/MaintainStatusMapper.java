package cn.iocoder.yudao.module.envirhealth.dal.mysql.maintainstatus;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.maintainstatus.MaintainStatusDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.envirhealth.controller.admin.maintainstatus.vo.*;

/**
 * 维护状态字典表【通用复用】 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MaintainStatusMapper extends BaseMapperX<MaintainStatusDO> {

    default PageResult<MaintainStatusDO> selectPage(MaintainStatusPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MaintainStatusDO>()
                .eqIfPresent(MaintainStatusDO::getSysMaintainStatusId, reqVO.getSysMaintainStatusId())
                .likeIfPresent(MaintainStatusDO::getName, reqVO.getName())
                .eqIfPresent(MaintainStatusDO::getCode, reqVO.getCode())
                .eqIfPresent(MaintainStatusDO::getStatus, reqVO.getStatus())
                .eqIfPresent(MaintainStatusDO::getSort, reqVO.getSort())
                .eqIfPresent(MaintainStatusDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MaintainStatusDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(MaintainStatusDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(MaintainStatusDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(MaintainStatusDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(MaintainStatusDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MaintainStatusDO::getId));
    }

}