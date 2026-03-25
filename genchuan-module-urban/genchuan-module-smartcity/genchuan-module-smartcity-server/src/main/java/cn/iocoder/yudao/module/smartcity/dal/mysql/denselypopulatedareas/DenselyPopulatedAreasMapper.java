package cn.iocoder.yudao.module.smartcity.dal.mysql.denselypopulatedareas;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.denselypopulatedareas.DenselyPopulatedAreasDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.denselypopulatedareas.vo.*;

/**
 * 人员密集场所安全 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface DenselyPopulatedAreasMapper extends BaseMapperX<DenselyPopulatedAreasDO> {

    default PageResult<DenselyPopulatedAreasDO> selectPage(DenselyPopulatedAreasPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DenselyPopulatedAreasDO>()
                .likeIfPresent(DenselyPopulatedAreasDO::getTheName, reqVO.getTheName())
                .eqIfPresent(DenselyPopulatedAreasDO::getTypeOfVenue, reqVO.getTypeOfVenue())
                .likeIfPresent(DenselyPopulatedAreasDO::getAddress, reqVO.getAddress())
                .betweenIfPresent(DenselyPopulatedAreasDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DenselyPopulatedAreasDO::getId));
    }

}