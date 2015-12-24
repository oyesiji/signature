package com.abbey;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

public class Base64Servlet extends HttpServlet {



  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    String imageBase64 = req.getParameter("base64");
    System.out.println(imageBase64);
    InputStream in = null;
    FileOutputStream fos = null;
    try {
      HttpServletRequestWrapper wrappedRequest = new HttpServletRequestWrapper(req);
      InputStream is = wrappedRequest.getInputStream();
      StringWriter writer = new StringWriter();
      IOUtils.copy(is, writer, "UTF-8");
      // String imageString = imageBase64;
      String imageString = writer.toString();
      imageString = imageString.substring("data:image/png;base64,".length());
      byte[] contentData = imageString.getBytes();
      byte[] decodedData = Base64.decodeBase64(contentData);
      String imgName = "c://temp//xavv.png";
      fos = new FileOutputStream(imgName);
      fos.write(decodedData);
    } catch (Exception e) {
      e.printStackTrace();
      String loggerMessage = "Upload image failed : ";

    } finally {
      if (in != null) {
        in.close();
      }
      if (fos != null) {
        fos.close();
      }
    }

  }

  private void writeBytesToFile(byte[] bytes, File file) throws IOException {
    OutputStream out = null;

    try {
      out = new FileOutputStream(file);
      out.write(bytes);
    } finally {
      out.close();
    }
  }



  public void doPostYY(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    String imageBase64 = req.getParameter("base64");
    System.out.println(imageBase64);
    String[] tempArray = imageBase64.split(",");
    String imageString = tempArray[1];
    byte[] decoded = Base64.decodeBase64(imageString);

    String path = "c:\\temp\\abbeyxx.png";
    OutputStream out1 = null;

    try {
      out1 = new BufferedOutputStream(new FileOutputStream(path));
      out1.write(decoded);
    } finally {
      if (out1 != null) {
        out1.close();
      }
    }
  }

  public void doPost2ww2(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    String imageBase64 = req.getParameter("base64");

    OutputStream out = resp.getOutputStream();
    writeOutputStream(imageBase64, out);

    resp.setContentType("image/png");
    resp.setHeader("Pragma", "");
    resp.setHeader("Cache-Control", "");
    resp.setHeader("Content-Disposition", "inline; fileName=image.png");
  }

  private void writeOutputStream(String value, OutputStream outputStream) throws IOException {


    // decoder.decodeBuffer(value);
    byte[] imgBytes = Base64.decodeBase64(value);
    BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(imgBytes));
    ImageIO.write(bufImg, "png", outputStream);
  }
}
