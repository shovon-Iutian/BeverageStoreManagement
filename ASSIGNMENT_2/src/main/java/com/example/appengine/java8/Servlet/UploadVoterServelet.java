package com.example.appengine.java8.Servlet;

import com.example.appengine.java8.Management.UploadVoter;
import com.example.appengine.java8.Service.UploadVoterInterface;
import com.opencsv.CSVReader;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Logger;

public class UploadVoterServelet extends HttpServlet {
    private static Logger logger = Logger.getLogger(UploadVoterServelet.class.getName());
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (ServletFileUpload.isMultipartContent(req)) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            UploadVoter uploaddata = new UploadVoter();
            try {
                List<FileItem> multiparts = upload.parseRequest(req);
                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        new File(item.getName()).getName();
                        CSVReader csvReader = new CSVReader(new InputStreamReader(item.getInputStream()));
                        String[] nextRecord;
                        while ((nextRecord = csvReader.readNext()) != null) {
                            System.out.println(nextRecord);
                            uploaddata.uploadFile(nextRecord);
                        }
                    }
                }
            } catch (Exception e) {
                logger.severe("Error importing file" + e.getMessage());
            }
        }
        resp.sendRedirect("/admin/voterlist");
    }
}
