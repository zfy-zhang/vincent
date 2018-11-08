package com.vincent.personal.controller.admin;

import com.github.pagehelper.PageInfo;
import com.vincent.personal.controller.AbstractController;
import com.vincent.personal.controller.helper.ExceptionHelper;
import com.vincent.personal.dto.LogActions;
import com.vincent.personal.dto.Types;
import com.vincent.personal.exception.TipException;
import com.vincent.personal.modal.bo.RestResponseBo;
import com.vincent.personal.modal.vo.ContentVo;
import com.vincent.personal.modal.vo.ContentVoExample;
import com.vincent.personal.modal.vo.MetaVo;
import com.vincent.personal.modal.vo.UserVo;
import com.vincent.personal.service.IContentService;
import com.vincent.personal.service.ILogService;
import com.vincent.personal.service.IMetaService;
import com.vincent.personal.util.Commons;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IDEA
 * author:vincent
 * Date:2018/11/8
 */
@Controller
@RequestMapping("/admin/article")
@Transactional(rollbackFor = TipException.class)
public class ArticleController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Resource
    private IContentService contentService;

    @Resource
    private IMetaService metaService;

    @Resource
    private ILogService logService;

    /**
     * 文章列表页面
     *
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @GetMapping(value = "")
    public String index(@RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "limit", defaultValue = "15") int limit,
                        HttpServletRequest request) {

        ContentVoExample contentVoExample = new ContentVoExample();
        contentVoExample.setOrderByClause("created desc");
        contentVoExample.createCriteria().andTypeEqualTo(Types.ARTICLE.getType());
        PageInfo<ContentVo> contentVoPageInfo = contentService.getArticlesWithpage(contentVoExample, page, limit);
        request.setAttribute("articles", contentVoPageInfo);
        return "admin/article_list";
    }

    /**
     * 文章发表页面
     *
     * @param request
     * @return
     */
    @GetMapping(value = "publish")
    public String newArticle(HttpServletRequest request) {
        List<MetaVo> metaVos = metaService.getMetas(Types.CATEGORY.getType());
        request.setAttribute("categories", metaVos);
        request.setAttribute(Types.ATTACH_URL.getType(), Commons.site_option(Types.ATTACH_URL.getType()));
        return "admin/article_edit";
    }


    /**
     * 文章编辑页面
     *
     * @param cid
     * @param request
     * @return
     */
    @GetMapping(value = "/{cid}")
    public String editArticle(@PathVariable String cid, HttpServletRequest request) {

        ContentVo contents = contentService.getContents(cid);
        request.setAttribute("contents", contents);
        List<MetaVo> metaVoList = metaService.getMetas(Types.CATEGORY.getType());
        request.setAttribute("categories", metaVoList);
        request.setAttribute(Types.ATTACH_URL.getType(), Commons.site_option(Types.ATTACH_URL.getType()));
        request.setAttribute("active", "article");
        return "admin/article_edit";
    }


    /**
     * 文章发表 post
     *
     * @param contentVo
     * @param request
     * @return
     */
    @PostMapping(value = "/publish")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo publishArticle(ContentVo contentVo, HttpServletRequest request) {

        UserVo userVo = this.user(request);
        contentVo.setAuthorId(userVo.getUid());
        contentVo.setType(Types.ARTICLE.getType());
        if (StringUtils.isNotBlank(contentVo.getCategories())) {
            contentVo.setCategories("默认分类");
        }

        try {
            contentService.publish(contentVo);
        }catch (Exception e) {
            String msg = "文章发布失败!";
            return ExceptionHelper.handlerException(logger, msg, e);
        }
        return RestResponseBo.ok();

    }

    /**
     * 文章更新 post
     *
     * @param contentVo
     * @param request
     * @return
     */
    @PostMapping(value = "/modify")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo modifyArticle(ContentVo contentVo,HttpServletRequest request) {

        UserVo users = this.user(request);
        contentVo.setAuthorId(users.getUid());
        contentVo.setType(Types.ARTICLE.getType());
        try {
            contentService.updateArticle(contentVo);
        } catch (Exception e) {
            String msg = "文章编辑失败";
            return ExceptionHelper.handlerException(logger, msg, e);
        }
        return RestResponseBo.ok();

    }


    /**
     * 删除文章 post
     *
     * @param cid
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo delete(@RequestParam int cid, HttpServletRequest request) {
        try {
            contentService.deleteByCid(cid);
            logService.insertLog(LogActions.DEL_ARTICLE.getAction(), cid + "", request.getRemoteAddr(), this.getUid(request));
        } catch (Exception e) {
            String msg = "文章删除失败";
            return ExceptionHelper.handlerException(logger, msg, e);
        }
        return RestResponseBo.ok();
    }
}
