package cn.iocoder.yudao.module.vehiclepass.service.siteinput.spacequery;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo.SpaceQueryPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo.SpaceQueryRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo.SpaceQuerySaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo.SpaceQueryLocationRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo.SpaceQueryChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo.SpaceQueryChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.siteinput.spacequery.SpaceQueryDO;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.siteinput.spacequery.SpaceQueryMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.vehiclepass.enums.ErrorCodeConstants.QUERY_NOT_EXISTS;

/**
 * 泊位查询 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class SpaceQueryServiceImpl implements SpaceQueryService {

    @Resource
    private SpaceQueryMapper queryMapper;

    @Override
    public Long createQuery(SpaceQuerySaveReqVO createReqVO) {
        // 插入
        SpaceQueryDO query = BeanUtils.toBean(createReqVO, SpaceQueryDO.class);
        queryMapper.insert(query);

        // 返回
        return query.getId();
    }

    @Override
    public void updateQuery(SpaceQuerySaveReqVO updateReqVO) {
        // 校验存在
        validateQueryExists(updateReqVO.getId());
        // 更新
        SpaceQueryDO updateObj = BeanUtils.toBean(updateReqVO, SpaceQueryDO.class);
        queryMapper.updateById(updateObj);
    }

    @Override
    public void deleteQuery(Long id) {
        // 校验存在
        validateQueryExists(id);
        // 删除
        queryMapper.deleteById(id);
    }

    @Override
    public void deleteQueryListByIds(List<Long> ids) {
        // 删除
        queryMapper.deleteByIds(ids);
    }


    private void validateQueryExists(Long id) {
        if (queryMapper.selectById(id) == null) {
            throw exception(QUERY_NOT_EXISTS);
        }
    }

    @Override
    public SpaceQueryDO getQuery(Long id) {
        return queryMapper.selectById(id);
    }

    @Override
    public SpaceQueryRespVO getQueryWithJoin(Long id) {
        return queryMapper.selectByIdJoin(id);
    }

    @Override
    public PageResult<SpaceQueryDO> getQueryPage(SpaceQueryPageReqVO pageReqVO) {
        return queryMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<SpaceQueryRespVO> getQueryPageWithJoin(SpaceQueryPageReqVO pageReqVO) {
        Page<SpaceQueryRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        com.baomidou.mybatisplus.core.metadata.IPage<SpaceQueryRespVO> pageResult = queryMapper.selectPageJoin(page, pageReqVO);
        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
    }

    @Override
    public SpaceQueryLocationRespVO getLocation(Long id) {
        return queryMapper.selectLocation(id);
    }

    @Override
    public SpaceQueryChartRespVO getChart(SpaceQueryChartReqVO chartReqVO) {
        // 获取泊位位置分布
        List<Map<String, Object>> spaceLocationList = queryMapper.selectSpaceLocationList(chartReqVO);
        List<SpaceQueryChartRespVO.SpaceLocation> spaceLocations = spaceLocationList.stream()
            .map(map -> SpaceQueryChartRespVO.SpaceLocation.builder()
                    .spaceNo((String) map.get("spaceNo"))
                    .lon((java.math.BigDecimal) map.get("lon"))
                    .lat((java.math.BigDecimal) map.get("lat"))
                    .spaceStatus((String) map.get("spaceStatus"))
                    .build())
            .collect(Collectors.toList());

        // 获取统计数据
        Map<String, Object> chartData = queryMapper.selectChartData(chartReqVO);
        SpaceQueryChartRespVO.CardData cardData = SpaceQueryChartRespVO.CardData.builder()
                .queryCount(((Number) chartData.get("queryCount")).longValue())
                .querySuccessRate(((Number) chartData.get("querySuccessRate")).doubleValue())
                .build();

        return SpaceQueryChartRespVO.builder()
                .spaceLocationList(spaceLocations)
                .cardData(cardData)
                .build();
    }

}