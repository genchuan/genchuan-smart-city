package cn.iocoder.yudao.module.envirhealth.dal.mysql.vehicle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.workstatus.WorkStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.WorkStatusDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 作业状态字典表【通用复用】 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface WorkStatusMapper extends BaseMapperX<WorkStatusDO> {

    default PageResult<WorkStatusDO> selectPage(WorkStatusPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WorkStatusDO>()
                .eqIfPresent(WorkStatusDO::getWorkStatusId, reqVO.getWorkStatusId())
                .likeIfPresent(WorkStatusDO::getWorkStatusName, reqVO.getWorkStatusName())
                .eqIfPresent(WorkStatusDO::getDescription, reqVO.getDescription())
                .eqIfPresent(WorkStatusDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(WorkStatusDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(WorkStatusDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(WorkStatusDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(WorkStatusDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(WorkStatusDO::getId));
    }

}