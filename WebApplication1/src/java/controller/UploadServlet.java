/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author AnhDuc
 */
public class UploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Thư mục lưu file upload
    private static final String UPLOAD_DIRECTORY = "upload";

    // Cài đặt kích thước tệp tin
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        // Kiểm tra request có chứa file upload
        if (!ServletFileUpload.isMultipartContent(request)) {// Nếu xảy ra lỗi
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            return;
        }

        // Cài đặt đường dẫn
        DiskFileItemFactory factory = new DiskFileItemFactory();
        
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // Thư mục tạm chứa file upload
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // Kích thước tối đa
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // Tổng kích thước tải lên (file + form html)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // Đường dẫn chứa file cần upload
        String uploadPath = getServletContext().getRealPath("")
                + File.separator + UPLOAD_DIRECTORY;

        // Tạo thư mục nếu thư mục chưa tồn tại
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);

                        // Lưu file vào bộ nhớ
                        item.write(storeFile);
                        request.setAttribute("msg", UPLOAD_DIRECTORY + "/" + fileName);
                        request.setAttribute("message",
                                "Tải tệp lên thành công >>" + UPLOAD_DIRECTORY + "/" + fileName);
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message",
                    "Có lỗi xảy ra: " + ex.getMessage());
        }
        // Gửi thông báo lỗi
        getServletContext().getRequestDispatcher("/message.jsp").forward(
                request, response);
    }

}
