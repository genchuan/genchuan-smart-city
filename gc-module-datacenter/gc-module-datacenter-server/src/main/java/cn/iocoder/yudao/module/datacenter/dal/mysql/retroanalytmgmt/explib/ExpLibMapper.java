package cn.iocoder.yudao.module.datacenter.dal.mysql.retroanalytmgmt.explib;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.explib.vo.ExpLibPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.retroanalytmgmt.explib.ExpLibDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 经验库 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ExpLibMapper extends BaseMapperX<ExpLibDO> {

    default PageResult<ExpLibDO> selectPage(ExpLibPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ExpLibDO>()
                .eqIfPresent(ExpLibDO::getExpId, reqVO.getExpId())
                .eqIfPresent(ExpLibDO::getExpNo, reqVO.getExpNo())
                .eqIfPresent(ExpLibDO::getExpTitle, reqVO.getExpTitle())
                .eqIfPresent(ExpLibDO::getEvtTypeId, reqVO.getEvtTypeId())
                .likeIfPresent(ExpLibDO::getEvtTypeName, reqVO.getEvtTypeName())
                .eqIfPresent(ExpLibDO::getExpType, reqVO.getExpType())
                .eqIfPresent(ExpLibDO::getExpContent, reqVO.getExpContent())
                .eqIfPresent(ExpLibDO::getEvalId, reqVO.getEvalId())
                .eqIfPresent(ExpLibDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(ExpLibDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(ExpLibDO::getId));
    }

}