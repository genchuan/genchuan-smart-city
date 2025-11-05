package cn.iocoder.yudao.module.datacenter.dal.mysql.mngcompcfg.bizmngcomp;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcomp.vo.BizMngCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.bizmngcomp.BizMngCompDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理部件信息管理 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface BizMngCompMapper extends BaseMapperX<BizMngCompDO> {

    default PageResult<BizMngCompDO> selectPage(BizMngCompPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BizMngCompDO>()
                .eqIfPresent(BizMngCompDO::getMngCompId, reqVO.getMngCompId())
                .eqIfPresent(BizMngCompDO::getCompCode, reqVO.getCompCode())
                .likeIfPresent(BizMngCompDO::getCompName, reqVO.getCompName())
                .eqIfPresent(BizMngCompDO::getMinorId, reqVO.getMinorId())
                .likeIfPresent(BizMngCompDO::getMinorName, reqVO.getMinorName())
                .eqIfPresent(BizMngCompDO::getDeptCode, reqVO.getDeptCode())
                .likeIfPresent(BizMngCompDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(BizMngCompDO::getGridId, reqVO.getGridId())
                .likeIfPresent(BizMngCompDO::getGridName, reqVO.getGridName())
                .eqIfPresent(BizMngCompDO::getCompStatus, reqVO.getCompStatus())
                .betweenIfPresent(BizMngCompDO::getInitDate, reqVO.getInitDate())
                .betweenIfPresent(BizMngCompDO::getChangeDate, reqVO.getChangeDate())
                .eqIfPresent(BizMngCompDO::getDataSource, reqVO.getDataSource())
                .eqIfPresent(BizMngCompDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(BizMngCompDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(BizMngCompDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(BizMngCompDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(BizMngCompDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(BizMngCompDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(BizMngCompDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(BizMngCompDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(BizMngCompDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(BizMngCompDO::getId));
    }

}