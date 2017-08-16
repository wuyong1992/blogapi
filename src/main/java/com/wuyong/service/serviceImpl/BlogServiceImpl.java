package com.wuyong.service.serviceImpl;


import com.google.common.collect.Lists;
import com.wuyong.common.ServerResponse;
import com.wuyong.pojo.Blog;
import com.wuyong.pojo.Category;
import com.wuyong.pojo.SearchParams;
import com.wuyong.pojo.User;
import com.wuyong.repository.BlogRepository;
import com.wuyong.repository.CategoryRepository;
import com.wuyong.repository.UserRepository;
import com.wuyong.service.IBlogService;
import com.wuyong.service.IFileService;
import com.wuyong.util.FTPUtil;
import com.wuyong.vo.BlogVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 坚果
 * on 2017/7/22
 */
@Transactional
@Service("iBlogService")
public class BlogServiceImpl implements IBlogService {

    private static final Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);

    @Autowired
    private IFileService iFileService;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * 文件上传
     *
     * @param file 文件
     * @param path 路径
     * @return
     */
    public String richTextImgUpload(MultipartFile file, String path) {
        return iFileService.upload(file, path);
    }

    /**
     * 保存博客
     *
     * @param blog 博客
     * @return
     */
    public ServerResponse blogSave(Blog blog) {
        //User currentUser = (User) session.getAttribute("currentUser");
        /*logger.info("currentUser====>"+currentUser);
        if (currentUser == null || currentUser.getUsername() == null) {
            return ServerResponse.createByErrorMessage("请先登录");
        }
        if (blog == null || blog.getAuthorId() == null){
            return ServerResponse.createByErrorMessage("没有编写博客或者没有登录");
        }
        if (!StringUtils.equals(blog.getAuthorId()+"",currentUser.getId()+"")) {
            return ServerResponse.createByErrorMessage("该博客与当前用户不符合");
        }*/
        //保存到数据库
        Blog blogSaved = blogRepository.save(blog);
        if (blogSaved.getId() == null) {
            return ServerResponse.createByErrorMessage("添加博客失败");
        }
        return ServerResponse.createBySuccess("添加成功", blogSaved);
    }


    //    blog更新
    public ServerResponse blogUpdate(Blog blog) {
        Blog blogFind = blogRepository.findOne(blog.getId());
        if (StringUtils.equals(blog.getContent(), "")) {
            blog.setContent(blogFind.getContent());
        }
        if (StringUtils.equals(blog.getImgUrl(), "") || blog.getImgUrl() == null) {
            blog.setImgUrl(blogFind.getImgUrl());
        }
        Blog newBlow = blogRepository.save(blog);

        return ServerResponse.createBySuccess(newBlow);
    }


    /**
     * 获取所有blog信息
     *
     * @return
     */
    public ServerResponse getAllBlogs() {
        List<Blog> blogList = blogRepository.findAll(new Sort(Sort.Direction.DESC, "id"));

        List<BlogVo> blogVoList = new ArrayList<BlogVo>();
        BlogVo blogVo = null;
        for (Blog blog : blogList) {
            User user = userRepository.findOne(blog.getAuthorId());
            blogVo = new BlogVo();
            blogVo.setAuthorName(user.getUsername());
            blogVo.setAuthorId(blog.getAuthorId());
            blogVo.setContent(blog.getContent());   //列表展示可以不用传输具体内容
            blogVo.setId(blog.getId());
            blogVo.setImgUrl(blog.getImgUrl());
            blogVo.setIntro(blog.getIntro());
            blogVo.setTitle(blog.getTitle());
            blogVo.setCreateTime(blog.getCreateTime());
            blogVo.setUpdateTime(blog.getUpdateTime());
            if (blog.getCategoryId() != null) {
                Category category = categoryRepository.findOne(blog.getCategoryId());
                blogVo.setCategoryName(category.getName());
            }
            blogVoList.add(blogVo);
        }

        if (blogVoList.size() > 0) {
            return ServerResponse.createBySuccess(blogVoList);
        }
        return ServerResponse.createByErrorMessage("获取失败");
    }

    //获取单个blog
    public ServerResponse getBlogById(Integer id) {
        if (id < 1) {
            return ServerResponse.createByErrorMessage("id不能是负数！");
        }
        Blog blog = blogRepository.findOne(id);
        if (blog == null) {
            return ServerResponse.createByErrorMessage("没有该id对应的blog");
        }
        BlogVo blogVo = new BlogVo();
        User user = userRepository.findOne(blog.getAuthorId());
        blogVo.setTitle(blog.getTitle());
        blogVo.setAuthorName(user.getUsername());
        blogVo.setAuthorId(blog.getAuthorId());
        blogVo.setContent(blog.getContent());
        blogVo.setId(blog.getId());
        blogVo.setImgUrl(blog.getImgUrl());
        blogVo.setIntro(blog.getIntro());
        blogVo.setTitle(blog.getTitle());
        blogVo.setCreateTime(blog.getCreateTime());
        blogVo.setUpdateTime(blog.getUpdateTime());
        return ServerResponse.createBySuccess(blogVo);
    }

    //    根据搜索条件返回对应的blogs
    public ServerResponse searchBlogs(SearchParams searchParams) {
        String title = StringUtils.trim(searchParams.getSearchTitle());
        Integer categoryId = searchParams.getSearchCategoryId();
        List<Blog> searchBlogs = Lists.newArrayList();
        if (StringUtils.isNotBlank(title) && categoryId != null) {
            //没传入title搜索条件
            searchBlogs = blogRepository.findBlogsByTitleContainingAndCategoryId(title,categoryId);
        }
        if (StringUtils.isNotBlank(title) && categoryId == null) {
            searchBlogs = blogRepository.findBlogsByTitleContaining(title);
        }
        if (StringUtils.isBlank(title) && categoryId != null) {
            searchBlogs = blogRepository.findBlogsByCategoryId(categoryId);
        }
        if (searchBlogs.size() > 0) {
            return ServerResponse.createBySuccess(searchBlogs);
        }
        return ServerResponse.createByErrorMessage("没有找到对应的blog");
    }
}
