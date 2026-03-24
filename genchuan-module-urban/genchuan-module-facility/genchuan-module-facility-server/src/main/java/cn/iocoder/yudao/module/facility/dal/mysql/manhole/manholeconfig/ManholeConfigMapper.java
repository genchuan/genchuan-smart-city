package cn.iocoder.yudao.module.facility.dal.mysql.manhole.manholeconfig;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig.vo.ManholeConfigPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig.vo.ManholeCoverConfigPageRespVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholeconfig.ManholeConfigDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 窨井盖监测配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ManholeConfigMapper extends BaseMapperX<ManholeConfigDO> {

    default PageResult<ManholeConfigDO> selectPage(ManholeConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ManholeConfigDO>()
                .eqIfPresent(ManholeConfigDO::getCoverId, reqVO.getCoverId())
                .eqIfPresent(ManholeConfigDO::getCollectFrequency, reqVO.getCollectFrequency())
                .eqIfPresent(ManholeConfigDO::getTiltAngleThreshold, reqVO.getTiltAngleThreshold())
                .betweenIfPresent(ManholeConfigDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ManholeConfigDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ManholeConfigDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ManholeConfigDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ManholeConfigDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ManholeConfigDO::getId));
    }

    IPage<ManholeCoverConfigPageRespVO> selectConfigPage(
            IPage<ManholeCoverConfigPageRespVO> page,
            @Param("coverId") String coverId,
            @Param("configStatus") Integer configStatus,
            @Param("tenantId") String tenantId
    );

}