package com.bookStore.admin.productmanage.controller;

import com.bookStore.admin.productmanage.service.ProductManageService;
import com.bookStore.entity.Product;
import com.bookStore.entity.ProductList;
import com.bookStore.utils.IdUtils;
import com.bookStore.utils.PageModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/admin/products/")
public class ProductManageController{
    @Autowired
    private ProductManageService productManageService;

    @RequestMapping("findProducts.do")
    public String findProducts(@RequestParam(defaultValue = "1")int pageIndex, Model model,Product product,Double minprice,Double maxprice){
        //分页显示 查询数据条数
       // System.out.println("product="+product+",minprice="+minprice+",maxprice"+maxprice);
        //product.setPrice();
        int recordCount = productManageService.findAllCounts(product);
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        //查询数据库中的所有数据
        pageModel.setRecordCount(recordCount);
        //查询所有
        List<Product> ps = productManageService.findAllProducts(product,pageModel,minprice,maxprice);
        //查询商品类别
        //List<Product> product = productManageService.findAllProdcutsCateGorry();
        model.addAttribute("product",product);
        model.addAttribute("ps",ps);
        model.addAttribute("pageModel",pageModel);
        model.addAttribute("minprice",minprice);
        model.addAttribute("maxprice",maxprice);
        return "/admin/products/list.jsp";
    }
    //商品添加
    @RequestMapping("addProducts.do")
    public String addProducts(Product product, MultipartFile upload, HttpSession session,Model model) throws IOException {
        //System.out.println("product="+product);
        //最为重要是把文件上传路径写到当前项目productImg文件夹路径下
        //服务器路径下productImg路径
        String path = session.getServletContext().getRealPath("/productImg");
       // System.out.println("path="+path);
        File file = new File(path);
        //如果此文件不存在则创建
        if(!file.exists()){
            file.mkdirs();
        }
        //防止文件名重复
        String filename = IdUtils.getUUID()+"-"+upload.getOriginalFilename();
        //文件路径和上传文件名之间加上分隔符
        String filepath = path+File.separator+filename;
        //文件上传
        upload.transferTo(new File(filepath));
        //设置商品id
        product.setId(IdUtils.getUUID());
        //设置商品存储路径
        product.setImgurl("/productImg/"+filename);
        //System.out.println("product="+product);
        //调用service实现商品添加
       int row = productManageService.addProducts(product);
       if(row > 0){
           return "findProducts.do";
       }else {
           model.addAttribute("error","添加失败！！");
           return "/admin/products/add.jsp";
       }
    }
    //编辑修改
    @RequestMapping("editAndModifyProduct.do")
    public String editAndModifyProduct(String id,String flag,Model model,Product product,MultipartFile upload,
                                       HttpSession session) throws IOException {
        //System.out.println("flag=" +flag);
        if(flag == null) {
            //根据id进行查找
             product = productManageService.findProductById(id);
            model.addAttribute("p", product);
            return "/admin/products/edit.jsp";
        }else{
            //进行修改
            //是否重新选择新的文件
            if(!upload.isEmpty()){//选择了新的图片路径
                //获取服务器路径下的图片存放路径
                String path = session.getServletContext().getRealPath("/productImg");
                //根据id来判断是哪一件商品进行修改
                Product targetProduct = productManageService.findProductById(id);
                File targetfile = new File(path+targetProduct.getImgurl());
                if(targetfile.exists()){
                    //删除原数据库中新的图片路径地址
                    targetfile.delete();
                }
                //设置新的图片路径
                String filename = IdUtils.getUUID()+"-"+upload.getOriginalFilename();
                //文件路径和上传文件名之间加上分隔符
                String filepath = path+File.separator+filename;
                //文件上传
                upload.transferTo(new File(filepath));
                //设置新的图片路径
                product.setImgurl("/productImg/"+filename);
            }
            //如果未选择新的图片路径
            productManageService.modifyProductById(product);
            return "findProducts.do";
        }
    }
    //删除
    @RequestMapping("deleteProduct.do")
    public String deleteProduc(String id,HttpSession session){
        //删除服务器路径下的图片
        //根据id查找被删除的商品属性
        Product targetProduct = productManageService.findProductById(id);
        File targetFile = new File(session.getServletContext().getRealPath("/")+targetProduct.getImgurl());
        if(targetFile.exists()){
            //如果该文件存在则删除
            targetFile.delete();
        }
        //int row = productManageService.removeProductById(id);

            //删除成功
            return "findProducts.do";
    }
    @RequestMapping("downloadListSales.do")
    public void downloadListSales(String year, String month, HttpServletResponse response)throws Exception {
        //根据年份和月份查看销售情况
        List<ProductList> plist = productManageService.findAllProductList(year,month);
        //excel文件名
        String fileName = year+"年"+month+"月销售榜单";
        //创建sheetName
        String sheetName = month+"月销售榜单";
        //标题名
        String titleName = year+"年"+month+"月销售榜单";
        //列名
        String[] cloumName = {"商品名称","商品销量"};
        //定义excel表格行列
        String[][] dataList = new String[plist.size()][2];
        for(int i=0;i<plist.size();i++){
            ProductList pl = plist.get(i);
            //第i行第一个单元格赋值第一个商品名称
            dataList[i][0] = pl.getProductName();
            //第i行第一个赋值一个商品销量
            dataList[i][1] = pl.getSalNum();
        }
        //创建工作表
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        //创建sheet第一行
        HSSFRow hssfRow0 = sheet.createRow(0);
        //创建第一个行的第一个 单元格
        HSSFCell hssfCell = hssfRow0.createCell(0);
        //合并单元格(标题合并单元格)
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,1));
        //合并的单元格放titile
        hssfCell.setCellValue(titleName);
        //第二行放列名属性值
        HSSFRow hssfRow1 = sheet.createRow(1);
        for(int i=0;i<cloumName.length;i++){
            HSSFCell cell2 = hssfRow1.createCell(i);
            cell2.setCellValue(cloumName[i]);
        }
        //创建数据行 从第三行开始，索引为2
        //外循环控制行，内循环控制列
        for(int j=0;j<dataList.length;j++){
            hssfRow1 = sheet.createRow(j+2);
            HSSFCell datacell = null;
            for(int k=0;k<2;k++){
                //创建一个单元格
                datacell = hssfRow1.createCell(k);
                //为单元格赋值
                datacell.setCellValue(dataList[j][k]);
            }
        }
        //String filename = fileName+".xls";
        //设置文件编码
        /*response.setContentType("application/ms-excel;charset=UTF-8");
        response.setHeader("content-Disposition","attachment;filename="+filename);*/

        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="
                + new String(fileName.getBytes(),"iso-8859-1") + ".xls");

        OutputStream out = response.getOutputStream();
        wb.write(out);

    }
}
