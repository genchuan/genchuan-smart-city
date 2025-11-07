package cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingstat;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingstat.vo.GeocodingStatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingstat.vo.GeocodingStatSaveReqVO;
import cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodinghouse.GeocodingHouseMapper;
import cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingpoi.GeocodingPoiMapper;
import cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingregion.GeocodingRegionMapper;
import cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingstreet.GeocodingStreetMapper;
import cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingzone.GeocodingZoneMapper;
import cn.iocoder.yudao.module.datacenter.framework.util.UuidUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingstat.GeocodingStatDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingstat.GeocodingStatMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 基本地点数据统计报表 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class GeocodingStatServiceImpl implements GeocodingStatService {

    @Resource
    private GeocodingStatMapper geocodingStatMapper;

    @Resource
    private GeocodingHouseMapper geocodingHouseMapper;

    @Resource
    private GeocodingPoiMapper geocodingPoiMapper;

    @Resource
    private GeocodingRegionMapper geocodingRegionMapper;

    @Resource
    private GeocodingStreetMapper geocodingStreetMapper;

    @Resource
    private GeocodingZoneMapper geocodingZoneMapper;

    @Override
    public Map<String, Object> countAllTables() {
        Map<String, Object> result = new HashMap<>();

        try {
            // 统计门牌楼牌数据
            Long houseCount = geocodingHouseMapper.selectCount(null);
            result.put("houseCount", houseCount);

            // 统计兴趣点数据
            Long poiCount = geocodingPoiMapper.selectCount(null);
            result.put("poiCount", poiCount);

            // 统计区域数据
            Long regionCount = geocodingRegionMapper.selectCount(null);
            result.put("regionCount", regionCount);

            // 统计街巷数据
            Long streetCount = geocodingStreetMapper.selectCount(null);
            result.put("streetCount", streetCount);

            // 统计地片区片数据
            Long zoneCount = geocodingZoneMapper.selectCount(null);
            result.put("zoneCount", zoneCount);

            // 计算总计
            Long totalCount = houseCount + poiCount + regionCount + streetCount + zoneCount;
            result.put("totalCount", totalCount);
            result.put("success", true);
            result.put("message", "统计完成");

        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "统计失败：" + e.getMessage());
        }

        return result;
    }

    @Override
    public Map<String, Object> getStatisticsSummary() {
        Map<String, Object> summary = countAllTables();

        // 添加百分比信息
        if (summary.get("success").equals(true)) {
            Long total = (Long) summary.get("totalCount");
            if (total > 0) {
                summary.put("housePercentage",
                        String.format("%.2f%%", ((Long) summary.get("houseCount") * 100.0 / total)));
                summary.put("poiPercentage",
                        String.format("%.2f%%", ((Long) summary.get("poiCount") * 100.0 / total)));
                summary.put("regionPercentage",
                        String.format("%.2f%%", ((Long) summary.get("regionCount") * 100.0 / total)));
                summary.put("streetPercentage",
                        String.format("%.2f%%", ((Long) summary.get("streetCount") * 100.0 / total)));
                summary.put("zonePercentage",
                        String.format("%.2f%%", ((Long) summary.get("zoneCount") * 100.0 / total)));
            }
        }
        return summary;
    }


//    ================================以下方法都废弃===================================

    @Override
    public Long createGeocodingStat(GeocodingStatSaveReqVO createReqVO) {
        // 插入
        GeocodingStatDO geocodingStat = BeanUtils.toBean(createReqVO, GeocodingStatDO.class);
        geocodingStat.setStatId(UuidUtils.generateUUID());
        geocodingStatMapper.insert(geocodingStat);
        // 返回
        return geocodingStat.getId();
    }

    @Override
    public void updateGeocodingStat(GeocodingStatSaveReqVO updateReqVO) {
        // 校验存在
        validateGeocodingStatExists(updateReqVO.getId());
        // 更新
        GeocodingStatDO updateObj = BeanUtils.toBean(updateReqVO, GeocodingStatDO.class);
        geocodingStatMapper.updateById(updateObj);
    }

    @Override
    public void deleteGeocodingStat(Long id) {
        // 校验存在
        validateGeocodingStatExists(id);
        // 删除
        geocodingStatMapper.deleteById(id);
    }

    private void validateGeocodingStatExists(Long id) {
        if (geocodingStatMapper.selectById(id) == null) {
            throw exception(GEOCODING_STAT_NOT_EXISTS);
        }
    }

    @Override
    public GeocodingStatDO getGeocodingStat(Long id) {
        return geocodingStatMapper.selectById(id);
    }

    @Override
    public PageResult<GeocodingStatDO> getGeocodingStatPage(GeocodingStatPageReqVO pageReqVO) {
        return geocodingStatMapper.selectPage(pageReqVO);
    }

}