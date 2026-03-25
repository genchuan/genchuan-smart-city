package cn.iocoder.yudao.module.smartcity.dal.mysql.pipegallerysafety;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.pipegallerysafety.PipeGallerySafetyDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.pipegallerysafety.vo.*;

/**
 * 管廊安全 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface PipeGallerySafetyMapper extends BaseMapperX<PipeGallerySafetyDO> {

    default PageResult<PipeGallerySafetyDO> selectPage(PipeGallerySafetyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PipeGallerySafetyDO>()
                .likeIfPresent(PipeGallerySafetyDO::getPipeGalleryName, reqVO.getPipeGalleryName())
                .likeIfPresent(PipeGallerySafetyDO::getLocationGallery, reqVO.getLocationGallery())
                .likeIfPresent(PipeGallerySafetyDO::getPipeGalleryNumber, reqVO.getPipeGalleryNumber())
                .betweenIfPresent(PipeGallerySafetyDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PipeGallerySafetyDO::getId));
    }

}