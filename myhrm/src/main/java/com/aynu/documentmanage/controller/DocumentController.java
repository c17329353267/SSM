package com.aynu.documentmanage.controller;

import com.aynu.documentmanage.service.DocumentService;
import com.aynu.entity.Document;
import com.aynu.entity.User;
import com.aynu.pages.PageModel;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class DocumentController {

    @Autowired
    private DocumentService documentService;
    //文件查找
    @RequestMapping("/findAllDocuments.do")
    public String findAllDocuments(@RequestParam(defaultValue = "1")Integer pageIndex, Document document,
                                   Model model){
        //查询数据总记录条数
        int counts = documentService.findAllDocumentCounts(document);
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        pageModel.setPageSize(2);
        pageModel.setRecordCount(counts);
        List<Document> documents = documentService.findAllDocuments(document,pageModel);
        model.addAttribute("documents",documents);
        model.addAttribute("document",document);
        model.addAttribute("pageModel",pageModel);
        return "/jsp/document/document.jsp";
    }
    //文档上传
    @RequestMapping("/addDocument.do")
    public String addDocument(Document document,HttpSession session,Model model) throws IOException {
        //文件上传的位置
        String path = "E:\\uploads";
        File upload = new File(path);
        //判断位置是否存在，不存在即创建
        if(!upload.exists()){
            upload.mkdirs();
        }
        //获取上传文件的传统文件名,防止上传重复的文件名造成覆盖问题
        String fileName =System.currentTimeMillis()+ document.getFile().getOriginalFilename();
        //获取登陆人的姓名
         User loginUser = (User) session.getAttribute("loginUser");
         //属性存到数据库，文档存到对应的文件,路径+文件名
         document.getFile().transferTo(new File(path,fileName));
         document.setFilename(fileName);
         document.setUser(loginUser);
        //System.out.println("loginUser"+loginUser);
        int rows = documentService.addDocument(document);
        if(rows > 0){
            //上传成功
            return "findAllDocuments.do";
        }else{
            model.addAttribute("fail","文件上传失败");
            return "/jsp/fail.jsp";
        }

    }
    //@Transactional
    //文件删除
    /*
        存在的问题，当数据库中存在数据而不存在的文件删除会出现问题
     */
    @RequestMapping("/removeDocument.do")
    public String removeDocument(Integer[] ids,Model model){
        int num=1;
        //System.out.println("进入了文件删除");
        String path = "E:\\uploads\\";
        List<String> filenames = documentService.findDocumentsByIds(ids);
        //根据id删除数据库中数据
        int rows = documentService.removeDocumentsByIds(ids);
        //根据id查找文件名(filename)并删除,
        int count = 0;
        for (String filename:filenames
             ) {
            System.out.println(filename + " ");
            path = path + filename;
            File delFile = new File(path);
            if (delFile.isFile() && delFile.exists() && rows >0) {
                delFile.delete();
                count++;
            }
            //初始化path
            path = "E:\\uploads\\";
        }
        //如果删除的文件数和filename数相同，则删除完成，即跳转
        if(count == filenames.size()){
            return "findAllDocuments.do";
        }else {
                //回滚事务
                //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                model.addAttribute("fail", "文件删除失败");
            return "/jsp/fail.jsp";
        }
    }
    //文档修改
    @RequestMapping("/modifyDocument.do")
    public String modifyDocument(Document document,Model model,Integer flag,HttpSession session) throws IOException {

       //flag==1进行查找
        if(flag == 1){
            //根据id进行查找并回显数据//此时文件未查找也未回显
            Document returnDocument = documentService.findDocumentsById(document.getId());
            model.addAttribute("document",returnDocument);
            //返回到修改界面
            return "/jsp/document/showUpdateDocument.jsp";
        }else{
            //先删除指定路径下的文档，修改数据库数据，这里判断文件是否重新选择
            //怎么判断文件是否重新选择：方式原始文件名或者读取文件类容，当原始文件名不为空且与原始文件名不一致时，重新上传的文件
            //根据ducument_id来获取当前数据中的原始文件名和  当前传入的file名字是否相一至
            //当前数据库
            //System.out.println("修改"+document);
            if(!document.getFile().isEmpty()){//上传有文件
                String path = "E:\\uploads\\";
                //根据当前上传的文件获取id，去数据库中查找源文件
                Document target = documentService.findDocumentsById(document.getId());
                //System.out.println("target源文件"+target);
                File targetfile = new File(path,target.getFilename());
                //判断该路径下是否存在该文件，存在即删除
                if(targetfile.exists()){
                    targetfile.delete();
                }
                //上传当前文件
                //获取文件名字
                String filename = System.currentTimeMillis()+document.getFile().getOriginalFilename();
                File file = new File(path,filename);
                //上传文件
                document.setFilename(filename);
                document.getFile().transferTo(file);

            }
            //更改数据库中的数据
            User loginUser = (User) session.getAttribute("loginUser");
            document.setUser(loginUser);
            //只需要修改 标题或者描述
            int row = documentService.modifyDocument(document);
            if(row > 0) {
                //修改成功后返回到查找界面
                return "findAllDocuments.do";
            }else{
                model.addAttribute("fail","修改失败小猪佩奇");
                return "/jsp/fail.jsp";

        }
        }
    }
    //文档下载
    @RequestMapping("/downloadDocuments.do")
    public ResponseEntity<byte[]> downloadDocument(Integer id, HttpServletRequest request) throws IOException {
        //根据传入的id查找目标文件
        Document target = documentService.findDocumentsById(id);
        //获取目标文件名
        String filename = target.getFilename();
        //下载路径
        String path = "E:\\uploads\\";
        File file = new File(path,filename);
        //创建响应头
        HttpHeaders headers = new HttpHeaders();
        //通知浏览器以下载的方式打开文件
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //处理文件在不同浏览器下载出现的中文乱码问题
        filename = processFileName(request,filename);
        headers.setContentDispositionFormData("attachment",filename);

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.OK);
    }
    //IE、chrom、Firefox文件中文乱码问题
    public String processFileName(HttpServletRequest request, String fileNames) {
        String codedfilename = null;
        try {
            String agent = request.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie

                String name = java.net.URLEncoder.encode(fileNames, "UTF8");

                codedfilename = name;
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等


                codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codedfilename;
    }
}
