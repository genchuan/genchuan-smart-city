package cn.iocoder.yudao.module.datacenter.dal.mysql.mngcompcfg.relmngcompsymbol;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.relmngcompsymbol.vo.RelMngCompSymbolPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.relmngcompsymbol.RelMngCompSymbolDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理部件图示关联 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface RelMngCompSymbolMapper extends BaseMapperX<RelMngCompSymbolDO> {

    default PageResult<RelMngCompSymbolDO> selectPage(RelMngCompSymbolPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RelMngCompSymbolDO>()
                .eqIfPresent(RelMngCompSymbolDO::getMngCompSymbolId, reqVO.getMngCompSymbolId())
                .eqIfPresent(RelMngCompSymbolDO::getMinorId, reqVO.getMinorId())
                .likeIfPresent(RelMngCompSymbolDO::getMinorName, reqVO.getMinorName())
                .eqIfPresent(RelMngCompSymbolDO::getSymbolId, reqVO.getSymbolId())
                .likeIfPresent(RelMngCompSymbolDO::getSymbolName, reqVO.getSymbolName())
                .eqIfPresent(RelMngCompSymbolDO::getSymbolPath, reqVO.getSymbolPath())
                .eqIfPresent(RelMngCompSymbolDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(RelMngCompSymbolDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(RelMngCompSymbolDO::getId));
    }

}