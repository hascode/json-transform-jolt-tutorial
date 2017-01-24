package com.hascode.tutorial;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CustomResponseWrapper extends HttpServletResponseWrapper {
    private PrintWriter pw;
    private final ByteArrayOutputStream baos;
    private ServletOutputStream os;

    public CustomResponseWrapper(HttpServletResponse response) {
        super(response);
        baos = new ByteArrayOutputStream(response.getBufferSize());
    }

    @Override
    public ServletOutputStream getOutputStream() {
        if (pw != null) {
            throw new IllegalStateException("getWriter() already called");
        }

        if (os == null) {
            os = new ServletOutputStream() {
                @Override
                public void write(int b) throws IOException {
                    baos.write(b);
                }

                @Override
                public void close() throws IOException {
                    baos.close();
                }

                @Override
                public void flush() throws IOException {
                }

                @Override
                public boolean isReady() {
                    return false;
                }

                @Override
                public void setWriteListener(WriteListener writeListener) {
                }
            };
        }

        return os;
    }

    public PrintWriter getPrintwriter() throws IOException {

        if (os != null) {
            throw new IllegalStateException("getOutputStream() already called");
        }

        if (pw == null) {
            pw = new PrintWriter(new OutputStreamWriter(baos, getCharacterEncoding()));
        }

        return pw;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (os != null) {
            throw new IllegalStateException("getOutputStream() already called");
        }

        if (pw == null) {
            pw = new PrintWriter(new OutputStreamWriter(baos, getCharacterEncoding()));
        }

        return pw;
    }

    @Override
    public void flushBuffer() throws IOException {
        super.flushBuffer();

        if (pw != null) {
            pw.flush();
        } else if (os != null) {
            os.flush();
        }
    }

    public String getContent() throws IOException {
        if (pw != null) {
            pw.close();
        }

        if (os != null) {
            os.close();
        }
        return new String(baos.toByteArray(), getCharacterEncoding());
    }

}
