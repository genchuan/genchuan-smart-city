package cn.iocoder.yudao.module.studentmgmt.service.communicatemgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.communicatemgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.communicatemgmt.CommunicateMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.communicatemgmt.CommunicateMgmtMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.CommunicateMgmtStatusEnum;
import com.alibaba.fastjson.JSONObject;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.COMMUNICATE_MGMT_NOT_EXISTS;

/**
 * 沟通管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CommunicateMgmtServiceImpl implements CommunicateMgmtService {

    @Resource
    private CommunicateMgmtMapper communicateMgmtMapper;

    @Override
    public Long createCommunicateMgmt(CommunicateMgmtSaveReqVO createReqVO) {
        // 插入
        CommunicateMgmtDO communicateMgmt = BeanUtils.toBean(createReqVO, CommunicateMgmtDO.class);
        communicateMgmtMapper.insert(communicateMgmt);

        // 返回
        return communicateMgmt.getId();
    }

    @Override
    public void updateCommunicateMgmt(CommunicateMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validateCommunicateMgmtExists(updateReqVO.getId());
        // 更新
        CommunicateMgmtDO updateObj = BeanUtils.toBean(updateReqVO, CommunicateMgmtDO.class);
        communicateMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteCommunicateMgmt(Long id) {
        // 校验存在
        validateCommunicateMgmtExists(id);
        // 删除
        communicateMgmtMapper.deleteById(id);
    }

    @Override
    public void deleteCommunicateMgmtListByIds(List<Long> ids) {
        // 删除
        communicateMgmtMapper.deleteByIds(ids);
    }


    private CommunicateMgmtDO validateCommunicateMgmtExists(Long id) {
        CommunicateMgmtDO communicateMgmtDO = communicateMgmtMapper.selectById(id);
        if (communicateMgmtDO == null) {
            throw exception(COMMUNICATE_MGMT_NOT_EXISTS);
        }
        return communicateMgmtDO;
    }

    @Override
    public CommunicateMgmtDO getCommunicateMgmt(Long id) {
        return communicateMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<CommunicateMgmtDO> getCommunicateMgmtPage(CommunicateMgmtPageReqVO pageReqVO) {
        return communicateMgmtMapper.selectPage(pageReqVO);
    }

    @Override
    public Boolean publish(CommunicateMgmtPublishReqVO reqVO) {
        int total = 0;
        LocalDateTime sendTime = reqVO.getSendTime();
        if (sendTime == null) {
            sendTime = LocalDateTime.now();
        }
        for (Long id : reqVO.getIds()) {
            // 校验存在
            CommunicateMgmtDO communicateMgmtDO = validateCommunicateMgmtExists(id);
            // 将状态置为已发布，记录发布时间，
            communicateMgmtDO.setStatus(CommunicateMgmtStatusEnum.published.getStatus());
            communicateMgmtDO.setSendTime(sendTime);
            // 获取当前用户
            String username = SecurityFrameworkUtils.getLoginUserNickname();
            communicateMgmtDO.setSendUser(username);
            // TODO 自动统计后续互动率
            communicateMgmtDO.setInteractRate(new BigDecimal(50));

            // 更新
            int i = communicateMgmtMapper.updateById(communicateMgmtDO);
            total += i;

        }
        return total > 0;
    }

    @Override
    public Boolean feedback(CommunicateMgmtFeedbackReqVO reqVO) {
        int total = 0;
        LocalDateTime replyTime = reqVO.getReplyTime();
        if (replyTime == null) {
            replyTime = LocalDateTime.now();
        }
        // 获取当前用户
//        String username = SecurityFrameworkUtils.getLoginUserNickname();
        for (Long id : reqVO.getIds()) {
            // 校验存在
            CommunicateMgmtDO communicateMgmtDO = validateCommunicateMgmtExists(id);

            CommunicateMgmtDO bean = BeanUtils.toBean(communicateMgmtDO, CommunicateMgmtDO.class);
            // 提交家长对沟通消息的反馈，记录反馈内容及时间，
            bean.setReplyTime(replyTime);
            bean.setReplyContent(reqVO.getReplyContent());
            bean.setId(null);
//            communicateMgmtDO.setSendUser(username);
            // TODO 自动更新互动率
            bean.setInteractRate(communicateMgmtDO.getInteractRate().add(new BigDecimal(1)));

            // 更新
            int i = communicateMgmtMapper.insert(bean);
            total += i;

        }
        return total > 0;
    }

    @Override
    public Boolean reply(CommunicateMgmtReplyReqVO reqVO) {

        LocalDateTime replyTime = reqVO.getReplyTime();
        if (replyTime == null) {
            replyTime = LocalDateTime.now();
        }

        // 校验存在
        CommunicateMgmtDO communicateMgmtDO = validateCommunicateMgmtExists(reqVO.getId());
        CommunicateMgmtDO bean = BeanUtils.toBean(communicateMgmtDO, CommunicateMgmtDO.class);
        // 提交家长对沟通消息的反馈，记录反馈内容及时间，
        bean.setReplyTime(replyTime);
        bean.setReplyContent(reqVO.getReplyContent());
        bean.setId(null);
        // 获取当前用户
//        String username = SecurityFrameworkUtils.getLoginUserNickname();
        // TODO 自动更新互动率
        bean.setInteractRate(communicateMgmtDO.getInteractRate().add(new BigDecimal(1)));

        // 更新
        int i = communicateMgmtMapper.insert(bean);

        return i > 0;
    }

    @Override
    public CommunicateMgmtChartRespVO chart(CommunicateMgmtChartReqVO reqVO) {
        CommunicateMgmtChartRespVO vo = new CommunicateMgmtChartRespVO();

        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }

        // 1. 卡片数据
        //totalCount (integer): 本期考评总记录数。
        vo = communicateMgmtMapper.selectTotalCount(startTime, endTime, CommunicateMgmtStatusEnum.published.getStatus());

        // 2. 趋势数据
        List<JSONObject> totalList = communicateMgmtMapper.selectRecentWeekInteractTrend(startTime, endTime);
        // 循环打印出从startTime到endTime的每一天的记录数
        List dailyTrendList = new ArrayList();
        for (LocalDateTime date = startTime; date.isBefore(endTime); date = date.plusDays(1)) {
            System.out.println(date + ": " + date);

            JSONObject jsonObject = new JSONObject();
            // 从totalList 中查找 date
            for (JSONObject item : totalList) {
                String applyTime = item.getString("date");
                if (applyTime.equals(date)) {
                    jsonObject.put("date", date);
                    jsonObject.put("count", item.getInteger("count"));
                    dailyTrendList.add(jsonObject);
                    break;
                }
            }
        }
        vo.setRecentWeekInteractTrend(dailyTrendList);

        return vo;
    }

    @Override
    public CommunicateInteractIndexRespVO interactIndex(CommunicateMgmtChartReqVO reqVO) {
        CommunicateInteractIndexRespVO vo = new CommunicateInteractIndexRespVO();

        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }

        // 1. 卡片数据
        // TODO
        List<JSONObject> msgTypeCountList = communicateMgmtMapper.selectMsgTypeCount(startTime, endTime);

        for (JSONObject item : msgTypeCountList) {

        }
        vo.setMsgTypeCount(msgTypeCountList);
        vo.setClassInteractRate(communicateMgmtMapper.selectClassInteractRate(startTime, endTime));
        vo.setReplyTimeDistribution(communicateMgmtMapper.selectReplyTimeDistribution(startTime, endTime));
        return vo;
    }

}