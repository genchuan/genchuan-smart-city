package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt.vo.PrizeMgmtPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PrizeMgmtDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrizeMgmtMapper extends BaseMapperX<PrizeMgmtDO> {

    default PageResult<PrizeMgmtDO> selectPage(PrizeMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PrizeMgmtDO>()
                .likeIfPresent(PrizeMgmtDO::getName, reqVO.getName())
                .eqIfPresent(PrizeMgmtDO::getType, reqVO.getType())
                .eqIfPresent(PrizeMgmtDO::getStatus, reqVO.getStatus())
                .orderByDesc(PrizeMgmtDO::getId));
    }

}
