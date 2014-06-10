package me.donnior.sparkle.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

import me.donnior.sparkle.Multipart;

public class ServletMultipartAdapter implements Multipart{

    private Part part;

    public ServletMultipartAdapter(Part part) {
        this.part = part;
    }
    
    @Override
    public String getName(){
        return this.part.getName();
    }
    
    public InputStream getInputStream(){
        try {
            return this.part.getInputStream();
        } catch (IOException e) {
            return null;
        }
    }
}
