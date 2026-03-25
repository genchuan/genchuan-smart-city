package cn.iocoder.yudao.module.smartcity.dal.mysql.pointinfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.pointinfo.PointInfoDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.pointinfo.vo.*;

/**
 * 监测点位 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface PointInfoMapper extends BaseMapperX<PointInfoDO> {

    default PageResult<PointInfoDO> selectPage(PointInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PointInfoDO>()
                .likeIfPresent(PointInfoDO::getPointNumber, reqVO.getPointNumber())
                .likeIfPresent(PointInfoDO::getPointName, reqVO.getPointName())
                .betweenIfPresent(PointInfoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PointInfoDO::getId));
    }

}