package cn.iocoder.yudao.module.waterdetection.dal.mysql.watersampleinfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersampleinfo.WaterSampleInfoDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.watersampleinfo.vo.*;

/**
 * 水质检测信息 Mapper
 *
 * @author 朱聪权
 */
@Mapper
public interface WaterSampleInfoMapper extends BaseMapperX<WaterSampleInfoDO> {

    default PageResult<WaterSampleInfoDO> selectPage(WaterSampleInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WaterSampleInfoDO>()
                .likeIfPresent(WaterSampleInfoDO::getSampleNo, reqVO.getSampleNo())
                .likeIfPresent(WaterSampleInfoDO::getSampleType, reqVO.getSampleType())
                .likeIfPresent(WaterSampleInfoDO::getSampleName, reqVO.getSampleName())
                .likeIfPresent(WaterSampleInfoDO::getSampleNature, reqVO.getSampleNature())
                .likeIfPresent(WaterSampleInfoDO::getSampleStatus, reqVO.getSampleStatus())
                .likeIfPresent(WaterSampleInfoDO::getDeliveryMethod, reqVO.getDeliveryMethod())
                .betweenIfPresent(WaterSampleInfoDO::getSamplingDate, reqVO.getSamplingDate())
                .likeIfPresent(WaterSampleInfoDO::getSamplingLocation, reqVO.getSamplingLocation())
                .likeIfPresent(WaterSampleInfoDO::getContactPhone, reqVO.getContactPhone())
                .betweenIfPresent(WaterSampleInfoDO::getStartDate, reqVO.getStartDate())
                .betweenIfPresent(WaterSampleInfoDO::getEndDate, reqVO.getEndDate())
                .orderByDesc(WaterSampleInfoDO::getId));
    }

}