package cn.iocoder.yudao.module.envirhealth.dal.mysql.river;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.monitorstatus.MonitorStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.river.MonitorStatusDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 监测状态字典表【通用复用】 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MonitorStatusMapper extends BaseMapperX<MonitorStatusDO> {

    default PageResult<MonitorStatusDO> selectPage(MonitorStatusPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MonitorStatusDO>()
                .eqIfPresent(MonitorStatusDO::getMonitorStatusId, reqVO.getMonitorStatusId())
                .likeIfPresent(MonitorStatusDO::getMonitorStatusName, reqVO.getMonitorStatusName())
                .eqIfPresent(MonitorStatusDO::getDescription, reqVO.getDescription())
                .eqIfPresent(MonitorStatusDO::getStatus, reqVO.getStatus())
                .eqIfPresent(MonitorStatusDO::getSort, reqVO.getSort())
                .eqIfPresent(MonitorStatusDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(MonitorStatusDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(MonitorStatusDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(MonitorStatusDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(MonitorStatusDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MonitorStatusDO::getId));
    }

}