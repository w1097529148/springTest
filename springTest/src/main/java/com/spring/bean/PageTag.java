package com.spring.bean;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.Map;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/23 12:24
 */
public class PageTag extends BodyTagSupport {

    private static final long serialVersionUID = -8476740758233598045L;
    private PageBean pageBean;

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.write(toHTML());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }

    public String toHTML() {
        StringBuffer sb=new StringBuffer();
        //下一次请求提交到后台的表单html代码拼接
        sb.append("<form id='pageBeanForm' action='"+pageBean.getUrl()+"' method='post'>");
        Map<String, String[]> paramMap = pageBean.getParamMap();
        if(paramMap!=null&&paramMap.size()>0) {
            for(Map.Entry<String, String[]> entry:paramMap.entrySet()) {
                if(!"page".equals(entry.getKey())) {
                    for(String val:entry.getValue()) {
                        sb.append("<input type='hidden' name='"+entry.getKey()+"'> value='"+val+"'");
                    }
                }
            }
        }
        sb.append("<input type='hidden' name='page'>");
        sb.append("</form>");
        //分页条html代码拼接
        sb.append("<div style='text-align: right; font-size: 12px;'>");
        sb.append(" 每页"+pageBean.getRows()+"条，共"+pageBean.getTotal()+"条，第"+pageBean.getPage()+"页，共"+pageBean.getMaxPage()+"页&nbsp;&nbsp;");
        sb.append(" <a href='javascript:gotoPage(1)'>首页</a>&nbsp;&nbsp;<a");
        sb.append(" href='javascript:gotoPage("+pageBean.getPreviousPage()+")'>上一页</a>&nbsp;&nbsp;<a");
        sb.append(" href='javascript:gotoPage("+pageBean.getNextPage()+")'>下一页</a>&nbsp;&nbsp;<a");
        sb.append(" href='javascript:gotoPage("+pageBean.getMaxPage()+")'>尾页</a>&nbsp;&nbsp;<input type='text'");
        sb.append(" id='skipPage'");
        sb.append(" style='text-align: center; font-size: 12px; width: 50px;'>&nbsp;&nbsp;<a");
        sb.append(" href='javascript:skipPage()'>Go</a>");
        sb.append(" </div>");

        //分页所需要调用的js代码
        sb.append("<script type='text/javascript'>");
        sb.append("		function gotoPage(page) {");
        sb.append("		  document.getElementById('pageBeanForm').page.value = page;");
        sb.append("		  document.getElementById('pageBeanForm').submit();");
        sb.append("		}");
        sb.append("		function skipPage() {");
        sb.append("		  var page = document.getElementById('skipPage').value;");
        sb.append("		  if(!page || isNaN(page) || parseInt(page)<1 || parseInt(page)>"+pageBean.getMaxPage()+"){");
        sb.append("		  alert('请输入1~N的数字');");
        sb.append("		  return;");
        sb.append("		}");
        sb.append("		gotoPage(page);");
        sb.append("	}");
        sb.append("</script>");

        return sb.toString();
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }
}


