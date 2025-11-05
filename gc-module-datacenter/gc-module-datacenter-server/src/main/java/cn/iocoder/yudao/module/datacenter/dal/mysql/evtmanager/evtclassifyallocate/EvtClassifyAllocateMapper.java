package cn.iocoder.yudao.module.datacenter.dal.mysql.evtmanager.evtclassifyallocate;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtclassifyallocate.vo.EvtClassifyAllocatePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtclassifyallocate.EvtClassifyAllocateDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 事件分级分拨 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface EvtClassifyAllocateMapper extends BaseMapperX<EvtClassifyAllocateDO> {

    default PageResult<EvtClassifyAllocateDO> selectPage(EvtClassifyAllocatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvtClassifyAllocateDO>()
                .eqIfPresent(EvtClassifyAllocateDO::getAllocateId, reqVO.getAllocateId())
                .eqIfPresent(EvtClassifyAllocateDO::getEvtCode, reqVO.getEvtCode())
                .eqIfPresent(EvtClassifyAllocateDO::getEvtTypeId, reqVO.getEvtTypeId())
                .likeIfPresent(EvtClassifyAllocateDO::getEvtTypeName, reqVO.getEvtTypeName())
                .eqIfPresent(EvtClassifyAllocateDO::getUrgencyLevel, reqVO.getUrgencyLevel())
                .eqIfPresent(EvtClassifyAllocateDO::getAllocateDeptCode, reqVO.getAllocateDeptCode())
                .likeIfPresent(EvtClassifyAllocateDO::getAllocateDeptName, reqVO.getAllocateDeptName())
                .eqIfPresent(EvtClassifyAllocateDO::getAllocatorId, reqVO.getAllocatorId())
                .likeIfPresent(EvtClassifyAllocateDO::getAllocatorName, reqVO.getAllocatorName())
                .betweenIfPresent(EvtClassifyAllocateDO::getAllocateTime, reqVO.getAllocateTime())
                .eqIfPresent(EvtClassifyAllocateDO::getAllocateDesc, reqVO.getAllocateDesc())
                .orderByDesc(EvtClassifyAllocateDO::getId));
    }

}