package cn.iocoder.yudao.module.envirhealth.service.publictoilet.toiletcomplaint;

import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcomplaint.ToiletComplaintPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcomplaint.ToiletComplaintSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail.ToiletComplaintDetailDO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletComplaintDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet.ToiletComplaintMapper;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 公厕投诉 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ToiletComplaintServiceImpl implements ToiletComplaintService {

    @Resource
    private ToiletComplaintMapper toiletComplaintMapper;

    @Override
    public Long createToiletComplaint(ToiletComplaintSaveReqVO createReqVO) {
        // 插入
        ToiletComplaintDO toiletComplaint = BeanUtils.toBean(createReqVO, ToiletComplaintDO.class);
        toiletComplaintMapper.insert(toiletComplaint);
        // 返回
        return toiletComplaint.getId();
    }

    @Override
    public void updateToiletComplaint(ToiletComplaintSaveReqVO updateReqVO) {
        // 校验存在
        validateToiletComplaintExists(updateReqVO.getId());
        // 更新
        ToiletComplaintDO updateObj = BeanUtils.toBean(updateReqVO, ToiletComplaintDO.class);
        toiletComplaintMapper.updateById(updateObj);
    }

    @Override
    public void deleteToiletComplaint(Long id) {
        // 校验存在
        validateToiletComplaintExists(id);
        // 删除
        toiletComplaintMapper.deleteById(id);
    }

    private void validateToiletComplaintExists(Long id) {
        if (toiletComplaintMapper.selectById(id) == null) {
            throw exception(TOILET_COMPLAINT_NOT_EXISTS);
        }
    }

    @Override
    public ToiletComplaintDO getToiletComplaint(Long id) {
        return toiletComplaintMapper.selectById(id);
    }

    @Override
    public PageResult<ToiletComplaintDO> getToiletComplaintPage(ToiletComplaintPageReqVO pageReqVO) {
        return toiletComplaintMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<ToiletComplaintDetailDO> getToiletComplaintDetailPage(ToiletComplaintPageReqVO pageReqVO) {
        Long total = toiletComplaintMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<ToiletComplaintDetailDO> list = toiletComplaintMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }
}