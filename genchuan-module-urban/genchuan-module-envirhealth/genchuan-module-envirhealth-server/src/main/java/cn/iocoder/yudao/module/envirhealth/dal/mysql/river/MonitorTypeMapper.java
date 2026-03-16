package cn.iocoder.yudao.module.envirhealth.dal.mysql.river;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.monitortype.MonitorTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.river.MonitorTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 监测类型字典表 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MonitorTypeMapper extends BaseMapperX<MonitorTypeDO> {

    default PageResult<MonitorTypeDO> selectPage(MonitorTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MonitorTypeDO>()
                .eqIfPresent(MonitorTypeDO::getMonitorTypeId, reqVO.getMonitorTypeId())
                .likeIfPresent(MonitorTypeDO::getMonitorName, reqVO.getMonitorName())
                .eqIfPresent(MonitorTypeDO::getDescription, reqVO.getDescription())
                .eqIfPresent(MonitorTypeDO::getStatus, reqVO.getStatus())
                .eqIfPresent(MonitorTypeDO::getSort, reqVO.getSort())
                .eqIfPresent(MonitorTypeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(MonitorTypeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(MonitorTypeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(MonitorTypeDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(MonitorTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MonitorTypeDO::getId));
    }

}