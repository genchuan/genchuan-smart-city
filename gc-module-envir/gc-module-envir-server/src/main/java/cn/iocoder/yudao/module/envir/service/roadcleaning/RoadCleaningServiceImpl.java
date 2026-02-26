package cn.iocoder.yudao.module.envir.service.roadcleaning;

import cn.iocoder.yudao.module.envir.controller.admin.publictoilet.vo.PublicToiletPageReqVO;
import cn.iocoder.yudao.module.envir.dal.dataobject.publictoilet.PublicToiletDetailDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.roadcleaning.RoadCleaningDetailDO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.envir.controller.admin.roadcleaning.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.roadcleaning.RoadCleaningDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envir.dal.mysql.roadcleaning.RoadCleaningMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envir.enums.ErrorCodeConstants.*;

/**
 * 道路清扫计划 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RoadCleaningServiceImpl implements RoadCleaningService {

    @Resource
    private RoadCleaningMapper roadCleaningMapper;

    @Override
    public Long createRoadCleaning(RoadCleaningSaveReqVO createReqVO) {
        // 插入
        RoadCleaningDO roadCleaning = BeanUtils.toBean(createReqVO, RoadCleaningDO.class);
        roadCleaningMapper.insert(roadCleaning);
        // 返回
        return roadCleaning.getId();
    }

    @Override
    public void updateRoadCleaning(RoadCleaningSaveReqVO updateReqVO) {
        // 校验存在
        validateRoadCleaningExists(updateReqVO.getId());
        // 更新
        RoadCleaningDO updateObj = BeanUtils.toBean(updateReqVO, RoadCleaningDO.class);
        roadCleaningMapper.updateById(updateObj);
    }

    @Override
    public void deleteRoadCleaning(Long id) {
        // 校验存在
        validateRoadCleaningExists(id);
        // 删除
        roadCleaningMapper.deleteById(id);
    }

    private void validateRoadCleaningExists(Long id) {
        if (roadCleaningMapper.selectById(id) == null) {
            throw exception(ROAD_CLEANING_NOT_EXISTS);
        }
    }

    @Override
    public RoadCleaningDO getRoadCleaning(Long id) {
        return roadCleaningMapper.selectById(id);
    }

    @Override
    public PageResult<RoadCleaningDO> getRoadCleaningPage(RoadCleaningPageReqVO pageReqVO) {
        return roadCleaningMapper.selectPage(pageReqVO);
    }

    @Override
    public List<RoadCleaningDetailDO> getRoadCleaningListDetail() {
        return roadCleaningMapper.selectListDetail();
    }

    @Override
    public PageResult<RoadCleaningDetailDO> getRoadCleaningDetailPage(RoadCleaningPageReqVO pageReqVO) {

        Long total = roadCleaningMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<RoadCleaningDetailDO> list = roadCleaningMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }
}