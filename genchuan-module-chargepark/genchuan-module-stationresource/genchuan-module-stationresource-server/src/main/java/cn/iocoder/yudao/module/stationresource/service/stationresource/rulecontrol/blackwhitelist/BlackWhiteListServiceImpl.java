package cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.blackwhitelist;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.stationresource.enums.ErrorCodeConstants.*;

/**
 * 黑白名单 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class BlackWhiteListServiceImpl implements BlackWhiteListService {

    @Resource
    private BlackWhiteListMapper blackWhiteListMapper;

    @Override
    public Long createBlackWhiteList(BlackWhiteListSaveReqVO createReqVO) {
        // 插入
        BlackWhiteListDO blackWhiteList = BeanUtils.toBean(createReqVO, BlackWhiteListDO.class);
        blackWhiteListMapper.insert(blackWhiteList);

        // 返回
        return blackWhiteList.getId();
    }

    @Override
    public void updateBlackWhiteList(BlackWhiteListSaveReqVO updateReqVO) {
        // 校验存在
        validateBlackWhiteListExists(updateReqVO.getId());
        // 更新
        BlackWhiteListDO updateObj = BeanUtils.toBean(updateReqVO, BlackWhiteListDO.class);
        blackWhiteListMapper.updateById(updateObj);
    }

    @Override
    public void deleteBlackWhiteList(Long id) {
        // 校验存在
        validateBlackWhiteListExists(id);
        // 删除
        blackWhiteListMapper.deleteById(id);
    }

    @Override
        public void deleteBlackWhiteListListByIds(List<Long> ids) {
        // 删除
        blackWhiteListMapper.deleteByIds(ids);
        }


    private void validateBlackWhiteListExists(Long id) {
        if (blackWhiteListMapper.selectById(id) == null) {
            throw exception(BLACK_WHITE_LIST_NOT_EXISTS);
        }
    }

    @Override
    public BlackWhiteListDO getBlackWhiteList(Long id) {
        return blackWhiteListMapper.selectById(id);
    }

    @Override
    public PageResult<BlackWhiteListDO> getBlackWhiteListPage(BlackWhiteListPageReqVO pageReqVO) {
        return blackWhiteListMapper.selectPage(pageReqVO);
    }

}
