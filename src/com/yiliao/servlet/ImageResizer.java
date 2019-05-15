package com.yiliao.servlet;


import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageResizer extends HttpServlet {
    private static final long serialVersionUID = -8285774993751841288L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String imageOutput = getParam(request, "output", "png");
        String imageRoot = getParam(request, "root", "/albums");
        String imageFile = getParam(request, "file", "/Album1/image1.jpg");
        int width = Integer.parseInt(getParam(request, "width", "250"));
        int height = Integer.parseInt(getParam(request, "width", "0"));
        if ("png".equals(imageOutput))
            response.setContentType("image/png");
        else
            response.setContentType("image/jpeg");
        String imageLoc = request.getSession().getServletContext().getRealPath(
                imageRoot)
                + imageFile;
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(imageLoc));
            int calcHeight = height > 0 ? height : (width
                    * bufferedImage.getHeight() / bufferedImage.getWidth());

            ImageIO.write(createResizedCopy(bufferedImage, width, calcHeight),
                    imageOutput, response.getOutputStream());
        } catch (Exception e) {
            log("Problem with image: " + imageLoc + e);
        }
    }

    BufferedImage createResizedCopy(Image originalImage, int scaledWidth,
            int scaledHeight) {
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = scaledBI.createGraphics();
        g.setComposite(AlphaComposite.Src);
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }

    private String getParam(HttpServletRequest request, String param, String def) {
        String parameter = request.getParameter(param);
        if (parameter == null || "".equals(parameter)) {
            return def;
        } else {
            return parameter;
        }
    }
}
