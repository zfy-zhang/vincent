package com.vincent.personal.controller.admin;

import com.vincent.personal.constant.WebConst;
import com.vincent.personal.controller.AbstractController;
import com.vincent.personal.controller.helper.ExceptionHelper;
import com.vincent.personal.dto.MetaDto;
import com.vincent.personal.dto.Types;
import com.vincent.personal.exception.TipException;
import com.vincent.personal.modal.bo.RestResponseBo;
import com.vincent.personal.service.IMetaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * 分类标签管理
 *
 * Created with IDEA
 * author:vincent
 * Date:2018/11/14
 */
@Controller
@RequestMapping("admin/category")
public class CategoryController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Resource
    private IMetaService metaService;

    @GetMapping(value = "")
    public String index(HttpServletRequest request) {
        List<MetaDto> categories = metaService.getMetaList(Types.CATEGORY.getType(), null, WebConst.MAX_POSTS);
        List<MetaDto> tags = metaService.getMetaList(Types.TAG.getType(), null, WebConst.MAX_POSTS);
        request.setAttribute("categories", categories);
        request.setAttribute("tags", tags);
        return "admin/category";
    }

    @PostMapping(value = "save")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo saveCategory(@RequestParam String cname, @RequestParam Integer mid) {
        try {
            metaService.saveMeta(Types.CATEGORY.getType(), cname, mid);
        } catch (Exception e) {
            String msg = "分类保存失败";
            return ExceptionHelper.handlerException(logger, msg, e);
        }
        return RestResponseBo.ok();
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo delete(@RequestParam int mid) {
        try {
            metaService.delete(mid);
        } catch (Exception e) {
            String msg = "删除失败";
            return ExceptionHelper.handlerException(logger, msg, e);
        }
        return RestResponseBo.ok();
    }

}
