package cn.iocoder.yudao.module.industry.service.park.vas.parkreversesearch;

import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkreversesearch.vo.ParkReverseSearchPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkreversesearch.vo.ParkReverseSearchSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkreversesearch.ParkReverseSearchDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.vas.parkreversesearch.ParkReverseSearchMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 反向寻车记录 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkReverseSearchServiceImpl implements ParkReverseSearchService {

    @Resource
    private ParkReverseSearchMapper parkReverseSearchMapper;

    @Override
    public Long createParkReverseSearch(ParkReverseSearchSaveReqVO createReqVO) {
        // 插入
        ParkReverseSearchDO parkReverseSearch = BeanUtils.toBean(createReqVO, ParkReverseSearchDO.class);
        parkReverseSearchMapper.insert(parkReverseSearch);
        // 返回
        return parkReverseSearch.getId();
    }

    @Override
    public void updateParkReverseSearch(ParkReverseSearchSaveReqVO updateReqVO) {
        // 校验存在
        validateParkReverseSearchExists(updateReqVO.getId());
        // 更新
        ParkReverseSearchDO updateObj = BeanUtils.toBean(updateReqVO, ParkReverseSearchDO.class);
        parkReverseSearchMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkReverseSearch(Long id) {
        // 校验存在
        validateParkReverseSearchExists(id);
        // 删除
        parkReverseSearchMapper.deleteById(id);
    }

    private void validateParkReverseSearchExists(Long id) {
        if (parkReverseSearchMapper.selectById(id) == null) {
            throw exception(PARK_REVERSE_SEARCH_NOT_EXISTS);
        }
    }

    @Override
    public ParkReverseSearchDO getParkReverseSearch(Long id) {
        return parkReverseSearchMapper.selectById(id);
    }

    @Override
    public PageResult<ParkReverseSearchDO> getParkReverseSearchPage(ParkReverseSearchPageReqVO pageReqVO) {
        return parkReverseSearchMapper.selectPage(pageReqVO);
    }

}
