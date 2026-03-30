package cn.iocoder.yudao.module.envirhealth.service.publictoilet.toiletcomplaint;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcomplaint.ToiletComplaintBatchHandleReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcomplaint.ToiletComplaintPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcomplaint.ToiletComplaintPendingRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcomplaint.ToiletComplaintSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletComplaintDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletComplaintDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet.ToiletComplaintMapper;
import cn.iocoder.yudao.module.envirhealth.framework.file.FileClient;
import cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.publictoilet.ToiletComplaintCodeGenerator;
import cn.iocoder.yudao.module.envirhealth.framework.util.convert.UrlConvert;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.TOILET_COMPLAINT_NOT_EXISTS;

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

    @Resource
    private ToiletComplaintCodeGenerator codeGenerator;

    @Resource
    private FileClient fileClient;

    @Resource
    private UrlConvert urlConvertUtil;

    @Override
    public Long createToiletComplaint(ToiletComplaintSaveReqVO createReqVO) {
        // 插入
        ToiletComplaintDO toiletComplaint = BeanUtils.toBean(createReqVO, ToiletComplaintDO.class);

        toiletComplaint.setComplaintId(codeGenerator.generateComplaintId());

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

    @Override
    public void deleteToiletComplaintBatch(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }

        // 校验所有计划是否存在
        List<ToiletComplaintDO> toiletComplaints = toiletComplaintMapper.selectBatchIds(ids);
        if (toiletComplaints.size() != ids.size()) {
            throw exception(TOILET_COMPLAINT_NOT_EXISTS);
        }

        // 批量删除
        toiletComplaintMapper.deleteBatchIds(ids);
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

    @Override
    public List<String> uploadPhotos(Long id, List<MultipartFile> files)   {
        // 校验投诉存在
        validateToiletComplaintExists(id);

        List<String> photoUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            String url = fileClient.uploadFile(file);
            // 转换为公网地址
            String publicUrl = urlConvertUtil.convertToPublicUrl(url);
            photoUrls.add(publicUrl);
        }

        // 获取当前投诉记录
        ToiletComplaintDO complaint = toiletComplaintMapper.selectById(id);

        // 解析现有的图片列表
        List<String> photoList = parsePhotoList(complaint.getReformPhoto());

        // 添加新图片
        photoList.addAll(photoUrls);

        // 更新数据库
        complaint.setReformPhoto(JSON.toJSONString(photoList));
        toiletComplaintMapper.updateById(complaint);

        return photoUrls;
    }

    /**
     * 获取图片列表
     */
    public List<String> getPhotos(Long id) {
        validateToiletComplaintExists(id);
        ToiletComplaintDO complaint = toiletComplaintMapper.selectById(id);
        return parsePhotoList(complaint.getReformPhoto());
    }

    /**
     * 删除图片
     */
    public void deletePhoto(Long id, String photoUrl) {
        validateToiletComplaintExists(id);

        ToiletComplaintDO complaint = toiletComplaintMapper.selectById(id);
        List<String> photoList = parsePhotoList(complaint.getReformPhoto());

        if (photoList.remove(photoUrl)) {
            complaint.setReformPhoto(JSON.toJSONString(photoList));
            toiletComplaintMapper.updateById(complaint);
        }
    }

    /**
     * 解析图片列表
     */
    private List<String> parsePhotoList(String photoJson) {
        if (photoJson == null || photoJson.trim().isEmpty()) {
            return new ArrayList<>();
        }
        try {
            return JSON.parseArray(photoJson, String.class);
        } catch (Exception e) {
            // 如果是单个图片地址，转为列表
            List<String> list = new ArrayList<>();
            list.add(photoJson);
            return list;
        }
    }

    @Override
    public void batchUpdateDispatchStatus(ToiletComplaintBatchHandleReqVO reqVO) {

        if (reqVO == null || reqVO.getIds() == null || reqVO.getIds().isEmpty()) {
            return;
        }

        // 校验全部存在
        List<ToiletComplaintDO> list = toiletComplaintMapper.selectBatchIds(reqVO.getIds());
        if (list.size() != reqVO.getIds().size()) {
            throw exception(TOILET_COMPLAINT_NOT_EXISTS);
        }

        // 批量更新状态
        toiletComplaintMapper.update(null, new LambdaUpdateWrapper<ToiletComplaintDO>()
                .set(ToiletComplaintDO::getDispatchStatus, reqVO.getDispatchStatus())
                .in(ToiletComplaintDO::getId, reqVO.getIds()));
    }

    @Override
    public ToiletComplaintPendingRespVO getPending() {
        ToiletComplaintPendingRespVO resp = new ToiletComplaintPendingRespVO();
        resp.setPendingTotal(toiletComplaintMapper.countPendingTotal());
        resp.setTypeCount(toiletComplaintMapper.countPendingTypeDistinct());
        resp.setTimeoutUnHandledCount(toiletComplaintMapper.countTimeoutUnHandled());
        resp.setTypeDistribution(toiletComplaintMapper.selectComplaintTypePie());
        resp.setAreaDistribution(toiletComplaintMapper.selectAreaPie());
        resp.setComplaintCountByArea(toiletComplaintMapper.selectAreaBar());
        return resp;
    }
}