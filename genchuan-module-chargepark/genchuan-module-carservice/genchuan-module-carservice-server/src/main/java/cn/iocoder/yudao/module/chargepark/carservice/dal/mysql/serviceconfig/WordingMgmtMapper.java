package cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.serviceconfig;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.serviceconfig.vo.WordingMgmtPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.serviceconfig.WordingMgmtDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 话术管理 Mapper
 *
 * @author carservice
 */
@Mapper
public interface WordingMgmtMapper extends BaseMapperX<WordingMgmtDO> {

    default PageResult<WordingMgmtDO> selectPage(WordingMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WordingMgmtDO>()
                .likeIfPresent(WordingMgmtDO::getName, reqVO.getName())
                .eqIfPresent(WordingMgmtDO::getType, reqVO.getType())
                .eqIfPresent(WordingMgmtDO::getStatus, reqVO.getStatus())
                .orderByDesc(WordingMgmtDO::getId));
    }

}
