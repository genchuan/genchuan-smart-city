package cn.iocoder.yudao.module.park.service.park.marketing.smoothstopcard;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.module.park.controller.admin.park.marketing.smoothstopcard.vo.SmoothStopCardPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.marketing.smoothstopcard.vo.SmoothStopCardSaveReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.marketing.smoothstopcard.vo.VerifyOrderFreeReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.marketing.smoothstopcard.vo.VerifyOrderFreeRespVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.marketing.smoothstopcard.SmoothStopCardDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.marketing.smoothstopcard.SmoothStopCardMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.*;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.SMOOTH_STOP_CARD_NOT_EXISTS;

/**
 * 畅停卡 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
@Slf4j
public class SmoothStopCardServiceImpl implements SmoothStopCardService {

    @Resource
    private SmoothStopCardMapper smoothStopCardMapper;

    @Override
    public Long createSmoothStopCard(SmoothStopCardSaveReqVO createReqVO) {
        // 插入
        SmoothStopCardDO smoothStopCard = BeanUtils.toBean(createReqVO, SmoothStopCardDO.class);
        smoothStopCardMapper.insert(smoothStopCard);
        // 返回
        return smoothStopCard.getId();
    }

    @Override
    public void updateSmoothStopCard(SmoothStopCardSaveReqVO updateReqVO) {
        // 校验存在
        validateSmoothStopCardExists(updateReqVO.getId());
        // 更新
        SmoothStopCardDO updateObj = BeanUtils.toBean(updateReqVO, SmoothStopCardDO.class);
        smoothStopCardMapper.updateById(updateObj);
    }

    @Override
    public void deleteSmoothStopCard(Long id) {
        // 校验存在
        validateSmoothStopCardExists(id);
        // 删除
        smoothStopCardMapper.deleteById(id);
    }

    private void validateSmoothStopCardExists(Long id) {
        if (smoothStopCardMapper.selectById(id) == null) {
            throw exception(SMOOTH_STOP_CARD_NOT_EXISTS);
        }
    }

    @Override
    public SmoothStopCardDO getSmoothStopCard(Long id) {
        return smoothStopCardMapper.selectById(id);
    }

    @Override
    public PageResult<SmoothStopCardDO> getSmoothStopCardPage(SmoothStopCardPageReqVO pageReqVO) {
        return smoothStopCardMapper.selectPage(pageReqVO);
    }

    @Override
    public VerifyOrderFreeRespVO verifyOrderFreeBySmoothCard(VerifyOrderFreeReqVO reqVO) {

        //TODO cs
        Long userId2=getLoginUserId();
        LoginUser loginUser=getLoginUser();
        String username= getLoginUserNickname();
        log.info("用户{},用户信息{},用户名字{}", userId2,loginUser,username);
        // 1. 参数校验
        if (reqVO == null ||  reqVO.getCarNumber() == null
                || reqVO.getParkLotId() == null) {
            throw exception(new ErrorCode(500,"验证畅停卡免费失败：请求参数车牌号或停车场ID缺失"));
        }

        Long userId = getLoginUserId();
        String carNumber = reqVO.getCarNumber();
        Long parkingLotId = reqVO.getParkLotId();
        LocalDateTime currentTime = LocalDateTime.now();

        VerifyOrderFreeRespVO respVO = new VerifyOrderFreeRespVO();

        // 2. 查询用户所有未删除且有效时间的畅停卡
        List<SmoothStopCardDO> cardList = smoothStopCardMapper.selectList(
                new LambdaQueryWrapper<SmoothStopCardDO>()
                        .eq(SmoothStopCardDO::getHolderId, userId)
                        .eq(SmoothStopCardDO::getDeleted, 0)  // 未删除
                        .le(SmoothStopCardDO::getEffectiveTime, currentTime)  // 生效时间 <= 当前时间
                        .ge(SmoothStopCardDO::getExpireTime, currentTime)     // 过期时间 >= 当前时间
        );

        if (CollectionUtils.isEmpty(cardList)) {
            log.debug("用户{}没有生效的畅停卡", userId);
            respVO.setVerify(false);
            respVO.setVerifyReason("用户没有生效的畅停卡");
            return respVO;
        }

        // 3. 遍历畅停卡，判断是否满足条件
        for (SmoothStopCardDO card : cardList) {
            try {
                // 3.1 检查车牌号是否符合
                if (!isCarNumberBound(card, carNumber)) {
                    continue;
                }

                // 3.2 检查是否适用于该车场
                if (!isParkingLotApplicable(card, parkingLotId)) {
                    continue;
                }

                // 3.3 检查有效时间（已在查询条件中处理，这里可做二次验证）
                if (!isCardValid(card, currentTime)) {
                    continue;
                }

                // 找到符合条件的畅停卡
                log.info("用户{}的车牌{}在车场{}可使用畅停卡{}免费",
                        userId, carNumber, parkingLotId, card.getId());
                respVO.setVerify(true);
                respVO.setVerifyReason("验证通过");
                return respVO;

            } catch (Exception e) {
                log.error("验证畅停卡异常，cardId: {}", card.getId(), e);
                continue; // 单张卡异常不影响其他卡验证
            }
        }

        // 4. 没有找到符合条件的畅停卡
        log.debug("用户{}车牌{}在车场{}没有符合条件的生效畅停卡",
                userId, carNumber, parkingLotId);
        respVO.setVerify(false);
        respVO.setVerifyReason("没有符合条件的生效畅停卡");
        return respVO;
    }

    /**
     * 检查车牌号是否绑定了畅停卡
     */
    private boolean isCarNumberBound(SmoothStopCardDO card, String targetCarNumber) {
        // 获取已绑定的车牌号列表
        String boundCars = card.getBoundCarNumbers();
        if (StringUtils.isBlank(boundCars)) {
            return false; // 未绑定任何车牌
        }

        // 处理车牌号（去除空格，统一格式）
        String normalizedTarget = normalizeCarNumber(targetCarNumber);

        // 拆分绑定的车牌号列表
        String[] boundCarArray = boundCars.split(",");
        for (String boundCar : boundCarArray) {
            if (normalizeCarNumber(boundCar).equals(normalizedTarget)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 规范化车牌号（统一格式，便于比较）
     */
    private String normalizeCarNumber(String carNumber) {
        if (StringUtils.isBlank(carNumber)) {
            return "";
        }
        // 去除所有空格、转换成大写
        return carNumber.replaceAll("\\s+", "").toUpperCase();
    }

    /**
     * 检查畅停卡是否适用于指定车场
     */
    private boolean isParkingLotApplicable(SmoothStopCardDO card, Long targetParkingLotId) {
        // 获取适用的车场ID列表
        String applicableLots = card.getApplyLotIds();
        if (StringUtils.isBlank(applicableLots)) {
            return false; // 未配置适用车场
        }

        // 支持通配符或特殊值表示所有车场
        if (applicableLots.equals("*") || applicableLots.equals("ALL")) {
            return true;
        }

        // 拆分适用车场ID列表
        String[] lotIdArray = applicableLots.split(",");
        for (String lotIdStr : lotIdArray) {
            try {
                Long lotId = Long.parseLong(lotIdStr.trim());
                if (lotId.equals(targetParkingLotId)) {
                    return true;
                }
            } catch (NumberFormatException e) {
                log.warn("畅停卡{}的适用车场ID格式错误: {}", card.getId(), lotIdStr);
                continue;
            }
        }

        return false;
    }

    /**
     * 检查畅停卡在指定时间是否有效
     */
    private boolean isCardValid(SmoothStopCardDO card, LocalDateTime checkTime) {
        // 检查生效时间
        if (card.getEffectiveTime() != null && checkTime.isBefore(card.getEffectiveTime())) {
            return false;
        }

        // 检查过期时间
        if (card.getExpireTime() != null && checkTime.isAfter(card.getExpireTime())) {
            return false;
        }

        return true;
    }

}
