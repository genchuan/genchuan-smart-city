package cn.iocoder.yudao.module.smartcity.dal.mysql.detectionlocalization;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.detectionlocalization.DetectionLocalizationDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.detectionlocalization.vo.*;

/**
 * 故障检测与定位 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface DetectionLocalizationMapper extends BaseMapperX<DetectionLocalizationDO> {

    default PageResult<DetectionLocalizationDO> selectPage(DetectionLocalizationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DetectionLocalizationDO>()
                .likeIfPresent(DetectionLocalizationDO::getEquipmentName, reqVO.getEquipmentName())
                .eqIfPresent(DetectionLocalizationDO::getEquipmentNumber, reqVO.getEquipmentNumber())
                .betweenIfPresent(DetectionLocalizationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DetectionLocalizationDO::getId));
    }

}