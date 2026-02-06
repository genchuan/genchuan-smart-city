package cn.iocoder.yudao.module.park.service.park.user.blackwhitelist;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.user.blackwhitelist.vo.BlackWhiteListPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.blackwhitelist.vo.BlackWhiteListSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.blackwhitelist.BlackWhiteListDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.user.blackwhitelist.BlackWhiteListMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.BLACK_WHITE_LIST_NOT_EXISTS;

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
    public boolean verifyWhitelistByCarNumber(String carNumber) {
        LocalDateTime now = LocalDateTime.now();

        LambdaQueryWrapper<BlackWhiteListDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlackWhiteListDO::getListType, "白名单")
                .eq(BlackWhiteListDO::getTargetType, "车辆")
                .eq(BlackWhiteListDO::getTargetId, carNumber)
//                .eq(BlackWhiteListDO::getDeleted, 0)
                .eq(BlackWhiteListDO::getStatus, "生效中")
                // 已生效
                .le(BlackWhiteListDO::getEffectTime, now)
                // 未过期 or 永久有效
                .and(w -> w.isNull(BlackWhiteListDO::getExpireTime)
                        .or()
                        .gt(BlackWhiteListDO::getExpireTime, now))
                // 只要一条
                .last("limit 1");

        BlackWhiteListDO record = blackWhiteListMapper.selectOne(wrapper);

        return record != null;
    }

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
