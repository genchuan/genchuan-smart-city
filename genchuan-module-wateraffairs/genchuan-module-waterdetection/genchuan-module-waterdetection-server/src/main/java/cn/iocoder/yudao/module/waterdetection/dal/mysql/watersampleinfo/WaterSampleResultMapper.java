package cn.iocoder.yudao.module.waterdetection.dal.mysql.watersampleinfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersampleinfo.WaterSampleResultDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 出厂水检测结果 Mapper
 *
 * @author 朱聪权
 */
@Mapper
public interface WaterSampleResultMapper extends BaseMapperX<WaterSampleResultDO> {

    default PageResult<WaterSampleResultDO> selectPage(PageParam reqVO, Long waterSampleId) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WaterSampleResultDO>()
            .eq(WaterSampleResultDO::getWaterSampleId, waterSampleId)
            .orderByDesc(WaterSampleResultDO::getId));
    }

    default int deleteByWaterSampleId(Long waterSampleId) {
        return delete(WaterSampleResultDO::getWaterSampleId, waterSampleId);
    }

}