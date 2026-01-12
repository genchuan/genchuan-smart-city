package cn.iocoder.yudao.module.industry.service.park.user.parkblackwhitelist;

import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkblackwhitelist.vo.ParkBlackWhiteListPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkblackwhitelist.vo.ParkBlackWhiteListSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkblackwhitelist.ParkBlackWhiteListDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.user.parkblackwhitelist.ParkBlackWhiteListMapper;
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
 * 黑白名单 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkBlackWhiteListServiceImpl implements ParkBlackWhiteListService {

    @Resource
    private ParkBlackWhiteListMapper parkBlackWhiteListMapper;

    @Override
    public Long createParkBlackWhiteList(ParkBlackWhiteListSaveReqVO createReqVO) {
        // 插入
        ParkBlackWhiteListDO parkBlackWhiteList = BeanUtils.toBean(createReqVO, ParkBlackWhiteListDO.class);
        parkBlackWhiteListMapper.insert(parkBlackWhiteList);
        // 返回
        return parkBlackWhiteList.getId();
    }

    @Override
    public void updateParkBlackWhiteList(ParkBlackWhiteListSaveReqVO updateReqVO) {
        // 校验存在
        validateParkBlackWhiteListExists(updateReqVO.getId());
        // 更新
        ParkBlackWhiteListDO updateObj = BeanUtils.toBean(updateReqVO, ParkBlackWhiteListDO.class);
        parkBlackWhiteListMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkBlackWhiteList(Long id) {
        // 校验存在
        validateParkBlackWhiteListExists(id);
        // 删除
        parkBlackWhiteListMapper.deleteById(id);
    }

    private void validateParkBlackWhiteListExists(Long id) {
        if (parkBlackWhiteListMapper.selectById(id) == null) {
            throw exception(PARK_BLACK_WHITE_LIST_NOT_EXISTS);
        }
    }

    @Override
    public ParkBlackWhiteListDO getParkBlackWhiteList(Long id) {
        return parkBlackWhiteListMapper.selectById(id);
    }

    @Override
    public PageResult<ParkBlackWhiteListDO> getParkBlackWhiteListPage(ParkBlackWhiteListPageReqVO pageReqVO) {
        return parkBlackWhiteListMapper.selectPage(pageReqVO);
    }

}
